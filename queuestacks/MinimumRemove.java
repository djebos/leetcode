package queuestacks;

import java.util.HashSet;
import java.util.LinkedList;

public class MinimumRemove {

    public static void main(String[] args) {
        MinimumRemove s = new MinimumRemove();
        System.out.println(s.minRemoveToMakeValid2("lee(t(c)o)de)"));
        System.out.println(s.minRemoveToMakeValid2("a)b(c)d"));
        System.out.println(s.minRemoveToMakeValid2("))(("));
        System.out.println(s.minRemoveToMakeValid2("(())))(()"));
    }

    // t: O(n + n + n): two iterations over the string and 1 for adding to hashmap
    // s: O(n + n + n)
    public String minRemoveToMakeValid(String s) {
        StringBuilder result = new StringBuilder();
        LinkedList<Integer> openPos = new LinkedList<>();
        LinkedList<Integer> closePosToRemove = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openPos.add(i);
            } else if (c == ')') {
                if (openPos.isEmpty()) {
                    closePosToRemove.add(i);                  
                } else {
                    openPos.removeLast();
                }
            }
        }
        HashSet<Integer> removeCharPos = new HashSet<>();
        removeCharPos.addAll(openPos);
        removeCharPos.addAll(closePosToRemove);
        for (int i = 0; i < s.length(); i++) {
            if (!removeCharPos.contains(i)) {
                result.append(s.charAt(i));
            }
        }
        
        return result.toString();
    }
    // Optimized solution: keep track of number of removed closing brackets and adjust removing position of open brackets
    // Remove not closed open brackets starting from the last one to keep the correct order
    // 
    // The solution is based upon an observation: open brackets will not be closed only if closing brackets were in the string before them
    // so open brackets for removal are always present at the last index where any brackets are expected:
    // e.g. a)b(()fbdaf -> pos 3 to remove and shift by 1 because at pos 1 was is removed
    //
    // t: O(n + n): first while loop to traverse the string and another one to remove open brackets if any
    // s: O(n + n): extra memory for open brackets and result string
    public String minRemoveToMakeValid2(String s) {
        StringBuilder result = new StringBuilder(s);
        LinkedList<Integer> openPos = new LinkedList<>();
        int removedClose = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openPos.add(i);
            } else if (c == ')') {
                if (openPos.isEmpty()) {
                    result.deleteCharAt(i - removedClose);
                    removedClose++; // record how many closing brackets were removed
                } else {
                    openPos.removeLast();
                }
            }
        }
        
        while (!openPos.isEmpty()) {
            result.deleteCharAt(openPos.getLast() - removedClose); // shift left open positions because they are definitely at the end
            // openPos.size() > 0 only if open brackets are at the end
            openPos.removeLast();
        }
        
        return result.toString();
    }
}