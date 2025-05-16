package stallholder;

import java.util.Arrays;

import stallholder.exceptions.InsertHeaderException;

/**
 * Represents an HTTP response.
 */
public class MyHttpResponse {
    /**
     * HTTP version of the response.
     */
    private HttpVersion httpVersion = HttpVersion.HTTP1;
    /**
     * HTTP headers of the response.
     */
    private MyHttpHeaders headers = null;
    /**
     * Server name of the response.
     * defaults to "stallholder-webserver"
     */
    private String server = "stallholder-webserver";
    /**
     * HTTP status code of the response.
     */
    private StatusCode statusCode;
    /**
     * Content type of the response.
     */
    private ContentType ct = null;
    /**
     * Content of the response.
     */
    private byte[] byte_content;
    /**
     * String content of the response.
     */
    private String content;
    /**
     * to track to either use byte_content or content
     */
    public boolean string_content;

    /**
     * Constructor for the response
     * 
     * Assumes plain content type
     * with status code OK
     */
    public MyHttpResponse() {
        this.content = "";
        this.ct = ContentType.PLAIN;
        this.statusCode = StatusCode.OK;
        this.string_content = true;
    }

    /**
     * Constructor for the response
     * @param sc Status code enum
     * @param myContentType enum for the content type
     * @param content String content
     */
    public MyHttpResponse(StatusCode sc, ContentType myContentType, String content) {
        this.content = content;
        this.statusCode = sc;
        this.ct = myContentType;
        this.string_content = true;
    }

    /**
     * Constructor for the response
     * defaults to PLAIN text content type
     * @param sc Enum of the status code
     */
    public MyHttpResponse(StatusCode sc) {
        this.content = "";
        this.statusCode = sc;
        this.string_content = true;
        this.ct = ContentType.PLAIN;
    }

    /**
     * Constructor for the response
     * 
     * Assumes OK status code and plain text content type
     * 
     * @param myContent the string content
     */
    public MyHttpResponse(String myContent) {
        this.content = myContent;
        this.statusCode = StatusCode.OK;
        this.ct = ContentType.PLAIN;
        this.string_content = true;
    }

    /**
     * Constructor for the response
     * @param myContent The string content value 
     * @param myContentType The enum content type
     */
    public MyHttpResponse(String myContent, ContentType myContentType) {
        content = myContent;
        ct = myContentType;
        statusCode = StatusCode.OK;
        string_content = true;
    }

    /**
     * Constructor for the response
     * @param myContent The string content value   
     * @param myContentType The string content type
     * @throws IllegalArgumentException if the content type is not valid
     */
    public MyHttpResponse(String myContent, String myContentType) throws IllegalArgumentException{
        content = myContent;
        ct = ContentType.fromContentTypeString(myContentType);
        statusCode = StatusCode.OK;
        string_content = true;
    }

    /**
     * Sets the HTTP status code of the response.
     * @param sc StatusCode Enum
     */
    public void setCode(StatusCode sc) {
        statusCode = sc;
    }

    /**
     * Sets the HTTP status code of the response.
     * @param sc the integer value of the status code we are trying to set
     */
    public void setCode(int sc) {
        statusCode = StatusCode.fromCode(sc);
    }

    /**
     * Sets the content type of the response.
     * @param myContentType the content type in enum format
     */
    public void setContentType(ContentType myContentType) {
        ct = myContentType;
    }

    /**
     * Sets the content type of the response.
     * @param myContentType the content type in string format
     * @throws IllegalArgumentException if the content type is not valid
     */
    public void setContentType(String myContentType) throws IllegalArgumentException {
        ct = ContentType.fromFileName(myContentType);
    }

    /**
     * Sets the content of the response.
     * @param myContent the content string
     * @param myContentType the content type
     */
    public void setContent(String myContent, ContentType myContentType) {
        content = myContent;
        ct = myContentType;
    }

    /**
     * Sets the content of the response.
     * @param byte_content byte contents to be set as the response content
     */
    public void setContent(byte[] byte_content) {
        this.byte_content = Arrays.copyOf(byte_content, byte_content.length);
        this.string_content = false;
    }

    /**
     * Sets a header which tells the browser to add
     * a value to the clients cookie-jar
     * @param cookie String to be parsed into a cookie-jar
     * @throws InsertHeaderException if there is an error during the intake 
     */
    public void setCookie(String cookie) throws InsertHeaderException {
        this.addToHeaders("Set-Cookie: " + cookie );
    }

    /**
     * A preset for cors
     * @param allowed_origin allowed origins for the response
     * @throws InsertHeaderException if allowed_origin in null 
     */
    public void setCORS(String allowed_origin) throws InsertHeaderException {
        if(allowed_origin != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Access-Control-Allow-Origin: ");
            sb.append(allowed_origin);
            this.addToHeaders(sb.toString());
        } else {
            throw new InsertHeaderException(new RuntimeException("CORS origin is null"));
        }
        this.addToHeaders("Access-Control-Allow-Methods: POST, GET, OPTIONS");
        this.addToHeaders("Access-Control-Allow-Headers: Content-Type");
        this.addToHeaders("Access-Control-Max-Age: 86400");
        this.addToHeaders("Vary: Accept-Encoding, Origin");
        this.addToHeaders("Keep-Alive: timeout=2, max=100");
        this.addToHeaders("Connection: Keep-Alive");
    }

    /**
     * example "Allow: OPTIONS, GET, HEAD, POST"
     * @param keyValuePair the key value pair to be added to the headers
     * @throws InsertHeaderException if there is an error during the intake
     */
    public void addToHeaders(String keyValuePair) throws InsertHeaderException {
        if(headers == null) {
            headers = new MyHttpHeaders();
        }
        headers.insertHeader(keyValuePair);
    }
    
    /**
     * returns the headers of the response
     * @return the response headers in byte format
     */
    public byte[] responseHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(httpVersion.toString() + " " + statusCode.toString() + "\r\n");
        sb.append("Server: " + server + "\r\n");
        if(ct != null) {
            sb.append("Content-Type: " + ct + "\r\n");
        }
        if(this.string_content) {
            sb.append("Content-length: " + content.length() + "\r\n");
        } else {
            sb.append("Content-length: " + byte_content.length + "\r\n");
        }
        sb.append("Connection: close\r\n");
        if(headers != null) {
            sb.append(headers.toString());
        }
        sb.append("\r\n");
        return sb.toString().getBytes();
    }

    /**
     * Returns the content of the response.
     * @return returns the content in byte[] format
     */
    public byte[] responseContent() {
        if(this.string_content) {
            return content.getBytes();
        }
        return this.byte_content;
    }
}
