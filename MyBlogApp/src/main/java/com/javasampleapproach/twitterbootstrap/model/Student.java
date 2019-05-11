package com.javasampleapproach.twitterbootstrap.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Data
@EqualsAndHashCode(of = {"studId", "email"}, callSuper = false)
@Document(collection = "students")
@JsonInclude(content = Include.NON_NULL)
public class Student {

	private String studId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String course;
	private String password;
	private String cnfPassword;
	private int isActive;
	private int delFlag;
	private int delReason;
	private String image;

}
