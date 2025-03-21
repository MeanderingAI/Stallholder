package stallholder;

/**
 * Enum representing the HTTP version.
 */
public enum HttpVersion {
    HTTP1("HTTP/1.1"),
    UNIMPLEMENTED("ERROR");

    private final String version;

    HttpVersion(String version) {
        this.version = version;
    }

    public String toString() {
        return this.version;
    }

    static HttpVersion getVersion(String version) {
        switch (version) {
            case "HTTP/1.1":
                return HTTP1;
        
            default:
                return UNIMPLEMENTED;
        }
    }
}
