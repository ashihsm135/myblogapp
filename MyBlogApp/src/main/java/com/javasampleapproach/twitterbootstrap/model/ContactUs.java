package com.javasampleapproach.twitterbootstrap.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@EqualsAndHashCode(of = {"id", "email"}, callSuper = false)
@Document(collection = "contactus")
@JsonInclude(content = Include.NON_NULL)
public class ContactUs {

	private String id;
	private String name;
	private String email;
	private String subject;
	private String message;
	private int status;
	
}
