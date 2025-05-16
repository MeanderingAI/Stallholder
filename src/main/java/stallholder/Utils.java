package stallholder;

/**
 * Utility class for string manipulation.
 */
public class Utils {
    /**
     * Private constructor to prevent instantiation.
     */
    private Utils() {
    
    }

    /**
     * Returns the last token in a path.
     * @param str the string to parse
     * @return the last token in the path
     * @throws RuntimeException if the strings do not contain a '/'
     */
    public static String getLastTokenInPath(String str) throws RuntimeException {
        int start_of_last_token = str.lastIndexOf('/');
        if (start_of_last_token == -1) {
            throw new RuntimeException("Expected string to contain a '/'.");
        }
        return str.substring(start_of_last_token+1);
    }

    /**
     * Returns the string without the last token in a path.
     * @param str the string to parse
     * @return the string without the last token in the path
     * @throws RuntimeException if the strings do not contain a '/'
     */
    public static String removeLastTokenInPath(String str) throws RuntimeException {
        int start_of_last_token = str.lastIndexOf('/');
        if (start_of_last_token == -1) {
            throw new RuntimeException("Expected string to contain a '/'.");
        }
        return str.substring(0, start_of_last_token);
    }

    /**
     * Checks if the string ends with a wildcard.
     * @param str the string to check
     * @return true if the string ends with "/*", false otherwise
     */
    public static boolean checkWildCardEnd(String str) {return str.endsWith("/*");}

    /**
     * Removes the wildcard from the end of a string.
     * @param str the string to modify
     * @return the string without the wildcard
     */
    public static String removeWildCard(String str) {
        return str.substring(0, str.length()-2);
    }
}
