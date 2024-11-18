package linkedlists;

// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/
public class FlattenLinkedList {
    public static void main(String[] args) {
        Node head = new Node(1, null, null, null);
        Node node2 = new Node(2, head, null, null);
        Node node3 = new Node(3, node2, null, null);
        Node node4 = new Node(4, node3, null, null);
        Node node5 = new Node(5, null, null, null);
        Node node6 = new Node(6, node5, null, null);
        Node node7 = new Node(7, null, null, null);
        node5.child = node7;
        node5.next = node6;
        node6.prev = node5;
        head.next = node2;
        node2.prev = head;
        node2.child = node5;
        node2.next = node3;
        node3.prev = node2;
        node3.next = node4;
        node4.prev = node3;
        head = flatten(head);

        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }
    
    /**
     * Optimal solution: use loop and recursion
     * 
     * loop - to iterate through the list by moving forward on the same level
     * recursion - to flatten the child level (subproblem)
     * 
     * flattenInternal returns the tail of the child list which is handy to determine the new tail of the list
     *     1. child become a next node for the current one
     *     2. tail is set to the flattenInternal return value
     *     3. current.child is set to null
     *     4. child.prev is set to current node i.e. the parent
     * if no childs: tail is updated to the current node, current node is set to next
     * 
     * t: O(n), every linked list node is touched once
     * s: O(1), no extra space isused
     * 
     */
    public static Node flatten(Node head) {
        flattenInternal(head);
        return head;
    }


    private static Node flattenInternal(Node head) {
        Node current = head;
        Node tail = null;
        while (current != null) {
            // System.out.println("curr: " + current.val);
            current.prev = tail;
            if (tail != null) {
                tail.next = current;
            }
            Node next = current.next;
            if (current.child != null) {
                Node flattenChildTail = flattenInternal(current.child);
                // System.out.println("child flat: " + flattenChildHeadTail[0].val + "" + flattenChildHeadTail[1].val);
                current.next = current.child;
                current.child.prev = current;
                current.child = null;
                tail = flattenChildTail;
            } else {
                tail = current;
            } 
            current = next;
        }
        return tail;
    }

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val, Node prev, Node next, Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
        public String toString() {
            String child = this.child != null ? this.child.val + "": "";
            String next = this.next != null ? this.next.val + "": "";
            String prev = this.prev != null ? this.prev.val + "": "";

            return String.format("val = '%s', child = '%s', next = '%s', prev = '%s'", val, child, next, prev);
        }
    };
}
