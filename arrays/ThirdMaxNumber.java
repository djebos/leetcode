package arrays;

import java.util.Arrays;

public class ThirdMaxNumber {
    public static void main(String[] args) {
        ThirdMaxNumber s = new ThirdMaxNumber();
        System.out.println(s.thirdMax(new int[] {3,2,1})); // 1
        System.out.println(s.thirdMax(new int[] {1, 2})); // 2
        System.out.println(s.thirdMax(new int[] {2,2,3,1})); // 1
        System.out.println(s.thirdMax(new int[] {-2147483648,-2147483648,-2147483648,-2147483648,1,1,1})); //1

    }

    /**
     Brute force: sort array and iterate from the end. If counter is 3 than the current element is the third max value
     counter is incremented only if prev value doesn't match the current one. counter starts with 1 because the first
     highest element is already set to nums[nums.length - 1]

     t: O(nlogn (quicksort) + n) 
     s: O(n) - space for QuickSort stack recursion
     */
    public int thirdMaxBF(int[] nums) {
        Arrays.sort(nums);
        if (nums.length < 3) {
            return nums[nums.length - 1];
        }
        int prev = nums[nums.length - 1];
        int i = nums.length - 2;
        int counter = 1;
        while(i >= 0) {
            int curr = nums[i];
            if (curr != prev) {
                prev = curr;
                counter++;
                if (counter == 3) {
                    return curr;
                }
            }
            i--;
        }
        return nums[nums.length - 1];
    }

    /**
     * 
     * Optimal solution: use 3 variables to track max values, if current number is more than max1 we shift max1 value
     * to max2 and max2 value to max3. If current number is equal to any of max values - skip
     * 
     * t: O(n)
     * s: O(1)
     * 
     */
    public int thirdMax(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            int max1 = nums[0];
            Integer max2 = null;
            Integer max3 = null;
            for (int i = 1; i < nums.length; i++) {
                int curr = nums[i];
                if (curr == max1 || (max2 != null && curr == max2) || (max3 != null && curr == max3)) {
                  continue;
                }

                if (curr > max1) {
                        // System.out.println("curr=" + curr + "max1=" + max1);
                      max3 = max2;
                      max2 = max1;
                      max1 = curr;
                    //   System.out.println("reassigned");
                } else if (max2 != null && curr >= max2 || max2 == null) {
                       max3 = max2;
                       max2 = curr;
                } else if (max3 != null && curr >= max3 || max3 == null) {
                       max3 = curr;
                }
                // System.out.println("max1=" + max1 + ";max2=" + max2 + ";max3=" + max3);
            }
            return max3 == null ? max1 : max3;
    }
}
