public class AlmostPalindrome {
    public static void main(String[] args) {
        System.out.println(validPalindrome("aba"));
        System.out.println(validPalindrome("abca"));
        System.out.println(validPalindrome("abc"));
        System.out.println(validPalindrome("Race a car"));
    }

    /**
     * Two pointer technique and breaking the solution down to the two problems:
     * main problem - find almost a palindrome
     * subproblem 1 - verify whether a substring starting AFTER the skipped character is a palindrome (l + 1)
     * subproblem 2 - verify whether a substring ending BEFORE the skipped character is a palindrome (r - 1)
     * 
     * t: O(n)
     * s: O(1)
     */
    public static boolean validPalindrome(String s) {
        for (int l = 0, r = s.length() - 1; l < r; r--, l++) {
            char right = s.charAt(r);
            char left = s.charAt(l);
            if (right != left) {
               return validPalindromInternal(s, l + 1, r) || validPalindromInternal(s, l, r - 1);
            }
         }
  
         return true;
        
    }

    private static boolean validPalindromInternal(String s, int l, int r) {
        System.out.println("r " + r + " l " + l);
        for (; l < r; l++, r--) {
            char right = s.charAt(r);
            char left = s.charAt(l);
            if (right != left) {
               return false;
            }
         }
  
         return true;
    }
}
