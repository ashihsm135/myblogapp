package com.javasampleapproach.twitterbootstrap.utility.test.linkedlist;

/**
 * Java program to find middle element of linked list in one pass. In order to
 * find middle element of linked list we need to find length first but since we
 * can only traverse linked list one time, we will use two pointers one which we
 * will increment on each iteration while other which will be incremented every
 * second iteration. so when first pointer will point to the end of linked list,
 * second will be pointing to the middle element of linked list
 * 
 * @author
 */

public class LinkedListTest {

	public static void main(String args[]) {

		}
}

class LinkedList {

	private Node	head;

	public void addBegin(String data) {
     Node newNode = new Node();
     newNode.data = data;
     newNode.next = head;
     
     head = newNode;
     
	}
	
	public void addEnd(String node) {

	}

	public static class Node {

		private Node	next;
		private String	data;



		public Node(String data) {

			this.data = data;
		}

		public Node() {
		}

		public String data() {

			return data;
		}



		public void setData(String data) {

			this.data = data;
		}



		public Node next() {

			return next;
		}



		public void setNext(Node next) {

			this.next = next;
		}



		public String toString() {

			return this.data;
		}
	}
}
