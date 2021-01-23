package javabasic;

public class ReverseLinkedList {
	public ListNode reverse(ListNode head) {
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
}
