package com.javasampleapproach.twitterbootstrap.utility.test.collectiontest;

class A{
	int a = 10;
	A(){
		System.out.println("CLASS A Constructr");
	}
	{
		System.out.println("CLASS A IIB");
	}
	static{
		System.out.println("CLASS A SIB");
	}
}

class B extends A{
	int a= 20;
}


public class C {

	
	public static void main(String [] args){
	
		B a = new B();
	 
		}
}
