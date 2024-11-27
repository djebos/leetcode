package queuestacks;

import java.util.ArrayDeque;
import java.util.Stack;

public class QueueWithTwoStacks {
    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.pushOpt(10);

        int param_2 = obj.popOpt();
        System.out.println(param_2);
        obj.pushOpt(20);
        obj.pushOpt(30);
        obj.pushOpt(40);
        int param_3 = obj.peekOpt();
        System.out.println(param_3);
        boolean param_4 = obj.emptyOpt();
        System.out.println(param_4);

        int param_5 = obj.popOpt();
        System.out.println(param_5);
        int param_6 = obj.popOpt();
        System.out.println(param_6);
        boolean param_7 = obj.emptyOpt();
        System.out.println(param_7);
    }

    static class MyQueue {

        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> queue = new Stack<Integer>();

        public MyQueue() {

        }

        /**
         * Recreate the stack by re-pushing elements to it from the queue and back e.g:
         * input 10,20,30
         * 
         * iteration:0
         * stack: [10]
         * queue: [10]
         * stack: [] - consumed
         * -----------------
         * iteration:1
         * stack: 20,10 - recreated from the queue
         * queue: 10,20
         * stack: [] - consumed
         * ------------------
         * iteration:2
         * stack: 30,20,10
         * queue: 10,20,30
         * stack: [] - consumed
         * 
         * t: O(n + n) = O(n) - loop over queue and stack
         * s: O(n + n) = O(n) - queue + stack memory
         */
        public void push(int x) {
            while (queue.size() != 0) {
                stack.push(queue.pop());
            }
            stack.push(x);

            while (stack.size() != 0) {
                queue.push(stack.pop());

            }
            System.out.println(queue);
        }

        // t: O(1)
        public int pop() {
            return (int) queue.pop();
        }

        // t: O(1)
        public int peek() {
            return (int) queue.peek();
        }

        // t: O(1)
        public boolean empty() {
            return queue.isEmpty();
        }

        // Optimal solution: use stack to keep track of inserted records
        // t: O(1)
        // s: O(1)
        public void pushOpt(int x) {
            stack.push(x);
        }
        // if queue doesn't have elements: sync them from the stack
        //
        // t: O(n)
        // s: O(n)
        public int popOpt() {
            if (queue.size() != 0) {
                return queue.pop();
            } else {
                while (stack.size() != 0) {
                    queue.push(stack.pop());
                }
                return queue.pop();
            }        
        }
        // if queue doesn't have elements: sync them from the stack
        // t: O(n)
        // s: O(n)
        public int peekOpt() {
            if (queue.size() != 0) {
                return queue.peek();
            } else {
                while (stack.size() != 0) {
                    queue.push(stack.pop());
                }
                return queue.peek();
            }
        }

        // t: O(1)
        public boolean emptyOpt() {
            return queue.isEmpty() && stack.isEmpty();
        }
    }
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
