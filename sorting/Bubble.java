package sorting;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] arr = new int[] {4,3,2,10,11,-1};
        System.out.println(Arrays.toString(arr));
        new Bubble().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    // Largest element bubbles to the end:
    // so after end of the ith loop the ith largest element will be in the appropriate position to the end
    
    // t: O(n^2), for every element do n comparisons and swaps
    // s: O(1), no extra space used
    public void sort(int[] arr) {
        for(int i =0; i < arr.length; i++) {
            for(int j = 0; j < arr.length - 1; j++ ) {
                int a = arr[j];
                int b = arr[j + 1];
                if (a > b) {
                    int tmp = a;
                    arr[j] = b;
                    arr[j + 1] = tmp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
