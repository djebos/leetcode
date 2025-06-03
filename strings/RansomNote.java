import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static void main(String[] args) {
        System.out.println(canConstructOpt("a", "b"));
        System.out.println(canConstructOpt("aa", "ab"));
        System.out.println(canConstructOpt("aa", "aab"));
    }
    // Complexity linear
    // t: O(mag + ran)
    // s: O(mag)
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> letters = new HashMap<>(); // s: O(mag)
        for (int i = 0; i < magazine.length(); i++) { // t: O(mag)
            char c = magazine.charAt(i); // t: O(1)
            Integer count = letters.get(c); // t: O(1)
            if (count == null) {
              letters.put(c, 1); // t: O(1)
            } else {
                letters.put(c, count + 1); // t: O(1)
            }
        }
        for (int i = 0; i < ransomNote.length(); i++) { // O(ran)
            char c = ransomNote.charAt(i); // t: O(1)
            Integer letterCount = letters.get(c); // t: O(1)
            if (letterCount == null || letterCount - 1 < 0) {
                return false;
            } else {
                letters.put(c, letterCount - 1); // O(1)
            }
        }
        return true;
    }
    // linear but with less operations and memory
    // t: O(mag + ran)
    // s: O(1)
    public static boolean canConstructOpt(String ransomNote, String magazine) {
        int[] magazineLettersCounter = new int[26]; // s: O(1)
        for (int i = 0; i < magazine.length(); i++) { // t: O(mag)
            magazineLettersCounter[magazine.charAt(i)-'a']++; // t: O(1)
        }

        for (int i = 0; i < ransomNote.length(); i++) { // t: O(ran)
            if (magazineLettersCounter[ransomNote.charAt(i)-'a'] <= 0) { // t: O(1)
                return false;
            } else {
                magazineLettersCounter[ransomNote.charAt(i)-'a']--; // t: O(1)
            }
        }

        return true;
    }
}
