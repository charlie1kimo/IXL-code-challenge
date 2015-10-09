import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
QUESTION #2:

//
// The following Java code is responsible for creating an HTML "SELECT"
// list of U.S. states, allowing a user to specify his or her state. This might
// be used, for instance, on a credit card transaction screen.
//
// Please rewrite this code to be "better". Submit your replacement code, and
// please also submit a few brief comments explaining why you think your code
// is better than the sample.
//
// (For brevity, this sample works for only 5 states. The real version would
// need to work for all 50 states. But it is fine if your rewrite shows only
// the 5 states here.)
//

public class StateUtils {

    //
    // Generates an HTML select list that can be used to select a specific
    // U.S. state.
    //
    public static String createStateSelectList()
    {
        return
                "<select name=\"state\">\n"
                        + "<option value=\"Alabama\">Alabama</option>\n"
                        + "<option value=\"Alaska\">Alaska</option>\n"
                        + "<option value=\"Arizona\">Arizona</option>\n"
                        + "<option value=\"Arkansas\">Arkansas</option>\n"
                        + "<option value=\"California\">California</option>\n"
                        // more states here
                        + "</select>\n"
                ;
    }

    //
    // Parses the state from an HTML form submission, converting it to
    // the two-letter abbreviation.
    //
    public static String parseSelectedState(String s)
    {
        if (s.equals("Alabama"))     { return "AL"; }
        if (s.equals("Alaska"))      { return "AK"; }
        if (s.equals("Arizona"))     { return "AZ"; }
        if (s.equals("Arkansas"))    { return "AR"; }
        if (s.equals("California"))  { return "CA"; }
        // more states here
    }

    //
    // Displays the full name of the state specified by the two-letter code.
    //
    public static String displayStateFullName(String abbr) {
        {
            if (abbr.equals("AL")) { return "Alabama";    }
            if (abbr.equals("AK")) { return "Alaska";     }
            if (abbr.equals("AZ")) { return "Arizona";    }
            if (abbr.equals("AR")) { return "Arkansas";   }
            if (abbr.equals("CA")) { return "California"; }
            // more states here
        }

    }
*/
/**
 *  Idea:
 *      Create a STATES mapping. (Array is used here, but ultimately I would factor out this to another file for
 *      storing constants.) Iterating through the map for each states to build 'select string' and 'get abbreviation'
 *
 *  Improvements:
 *      Reduce the duplicated codes for checking each of the states.
 *      Easier to add / remove states.
 *      Adding / removing states only needs to change 'STATES' variable.
 */
public class StateUtils {
    private static final String[][] STATES = {
            {"Alabama", "AL"},
            {"Alaska", "AK"},
            {"Arizona", "AZ"},
            {"Arkansas", "AR"},
            {"California", "CA"}
            // more states
    };

    private static Map<String, String> buildStatesMap(boolean isReveresed) {
        Map<String, String> map = new HashMap<String, String>();
        for (String[] state : STATES) {
            if (isReveresed) {
                map.put(state[1], state[0]);
            }
            else {
                map.put(state[0], state[1]);
            }
        }
        return map;
    }

    //
    // Generates an HTML select list that can be used to select a specific
    // U.S. state.
    //
    public static String createStateSelectList() {
        Map<String, String> map = buildStatesMap(false);
        StringBuilder sb = new StringBuilder();
        sb.append("<select name=\"state\">\n");
        for (String state : map.keySet()) {
            sb.append("<option value=\"");
            sb.append(state);
            sb.append("\">");
            sb.append(state);
            sb.append("</option>\n");
        }
        sb.append("</select>\n");

        return sb.toString();
    }

    //
    // Parses the state from an HTML form submission, converting it to
    // the two-letter abbreviation.
    //
    public static String parseSelectedState(String s) {
        Map<String, String> map = buildStatesMap(false);
        return (map.containsKey(s)) ? map.get(s) : null;
    }

    //
    // Displays the full name of the state specified by the two-letter code.
    //
    public static String displayStateFullName(String abbr) {
        Map<String, String> map = buildStatesMap(true);
        return (map.containsKey(abbr)) ? map.get(abbr) : null;
    }
}