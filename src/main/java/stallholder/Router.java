
package stallholder;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

public class Router {
    private List<RouterPair> requestList = null;
    private RequestHandler not_found_handler = null;

    public Router(RequestHandler default404) throws IOException {
        requestList = new ArrayList<RouterPair>();
        not_found_handler = default404;
    }

    public void AddRoute(String path, RequestHandler handler) {
        MyHttpRequest filter = new MyHttpRequest(HttpVerb.GET, path);
        this.AddRoute(filter, handler);
    }

    public void AddRoute(HttpVerb verb, String path, RequestHandler handler) {
        MyHttpRequest filter = new MyHttpRequest(verb, path);
        this.AddRoute(filter, handler);
    }

    public void AddRoute(MyHttpRequest filter, RequestHandler handler) {
        if(filter.getVerb() == null ||
            filter.getVerb() == HttpVerb.ERROR) {
            throw new IllegalArgumentException("Filter must have a valid HTTP verb");
        }
        requestList.add(new RouterPair(filter, handler));
    }

    private RequestHandler getHandler(MyHttpRequest request) {
        for (RouterPair route : requestList) {
            if (request.Matches(route.getFilter())) {
                return route.getHandler();
            }
        }
        return not_found_handler;
    }

    public MyHttpResponse handle(MyHttpRequest request) throws IOException, ParseException, SQLException {
        return this.getHandler(request).HandleRequest(request);
    }

}
