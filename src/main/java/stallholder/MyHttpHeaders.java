package stallholder;

import java.util.HashMap;

import stallholder.exceptions.InsertHeaderException;

/**
 * Holds http headers
 */
public class MyHttpHeaders extends HashMap<String, String> {
    /**
     * Cookies associated with headers
     */
    private MyHttpCookie cookies = null;

    /**
     * Constructor for the headers
     */
    public MyHttpHeaders() {
        super();
    }

    /**
     * Default constructor
     * @param line line to parse into the header object
     * @throws InsertHeaderException If there is an issue with adding the header to the set
     */
    public void insertHeader(String line) throws InsertHeaderException
    {   
        int first_section_idx = line.indexOf(':');
        if(first_section_idx == -1) {
            throw new InsertHeaderException(new RuntimeException("No colon found"), line);
        }
        String first_section = line.substring(0, first_section_idx);
        if(this.containsKey(first_section)) {
            throw new InsertHeaderException(new RuntimeException("Already have that header, something went wrong."));
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

    /**
     * Returns the cookies associated with the request
     * @return the cookie-jar
     */
    public MyHttpCookie getCookies() {
        return cookies;
    }

    /**
     * To retrieve a header from the headers object
     * 
     * @param header the key of the header we are looking for
     * @return the string value associated with the key
     */
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
