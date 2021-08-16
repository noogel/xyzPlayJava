package noogel.xyz.algorithm;

public class ReverseList_206_Easy {
    public static void main(String[] args) {
        ListNode listNode = ListNode.of(1, ListNode.of(2, ListNode.of(3, ListNode.of(4, null))));
        ListNode listNode1 = reverseList(listNode);
        System.out.println(1);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode q = head.next;
        p.next = null;
        while (q!=null) {
            ListNode r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return p;
    }
}
