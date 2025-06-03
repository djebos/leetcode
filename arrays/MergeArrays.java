package arrays;

import java.util.Arrays;

public class MergeArrays {
    public static void main(String[] args) {
        int[] arr1 = new int[] {1,2,3,0,0,0};
        mergeOpt(arr1, 3, new int[] {2,5,6}, 3);
        System.out.println(Arrays.toString(arr1));
    }

    // Straightforward approach: create a merged array and insert the lowest value first
    // if reached end of first array - append second values and wise versa
    //
    // t: O(m + n) + O(m + n) = O(m+n)
    // s: O(m + n) for merged array
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] merged = new int[m+n]; // s: O(m + n)
        for(int i =0, j = 0, k = 0; i < m || j < n; k++) {
            if (i < m && j < n) {
                int a = nums1[i];
                int b = nums2[j];
                if (a > b) {
                  merged[k] = b;
                  j++;
                } else {
                    merged[k] = a;
                    i++;
                }
            } else if (i == m) { // left empty => append right
                merged[k] = nums2[j];
                j++;
            } else { // right empty => append left
                merged[k] = nums1[i];
                i++;
            }
        }
        for (int i = 0; i < n + m; i++) {
            nums1[i] = merged[i];
        }
    }

    // Easy one with sorting - slow
    //
    // t: O(n) + O((m + n) * log(n+m)) - initial copy + sort
    // s: O(n + m) - sort
    public static void mergeWithSort(int[] nums1, int m, int[] nums2, int n) {
        for(int i = m, j = 0; i < n + m; j++, i++ ) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
    }


    // Best two pointers without extra memory: merging from the end to avoid shifting elements left
    //
    // t: O(n + m)
    // s: O(1)
    public static void mergeOpt(int[] nums1, int m, int[] nums2, int n) {
        int i = m -1; // left current element
        int j = n -1; // right current element
        int k = m + n -1; // result array current element
        while (k > 0 && i >= 0 || j >= 0) {
           if (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
                k--;
            } else {
                nums1[k] = nums2[j];
                j--;
                k--;
            }
           } else if (i < 0) { // left ended, append right 
            nums1[k] = nums2[j];
            j--;
            k--; 
           } else {
            nums1[k] = nums1[i];
            i--;
            k--; 
           }
        }

    }
}
