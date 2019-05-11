package com.javasampleapproach.twitterbootstrap.utility.test.array;

import java.util.Scanner;

class Driver {

	public static void main(String[] args) {

		ArrayClass arrayObj = new ArrayClass();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter no. of elements: ");
		int n = input.nextInt();
		arrayObj.a = new int[n];
		arrayObj.readArray(n);
		arrayObj.printArray(n);
		System.out.println("Length of the array is: " + arrayObj.a.length);
		System.out.println("Sum of elements in the array is: "
				+ arrayObj.sumArray(n));
		System.out.println("Average of elements in the array is: "
				+ arrayObj.avgArray(n));
		System.out.println("Least element in the array is: "
				+ arrayObj.leastElement(n));
		System.out.println("Largest element in the array is: "
				+ arrayObj.largestElement(n));
		System.out.println("Enter a element to search: ");
		int key = input.nextInt();
		int index = arrayObj.linearSearch(key, n);
		if (index != -1)
			System.out
					.println("Linear search: Given element is found at index: "
							+ index);
		else
			System.out.println("Linear search: Given element is not found");
		index = arrayObj.binarySearch(key, n);
		if (index != -1)
			System.out
					.println("Binary search: Given element is found at index: "
							+ index);
		else
			System.out.println("Binary search: Given element is not found");
		System.out.println("Enter a element to get its frequency: ");
		key = input.nextInt();
		System.out.println("Frequency of the given element is: "
				+ arrayObj.getFrequency(key, n));
		arrayObj.getFrequencyOpt(arrayObj.a, n);
	}
	
	
	
}