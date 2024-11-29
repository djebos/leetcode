package sorting;

import java.util.Arrays;

public class Selection {
        public static void main(String[] args) {
        int[] arr = new int[] {4,3,2,10,11,-1};
        System.out.println(Arrays.toString(arr));
        new Bubble().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    // Swap elements if ith is more than jth thus comparing ith to all the elements and finding the lowest one
    // t: O(n^2), for every ith position n - i operations are executed which with a large n is equal to n
    // s: O(1), no extra space used
    public void sort(int[] arr) {
        for(int i =0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length; j++ ) {
                int ith = arr[i];
                int jth = arr[j];
                if (ith > jth) {
                    arr[i] = jth;
                    arr[j] = ith;
                }
            }
            System.out.println(Arrays.toString(arr));

        }
    }
}
