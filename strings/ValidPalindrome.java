public class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindromeOpt("aba"));
        System.out.println(isPalindromeOpt("A man, a plan, a canal: Panama"));
        System.out.println(isPalindromeOpt("ab_a"));
        System.out.println(isPalindromeOpt("ab_aa"));
        System.out.println(isPalindromeOpt("a"));
        System.out.println(isPalindromeOpt("0P"));
    }

    /**
     * Brute force: Remove whitespaces and non-word characters, reverse and compare
     * 
     * t: O(n + n + n) = O(n)
     * s: O(n + n) = O(n)
     */
    public static boolean isPalindrome(String s) {
        s = s.replaceAll("\\s*\\W*_*", "").toLowerCase();
        String reverse = new StringBuilder(s).reverse().toString();
        return reverse.toString().equals(s);
    }

    /**
     * Two pointers: Skip whitespaces and non-word characters, start from 0 and
     * s.length - 1
     * 
     * t: O(n)
     * s: O(1)
     */
    public static boolean isPalindromeOpt(String s) {

        for (int l = 0, r = s.length() - 1; l <= r;) {
            char left = s.charAt(l);
            char right = s.charAt(r);
            if (!Character.isLetterOrDigit(left)) {
                l++;
                continue;
            }
            if (!Character.isLetterOrDigit(right)) {
                r--;
                continue;
            }
            int diff = Math.abs(left - right);
            if (diff != 0) {
                if (Character.isDigit(left) || Character.isDigit(right)) {
                    return false;
                } else if (diff != 32) {
                    return false;
                }
            }
            l++;
            r--;
        }
        return true;
    }
}
