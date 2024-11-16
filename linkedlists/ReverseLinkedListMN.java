package linkedlists;

public class ReverseLinkedListMN {
    public static void main(String[] args) {
        System.out.println(
                reverseMN(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2, 4)); // 14325null
        System.out.println(
                reverseMN(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 1, 4)); // 43215null
        System.out.println(
                reverseMN(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 1, 5)); // 54321null
        System.out.println(
                reverseMN(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 1, 1)); // 54321null
    }

    /**
     * Optimal solution:
     * 
     * 1. Find the reverse position by iterating from the given head.
     * 2. Reverse the number of elements by calling a method for a sublist (size = n
     * - m + 1)
     * 2.1 Link to the rest of the elements to the tail of the sublist
     * 2.2 Return the head of the sublist
     * 3. If sublist starts from the first position - return it
     * 3.1 If the sublist not starts from the first position in the original list -
     * assign it as next to the previous node from the original list
     * 
     * t: O(n)
     * s: O(1)
     */
    public static ListNode reverseMN(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        if (m - n + 1 == 1) {
            return head;
        }
        ListNode current = head;
        ListNode prev = null;
        ListNode newList = head;
        int counter = 1;
        int size = n - m + 1;
        while (current != null) {
            if (counter == m) { // current node is a start of reversed sublist
                ListNode reversedAndTail = buildReverseAndAttachTail(current, size);
                if (m > 1) {
                    prev.next = reversedAndTail;
                } else {
                    newList = reversedAndTail;
                }
                break;
            } else {
                prev = current;
                current = current.next;
                counter++;
            }
        }
        return newList;
    }

    private static ListNode buildReverseAndAttachTail(ListNode start, int size) {
        ListNode reversedListHead = null;
        ListNode reversedListTail = start;
        ListNode current = start;
        while (size >= 1) {
            ListNode next = current.next;
            current.next = reversedListHead;
            reversedListHead = current;
            current = next;
            size--;
        }
        reversedListTail.next = current;

        return reversedListHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String toString() {
            return val + (next != null ? next.toString() : "null");
        }
    }
}
