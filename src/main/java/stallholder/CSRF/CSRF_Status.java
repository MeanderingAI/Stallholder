package stallholder.CSRF;

public enum CSRF_Status {
    VALID,
    MISSING, // should log these
    EXPIRED,
}
