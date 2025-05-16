package stallholder.CSRF;

/**
 * Enum representing the status of a CSRF token.
 */
public enum CSRF_Status {
    /**
     * VALID token
     */
    VALID,
    /**
     * MISSING token
     */
    MISSING, // should log these
    /**
     * EXPIRED token
     */
    EXPIRED,
}
