package noogel.xyz.algorithm;

public class ReverseKGroup_25_Hard {
    public static void main(String[] args) {
        // TODO bad
        ListNode listNode = reverseKGroup(ListNode.of(1, ListNode.of(2, ListNode.of(3, ListNode.of(4, ListNode.of(5, null))))), 2);
        System.out.println(listNode);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head;
        ListNode q = head.next;
        p.next = null;
        int count = 0;
        while (q != null) {
            ListNode r = q.next;
            if (count % k != k - 1) {
                // do
                q.next = p;
                p = q;
                q = r;
            } else {
                p.next = q;
                p = q;
                q = r;
            }
            count++;
        }
        return p;
    }
}
