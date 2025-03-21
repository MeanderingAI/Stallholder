package stallholder;

/**
 * Enum representing the HTTP verbs.
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Methods
 */
public enum HttpVerb {
    GET("GET"), HEAD("HEAD"), POST("POST"), 
    PUT("PUT"), DELETE("DELETE"), CONNECT("CONNECT"), 
    OPTIONS("OPTIONS"), TRACE("TRACE"), PATCH("PATCH"),
    ERROR("ERROR");

    private final String message;

    HttpVerb(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }

    static HttpVerb getVerb(String verb) {
        switch (verb) {
            case "GET":
                return GET;
            case "HEAD":
                return HEAD;
            case "POST":
                return POST;
            case "PUT":
                return PUT;
            case "DELETE":
                return DELETE;
            case "CONNECT":
                return CONNECT;
            case "OPTIONS":
                return OPTIONS;
            case "TRACE":
                return TRACE;
            case "PATCH":
                return PATCH;
            default:
                return ERROR;
        }
    }
}

