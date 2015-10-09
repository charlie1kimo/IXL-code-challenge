import java.util.*;

/*
 *
 QUESTION #1:

 Write a Java method that takes an array of "sets" of String objects,
 and determines whether _all_ sets in the array are equivalent.

 Each "set" in the input array is represented as an array of String objects, in
 no particular order, and possibly containing duplicates. Nevertheless, when
 determining whether two of these "sets" are equivalent, you should disregard
 order and duplicates. For example, the sets represented by these arrays are
 all equivalent:

 {"a", "b"}
 {"b", "a"}
 {"a", "b", "a"}

 The signature for your method should be:

 public static boolean allStringSetsIdentical(String[ ][ ] sets)

 Examples of the method in operation:

 allStringSetsIdentical(new String[][] {{"a","b"},{"b","b","a"},{"b","a"}})
 returns true

 allStringSetsIdentical(new String[][] {{"a","b"},{"a"},{"b"}}) returns false
 */

/**
 * Provided two solutions based on the requirements.
 * If we would want to use less space, use 'allStringSetsIdentical2()'
 * Otherwise, use 'allStringSetsIdentical()' cus it's faster.
 */
public class Solution1 {

    /*
     *  Time: O(n*m), n = sets size, m = individual set size.
     *  Space: O(n*m)
     */
    public static boolean allStringSetsIdentical(String[][] sets) {
        if (sets == null || sets.length == 0 || sets.length == 1) return true;

        List<Set<String>> listSets = new ArrayList<Set<String>>();
        for (int i = 0; i < sets.length; i++) {
            Set<String> uniqueSet = new HashSet<String>();
            for(String s : sets[i]) {
                uniqueSet.add(s);
            }
            listSets.add(uniqueSet);
        }

        for (int i = 1; i < listSets.size(); i++) {
            if (!listSets.get(0).equals(listSets.get(i))) return false;
        }
        return true;
    }

    /*
     * Time: O(n* m*log(m) )
     * Space: O(m)
     */
    public static boolean allStringSetsIdentical2(String[][] sets) {
        if (sets == null || sets.length == 0 || sets.length == 1) return true;

        Set<String> unique = new HashSet<String>();
        for (String ele : sets[0]) {
            unique.add(ele);
        }
        for (int i = 1; i < sets.length; i++) {
            Arrays.sort(sets[i]);
            if (sets[i].length == 0 && unique.size() > 0) return false;
            if ((sets[i].length >0 && !unique.contains(sets[i][0])) || (sets[i].length == 1 && unique.size() > 1)) return false;
            for (int j = 1; j < sets[i].length; j++) {
                if (sets[i][j-1].equals(sets[i][j])) continue;

                if (!unique.contains(sets[i][j])) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][][] tests = {
                null,
                {},
                {{}},
                {{"a"}},
                { {}, {} },
                { {}, {"a"} },
                { {"a", "b"}, {"b", "b", "a"}, {"b", "a"} },
                { {"a", "b"}, {"a"}, {"b"}},
                { {"a"}, {"b"}, {"c"} }
        };
        boolean[] results = {
                true,
                true,
                true,
                true,
                true,
                false,
                true,
                false,
                false
        };

        for (int i = 0; i < tests.length; i++) {
            assert allStringSetsIdentical(tests[i]) == results[i] : "Funct 1: Test #"+(i+1)+" Fails.";
            assert allStringSetsIdentical2(tests[i]) == results[i] : "Funct 2: Test #"+(i+1)+" Fails.";
        }
    }
}
