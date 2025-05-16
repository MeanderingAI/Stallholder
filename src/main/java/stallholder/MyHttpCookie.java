package stallholder;

import java.util.HashMap;

/**
 * Represents an HTTP cookie.
 */
public class MyHttpCookie extends HashMap<String, String> { 

    /**
     * Constructor for the cookie
     */
    public MyHttpCookie() {
        super();
    }
    
    /**
     * Constructor for the cookie
     * @param cookie string representation of the cookie
     */
    public void setCookie(String cookie) {
        String[] parts = cookie.split(";");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                this.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
    }
}
