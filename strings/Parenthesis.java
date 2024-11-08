import java.util.ArrayDeque;

// https://leetcode.com/problems/valid-parentheses/description/
public class Parenthesis {

    public static void main(String[] args) {
        boolean valid = isValid("{}[]()");
        System.out.println("{}[]() ->" + valid);

        boolean valid2 = isValid("{}[](]");
        System.out.println("{}[](] ->" + valid2);
    }

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        int currentPos = 1;
        var stack = new ArrayDeque<Character>();
        stack.add(s.charAt(0));
        while (currentPos < s.length()) {
            if (stack.isEmpty() || !matchParentheses(s, currentPos, stack)) {
                stack.addLast(s.charAt(currentPos));
            } else if (matchParentheses(s, currentPos, stack)) {
                stack.removeLast();
            }
            currentPos++;
        }
        return stack.isEmpty();
    }

    private static boolean matchParentheses(String s, int currentPos, ArrayDeque<Character> stack) {
        if (stack.getLast() == '(' && ')' == s.charAt(currentPos)) {
            return true;
        } else if (stack.getLast() == '[' && ']' == s.charAt(currentPos)) {
            return true;
        } else return stack.getLast() == '{' && '}' == s.charAt(currentPos);
    }
}
