package arrays;

import java.util.Arrays;

public class MergeArrays {
    public static void main(String[] args) {
        int[] arr1 = new int[] {1,2,3,0,0,0};
        merge(arr1, 3, new int[] {2,5,6}, 3);
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
}
