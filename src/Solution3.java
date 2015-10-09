/*
QUESTION #3:

        Write a Java method with the following method signature that takes a String and
        returns a String formatted so that it satisfies the requirements below.  It may
        need to insert newlines and/or delete spaces.

        Method Signature:
public static String wrapText(String text, int maxCharsPerLine)

        Definitions and Assumptions:
        A word is a nonempty sequence of characters that contains no spaces and no newlines.
        Lines in the return String are separated by the newline character, '\n'.
        Words on each line are separated by spaces. Assume that the String argument does
        not contain any whitespace characters other than spaces and newlines.

        Requirements:
        1. Newlines in the String argument are preserved.
        2. Words in the return String are separated by either a single space or by one or
        more newlines.
        3. Lines in the return String do not start or end with any spaces.
        4. When constructing the return String from the String argument, each word in the
        String argument with at most maxCharsPerLine characters should not be broken up.
        Each word in the String argument with more than maxCharsPerLine characters should
        be broken up so that all of the other requirements are satisfied.
        5. The String argument may contain lines longer than maxCharsPerLine. Newlines
        should be added so that each line in the return String has at most maxCharsPerLine
        characters. To determine where newlines should be added, try to fit as many words
        as possible on a line (while keeping line length at most maxCharsPerLine and
        satisfying the other requirements) before starting a new line.
*/
public class Solution3 {

    /**
     * @params:
     *      String text: input text string to wrap
     *      int maxCharsPerLine: maximum line length.
     * @returns:
     *      null -> if there's error or text is not able to be wrapped.
     *      (String) wrapped string otherwise.
     */
    public static String wrapText(String text, int maxCharsPerLine) {
        if (text == null || maxCharsPerLine == 0) return null;

        StringBuilder ret = new StringBuilder();
        int lineCharsCount = 0;
        StringBuilder wordSB = new StringBuilder();

        char[] textArray = text.toCharArray();
        for (char currChar : textArray) {
            // separator ' ' & '\n'
            if (currChar == ' ' || currChar == '\n') {
                String word = wordSB.toString();

                // a huge string without break or maxCharsPerLine is too small;
                if (word.length() > maxCharsPerLine) return null;

                // +1 for white space.
                if (lineCharsCount > 0 && lineCharsCount + word.length() + 1 > maxCharsPerLine) {
                    ret.append("\n");
                    lineCharsCount = 0;
                }

                if (lineCharsCount > 0) {
                    ret.append(" ");
                    lineCharsCount++;
                }
                ret.append(word);
                lineCharsCount += word.length();
                wordSB = new StringBuilder();

                if (currChar == '\n') {
                    ret.append(currChar);
                    lineCharsCount = 0;
                }

            }
            else {
                wordSB.append(currChar);
            }
        }

        // process last word
        String word = wordSB.toString();

        // a huge string without break or maxCharsPerLine is too small;
        if (word.length() > maxCharsPerLine) return null;

        if (word.length() > 0) ret.append(word);
        return ret.toString();
    }

    public static void testWrapTextOutput(int testCase, String output, int maxCharsPerLine) {
        if (output.isEmpty()) return;
        String[] lines = output.split("\n");
        for (String line : lines) {
            assert line.length() <= maxCharsPerLine
                    : "ERROR @ test#" +  testCase + ";Line: " + line + "; exceed " + maxCharsPerLine;
        }
    }

    public static void main(String[] args) {
        String[] tests = {
                null,
                "",
                "",
                "this is.\n",
                "\n\n\n",
                "this is a string.\nAnd that is not a string.\n",
                "An assertion is a statement in the JavaTM programming language that enables you to test your assumptions about your program. For example, if you write a method that calculates the speed of a particle, you might assert that the calculated speed is less than the speed of light.\n"
                        + "\n"
                        + "Each assertion contains a boolean expression that you believe will be true when the assertion executes. If it is not true, the system will throw an error. By verifying that the boolean expression is indeed true, the assertion confirms your assumptions about the behavior of your program, increasing your confidence that the program is free of errors."
        };

        int[] testArgs = {
                1,
                0,
                1,
                3,
                5,
                10,
                50,
        };

        boolean[] isResultNull = {
            true,
            true,
            false,
            true,
            false,
            false,
            false
        };

        for (int i = 0; i < tests.length; i++) {
            String output = wrapText(tests[i], testArgs[i]);
            if (isResultNull[i]) {
                assert output == null
                        : "ERROR @ test#" + i + ": Output should be null.";
                continue;
            }
            testWrapTextOutput(i, output, testArgs[i]);
        }
    }
}
