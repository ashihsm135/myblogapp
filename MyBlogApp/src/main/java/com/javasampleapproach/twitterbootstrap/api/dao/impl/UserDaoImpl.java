package com.javasampleapproach.twitterbootstrap.api.dao.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.javasampleapproach.twitterbootstrap.dao.api.UserDao;
import com.javasampleapproach.twitterbootstrap.model.User;


public class UserDaoImpl implements UserDao{

	@Override
	public Iterable<User> findAll(Sort sort) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> S save(S entity) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Iterable<S> save(Iterable<S> entities) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String id) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<User> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findAll(Iterable<String> ids) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String id) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends User> entities) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {

		// TODO Auto-generated method stub
		
	}

	@Override
	public User findOneByEmail(String email) {

		// TODO Auto-generated method stub
		return null;
	}

}
