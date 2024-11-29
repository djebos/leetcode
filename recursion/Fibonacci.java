package recursion;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(new Fibonacci().fib(5)); //5 -> 0 1 1 2 3 5
        System.out.println(new Fibonacci().fib(0)); //0 -> 0

        System.out.println(new Fibonacci().fib(1)); //1 -> 0 1
        System.out.println(new Fibonacci().fib(6)); //8 -> 0 1 1 2 3 5 8
        System.out.println(new Fibonacci().fib(3)); //2 -> 0 1 1 2
    }


    /*
     * Iterative aproach: if n == 0 or n == 1 return immediately
     * Otherwise, iterate n times and keep track of n-1 and n-2 values.
     * after calculating a new n - 1 value -> shift it's old value to n - 2
     * 
     * t: O(n), iterate n times
     * s: O(1), no extra memory
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prev1 = 0;
        int prev2 = 0;
        for(int i = 1; i <= n; i++) {
            if (i == 1) {
                prev2 = 1;
            } else {
                int tmp = prev2;
                prev2 = prev2 + prev1;
                prev1 = tmp;
            }
        }

        return prev2;
    }

    /*
     * Recursive approach (not optimal):
     * 
     * 1. Base case: return 1 if n == 1
     *               return 0 if n == 0
     * 2. Recursive case:
     *  a) calculate fibonacci for n - 1
     *  b) calculate fibonacci for n - 2
     *  c) return sum
     * 
     * t: O(2^N) because recursion is triggered n times for n - 1 and for n - 2, again and again
     * s: O(2^N) for recursion stack
     */
    public int fibRecursive(int n) {
       if (n == 0) {
        return 0;
       }
       if (n == 1) {
        return 1;
       }
       return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

}
