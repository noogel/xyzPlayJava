package noogel.xyz.algorithm;

public class GetIntersectionNode {
    public static void main(String[] args) {

    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA;
        ListNode t2 = headB;
        while (t1 != t2) {
            t1 = t1 == null ? headB: t1.next;
            t2 = t2 == null ? headA: t2.next;
        }
        return t1;
    }
}