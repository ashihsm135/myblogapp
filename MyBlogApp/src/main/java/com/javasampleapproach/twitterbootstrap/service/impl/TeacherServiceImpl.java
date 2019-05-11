package com.javasampleapproach.twitterbootstrap.service.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javasampleapproach.twitterbootstrap.dao.api.TeacherDao;
import com.javasampleapproach.twitterbootstrap.exception.SocioSeerException;
import com.javasampleapproach.twitterbootstrap.model.Teacher;
import com.javasampleapproach.twitterbootstrap.service.api.TeacherService;
import com.javasampleapproach.twitterbootstrap.utility.EncryptionUtil;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	EncryptionUtil encryptionUtil;

	@Autowired
	TeacherDao teacherDao;

	@Override
	public Teacher save(@NotNull Teacher teacher) {
		Teacher existingTacher = getTeacherByEmail(teacher.getEmail());
		if (existingTacher != null) {
			String message = String.format(
					"There already exists an teacher with email : %s",
					teacher.getEmail());
			throw new IllegalArgumentException(message);
		}
		try {
			teacher.setPassword(encryptionUtil.encode(teacher.getPassword()));
			return teacherDao.save(teacher);
		} catch (Exception e) {
			String message = String.format(
					"Error while saving teacher with email : %s",
					teacher.getEmail());
			throw new SocioSeerException(message, e);
		}
	}

	@Override
	public Teacher update(String id, Teacher entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher get(@NotNull String teacherId) {
		
		Teacher teacher = teacherDao.findOne(teacherId);
		
		
		return teacher;
	}

	@Override
	public Teacher getTeacherByEmail(String email) {
		try {
			Teacher teacher = teacherDao.findOneByEmail(email);
			return teacher;
		} catch (Exception e) {
			String message = String.format(
					"Error while fetching teacher by email : %s", email);
			throw new SocioSeerException(message, e);
		}
	}

	@Override
	public List<Teacher> findAllTeacher() {
		try {
			List<Teacher> teacherList = (List<Teacher>) teacherDao.findAll();
			return teacherList;
		} catch (Exception e) {
			String message = String.format(
					"Error while fetching teacher List");
			throw new SocioSeerException(message, e);
		}
	}
}
