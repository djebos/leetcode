package arrays;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/container-with-most-water/description/
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        // Best test case
        // [7,1,2,3,9] - height = 7 < 9 = 7; width p2 - p1 = 4; squere = 28
        int[] bestCaseArr = new int[] { 7, 1, 2, 3, 9 };
        System.out.println("Arr '" + Arrays.toString(bestCaseArr) + "' => " + containerWithMostWater1Loop(bestCaseArr));

        // Empty arr
        // [] - height =0, width = 0; height = 0
        int[] emptyArr = new int[0];
        System.out.println("Arr '" + Arrays.toString(emptyArr) + "' => " + containerWithMostWater1Loop(emptyArr));

        // Arr of 1 element
        // [2] - height = 0, width = 0; height = 0 | we need at least two elements to
        // form a bucket
        int[] oneElemArr = new int[] { 1 };
        System.out.println("Arr '" + Arrays.toString(oneElemArr) + "' => " + containerWithMostWater1Loop(oneElemArr));

        // Not obvious test case
        // [6,9,3,4,5,8] - 6 * 5 = 30, 8 * 4 = 32
        int[] arr = new int[] { 6, 9, 3, 4, 5, 8 };
        System.out.println("Arr '" + Arrays.toString(arr) + "' => " + containerWithMostWater1Loop(arr));

        // Gradual increase test case
        // [1,2,3,9,4,3,2,1] - 2 * 5 = 10, 3 * 2 = 6
        int[] gardIncr = new int[] { 1, 2, 3, 9, 4, 3, 2, 1 };
        System.out.println("Arr '" + Arrays.toString(gardIncr) + "' => " + containerWithMostWater1Loop(gardIncr));
    }

    // Brute force
    // t: O(n^2)
    // s: O(1)
    static int containerWithMostWater(int[] arr) {
        // iterate over arr
        // get i-th element as a left side of a bucket
        // iterate over i + 1 elements of arr
        // get i + 1 element as a right side of a bucket
        // calculate the squere by selecting the smaller value as height and i + 1 - i
        // as a width
        int maxWidth = 0;
        for (int i = 0; i < arr.length; i++) {
            int left = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int right = arr[j];
                int width = j - i;
                int height = Math.min(left, right);
                maxWidth = Math.max(maxWidth, width * height);
            }
        }
        return maxWidth;
    }

    // 1 loop with two pointers at the beginning and at the end moving towards each
    // other
    // Moving only the side of lower height because otherwise its potential square
    // will only be less than current
    // because width is gonna pare down and height is fixed. For example:
    // [6,9,3,4,5,8]
    // p1 = 6, p2 = 8, square = 6 * 5 = 30
    // moving p2 (losing 8 that contributes to the best square)
    // p1 = 6, p2 = 5, square = 5 * 4 = 20
    // moving p1
    // p1 = 9, p2 = 5, square = 5 *3 = 15
    // From what is evident, square is goint to pare down as we move sides closer to
    // each other
    // but if we move higher side closer we cannot find it a better counterpart
    // (case with moving from 8)
    //
    // t: O(n)
    // s: O(1)
    static int containerWithMostWater1Loop(int[] arr) {
        int maxWidth = 0;
        for (int i = 0, j = arr.length - 1; arr.length > 1 && i != j;) {
            int left = arr[i];
            int right = arr[j];
            int width = j - i;
            int height = Math.min(left, right);
            maxWidth = Math.max(maxWidth, width * height);
            if (height == left) {
                i++;
            } else {
                j--;
            }
        }
        return maxWidth;
    }
}
