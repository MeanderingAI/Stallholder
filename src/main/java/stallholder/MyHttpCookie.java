package stallholder;

import java.util.HashMap;

public class MyHttpCookie extends HashMap<String, String> { 
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
