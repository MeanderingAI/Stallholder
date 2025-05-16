package stallholder.Enum;

/**
 * Enum representing the HTTP verbs.
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Methods
 */
public enum HttpVerb {
    /**
     * GET enum value
     */
    GET("GET"), 
    /**
     * HEAD enum value
     */
    HEAD("HEAD"), 
    /**
     * POST enum value
     */
    POST("POST"), 
    /**
     * PUT enum value
     */
    PUT("PUT"), 
    /**
     * DELETE enum value
     */
    DELETE("DELETE"), 
    /**
     * CONNECT enum value
     */
    CONNECT("CONNECT"), 
    /**
     * OPTIONS enum value
     */
    OPTIONS("OPTIONS"), 
    /**
     * TRACE enum value
     */
    TRACE("TRACE"), 
    /**
     * PATCH enum value
     */
    PATCH("PATCH"),
    /**
     * ERROR enum value
     */
    ERROR("ERROR");

    /**
     * String representation of the HTTP verb.
     */
    private final String message;

    /**
     * Constructor for the HttpVerb enum.
     * @param message string message
     */
    HttpVerb(String message) {
        this.message = message;
    }

    /**
     * @return the string representation of the HTTP verb.
     */
    public String toString() {
        return message;
    }

    /**
     * Returns the HttpVerb enum corresponding to the given string.
     * @param verb the string representation of the HTTP verb
     * @return enum representation of the verb
     */
    public static HttpVerb getVerb(String verb) {
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

