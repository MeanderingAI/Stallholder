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
        content = "";
        ct = ContentType.HTML;
        statusCodes = StatusCodes.OK;
        string_content = true;
    }

    public MyHttpResponse(StatusCodes sc) {
        content = "";
        statusCodes = sc;
        string_content = true;
    }

    public MyHttpResponse(String myContent) {
        content = myContent;
        statusCodes = StatusCodes.OK;
        ct = ContentType.HTML;
        string_content = true;
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
