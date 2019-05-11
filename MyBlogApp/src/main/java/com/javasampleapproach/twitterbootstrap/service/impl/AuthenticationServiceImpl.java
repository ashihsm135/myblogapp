package com.javasampleapproach.twitterbootstrap.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.javasampleapproach.twitterbootstrap.dao.api.AuthenticationRepository;
import com.javasampleapproach.twitterbootstrap.exception.BadCredentialsException;
import com.javasampleapproach.twitterbootstrap.exception.UserAccountInactiveExcpetion;
import com.javasampleapproach.twitterbootstrap.model.AuthenticationToken;
import com.javasampleapproach.twitterbootstrap.model.User;
import com.javasampleapproach.twitterbootstrap.model.constants.Constant;
import com.javasampleapproach.twitterbootstrap.model.constants.StatusConstants;
import com.javasampleapproach.twitterbootstrap.service.api.AuthenticationService;
import com.javasampleapproach.twitterbootstrap.service.api.UserService;
import com.javasampleapproach.twitterbootstrap.utility.EncryptionUtil;
import com.javasampleapproach.twitterbootstrap.utility.TokenUtils;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private static Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

  @Autowired
  private AuthenticationRepository authenticationRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private TokenUtils tokenUtils;

  @Autowired
  private EncryptionUtil encryptionUtil;

  @Override
  public AuthenticationToken generateToken(String email, String password,
      String client) {

    if (StringUtils.isEmpty(client)) {
      client = Constant.CLIENT_WEB;
    }

    User existingUser = userService.getUserByEmail(email);

    if (existingUser == null) {
      String message = String.format("No used found with email : %s", email);
      log.info(message);
      throw new BadCredentialsException(message);
    }

    AuthenticationToken existingAuthenticationToken =
        authenticationRepository.findOneByUserId(existingUser.getId());
    if (existingAuthenticationToken != null) {
      if (!tokenUtils.isTokenValid(existingAuthenticationToken.getAuthToken())) {
        deleteAuthenticationTokenByUserId(existingUser.getId());
      } else {
        return existingAuthenticationToken;
      }
    }    validateUser(password, existingUser);
    long creationTime = System.currentTimeMillis();
    String authToken = tokenUtils.generateToken(email, creationTime);
    AuthenticationToken savedToken =
        authenticationRepository.save(new AuthenticationToken(existingUser.getId(), authToken,
            client, creationTime));
    log.info(String.format("Auth token generated for user with email : %s", email));
    return savedToken;
  }

  @Override
  public AuthenticationToken getAuthenticationTokenByUserId(String userId) {
    return authenticationRepository.findOneByUserId(userId);
  }

  @Override
  public void deleteAuthenticationTokenByUserId(String userId) {
    authenticationRepository.deleteByUserId(userId);
  }

  @Override
  public Map<String, Object> validateAuthToken( String authenticationToken) {

    Map<String, Object> dataMap = new HashMap<>();
    boolean isTokenValid = false;
    AuthenticationToken existingAuthToken = null;
    try {
      existingAuthToken = authenticationRepository.findOneByAuthToken(authenticationToken);
      if (existingAuthToken == null) {
        log.info("Invalid auth token : " + authenticationToken);
        throw new IllegalArgumentException("Invalid auth token : " + authenticationToken);
      }
      isTokenValid = tokenUtils.isTokenValid(authenticationToken);
      dataMap.put(Constant.KEY_USER_ID, existingAuthToken.getUserId());
    } catch (Exception e) {
      log.error("Authentication token has expired.");
    }
    dataMap.put(Constant.KEY_IS_AUTH_TOKEN_VALID, isTokenValid);
    return dataMap;
  }

  private void validateUser(String password, User user) {

    if (user.getIsActive() != StatusConstants.ACTIVE) {
      log.info("User account is not active.");
      throw new UserAccountInactiveExcpetion("User account is not active.");
    }

    if (!encryptionUtil.matchPassword(user.getPassword(), password)) {
      String msg =
          String.format("Invalid user credentials for user  with email : %s", user.getEmail());
      log.info(msg);
      throw new BadCredentialsException(msg);
    }
  }

}
