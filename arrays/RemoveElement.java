package arrays;

import java.util.Arrays;
// https://leetcode.com/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = new int[] {3,2,2,3,1};
        int val = 3;
        System.out.println(removeElement(nums, val));
        System.out.println(Arrays.toString(nums));
    }

       // Two pointers: left tracks current element, right - its swap position
       // When two pointers meet it means that all the val numbers were swapped
       //
       // t: O(n)
       // s: O(1)
        public static int removeElement(int[] nums, int val) {
            int k = 0;
            for(int i = 0, j = nums.length - 1; i <= j; ) { // when two points meet - allow last iteration 
                if(nums[i] == val) {
                    if (nums[j] != val) {
                      int temp = nums[j];
                      nums[j] = nums[i];
                      nums[i] = temp;
                      j--;
                      i++;
                      k++;
                    } else {
                        j--;
                    }
                } else {
                    k++;
                    i++;
                }
            }
    
            return k;
        }
}
