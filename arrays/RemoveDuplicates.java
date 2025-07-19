package arrays;

import java.util.Arrays;
// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3, 4 };
        System.out.println(removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
        int[] arr2 = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        System.out.println(removeDuplicates(arr2));
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = new int[] { 1, 2, 2, 2, 2, 2 };
        System.out.println(removeDuplicates(arr3));
        System.out.println(Arrays.toString(arr3));
        int[] arr4 = new int[] { 1,1,2,3 };
        System.out.println(removeDuplicates(arr4));
        System.out.println(Arrays.toString(arr4));
    }

    // Two pointers approach: 
    // Pointer i keeps track of the last unique element position
    // Pointer j interates over non-unique array
    //
    // t: O(n)
    // s: O(1)
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) { // unique found
                if (j - i > 1) {
                    i++; // avoid overwriting unique at i-th index
                    nums[i] = nums[j];
                } else {
                    i++; // if numbers are unique but sequentual then it mean there's one more unique number
                }

            }
        }
        return i + 1;
    }
}
