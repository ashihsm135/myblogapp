package com.javasampleapproach.twitterbootstrap.model;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@EqualsAndHashCode(of = {"id", "email"}, callSuper = false)
@Document(collection = "users")
@JsonInclude(content = Include.NON_NULL)
public class User extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String firstName;
	private String lastName;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String email;
	private String phone;
	private String password;
	private String cnfPassword;
	private String image;
	@Transient
	private String role;
	@DBRef
	private Set<Role> roles;
}
