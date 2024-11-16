package linkedlists;

import linkedlists.ReverseLinkedList.ListNode;

public class ReverseLinkedList {

    public static void main(String[] args) {
        System.out.println(reverseListOpt(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))))));
    }

    /**
     * Brute force: Create a new linked list by iterating over the give list using backward linking
     * 
     * head = 1,2,3,4
     * 
     * before loop: put head to new list
     * new list = 1
     * iteration 0: put the old next node referenced by head by setting next to head
     * new list = 2,1
     * iteration 1: put the old next node 3 referenced by 2 by setting next to 2
     * new list = 3,2,1
     * iteration 2: put the old next node 4 referenced by 3 by setting next to 3
     * new list = 4,3,2,1
     * 
     * t: O(n) while iterations over oldList
     * s: O(n) extra space for newList
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newList = new ListNode(head.val);
        ListNode oldList = head;
        while (oldList.next != null) {
          ListNode next = oldList.next;
          oldList = next;
          ListNode newNode = new ListNode(next.val, newList);
          newList = newNode;
        }
        return newList;
    }

    /**
     * Optimal with recursion: Iterate to the last element and if next == null it means the new head is found. 
     * After that reassign next node to the current one in each iteration
     * 
     * t: O(n) recursive calls over head
     * s: O(1) no extra space
     */
    public static ListNode reverseListOpt(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = reverseListOptInternal(head, head.next);
        head.next = null; // prevent cycles
        return newHead;
       
    }

    private static ListNode reverseListOptInternal(ListNode current, ListNode next) {
        if (next == null) {
            return current;
        } else {
          ListNode newHead = reverseListOptInternal(next, next.next);
          next.next = current;
          return newHead;
        }
    }

 public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public String toString() {
            return val + (next != null ? next.toString() : "null");
        }
    }

}