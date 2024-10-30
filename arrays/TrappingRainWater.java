package arrays;

/**
 * Hard
 * https://leetcode.com/problems/trapping-rain-water/description/
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(trapOptimal(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 })); // 6
        System.out.println(trapOptimal(new int[] { 4, 2, 0, 3, 2, 5 })); // 9
        System.out.println(trapOptimal(new int[] { 4, 2, 3 })); // 1
        System.out.println(trapOptimal(new int[] { 5, 4, 1, 2 })); // 1

    }

    /**
     * 1. Water can be trapped only if there're two walls
     * 2. If two walls are of different height then hight of trapped water is
     * min(a,b)
     * 3. If left wall is lower than right wall it traps water of left wall height
     * 4. If left wall is higher than right then it traps water of right wall height
     * 
     * Brute force: calculate water height per position in the array by looking for
     * the highest left and right walls
     * t: O(n^2)
     * s: O(1)
     * 
     * Times out on Leetcode
     */
    public static int trap(int[] height) {
        int totalWater = 0;
        for (int i = 1; i < height.length; i++) {
            int left = 0;
            int right = 0;
            for (int leftIdx = i - 1; leftIdx >= 0; leftIdx--) {
                left = Math.max(left, height[leftIdx]);
            }
            for (int rightIdx = i + 1; rightIdx < height.length; rightIdx++) {
                right = Math.max(right, height[rightIdx]);
            }
            int currentWater = Math.min(left, right) - height[i];
            if (currentWater > 0) {
                totalWater += currentWater;
            }
        }
        return totalWater;
    }

    /**
     * 1. Water can be trapped only if there're two walls
     * 2. If two walls are of different height then hight of trapped water is
     * min(a,b)
     * 3. If left wall is lower than right wall it traps water of left wall height
     * 4. If left wall is higher than right then it traps water of right wall height
     * 
     * Improved Brute Force: do not calculate left wall in the loop, calculate right wall only if the target position matches right wall index
     * the highest left and right walls
     * t: O(n^2)
     * s: O(1)
     * 
     * Passes on Leetcode
     */
    public static int trapImprove(int[] height) {
        int totalWater = 0;
        int highestLeft = 0;
        int highestRight = 0;
        int highestRightIdx = 0;
        for (int i = 1; i < height.length; i++) {
            int leftIdx = i - 1;
            boolean updateRightIdx = false;

            if (height[leftIdx] > highestLeft) {
                highestLeft = height[leftIdx];
            }

            if (highestRightIdx == 0) {
                updateRightIdx = true;
                highestRightIdx = i + 1;
            } else if (highestRightIdx == i) {
                updateRightIdx = true;
                highestRight = 0;
                highestRightIdx++;
            }
            if (updateRightIdx) {
                for (int rightIdx = highestRightIdx; rightIdx < height.length; rightIdx++) {
                    if (highestRight < height[rightIdx]) {
                        highestRight = height[rightIdx];
                        highestRightIdx = rightIdx;
                    }
                }
            }
            int currentWater = Math.min(highestLeft, highestRight) - height[i];
            if (currentWater > 0) {
                totalWater += currentWater;
            }
        }
        return totalWater;
    }

    /**
     * Two pointer technique: calculate water height per position in the array by moving the side that is smaller
     * t: O(n)
     * s: O(1)
     * 
     * Times out on Leetcode
     */
    public static int trapOptimal(int[] height) {
        int totalWater = 0;
        int maxLeft = 0;
        int maxRight = 0;
        for (int leftIdx = 0, rightIdx = height.length - 1; leftIdx != rightIdx;) {
            int left = height[leftIdx];
            int right = height[rightIdx];
            if (left <= right) { // this condition guarantees that we don't have to check for lower value by comparing maxLeft and maxRight to calculate water at position
                if (maxLeft > left) { // left wall can be formed
                    totalWater += maxLeft - left;
                } else {
                    maxLeft = left;
                }
                leftIdx++;
            } else {
                if (maxRight > right) { // right wall can be formed
                    totalWater += maxRight - right;
                } else {
                    maxRight = right;
                }
                rightIdx--;
            }

        }
        return totalWater;
    }
}
