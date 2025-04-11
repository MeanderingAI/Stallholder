package stallholder;

import java.text.ParseException;
import java.util.Arrays;

/**
 * Represents an HTTP response.
 */
public class MyHttpResponse {
    private HttpVersion httpVersion = HttpVersion.HTTP1;
    private MyHttpHeaders headers = null;
    private String server = "stallholder-webserver";
    private StatusCodes statusCodes;
    private ContentType ct = null;
    private byte[] byte_content;
    private String content;
    public boolean string_content;

    public MyHttpResponse() {
        this.content = "";
        this.ct = ContentType.HTML;
        this.statusCodes = StatusCodes.OK;
        this.string_content = true;
    }

    public MyHttpResponse(StatusCodes sc, ContentType myContentType, String content) {
        this.content = content;
        this.statusCodes = sc;
        this.ct = myContentType;
        this.string_content = true;
    }

    public MyHttpResponse(StatusCodes sc) {
        this.content = "";
        this.statusCodes = sc;
        this.string_content = true;
    }

    public MyHttpResponse(String myContent) {
        this.content = myContent;
        this.statusCodes = StatusCodes.OK;
        this.ct = ContentType.HTML;
        this.string_content = true;
    }

    public MyHttpResponse(String myContent, ContentType myContentType) {
        content = myContent;
        ct = myContentType;
        statusCodes = StatusCodes.OK;
        string_content = true;
    }

    public void SetCode(StatusCodes sc) {
        statusCodes = sc;
    }

    public void SetContentType(ContentType myContentType) {
        ct = myContentType;
    }

    public void SetContent(String myContent, ContentType myContentType) {
        content = myContent;
        ct = myContentType;
    }

    public void SetContent(byte[] byte_content) {
        this.byte_content = Arrays.copyOf(byte_content, byte_content.length);
        this.string_content = false;
    }

    public void setCORS(String allowed_origin) throws ParseException {

        if(allowed_origin != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Access-Control-Allow-Origin: ");
            sb.append(allowed_origin);
            this.addToHeaders(sb.toString());
        } else {
            throw new ParseException("CORS origin is null", 0);
        }
        this.addToHeaders("Access-Control-Allow-Methods: POST, GET, OPTIONS");
        this.addToHeaders("Access-Control-Allow-Headers: Content-Type");
        this.addToHeaders("Access-Control-Max-Age: 86400");
        this.addToHeaders("Vary: Accept-Encoding, Origin");
        this.addToHeaders("Keep-Alive: timeout=2, max=100");
        this.addToHeaders("Connection: Keep-Alive");
    }

    /*
    example "Allow: OPTIONS, GET, HEAD, POST"
     */
    public void addToHeaders(String keyValuePair) throws ParseException {
        if(headers == null) {
            headers = new MyHttpHeaders();
        }
        headers.insertHeader(keyValuePair);
    }
    
    public byte[] responseHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(httpVersion.toString() + " " + statusCodes.toString() + "\r\n");
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

    public byte[] responseContent() {
        if(this.string_content) {
            return content.getBytes();
        }
        return this.byte_content;
    }
}
