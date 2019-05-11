package com.javasampleapproach.twitterbootstrap.model;

import java.io.Serializable;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String isActive = "Y";
	private String delFlag = "N";
	private String delReason = "";
	
	@Indexed
	@CreatedDate
	private String createdDate;
	
	@Indexed
	@CreatedBy
	private String createdBy;
	
	@Indexed
	@LastModifiedDate
	private String updatedDate;
	
	@Indexed
	@LastModifiedBy
	private int updatedBy;

}
