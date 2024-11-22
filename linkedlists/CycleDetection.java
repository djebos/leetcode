package linkedlists;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/linked-list-cycle-ii/
public class CycleDetection {
    public static void main(String[] args) {
        CycleDetection s = new CycleDetection();
        ListNode head = new ListNode(1, null);
        ListNode next = new ListNode(2, null);
        ListNode next2 = new ListNode(3, null);
        ListNode next3 = new ListNode(4, null);
        ListNode next4 = new ListNode(5, null);

        head.next = next;
        next.next = next2;
        next2.next = next3;
        next3.next = next4;
        next4.next = next;
        System.out.println(s.detectCycleFloyd(head));
    }

    /**
     * Brute force: keep track of seen elements and return if set contains one of
     * them
     * 
     * t: O(n)
     * s: O(n)
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.contains(head)) {
                seen.add(head);
                head = head.next;
            } else {
                return head;
            }
        }
        return null;
    }

    /**
     * Floyd Tortoise and Hare: start with two pointers, tortoise moves 1 pos next,
     * hare moves 2 pos next. If there's a cycle
     * they will meet. From the meeting and initial head node start iteration until
     * two pointers meet.
     * 
     * t: O(n)
     * s: O(1)
     */
    public ListNode detectCycleFloyd(ListNode head) {
        ListNode hare = head;
        ListNode tortoise = head;
        while (hare != null && hare.next != null) { // since hare is faster it will reach the end faster. if end reached
                                                    // -> no cycles
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) { // meeting point, start iteration from head to the meeting point to find the
                                    // cycle start
                ListNode p1 = head;
                ListNode p2 = tortoise;
                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1;
            }
        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x, ListNode node) {
            val = x;
            next = node;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
