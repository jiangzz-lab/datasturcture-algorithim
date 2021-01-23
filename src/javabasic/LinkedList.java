package javabasic;

public class LinkedList {
	public static boolean hasCycle(ListNode head) {
		// sanity check
		if (head == null || head.next == null) {
			return false;
		}
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (fast != null && fast.next != null) {
			if (fast == slow) {
				return true;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return false;
	}

	public static ListNode middle(ListNode head) {
		// sanity check
		if (head == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static ListNode reverse(ListNode head) {
		// sanity check
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			// store cur.next
			ListNode nxt = cur.next;
			// reverse
			cur.next = pre;
			// move on
			pre = cur;
			cur = nxt;
		}
		return pre;
	}

	public static ListNode insert(ListNode head, int value) {
		// create new node
		ListNode newNode = new ListNode(value);
		// sanity check
		if (head == null) {
			return newNode;
		}
		ListNode cur = head;
		// case 1:
		if (head.value > value) {
			newNode.next = head;
			return newNode;
		}
		// case 2, case 3:
		// find the position to insert
		while (cur.next != null && cur.next.value <= value) {
			cur = cur.next;
		}
		// insert
		newNode.next = cur.next;
		cur.next = newNode;
		return head;
	}

	public static ListNode merge(ListNode one, ListNode two) {
		// sanity check
		if (one == null) {
			return two;
		}
		if (two == null) {
			return one;
		}
		// create dummyHead
		ListNode dummyHead = new ListNode(-1);
		ListNode cur = dummyHead;
		ListNode cur1 = one;
		ListNode cur2 = two;

		while (cur1 != null && cur2 != null) {
			if (cur1.value < cur2.value) {
				cur.next = cur1;
				cur = cur.next;
				cur1 = cur1.next;
			} else { // cur1.value >= cur2.value
				cur.next = cur2;
				cur = cur.next;
				cur2 = cur2.next;
			}
		}
		// post process
		if (cur1 != null) {
			cur.next = cur1;
		}
		if (cur2 != null) {
			cur.next = cur2;
		}
		return dummyHead.next;
	}

	public static ListNode mergeHalf(ListNode leftHalf, ListNode rightHalf) {
		// sanity check
		if (leftHalf == null) {
			return rightHalf;
		}
		if (rightHalf == null) {
			return leftHalf;
		}

		ListNode dummyHead = new ListNode(-1);
		ListNode cur = dummyHead;
		ListNode cur1 = leftHalf;
		ListNode cur2 = rightHalf;
		while (cur1 != null && cur2 != null) {
			cur.next = cur1;
			cur1 = cur1.next;
			cur.next.next = cur2;
			cur2 = cur2.next;
			cur = cur.next.next;
		}
		if (cur1 != null) {
			cur.next = cur1;
		}
		if (cur2 != null) {
			cur.next = cur2;
		}
		return dummyHead.next;
	}

	public static ListNode reorder(ListNode head) {
		// sanity check
		if (head == null || head.next == null) {
			return head;
		}
		// find the middle point and divide
		ListNode mid = middle(head);
		ListNode rightHalf = mid.next;
		ListNode leftHalf = head;
		mid.next = null;
		// reverse rightHalf
		rightHalf = reverse(rightHalf);
		// merge one by one
		head = mergeHalf(leftHalf, rightHalf);
		return head;
	}

	public static ListNode partition(ListNode head, int target) {
		// sanity check
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummySmall = new ListNode(-1);
		ListNode dummyLarge = new ListNode(-1);
		ListNode cur = head;
		ListNode cur1 = dummySmall;
		ListNode cur2 = dummyLarge;
		while (cur != null) {
			if (cur.value < target) {
				cur1.next = cur;
				cur1 = cur1.next;
				cur = cur.next;
			} else {
				cur2.next = cur;
				cur2 = cur2.next;
				cur = cur.next;
			}
		}
		// merge and cut the tail
		cur1.next = dummyLarge.next;
		cur2.next = null;
		return dummySmall.next;
	}

	public static ListNode mergeSort(ListNode head) {
		// sanity check; base case
		if (head == null || head.next == null) {
			return head;
		}
		// find the middle node and divide
		ListNode mid = middle(head);
		ListNode leftHalf = head;
		ListNode rightHalf = mid.next;
		mid.next = null;
		// merge sort leftHalf and rightHalf
		ListNode leftResult = mergeSort(leftHalf);
		ListNode rightResult = mergeSort(rightHalf);
		// merge the sorted halves
		return merge(leftResult, rightResult);
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// sanity check
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode dummySum = new ListNode(-1);
		ListNode cur = dummySum;
		ListNode cur1 = l1;
		ListNode cur2 = l2;
		int carrier = 0;

		while (cur1 != null && cur2 != null) {
			int sum = cur1.value + cur2.value + carrier;
			ListNode newNode = new ListNode(sum % 10);
			carrier = sum / 10;

			cur.next = newNode;
			cur = cur.next;
			cur1 = cur1.next;
			cur2 = cur2.next;
		}

		while (cur1 != null) {
			int sum = cur1.value + carrier;
			ListNode newNode = new ListNode(sum % 10);
			carrier = sum / 10;

			cur.next = newNode;
			cur = cur.next;
			cur1 = cur1.next;
		}

		while (cur2 != null) {
			int sum = cur2.value + carrier;
			ListNode newNode = new ListNode(sum % 10);
			carrier = sum / 10;

			cur.next = newNode;
			cur = cur.next;
			cur2 = cur2.next;
		}
		// add one more node when carrier != 0
		if (carrier != 0) {
			ListNode newNode = new ListNode(carrier);
			cur.next = newNode;
		}
		return dummySum.next;
	}

	public static boolean isPalindrome(ListNode head) {
		// sanity check
		if (head == null || head.next == null) {
			return true;
		}
		// find the middle point and divide
		ListNode mid = middle(head);
		ListNode leftHalf = head;
		ListNode rightHalf = mid.next;
		mid.next = null;

		// reverse rightHalf
		rightHalf = reverse(rightHalf);
		// compare
		ListNode cur1 = leftHalf;
		ListNode cur2 = rightHalf;
		while (cur2 != null) {
			if (cur1.value != cur2.value) {
				return false;
			}
			// move on
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return true;
	}

	public static ListNode remove(ListNode head, int target) {
		// sanity check
		if (head == null) {
			return null;
		}
		// use a dummy node
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode cur = head;
		ListNode pre = dummy;
		while (cur != null) {
			if (cur.value == target) {
				pre.next = cur.next;
				cur = cur.next;
			} else {
				pre = cur;
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		// sanity check
		if (n <= 0) {
			return head;
		}
		// create dummy node
		ListNode dummy = new ListNode(-1);
		dummy.next = head;

		// move head by n nodes
		while (n > 0 && head != null) {
			head = head.next;
			n--;
		}
		// if n > 0 here, the total number of nodes is smaller than n
		if (n > 0) {
			return dummy.next;
		}
		ListNode prev = dummy;
		while (head != null) {
			prev = prev.next;
			head = head.next;
		}
		// when head == null, prev.next is the node to remove
		prev.next = prev.next.next;
		return dummy.next;
	}

	public static ListNode insert(ListNode head, int index, int value) {
		// sanity check
		if (index < 0) {
			return head;
		}
		// create newNode
		ListNode newNode = new ListNode(value);
		// create dummyHead
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		ListNode prev = dummyHead;
		while (index > 0 && prev.next != null) {
			index--;
			prev = prev.next;
		}
		// if index = 0, index is within the scope, insert after prev
		if (index == 0) {
			newNode.next = prev.next;
			prev.next = newNode;
		}
		return dummyHead.next;
	}
}
