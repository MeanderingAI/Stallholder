package stallholder.Handlers;

import stallholder.ContentType;
import stallholder.MyHttpRequest;
import stallholder.MyHttpResponse;
import stallholder.RequestHandler;
import stallholder.StatusCode;

/**
 * Represents a 404 Not Found request handler.
 */
public class URINotFoundHandler extends RequestHandler {
    private static URINotFoundHandler instance;
    private String stringedResponse;
    
    /**
     * Constructor for the 404 Not Found request handler.
     */
    private URINotFoundHandler() {
        this.stringedResponse = "404 Page not found";
    }
    
    /**
     * Handles the request and returns a 404 Not Found response.
     * @param request the HTTP request
     * @return a MyHttpResponse object with a 404 Not Found status code
     */
    @Override
    public MyHttpResponse HandleRequest(MyHttpRequest request) {
        MyHttpResponse response = new MyHttpResponse(StatusCode.NOT_FOUND);
        response.setContent(this.stringedResponse, ContentType.PLAIN);
        return response;
    }

    /**
     * Singleton instance of the URINotFoundHandler.
     * @return the singleton instance of URINotFoundHandler
     */
    public static URINotFoundHandler getInstance() {
        if(instance == null) {
            instance = new URINotFoundHandler();
        }
        return instance;
    }
}
