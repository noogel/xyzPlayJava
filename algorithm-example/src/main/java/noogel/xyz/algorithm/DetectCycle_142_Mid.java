package noogel.xyz.algorithm;

import java.util.List;

public class DetectCycle_142_Mid {
    public static void main(String[] args) {
//        ListNode test1 = ListNode.of(3, ListNode.of(2, ListNode.of(0, ListNode.of(4, null))));
//        test1.next.next.next.next = test1.next;

        ListNode test2 = ListNode.of(1, ListNode.of(2, null));
        test2.next.next = test2;
        ListNode listNode = detectCycle(test2);
        System.out.println(listNode);
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next != null ? fast.next.next: null;
            if (slow == fast) {
                ListNode c1 = head;
                ListNode c2 = slow;
                while (c1!=c2) {
                    c1 = c1.next;
                    c2 = c2.next;
                }
                return c1;
            }
        }
        return null;
    }
}
