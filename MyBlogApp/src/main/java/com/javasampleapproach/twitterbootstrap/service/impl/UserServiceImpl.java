package com.javasampleapproach.twitterbootstrap.service.impl;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.javasampleapproach.twitterbootstrap.dao.api.UserDao;
import com.javasampleapproach.twitterbootstrap.exception.SocioSeerException;
import com.javasampleapproach.twitterbootstrap.model.User;
import com.javasampleapproach.twitterbootstrap.service.api.UserService;
import com.javasampleapproach.twitterbootstrap.utility.EncryptionUtil;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private EncryptionUtil encryptionUtil;
 
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public User save(@NotNull User user) {
    User existingUser = getUserByEmail(user.getEmail());
    if (existingUser != null) {
      String message =
          String.format("There already exists an user with email : %s", user.getEmail());
      throw new IllegalArgumentException(message);
    }
    try {
    	user.setPassword(encryptionUtil.encode(user.getPassword()));
      return userDao.save(user);
    } catch (Exception e) {
       String message = String.format("Error while saving user with email : %s", user.getEmail());
       throw new SocioSeerException(message,e);
    }
  }
  
  @Override
  public User get(String userId) {
    try {
      User user = userDao.findOne(userId);
      return user;
    } catch (Exception e) {
      String message = String.format("Error while fetching user by id : %s", userId);
      throw new SocioSeerException(message, e);
    }
  }
  
  @Override
  public User getUserByEmail(String email) {
    try {
      User user = userDao.findOneByEmail(email);
      return user;
    } catch (Exception e) {
      String message = String.format("Error while fetching user by email : %s", email);
      throw new SocioSeerException(message, e);
    }
  }
  
@Override
public User update(String id, User entity) {
	// TODO Auto-generated method stub
	return null;
}

}
