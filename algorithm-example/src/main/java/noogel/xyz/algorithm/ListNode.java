package noogel.xyz.algorithm;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode of(int val, ListNode next) {
        ListNode node = new ListNode(val);
        node.next = next;
        return node;
    }
}
