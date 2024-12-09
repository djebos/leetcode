package sorting;

import java.util.Arrays;

public class Insertion {
    public static void main(String[] args) {
        int[] arr = new int[] {4,3,2,10,11,-1};
        new Insertion().sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // t: O(n^2) in case array is reversed, but if the array almost sorted - O(n)
    // s: O(1)
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int ith = arr[i];
            int j = i -1;
            // j+1 is a target position for insertion
            for (; j >= 0 && arr[j] > ith; j--) {
                arr[j + 1] = arr[j];
            }
            // because at the last iteration j will be -1
            // if arr[j] < ith it means that ith element must be inserted after the jth element
            arr[j + 1] = ith;

        }
    }
}
