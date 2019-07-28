import java.util.HashSet;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode listNode = ListNode.of(
                1,
                ListNode.of(
                        2,
                        ListNode.of(
                                3,
                                ListNode.of(
                                        4,
                                        ListNode.of(
                                                5, null
                                        )
                                )
                        )
                )
        );
        ListNode next = listNode;
        while (null != next) {
            System.out.println(next.val);
            next = next.next;
        }

        System.out.println("===");
        ListNode reverse = reverse3(listNode);

        ListNode next2 = reverse;
        while (null != next2) {
            System.out.println(next2.val);
            next2 = next2.next;
        }

        hasCycle(listNode);
    }

    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> points = new HashSet<ListNode>();
        ListNode cur = head;
        while (null != cur){
            if(points.contains(cur)){
                return true;
            }
            points.add(cur);
            cur = cur.next;
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/linked-list-cycle/
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        if (null == head){
            return false;
        }else if (null == head.next){
            return false;
        }else if (head == head.next.next){
            return true;
        }
        ListNode minCur = head.next;
        ListNode maxCur = head.next.next;
        while (minCur != maxCur){
            if (null == minCur.next){
                return false;
            }else if (null == maxCur.next){
                return false;
            }else if (null == maxCur.next.next){
                return false;
            }
            minCur = minCur.next;
            maxCur = maxCur.next.next;
            if (minCur == maxCur){
                return true;
            }
        }
        return false;
    }
    public static ListNode detectCycle(ListNode head) {
        HashSet<ListNode> points = new HashSet<ListNode>();
        ListNode cur = head;
        while (null != cur){
            if(points.contains(cur)){
                return cur;
            }
            points.add(cur);
            cur = cur.next;
        }
        return null;
    }

    /**
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/
     * @param head
     * @return
     */
    public static ListNode detectCycle2(ListNode head) {
        if (null == head){
            return null;
        }else if (null == head.next){
            return null;
        }else if (head == head.next.next){
            return head;
        }
        ListNode meetHead = head;
        ListNode minCur = head.next;
        ListNode maxCur = head.next.next;
        while (minCur != maxCur){
            if (null == minCur.next){
                return null;
            }else if (null == maxCur.next){
                return null;
            }else if (null == maxCur.next.next){
                return null;
            }
            minCur = minCur.next;
            maxCur = maxCur.next.next;
            if (minCur == maxCur){
                ListNode meetNode = minCur;
                while (meetHead != meetNode){
                    meetHead = meetHead.next;
                    meetNode = meetNode.next;
                }
                return meetHead;
            }
        }
        return null;
    }
        /**
         * 官方推荐的
         * @param head
         * @return
         */
    public static ListNode reverse2(ListNode head) {
        // prev -> curr -> nextTemp
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 自己写的
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        // cur -> prev -> tmp
        ListNode cur = head;
        ListNode next = head.next;
        head.next = null;
        while (null != cur) {
            ListNode tmp = null;
            if (null != next) {
                tmp = next.next;
                next.next = cur;
            }
            if (null == next) {
                break;
            }
            cur = next;
            next = tmp;
        }
        return cur;
    }

    /**
     * 复写的
     * 1. 记录前一个节点，当前节点
     * 2. 迭代
     * 3. 取出当前节点到临时变量
     * 4. -- 现在有 prev -> curr -> next 三个节点
     * 5. 将 curr.next -> prev，改变指向方向
     * 6. 依次挪动节点位置 prev = curr , curr = nextTemp
     * 7. 最后返回 prev
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param head
     * @return
     */
    public static ListNode reverse3(ListNode head) {
        // prev -> curr -> next
        ListNode prev = null;
        ListNode curr = head;
        while (null != curr) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
