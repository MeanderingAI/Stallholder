package stallholder;

import stallholder.exceptions.HandleRequestException;

/**
 * Represents a request handler.
 * 
 * An implementation of this class is used to handle requests and return responses.
 */
public abstract class RequestHandler {
    /**
     * Default constructor for the RequestHandler class.
     */
    public RequestHandler() {}
    
    /**
     * signature of a function which handles the request
     * 
     * @param request a MyHttpRequest object
     * @return a MyHttpResponse object
     * @throws HandleRequestException if there is an exception while processing the request
     */
    public abstract MyHttpResponse HandleRequest(MyHttpRequest request) throws HandleRequestException;
}
