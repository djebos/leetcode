import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringOpt("abcba")); //3
        System.out.println(lengthOfLongestSubstringOpt("")); // 0
        System.out.println(lengthOfLongestSubstringOpt("111")); // 1
        System.out.println(lengthOfLongestSubstringOpt("ababcd")); // 4
        System.out.println(lengthOfLongestSubstringOpt("tmmzuxt")); // 5
    }

    /**
     * Sliding window: if current char is found by looking behind the current start position
     * for the substring is shifted to the found position + 1
     * e.g.
     * abcba
     * i = 0, c = 'a', substrStart = 0, maxLength = 1,                      sliding window = a
     * i = 1, c = 'b', substrStart = 0, maxLength = 2                       sliding window = ab
     * i = 2, c = 'c', substrStart = 0, maxLength = 3                       sliding window = abc
     * i = 3, c = 'b', substrStart = 1 + 1, maxLength = 3       - hit       sliding window = cb
     * i = 4, c = 'a', substrStart = 2, maxLength = Max(3, 4 - 2 + 1 ) = 3  sliding window = cba
     * 
     * e.g.
     * ababcd
     * i = 0, c = 'a', substrStart = 0, maxLength = 1                           
     * i = 1, c = 'b', substrStart = 0, maxLength = 2
     * i = 2, c = 'a', substrStart = 0 + 1, maxLength = 2       - hit
     * i = 3, c = 'b', substrStart = 1 + 1, maxLength = 2       - hit
     * i = 4, c = 'c', substrStart = 2, maxLength = Max(2, 4 - 2 + 1 ) = 3
     * i = 5, c = 'd', substrStart = 2, maxLength = Max(3, 5 - 2 + 1 ) = 4
     * 
     * t: O(n^2) because in the worst case e.g. s = 'abcdefg' we will have to look behind 21 times 
     * s: O(1) no extra space is used
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int substrStart = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int indexOfC = s.indexOf(c, substrStart, i);
            if (indexOfC != -1) {
                substrStart = indexOfC + 1;
            } else {
                maxLength = Math.max(maxLength, i - substrStart + 1);
            }
        }

        return maxLength;
    }


    /**
     * Sliding window optimized for time: if current char is found in the map left boundary (i)
     * is shifted to the found position + 1. Left boundary is shifted only if found pos is to the right 
     * of the current left position
     * 
     * t: O(n) 
     * s: O(n) hashmap space
     */
    public static int lengthOfLongestSubstringOpt(String s) {
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>(s.length());
        for(int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            var dupPos = map.get(c);
            if (dupPos != null && dupPos >= i) {
                map.put(c, j);
                i = dupPos + 1;
            } else {
                map.put(c, j);
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        return maxLength;
    }
}
