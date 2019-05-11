package com.javasampleapproach.twitterbootstrap.dao.api;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.javasampleapproach.twitterbootstrap.model.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, String>{

    Role findByRole(String role);

}
