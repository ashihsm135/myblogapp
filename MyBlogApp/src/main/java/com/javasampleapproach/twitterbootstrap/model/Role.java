package com.javasampleapproach.twitterbootstrap.model;

import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Document(collection = "role")
@JsonInclude(content = Include.NON_NULL)
public class Role {
	
	@Id
	private String id;

	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String role;

}
