package com.javasampleapproach.twitterbootstrap.utility.test.polymorphism;


class Parent{

	public void eat(){
		System.out.println("Parent Eat");
	}
	
}

class Child extends Parent{
	
	@Override
	public void eat(){
		System.out.println("Chield Eat");
	}
	
}


public class DynamicBinding {

	public static void main(String args[]){
     
		Parent p = new Child();
		
		p.eat();
	}

	
}


