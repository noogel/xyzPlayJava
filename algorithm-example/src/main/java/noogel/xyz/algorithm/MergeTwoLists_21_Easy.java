package noogel.xyz.algorithm;

public class MergeTwoLists_21_Easy {
    public static void main(String[] args) {
        ListNode listNode = mergeTwoLists(ListNode.of(1, ListNode.of(2, ListNode.of(4, null))),
                ListNode.of(1, ListNode.of(3, ListNode.of(4, null))));
        System.out.println(listNode);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
