package com.javasampleapproach.twitterbootstrap.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.javasampleapproach.twitterbootstrap.dao.api.RoleDao;
import com.javasampleapproach.twitterbootstrap.dao.api.UserDao;
import com.javasampleapproach.twitterbootstrap.model.Role;
import com.javasampleapproach.twitterbootstrap.model.User;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserDao userRepository;

	@Autowired
	private RoleDao roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		User user = userRepository.findOneByEmail(email);
		if (user != null) {
			List<GrantedAuthority> authorities = getUserAuthority(user
					.getRoles());
			return buildUserForAuthentication(user, authorities);
		} else {
			throw new UsernameNotFoundException("username not found");
		}
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<>();
		userRoles.forEach((role) -> {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		});

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user,
			List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), authorities);
	}

	public User findUserByEmail(String email) {
		User user  = userRepository.findOneByEmail(email);
		//user.setImage(Constant.REAL_PATH +"/"+ (user.getImage() != null ? user.getImage() : ""));
		return userRepository.findOneByEmail(email);
	}

	public void saveUser(@NotNull User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole(user.getRole());
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
}
