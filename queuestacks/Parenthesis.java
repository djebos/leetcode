package queuestacks;
import java.util.LinkedList;

// https://leetcode.com/problems/valid-parentheses/description/
public class Parenthesis {

    public static void main(String[] args) {
        boolean valid = isValid("{}[]()");
        System.out.println("{}[]() ->" + valid); // true

        boolean valid2 = isValid("{}[](]");
        System.out.println("{}[](] ->" + valid2); // false: last round bracket closed by squere bucket


        boolean valid3 = isValid("{[()]}");
        System.out.println("{[()]} ->" + valid3); // true: internal brackets close correctly

        boolean valid4 = isValid("{([(])]}");
        System.out.println("{([(])]} ->" + valid4); // false: wrong order

        boolean valid5 = isValid("]");
        System.out.println("] ->" + valid5); // false: wrong order
    }
    // 
    // Keep track of opening brackets using a linked list, if closing one is found - compare to the last open bracket
    // if match is found - remove the last open bracket
    // if not - string isn't valid
    //
    // t: O(n)
    // s: O(n)
    public static boolean isValid(String s) {
       if (s.isEmpty()) {
        return true;
       }
       LinkedList<Character> openBrackets = new LinkedList<>();
       
       for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '[' || c == '{' || c == '(') {
            openBrackets.add(c);
        } else if (!openBrackets.isEmpty()) {
            if (c == ']' &&  '[' == openBrackets.getLast()) {
                openBrackets.removeLast();
            } else if (c == ')' && '(' == openBrackets.getLast()) {
                openBrackets.removeLast();
            } else if (c == '}' && '{' == openBrackets.getLast()) {
                openBrackets.removeLast();
            } else {
                return false; // no matching opening bracket
            }
        } else {
            return false; // covers case when closing brackets outnumber opening ones
        }
       }
       return openBrackets.isEmpty();
    }

}
