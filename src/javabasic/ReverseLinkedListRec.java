package javabasic;

public class ReverseLinkedListRec {
	public ListNode reverse(ListNode head) {
		// base case
		if (head == null || head.next == null) {
			return head;
	}
	// solve subproblem
	ListNode newHead = reverse(head.next);
	head.next.next = head;
	head.next = null;
	return newHead;
	}
}
