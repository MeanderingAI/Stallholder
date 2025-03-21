package stallholder;

public class Utils {
    public static String getLastTokenInPath(String str) throws RuntimeException {
        int start_of_last_token = str.lastIndexOf('/');
        if (start_of_last_token == -1) {
            throw new RuntimeException("Expected string to contain a slash.");
        }
        return str.substring(start_of_last_token+1);
    }

    public static String removeLastTokenInPath(String str) throws RuntimeException {
        int start_of_last_token = str.lastIndexOf('/');
        if (start_of_last_token == -1) {
            throw new RuntimeException("Expected string to contain a slash.");
        }
        return str.substring(0, start_of_last_token);
    }

    public static boolean checkWildCardEnd(String str) {return str.endsWith("/*");}

    public static String removeWildCard(String str) {
        return str.substring(0, str.length()-2);
    }

}
