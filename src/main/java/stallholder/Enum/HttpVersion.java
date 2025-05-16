package stallholder.Enum;

/**
 * Enum representing the HTTP version.
 */
public enum HttpVersion {
    /**
     * HTTP/1.1 version
     */
    HTTP1("HTTP/1.1"),
    /**
     * HTTP/2.0 version
     */
    UNIMPLEMENTED("ERROR");

    private final String version;

    /**
     * Constructor for the HttpVersion enum.
     * @param version String representation of the HTTP version
     */
    HttpVersion(String version) {
        this.version = version;
    }

    /**
     * @return the string representation of the HTTP version.
     */
    public String toString() {
        return this.version;
    }

    /**
     * Returns the HttpVersion enum corresponding to the given version string.
     * This is used for parsing the HTTP version in HTTP requests.
     * @param version string representation of the HTTP version
     * @return enum representation of the HTTP version
     */
    public static HttpVersion getVersion(String version) {
        switch (version) {
            case "HTTP/1.1":
                return HTTP1;
        
            default:
                return UNIMPLEMENTED;
        }
    }
}
