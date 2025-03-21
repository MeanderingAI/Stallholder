package stallholder;

public enum StatusCodes {
    CONTINUE(100, "continue"),
    SWITCHING_PROTOCOLS(101, "switching protocols"),
    PROCESSING(102, "processing"),
    EARLY_HINTS(103, "early_hints"),
    OK(200, "ok"),
    CREATED(201, "created"),
    ACCEPTED(202, "accepted"),
    NON_AUTHORITATIVE_INFORMATION(203, "non-authoritative information"), //special case
    NO_CONTENT(204, "no content"),
    RESET_CONTENT(205, "reset content"),
    PARTIAL_CONTENT(206, "partial content"),
    MULTI_STATUS(207, "multi-status"),
    ALREADY_REPORTED(208, "already reported"),
    IM_USED(226, "I'm used"),
    MULTIPLE_CHOICES(300, "multiple choices"),
    MOVED_PERMANENTLY(301, "moved permanently"),
    FOUND(302, "found"),
    SEE_OTHER(303, "see other"),
    NOT_MODIFIED(304, "not modified"),
    TEMPORARY_REDIRECT(307, "temporary redirect"),
    PERMANENT_REDIRECT(308, "permanent redirect"),
    BAD_REQUEST(400, "bad request"),
    UNAUTHORIZED(401, "unauthorized"),
    PAYMENT_REQUIRED(402, "payment required"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not found"),
    METHOD_NOT_ALLOWED(405, "method not allowed"),
    NOT_ACCEPTABLE(406, "not acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "proxy authentication required"),
    REQUEST_TIMEOUT(408, "request timeout"),
    CONFLICT(409, "conflict"),
    GONE(410, "gone"),
    LENGTH_REQUIRED(411, "length required"),
    PRECONDITION_FAILED(412, "precondition failed"),
    PAYLOAD_TOO_LARGE(413, "payload too large"),
    URI_TOO_LONG(414, "uri too long"), //special case
    UNSUPPORTED_MEDIA_TYPE(415, "unsupported media type"),
    RANGE_NOT_SATISFIABLE(416, "range not satisfiable"),
    EXPECTATION_FAILED(417, "expectation failed"),
    IM_A_TEAPOT(418, "Im a teapot"), //special case
    MISDIRECTED_REQUEST(421, "misdirected request"),
    UNPROCESSABLE_CONTENT(422, "unprocessable content"),
    LOCKED(423, "locked"),
    FAILED_DEPENDENCY(424, "failed dependency"),
    TOO_EARLY(425, "too early"),
    UPGRADE_REQUIRED(426, "upgrade required"),
    PRECONDITION_REQUIRED(428, "precondition required"),
    TOO_MANY_REQUESTS(429, "too many requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "request header fields too large"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "unavailable for legal reasons"),
    INTERNAL_SERVER_ERROR(500, "internal server error"),
    NOT_IMPLEMENTED(501, "not implemented"),
    BAD_GATEWAY(502, "bad gateway"),
    SERVICE_UNAVAILABLE(503, "service unavailable"),
    GATEWAY_TIMEOUT(504, "gateway timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "http version not supported"),
    VARIANT_ALSO_NEGOTIATES(506, "variant also negotiates"),
    INSUFFICIENT_STORAGE(507, "insufficient storage"),
    LOOP_DETECTED(508, "loop detected"),
    NOT_EXTENDED(510, "not extended"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "network authentication required");

    private final String fullMessage;
    private final Integer code;
    private final String message;

    private StatusCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
        StringBuilder fm = new StringBuilder();
        fm.append(code);
        fm.append(" ");
        fm.append(message);
        this.fullMessage = fm.toString();
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String toString() {
        return fullMessage;
    }
}
