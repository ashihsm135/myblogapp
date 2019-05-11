package com.javasampleapproach.twitterbootstrap.utility.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialixationProcess {

	 private static final long serialVersionUID = 1L; 
	    private NonSerial nonSerial; 
	    SerialixationProcess(NonSerial  nonSerial){ 
	      this.nonSerial = nonSerial; 
	    } 
	    public static void main(String [] args)  { 
	      NonSerial nonSer = new NonSerial(); 
	      SerialixationProcess c = new SerialixationProcess(nonSer); 
	      try { 
	       FileOutputStream fs = new FileOutputStream("test1.ser"); 
	       ObjectOutputStream os = new ObjectOutputStream(fs); 
	       os.writeObject(c); 
	       os.close(); 
	      } catch (Exception e) {  e.printStackTrace(); } 
	      try { 
	        FileInputStream fis = new FileInputStream("test1.ser"); 
	        ObjectInputStream ois = new ObjectInputStream(fis); 
	        c = (SerialixationProcess)  ois.readObject(); 
	        ois.close(); 
	      } catch (Exception e) { 
	        e.printStackTrace(); 
	      } 
	     } 
}

 class NonSerial { 
    //This is a non-serializable  class 
}

