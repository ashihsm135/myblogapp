package com.javasampleapproach.twitterbootstrap.utility.test;

public class ListTest {

	public static void main(String args[]) {

		Teacher t = new Student();
		Student s = new Student();
		Course c = new Student();
		
		System.out.println(t.getName());
		System.out.println(s.getName());
		System.out.println(c.getName());
		
	}
}

class Course {

	String name = "ARPIT";
	int id;

	String getName() {
		return name;
	}

}

class Teacher extends Course {

	String name = "NEERAJ";
	int id;

	String getName() {
		return name;
	}
}

class Student extends Teacher {

	String name = "SALABH";
	int id;

	String getName() {
		return name;
	}

}