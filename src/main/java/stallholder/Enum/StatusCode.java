package stallholder.Enum;

/**
 * Enum representing HTTP status codes.
 */
public enum StatusCode {
    /**
     * Continue response
     */
    CONTINUE(100, "continue"), // continue
    /**
     * Switching Protocols response
     */
    SWITCHING_PROTOCOLS(101, "switching protocols"),
    /**
     * Processing response
     */
    PROCESSING(102, "processing"), 
    /**
     * Early Hints response
     */
    EARLY_HINTS(103, "early_hints"),
    /**
     * OK response
     */
    OK(200, "ok"),
    /**
     * Created response
     */
    CREATED(201, "created"),
    /**
     * Accepted response
     */
    ACCEPTED(202, "accepted"), 
    /**
     * Non-Authoritative Information response
     */
    NON_AUTHORITATIVE_INFORMATION(203, "non-authoritative information"), 
    /**
     * No Content response
     */
    NO_CONTENT(204, "no content"),
    /**
     * Reset Content response
     */
    RESET_CONTENT(205, "reset content"),
    /**
     * Partial Content response
     */
    PARTIAL_CONTENT(206, "partial content"),
    /**
     * Multi-Status response
     */
    MULTI_STATUS(207, "multi-status"),
    /**
     * Already Reported response
     */
    ALREADY_REPORTED(208, "already reported"),
    /**
     * IM Used response
     */
    IM_USED(226, "I'm used"),
    /**
     * Multiple Choices response
     */
    MULTIPLE_CHOICES(300, "multiple choices"),
    /**
     * Moved Permanently response
     */
    MOVED_PERMANENTLY(301, "moved permanently"),
    /**
     * Found response
     */
    FOUND(302, "found"),
    /**
     * See Other response
     */
    SEE_OTHER(303, "see other"),
    /**
     * Not Modified response
     */
    NOT_MODIFIED(304, "not modified"),
    /**
     * Use Proxy response
     */
    TEMPORARY_REDIRECT(307, "temporary redirect"),
    /**
     * Permanent Redirect response
     */
    PERMANENT_REDIRECT(308, "permanent redirect"),
    /**
     * Bad Request response
     */
    BAD_REQUEST(400, "bad request"),
    /**
     * Unauthorized response
     */
    UNAUTHORIZED(401, "unauthorized"),
    /**
     * Payment Required response
     */
    PAYMENT_REQUIRED(402, "payment required"),
    /**
     * Forbidden response
     */
    FORBIDDEN(403, "forbidden"),
    /**
     * Not Found response
     */
    NOT_FOUND(404, "not found"),
    /**
     * Method Not Allowed response
     */
    METHOD_NOT_ALLOWED(405, "method not allowed"),
    /**
     * Not Acceptable response
     */
    NOT_ACCEPTABLE(406, "not acceptable"),
    /**
     * Proxy Authentication Required response
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "proxy authentication required"),
    /**
     * Request Timeout response
     */
    REQUEST_TIMEOUT(408, "request timeout"),
    /**
     * Conflict response
     */
    CONFLICT(409, "conflict"),
    /**
     * Gone response
     */
    GONE(410, "gone"),
    /**
     * Length Required response
     */
    LENGTH_REQUIRED(411, "length required"),
    /**
     * Precondition Failed response
     */
    PRECONDITION_FAILED(412, "precondition failed"),
    /**
     * Payload Too Large response
     */
    PAYLOAD_TOO_LARGE(413, "payload too large"),
    /**
     * URI Too Long response
     */
    URI_TOO_LONG(414, "uri too long"), //special case
    /**
     * Unsupported Media Type response
     */
    UNSUPPORTED_MEDIA_TYPE(415, "unsupported media type"),
    /**
     * Range Not Satisfiable response
     */
    RANGE_NOT_SATISFIABLE(416, "range not satisfiable"),
    /**
     * Expectation Failed response
     */
    EXPECTATION_FAILED(417, "expectation failed"),
    /**
     * I'm a teapot response
     */
    IM_A_TEAPOT(418, "Im a teapot"), //special case
    /**
     * Misdirected Request response
     */
    MISDIRECTED_REQUEST(421, "misdirected request"),
    /**
     * Unprocessable Content response
     */
    UNPROCESSABLE_CONTENT(422, "unprocessable content"),
    /**
     * Locked response
     */
    LOCKED(423, "locked"),
    /**
     * Failed Dependency response
     */
    FAILED_DEPENDENCY(424, "failed dependency"),
    /**
     * Too Early response
     */
    TOO_EARLY(425, "too early"),
    /**
     * Upgrade Required response
     */
    UPGRADE_REQUIRED(426, "upgrade required"),
    /**
     * Precondition Required response
     */
    PRECONDITION_REQUIRED(428, "precondition required"),
    /**
     * Too Many Requests response
     */
    TOO_MANY_REQUESTS(429, "too many requests"),
    /**
     * Request Header Fields Too Large response
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "request header fields too large"),
    /**
     * Unavailable For Legal Reasons response
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "unavailable for legal reasons"),
    /**
     * Internal Server Error response
     */
    INTERNAL_SERVER_ERROR(500, "internal server error"),
    /**
     * Not Implemented response
     */
    NOT_IMPLEMENTED(501, "not implemented"),
    /**
     * Bad Gateway response
     */
    BAD_GATEWAY(502, "bad gateway"),
    /**
     * Service Unavailable response
     */
    SERVICE_UNAVAILABLE(503, "service unavailable"),
    /**
     * Gateway Timeout response
     */
    GATEWAY_TIMEOUT(504, "gateway timeout"),
    /**
     * HTTP Version Not Supported response
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "http version not supported"),
    /**
     * Variant Also Negotiates response
     */
    VARIANT_ALSO_NEGOTIATES(506, "variant also negotiates"),
    /**
     * Insufficient Storage response
     */
    INSUFFICIENT_STORAGE(507, "insufficient storage"),
    /**
     * Loop Detected response
     */
    LOOP_DETECTED(508, "loop detected"),
    /**
     * Not Extended response
     */
    NOT_EXTENDED(510, "not extended"),
    /**
     * Network Authentication Required response
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, "network authentication required");

    private final String fullMessage;
    private final Integer code;
    private final String message;

    /**
     * Constructor for the StatusCodes enum.
     * @param code The integer code
     * @param message The message associated with the code
     */
    private StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
        StringBuilder fm = new StringBuilder();
        fm.append(code);
        fm.append(" ");
        fm.append(message);
        this.fullMessage = fm.toString();
    }

    /**
     * Returns the code of the status code.
     * @return returns the integer code 
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Returns the message of the status code.
     * @return message of the status code
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the full message of the status code.
     * @return full message of the status code
     */
    public String toString() {
        return fullMessage;
    }

    /**
     * Returns the StatusCodes enum corresponding to the given code.
     * @param code the integer code of the status code
     * @return StatusCodes enum corresponding to the code
     * @throws IllegalArgumentException if the code does not have a corresponding enum value
     */
    public static StatusCode fromCode(Integer code) {
        for (StatusCode sc : StatusCode.values()) {
            if (sc.getCode().equals(code)) {
                return sc;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}
