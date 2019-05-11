package com.javasampleapproach.twitterbootstrap.utility.test.array;

import java.util.Scanner;

public class ArrayClass {

	int[]	a;

	// Method for reading elements in to an array
	public void readArray(int n) {

		System.out.println("Enter " + n + " elements: ");
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < n; i++) {
			a[i] = input.nextInt();
		}
	}



	// Method for printing elements in an array
	public void printArray(int n) {

		System.out.println("Array elements are: ");
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.print("\n");
	}



	// Method to return the sum of all elements in the array
	public int sumArray(int n) {

		int sum = 0;
		for (int i = 0; i < n; i++)
			sum = sum + a[i];
		return sum;
	}



	// Method to return the average of all elements in the array
	public float avgArray(int n) {

		float average = 0.0f;
		average = (float) sumArray(n) / n;
		return average;
	}



	// Method to return the least element in the array
	public int leastElement(int n) {

		int min = a[0];
		for (int i = 0; i < n; i++) {
			if (min > a[i]) {
				min = a[i];
			}
		}
		return min;
	}



	// Method to return the largest element in the array
	public int largestElement(int n) {

		int max = a[0];
		for (int i = 0; i < n; i++) {
			if (max < a[i]) {
				max = a[i];
			}
		}
		return max;
	}



	// Method to search for an array element using linear search
	public int linearSearch(int key, int n) {

		int index = -1;
		for (int i = 0; i < n; i++) {
			if (a[i] == key) {
				index = i;
				break;
			}
		}
		return index;
	}



	// Method to search for an array element using binary search
	public int binarySearch(int key, int n) {

		int index = -1;
		int low = 0, high = n - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (key == a[mid]) {
				index = mid;
				break;
			} else if (key < a[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return index;
	}



	// Method to return the frequency of a given element
	public int getFrequency(int key, int n) {

		int count = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] == key) {
				count++;
			}
		}
		return count;
	}



	// Method to return the frequency of all elements in the array where
	// elements are in the range 1-n
	// Step 1: Decrement each element by 1 to make the elements to be in the
	// range 0 to n – 1
	// Step 2: Add n to keep track of the frequency of an element. arr[arr[i]%n]
	// = arr[arr[i]%n] + n
	// Step 3: Print the frequency of elements by dividing the values by n
	public void getFrequencyOpt(int[] arr, int n) {

		for (int i = 0; i < n; i++)
			arr[i] = arr[i] - 1;
		for (int i = 0; i < n; i++)
			arr[arr[i] % n] = arr[arr[i] % n] + n;
		System.out.println("Frequency of elements are: ");
		for (int i = 0; i < n; i++)
			System.out.println((i + 1) + " -> " + (arr[i] / n));
	}
}
