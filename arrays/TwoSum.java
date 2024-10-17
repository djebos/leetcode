
/**
 * Leetcode https://leetcode.com/problems/two-sum/description/
 */
package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        if (sumOfTwoIndicesOpt(new int[] {}, 10) != null) {
            System.exit(1);
        }
        System.out.println("Empty arr OK!");

        if (sumOfTwoIndicesOpt(new int[] { 10 }, 10) != null) {
            System.exit(1);
        }
        System.out.println("One element arr OK!");
        List<Integer> twoArrResuList = Arrays.stream(sumOfTwoIndicesOpt(new int[] { 2, 8 }, 10)).boxed().toList();
        if (!twoArrResuList.contains(0) && !twoArrResuList.contains(1) || twoArrResuList.size() != 2) {
            System.out.println("Actual result " + Arrays.toString(sumOfTwoIndicesOpt(new int[] { 2, 8 }, 10)));
            System.exit(1);
        }

        System.out.println("Two arr OK!");

        if (sumOfTwoIndicesOpt(new int[] { 2, 8, 1, 10, 15 }, 22) != null) {
            System.out
                    .println("Actual result " + Arrays.toString(sumOfTwoIndicesOpt(new int[] { 2, 8, 1, 10, 15 }, 22)));
            System.exit(1);
        }
        System.out.println("No solution OK!");
        List<Integer> happyArrResuList = Arrays
                .stream(sumOfTwoIndicesOpt(new int[] { 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 7, 1, 1, 1, 1, 1 }, 11)).boxed()
                .toList();
        if (!happyArrResuList.contains(11) && !happyArrResuList.contains(5) || happyArrResuList.size() != 2) {
            System.out.println("Actual result " + Arrays
                    .toString(sumOfTwoIndicesOpt(new int[] { 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 7, 1, 1, 1, 1, 1 }, 11)));
            System.exit(1);
        }
        System.out.println("Happy path OK!");
    }

    /**
     * t: O(n^2) -> can be improved by using more memory
     * s: O(1) -> best
     */
    static int[] sumOfTwoIndices(int[] arr, int targetSum) {
        for (int i = 0; i < arr.length - 1; i++) {
            int num1 = arr[i];
            int target = targetSum - num1;
            for (int k = i + 1; k < arr.length; k++) {
                if (arr[k] == target) {
                    return new int[] { i, k };
                }
            }
        }
        return null;
    }

    /**
     * t: O(n) - improved
     * s: O(n) - degraded
     */
    static int[] sumOfTwoIndicesOpt(int[] arr, int targetSum) {

        Map<Integer, Integer> store = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer currentFoundIdx = store.get(arr[i]);
            if (currentFoundIdx != null) { // pair found because previously some digit added its target
                return new int[] { i, currentFoundIdx };
            }
            // None expects pair with current digit: calculate its own target and store
            int currentTarget = targetSum - arr[i];

            store.put(currentTarget, i);
        }
        return null;
    }
}
