package stallholder.CSRF;

import java.util.HashMap;
import java.util.Map;

public class CSRF_Set {
    private static Map<String, CSRF_Entry> map;

    public CSRF_Set() {
        map = new HashMap<>();
    }

    public CSRF_Entry add() {
        CSRF_Entry entry = new CSRF_Entry();
        map.put(entry.getToken(), entry);
        return entry;
    }

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