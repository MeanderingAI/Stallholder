package stallholder;

import java.text.ParseException;
import java.util.HashMap;

import stallholder.exceptions.InsertHeaderException;

/**
 * Holds http headers
 */
public class MyHttpHeaders extends HashMap<String, String> {
    private MyHttpCookie cookies = null;
    /**
     * @param line line to parse into the header object
     * @throws ParseException
     * @throws IllegalArgumentException
     */
    public void insertHeader(String line) throws ParseException, IllegalArgumentException
    {   
        int first_section_idx = line.indexOf(':');
        if(first_section_idx == -1) {
            throw new InsertHeaderException(new Exception("No colon found"), line);
        }
        String first_section = line.substring(0, first_section_idx);
        if(this.containsKey(first_section)) {
            throw new IllegalArgumentException("Already have that header, something went wrong.");
        }
        String second_section = line.substring(first_section_idx + 1);
        String key = first_section.toLowerCase();
        if(key.equals("cookie")) {
            cookies = new MyHttpCookie();
            cookies.setCookie(second_section);
        } else {
            this.put(key, second_section);
        }
    }

    public MyHttpCookie getCookies() {
        return cookies;
    }

    public String getHeader(String header) {
        return this.get(header.toLowerCase());
    }

    /**
     * String representation of the headers
     * @return String of headers
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Entry<String, String> node : this.entrySet()) {
            sb.append(node.getKey());
            sb.append(": ");
            sb.append(node.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }
}
