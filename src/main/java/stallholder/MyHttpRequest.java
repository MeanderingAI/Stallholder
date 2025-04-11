package stallholder;



import java.text.ParseException;
import java.util.Map.Entry;

/**
 * Represents an HTTP request.
 */
public class MyHttpRequest {
    private MyHttpHeaders headers;
    private HttpVerb verb = null;
    private String requestURI = null;
    private HttpVersion httpVersion = null;
    private String body = null;

    /**
     * Default constructor
     */
    public MyHttpRequest() {
        this.headers = new MyHttpHeaders();
        this.verb = HttpVerb.ERROR;
        this.requestURI = "<unset>";
        this.body = "";
        this.httpVersion = HttpVersion.HTTP1;
    }

    /**
     * Constructor where you can specify the verb and requestURI
     * 
     * @param verb
     * @param requestURI
     */
    public MyHttpRequest(HttpVerb verb, String requestURI) {
        this.verb = verb;
        this.requestURI = requestURI;
        this.body = "";
        this.httpVersion = HttpVersion.HTTP1;
    }
    
    /**
     * Useful when specifying routes
     * 
     * @param toMatch HttpRequest to match against
     * @return true if the request matches
     */
    public boolean Matches(MyHttpRequest toMatch) {
        boolean ends_in_wild_card = toMatch.getRequestURL().endsWith("/*");
        if(ends_in_wild_card) {
            String new_url = Utils.removeWildCard(toMatch.getRequestURL());
            String clipped_url = Utils.removeLastTokenInPath(getRequestURL());
            return  clipped_url.equals(new_url)
                    && getVerb().equals(toMatch.getVerb());
        } else {
            return getRequestURL().equals(toMatch.getRequestURL())
                    && getVerb().equals(toMatch.getVerb());
        }
    }

    public String RemoteAddress() {
        return this.headers.getHeader("x-forwarded-for");
    }

    /**
     * Used when parsing request into object
     * 
     * @param line line to parse
     * @throws ParseException if line is not formatted correctly
     */
    public void parseFirstLine(String line) throws ParseException {
        int first_space = line.indexOf(' ');
        int second_space = line.indexOf(' ', first_space + 1);
        
        String third_section = line.substring(second_space + 1);
        if(third_section.contains(" ")) {
            throw new ParseException(line, second_space);
        }
        
        String first_section = line.substring(0, first_space);
        String second_section = line.substring(first_space + 1, second_space);
        this.verb =  HttpVerb.getVerb(first_section);

        this.requestURI = second_section;
        this.httpVersion = HttpVersion.getVersion(third_section);
    }

    /**
     * Inserts a header into the MyHttpHeaders object
     * 
     * @param line
     * @throws ParseException
     * @throws IllegalArgumentException
     */
    public void insertHeader(String line) throws ParseException, IllegalArgumentException {
        this.headers.insertHeader(line);
    }

    /**
     * @return requests http verb
     */
    public HttpVerb getVerb() {
        return verb;
    }

    /**
     * @return the headers associated with the request
     */
    public MyHttpHeaders getHeaders() { return headers; }

    /**
     * @param newRequestURI new request URI
     */
    public void setRequestURL(String newRequestURI) {
        requestURI = newRequestURI;
    }

    /**
     * 
     * @return request URI
     */
    public String getRequestURL() {
        return requestURI;
    }

    /**
     * Adds a line to the body of the request
     * @param line
     */
    public void addToBody(String line) {
        if(this.body == null) {
            this.body = "";
        }
        this.body = this.body + line;
    }

    /**
     * Checks if the request has a body
     * @return true if there is a body
     */
    public boolean hasBody() {
        return this.body != null;
    }

    /**
     * Returns the body of the request
     * @return teh body string
     */
    public String getBody() {
        return body;
    }

    /**
     * @return http version of the request
     */
    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    /**
     * String representation of the request
     * @return String of the request
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(verb + " " + requestURI + " " + httpVersion + "\n");
        if(headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * Compares two requests for equality
     * 
     * @param toComp
     * @return true if the requests match URL and verb
     */
    public boolean IsEquals(MyHttpRequest toComp) {
        return this.getVerb() == toComp.getVerb()
                && this.getRequestURL().equals(toComp.getRequestURL());
    }

}
