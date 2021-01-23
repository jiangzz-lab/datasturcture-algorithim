package javabasic;

import java.util.PriorityQueue;

public class MyLinkedList {
	ListNode head;

	// constructor
	public MyLinkedList() {

	}

// Get the value of index-th node in the linked list; return -1 when the index is not valid
	public int get(int index) {
		ListNode cur = head;
		if (index < 0) {
			return -1;
		}
		while (index > 0 && cur != null) {
			cur = cur.next;
			index--;
		}
		return cur != null ? cur.value : -1;
	}

	/*
	 * Add a node of value val before the first element of the linked list. After
	 * the insertion, the new node will be the first node of the linked list
	 */
	public void addAtHead(int val) {
		ListNode newNode = new ListNode(val);
		newNode.next = head;
		head = newNode;
	}

	/* Append a node of value val to the last element of the linked list */
	public void addAtTail(int val) {
		ListNode newNode = new ListNode(val);
		if (head == null) {
			head = newNode;
		}
		ListNode prev = head;
		while (prev.next != null) {
			prev = prev.next;
		}
		prev.next = newNode;
	}

	/* Add a node of value val before the index-th node in the linked list */
	public void addAtIndex(int index, int val) {
		if (index < 0) {
			return;
		}
		ListNode newNode = new ListNode(val);
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy;
// insert before the index-th node of the input list means insert after the index-th node of the dummy list
// find the index-th node in the dummy list and insert after it
		while (index > 0 && prev != null) {
			prev = prev.next;
			index--;
		}
		if (prev != null) {
			newNode.next = prev.next;
			prev.next = newNode;
		}
		head = dummy.next;
	}

	public void deleteAtIndex(int index) {
		// sanity check
		if (index < 0) {
			return;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy;
		while (index > 0 && head != null) {
			prev = head;
			head = head.next;
			index--;
		}
		if (head != null) {
			prev.next = head.next;
		}
		head = dummy.next;
	}
	

}
