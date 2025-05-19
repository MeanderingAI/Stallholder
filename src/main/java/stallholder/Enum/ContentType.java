package stallholder.Enum;

import java.util.Locale;

/**
 * Enum representing the content type of a file.
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Headers/Content-Type
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/MIME_types
 */
public enum ContentType {
    /**
     * HTML content type
     */
    HTML("text/html"), 
    /**
     * JavaScript content type
     */
    JAVASCRIPT("text/javascript"), 
    /**
     * CSS content type
     */
    CSS("text/css"),
    /**
     * ICO content type
     */
    ICO("image/x-con"), 
    /**
     * png data
     */
    PNG("image/png"), 
    /**
     * jpg data
     */
    JPG("image/jpg"), 
    /**
     * gif data
     */
    GIF("image/gif"),
    /**
     * text plain data 
     */
    PLAIN("text/plain"), 
    /**
     * multipart form data
     */
    FORMDATA("multipart/form-data"), 
    /**
     * URL encoded form data
     */
    URLFORMDATA("application/x-www-form-urlencoded"),
    /**
     * Json content type
     */
    JSON("application/json"), 
    /**
     * octet-streams
     */
    OCTET_STREAM("application/octet-stream"),
    /**
     * Unimplemented content type
     */
    UNIMPLEMENTED("ERROR");

    private final String type;

    /**
     * Constructor for the ContentType enum.
     * @param type String of the content type
     */
    ContentType(String type) {
        this.type = type;
    }

    /**
     * @return the string representation of the content type.
     */
    public String toString() {
        return type;
    }

    /**
     * Returns the ContentType enum corresponding to the given file name.
     *
     * @param fileName The name of the file.
     * @return The ContentType enum corresponding to the file name.
     * @throws IllegalArgumentException if the type string does not have a corresponding enum value
     */
    public static ContentType fromFileName(String fileName) throws IllegalArgumentException{
        fileName = fileName.toLowerCase(Locale.ROOT);
        if(fileName.endsWith(".html")) {
            return HTML;
        }
        if(fileName.endsWith(".ico")) {
            return ICO;
        }
        if(fileName.endsWith(".png") || fileName.endsWith(".apng")) {
            return PNG;
        }
        if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return JPG;
        }
        if(fileName.endsWith(".css")) {
            return CSS;
        }
        if(fileName.endsWith(".mjs") || fileName.endsWith(".js")) {
            return JAVASCRIPT;
        }
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        throw new IllegalArgumentException("Invalid file type: " + extension);
    }

    /**
     * Returns the ContentType enum corresponding to the given type string.
     * This is used for parsing the Content-Type header in HTTP requests.
     * 
     * @param type string which 
     * @return ContentType enum corresponding to the type string
     * @throws IllegalArgumentException if the type string does not have a corresponding enum value
     */
    public static ContentType getContentType(String type) throws IllegalArgumentException {
        switch (type) {
            case "text/html":
                return HTML;
            case "image/x-con":
                return ICO;
            case "image/jpg":
                return JPG;
            case "image/png":
                return PNG;
            case "application/octet-stream":
                return OCTET_STREAM;
            case "application/json":
                return JSON;
            default:
                throw new IllegalArgumentException("Invalid content type: " + type);
        }
    }

    /**
     * Returns the ContentType enum corresponding to the given type string.
     * @param type string content type
     * @return ContentType enum corresponding to the type string
     * @throws IllegalArgumentException if the type string does not have a corresponding enum value
     */
    @Deprecated
    public static ContentType fromContentTypeString(String type) throws IllegalArgumentException{
        switch (type) {
            case "text/html":
                return HTML;
            case "image/x-con":
                return ICO;
            case "image/jpg":
                return JPG;
            case "image/png":
                return PNG;
            case "text/css":
                return CSS;
            case "text/plain":
                return PLAIN;
            case "application/json":
                return JSON;
            default:
                throw new IllegalArgumentException("Invalid content type: " + type);
        }
    }
}
