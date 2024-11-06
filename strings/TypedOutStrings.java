public class TypedOutStrings {

    public static void main(String[] args) {
        System.out.println(stringsEqualOptimalRef("ab#z", "az#z")); // true
        System.out.println(stringsEqualOptimalRef("abc#d", "ad")); // false
        System.out.println(stringsEqualOptimalRef("ab##", "a#c#")); // true
        System.out.println(stringsEqualOptimalRef("AB#z", "AZ#z")); // true
        System.out.println(stringsEqualOptimalRef("bxj##tw", "bxo#j##tw")); // true
        System.out.println(stringsEqualOptimalRef("xywrrmp", "xywrrmu#p")); // true
        System.out.println(stringsEqualOptimalRef("bxj##tw", "bxj###tw")); // false
        System.out.println(stringsEqualOptimalRef("nzp#o#g", "b#nzp#o#g")); // false
    }

    /*
     * Brute Force: apply # operations to the last character and compare strings at
     * the end
     * 
     * t: O(2a + b) = O(a + b)
     * s: O(a + b)
     */
    public static boolean stringsEqual(String s1, String s2) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();

        // O(a)
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == '#') {
                int deletionPos = str1.length() - 1;
                if (deletionPos >= 0) {
                    str1.deleteCharAt(deletionPos);
                }
            } else {
                str1.append(s1.charAt(i));
            }
        }
        // O(b)
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == '#') {
                int deletionPos = str2.length() - 1;
                if (deletionPos >= 0) {
                    str2.deleteCharAt(deletionPos);
                }
            } else {
                str2.append(s2.charAt(i));
            }
        }

        // O(a) or O(b)
        return str1.toString().equals(str2.toString());
    }

    /*
     * Two Pointers: Start from the end of each string and compare only chars
     * by skipping # sequences via the main loop
     * 
     * t: O(a + b)
     * s: O(1)
     */
    public static boolean stringsEqualOptimal(String s1, String s2) {
        int currentBackspace = 0;
        int currentBackspace2 = 0;
        for (int i1 = s1.length() - 1, i2 = s2.length() - 1; i1 >= 0 || i2 >= 0;) {
            boolean compare = true;
            if (i1 >= 0) {
                if (s1.charAt(i1) == '#') {
                    currentBackspace++;
                    i1--;
                    compare = false;
                } else {
                    if (currentBackspace != 0) {
                        i1--;
                        currentBackspace--;
                        compare = false;
                    }
                }
            }
            if (i2 >= 0) {
                if (s2.charAt(i2) == '#') {
                    i2--;
                    currentBackspace2++;
                    compare = false;
                } else {
                    if (currentBackspace2 != 0) {
                        i2--;
                        currentBackspace2--;
                        compare = false;
                    }
                }
            }
            if (compare == false) {
                continue;
            }
            if (i2 >= 0 && i1 >= 0 && s1.charAt(i1) != s2.charAt(i2) || (i1 >= 0 && i2 < 0) || (i1 < 0 && i2 >= 0)) {
                return false;
            } else {
                i1--;
                i2--;
            }
        }
        return true;
    }

    /*
     * Refactored Two Pointers: Start from the end of each string and compare only
     * chars by skipping # sequences in nested while loops
     * 
     * Drawbacks: requires index zero checks everywhere
     * t: O(a + b)
     * s: O(1)
     */
    public static boolean stringsEqualOptimalRef(String s1, String s2) {
        for (int i1 = s1.length() - 1, i2 = s2.length() - 1; i1 >= 0 || i2 >= 0;) {
            if ((i1 >= 0 && s1.charAt(i1) == '#') || (i2 >= 0 && s2.charAt(i2) == '#')) { // not ready to compare
                if (i1 >= 0 && s1.charAt(i1) == '#') { // skip chars per #
                    int skipChars = 2;
                    while (skipChars > 0) {
                        skipChars--;
                        i1--;
                        if (i1 >= 0 && s1.charAt(i1) == '#') { // account for new #
                            skipChars+=2;
                        }
                    }
                }

                if ( i2 >= 0 && s2.charAt(i2) == '#') {
                    int skipChars = 2;
                    while (skipChars > 0) {
                        skipChars--;
                        i2--;
                        if (i2 >= 0 && s2.charAt(i2) == '#') {
                            skipChars+=2;
                        }
                    }
                }
            } else {
                if ((i1 >= 0 && i2 >= 0 && s1.charAt(i1) != s2.charAt(i2)) || (i1 >= 0 && i2 < 0) || (i1 < 0 && i2 >= 0) ) {
                    return false;
                } else {
                    i1--;
                    i2--;
                }
            }
        }
        return true;
    }
}
