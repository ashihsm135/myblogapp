package com.javasampleapproach.twitterbootstrap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javasampleapproach.twitterbootstrap.service.api.TeacherService;
import com.javasampleapproach.twitterbootstrap.model.Teacher;
import com.javasampleapproach.twitterbootstrap.model.UserBean;

@RestController
@RequestMapping(value = "teacher", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createTeacherPage(ModelAndView mav) {
		mav.addObject("loggedInUser", new UserBean());
		mav.addObject("teacher", new Teacher());
		mav.setViewName("/teacher/create");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createTeacher(@ModelAttribute(value="teacher") Teacher  teacher) {
		  ModelAndView mav = new ModelAndView();
		  teacher = teacherService.save(teacher);
		  mav.setViewName("/teacher/create");
		  return mav;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listTeacher(ModelAndView mav) {
		mav.addObject("loggedInUser", new UserBean());
		//mav.addObject("user", new User());
		List<Teacher> teachers = teacherService.findAllTeacher();
		System.out.println("teacher size : "+teachers.size());
		mav.addObject("teachers", teachers);
		mav.setViewName("/teacher/list");
		return mav;
	}
}
