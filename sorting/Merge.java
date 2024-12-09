package sorting;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] arr = new int[] {4,3,2,10,11,-1};
        new Merge().sort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = new int[] {0};
        new Merge().sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = new int[] {0,-1};
        new Merge().sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }

    //  Divide and Conquer: split into two subarrays and sort them by comparing elements from both
    // after splitting to size of 1 the return from recursion begins
    // so the first merge occurs between just two elements e.g. leftIndex = 0; mid = 0; rightIndex = 1
    // after that we return to recursive invocation where there're two subarrays of two elements each
    // but the left subarray is sorted so mergeSort is called for the right one
    //
    // t: O(nlogn)
    // s: O(n) recursion
    public void sort(int[] arr) {
      mergeSort(arr, 0, arr.length - 1);
    }

    void mergeSort(int[] arr, int start, int end) {
       if (start >= end) {
         return;
       }
        int mid = (start + end) / 2;
        System.out.println("mid = "  + mid);
        mergeSort(arr, start, mid); // sort left (divide)
        mergeSort(arr, mid + 1, end); // sort right (divide)
        mergeArrays(arr, start, mid, end); // conquer
    }

    void mergeArrays(int[] arr, int start, int mid, int end) {
             int right = mid + 1;
             int left = start;
             System.out.println("left = " + left + " right = " + right + " mid = " + mid);
             while (left <= mid && right <= end) {
                int leftElem = arr[left];
                int rightElem = arr[right];
                if (leftElem > rightElem) {
                    for(int i = right - 1; i >= left; i--) { // not the best solution because in the worst case  
                        // (left side has only bigger elements than the right side) arr.length / 2 shifts will be needed per right element
                        // N / 2 * N/2
                        arr[i+1] = arr[i];
                    }
                    arr[left] = rightElem;
                    right++;
                    left++;
                } else {
                    left++;
                }
             }

    }
}
