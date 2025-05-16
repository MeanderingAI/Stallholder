package stallholder.CSRF;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a set of CSRF tokens.
 */
public class CSRF_Set {
    private static Map<String, CSRF_Entry> map;

    /**
     * Constructor for the CSRF_Set class.
     */
    public CSRF_Set() {
        map = new HashMap<>();
    }

    /**
     * Adds a new CSRF token to the set.
     * @return the newly created CSRF_Entry
     */
    public CSRF_Entry add() {
        CSRF_Entry entry = new CSRF_Entry();
        map.put(entry.getToken(), entry);
        return entry;
    }

    /**
     * Removes a CSRF token from the set.
     * @param csrf_token the string token
     * @return The CSRF_Status
     */
    public CSRF_Status tokenStatus(String csrf_token) {
        CSRF_Entry entry = map.get(csrf_token);
        if(entry == null) {
            return CSRF_Status.MISSING;
        } else if(entry.isExpired()) {
            return CSRF_Status.EXPIRED;
        } else {
            return CSRF_Status.VALID;
        }
    }
}