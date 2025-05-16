package stallholder.Handlers;

import stallholder.MyHttpResponse;
import stallholder.Enum.StatusCode;

/**
 * Represents a 404 Not Found HTTP response.
 */
public class URINotFound extends MyHttpResponse {
    /**
     * Constructor for the 404 Not Found response.
     */
    public URINotFound() {
        super(StatusCode.NOT_FOUND);
    }
}
