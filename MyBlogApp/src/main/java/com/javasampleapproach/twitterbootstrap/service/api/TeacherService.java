package com.javasampleapproach.twitterbootstrap.service.api;

import java.util.List;

import com.javasampleapproach.twitterbootstrap.model.Teacher;

public interface TeacherService extends CrudApi<Teacher>{
	
	public Teacher getTeacherByEmail(String email);

	public List<Teacher> findAllTeacher();	

}
