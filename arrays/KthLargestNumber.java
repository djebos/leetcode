package arrays;

import java.util.Arrays;
import java.util.LinkedList;

public class KthLargestNumber {
    public static void main(String[] args) {
        // System.out.println(new KthLargestNumber().findKthLargest(new int[]{1,2,2,-1,10}, 4)); // 1
        // System.out.println(new KthLargestNumber().findKthLargest(new int[]{1,2,2,-1,10}, 1)); // 10
        // System.out.println(new KthLargestNumber().findKthLargest(new int[]{3,2,1,5,6,4}, 2)); // 5
        // System.out.println(new KthLargestNumber().findKthLargest(new int[]{2,1}, 2)); // 5
                System.out.println(new KthLargestNumber().findKthLargest(new int[]{1,2,2,-1,10}, 4)); // 1
        System.out.println(new KthLargestNumber().findKthLargestLinked(new int[]{1,2,2,-1,10}, 1)); // 10
        System.out.println(new KthLargestNumber().findKthLargestLinked(new int[]{3,2,1,5,6,4}, 2)); // 5
        System.out.println(new KthLargestNumber().findKthLargestLinked(new int[]{2,1}, 2)); // 1
        System.out.println(new KthLargestNumber().findMaxPos(3, new Integer[]{2, 2,1,null,null}, 0,4));
        System.out.println(new KthLargestNumber().findMaxPos(-1, new Integer[]{1, null,null,null,null}, 0,4));
        System.out.println(new KthLargestNumber().findMaxPos(3, new Integer[]{2}, 0,0));
        System.out.println(new KthLargestNumber().findMaxPos(0, new Integer[]{5, 2,1,0,0}, 0,4));
        System.out.println(new KthLargestNumber().findMaxPos(-1, new Integer[]{5, 2,1,0,0}, 0,4));


    }

    // Keep track of k number of max values; if current number is higher than max at current position 
    // => replace current max and shift its value right
    // Times out on Leetcode
    // t: O(n*k)
    // s: O(k)
    public int findKthLargest(int[] nums, int k) {
        Integer[] arr = new Integer[k];
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for(int maxPos = 0; maxPos < k; maxPos++) {
                Integer maxNum = arr[maxPos];
                if (maxNum == null) {
                    arr[maxPos] = num;
                    break;
                } else if (num > maxNum) {
                    for(int reassignPos = k - 2; reassignPos >= maxPos; reassignPos--) {
                        arr[reassignPos+1]=arr[reassignPos];
                    }
                    arr[maxPos] = num;
                    break;
                }
            }
        }
        return arr[k-1];
    }

    // Improved: user linked list for max values and easier insertion 

    // t: O(n*logk*k)
    // s: O(k+n*logk) = O(n*logk) (recursion to find max pos)
    public int findKthLargestLinked(int[] nums, int k) {
        Integer[] arr = new Integer[k];
        for(int i = 0; i < nums.length; i++) { // O(n)
            int num = nums[i];
            int pos = findMaxPos(num, arr, 0, k -1); // O(logk)
            if (pos != -1) {
                if (pos + 1 < arr.length) {
                    System.arraycopy(arr, pos, arr, pos + 1, arr.length - pos -1); // O(k) too slow
                }
                arr[pos] = num;
            }
        }
        System.out.println(Arrays.toString(arr));
        return arr[k-1];
    }

    public int findMaxPos(int a, Integer[] arr, int startIdx, int endIdx) {
        int elemPos = endIdx + startIdx / 2;

        if (arr[elemPos] != null && a < arr[elemPos]) {
            if (endIdx >= startIdx) {
                return endIdx + 1 <= arr.length - 1 ? endIdx + 1 : -1;
            }
            return findMaxPos(a, arr, elemPos + 1, endIdx);
        } else if (arr[elemPos] != null && a > arr[elemPos] || arr[elemPos] == null) {
            if (endIdx == startIdx) {
                return endIdx - 1 >= 0 ? endIdx - 1 : endIdx;
            }
            return findMaxPos(a, arr, startIdx, elemPos - 1);
        } else {
            return elemPos;
        }
    }

    // t: O(n*logn) - sorting
    // s: O(n) - sorting extra space
    public int findKthLargestBruteForce(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
