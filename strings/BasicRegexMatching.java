/**
 * Leetcode https://leetcode.com/problems/regular-expression-matching/
 */
public class BasicRegexMatching {
    public static void main(String[] args) {
        boolean aabicddMatch = isMatch("aabicdd", "a*b.cd*");

        System.out.println("'aabicdd' match: " + aabicddMatch);
//
        boolean aaabiucddddkMatch = isMatch("aaabiucddddk", "a*b..cd*k");
        System.out.println("'aaabiucddddk' match: " + aaabiucddddkMatch);
//
        boolean matchToEnd = isMatch("korval", "kor.*");
        System.out.println("'koraval' match: " + matchToEnd);

        boolean invalidmatchToEnd = isMatch("korval", "kor.*.");
        System.out.println("Invalid 'koraval' match: " + invalidmatchToEnd);

        boolean invalidmatch2 = isMatch("aa", "a");
        System.out.println("Invalid 'aa' match: " + invalidmatch2);
    }

    public static boolean isMatch(String input, String pattern) {
        int inputPos = 0;
        int patternPos = 0;
        while (inputPos < input.length() && patternPos < pattern.length()) {
            char first = pattern.charAt(patternPos);
            if (patternPos + 1 < pattern.length() && pattern.charAt(patternPos + 1) == '*') {
                if (Character.isLetter(first)) {
                    while (inputPos < input.length() && input.charAt(inputPos) == first) {
                        inputPos++;
                    }
                } else if (first == '.') {
                    while (inputPos < input.length()) {
                        inputPos++;
                    }
                }
                patternPos +=2;
            } else if (Character.isLetter(first) && first == input.charAt(inputPos)) {
                inputPos++;
                patternPos++;
            } else if (first == '.') {
                inputPos++;
                patternPos++;
            } else {
                return false;
            }
        }
        return pattern.length() == patternPos && input.length() == inputPos;
    }
}
