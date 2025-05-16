
package stallholder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import stallholder.Enum.HttpVerb;

/**
 *  A router pair of the handler and the filters
 *  which lead to the handler
 */
class RouterPair {
    private MyHttpRequest filter;
    private RequestHandler handler;

    public RouterPair(MyHttpRequest filter, RequestHandler requestHandler) {
        this.filter = filter;
        this.handler = requestHandler;
    }

    public MyHttpRequest getFilter() {
        return filter;
    }

    public RequestHandler getHandler() {
        return handler;
    }
}

/**
 * Router class
 */
public class Router {
    private List<RouterPair> requestList = null;
    private RequestHandler not_found_handler = null;

    /**
     * Constructor for the router
     * @param default404 a default handler for not found response
     */
    public Router(RequestHandler default404) {
        requestList = new ArrayList<RouterPair>();
        not_found_handler = default404;
    }

    /**
     * Adds a route to the router
     * 
     * @param path path of the resource to match against, defaults to GET verbs
     * @param handler the request handler to use
     */
    public void addRoute(String path, RequestHandler handler) {
        MyHttpRequest filter = new MyHttpRequest(HttpVerb.GET, path);
        this.addRoute(filter, handler);
    }

    /**
     * Adds a route to the router
     * 
     * @param verb HTTP verb to match against
     * @param path the path of the resource to match against
     * @param handler the handler
     */
    public void addRoute(HttpVerb verb, String path, RequestHandler handler) {
        MyHttpRequest filter = new MyHttpRequest(verb, path);
        this.addRoute(filter, handler);
    }

    /**
     * Adds a route to the router
     * 
     * @param filter the request to filter against
     * @param handler the request handler
     */
    public void addRoute(MyHttpRequest filter, RequestHandler handler) {
        if(filter.getVerb() == null ||
            filter.getVerb() == HttpVerb.ERROR) {
            throw new IllegalArgumentException("Filter must have a valid HTTP verb");
        }
        requestList.add(new RouterPair(filter, handler));
    }

    /**
     * Returns the request handler for the given request
     * 
     * @param request the request to get the handler of
     * @return the request handler
     */
    private RequestHandler getHandler(MyHttpRequest request) {
        for (RouterPair route : requestList) {
            if (request.Matches(route.getFilter())) {
                return route.getHandler();
            }
        }
        return not_found_handler;
    }

    /**
     * Handles the request
     * 
     * @param request the request to handle
     * @return the MyHttpResponse object
     * @throws IOException if there is an exception while processing the request
     */
    public MyHttpResponse handle(MyHttpRequest request) throws IOException {
        return this.getHandler(request).HandleRequest(request);
    }

}
