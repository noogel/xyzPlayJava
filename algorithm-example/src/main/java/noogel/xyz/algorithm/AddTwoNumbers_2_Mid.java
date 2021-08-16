package noogel.xyz.algorithm;

public class AddTwoNumbers_2_Mid {
    public static void main(String[] args) {
        ListNode l1 = ListNode.of(1, ListNode.of(2, ListNode.of(3, ListNode.of(4, null))));
        ListNode l2 = ListNode.of(1, ListNode.of(8, ListNode.of(3, ListNode.of(4, null))));
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resp = null;
        ListNode curNode = null;
        int add = 0;
        while (l1 != null || l2 != null || add != 0) {
            int cur = (l1!=null ? l1.val : 0) + (l2 != null ? l2.val: 0) + add;
            if (resp == null) {
                resp = new ListNode(cur % 10);
                curNode = resp;
            } else {
                curNode.next = new ListNode(cur % 10);
                curNode = curNode.next;
            }
            add = cur / 10;
            if (l1!= null) l1 = l1.next;
            if (l2!= null) l2 = l2.next;
        }
        return resp;
    }
}
