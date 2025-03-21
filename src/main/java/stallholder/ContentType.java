package stallholder;

import java.util.Locale;

/**
 * Enum representing the content type of a file.
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Headers/Content-Type
 */
public enum ContentType {
    HTML("text/html"), JAVASCRIPT("text/javascript"), CSS("text/css"),
    ICO("image/x-con"), PNG("image/png"), JPG("image/jpg"), GIF("image/gif"),
    PLAIN("text/plain"), FORMDATA("multipart/form-data"), URLFORMDATA("application/x-www-form-urlencoded"),
    JSON("application/json"), UNIMPLEMENTED("ERROR");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }


    public static ContentType fromFileName(String fileName) {
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
        return UNIMPLEMENTED;
    }

    public ContentType getContentType(String type) {
        switch (type) {
            case "text/html":
                return HTML;
            case "image/x-con":
                return ICO;
            case "image/jpg":
                return JPG;
            case "image/png":
                return PNG;
            default:
                return UNIMPLEMENTED;
        }
    }
}
