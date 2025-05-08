package stallholder.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Session_Entry {
    private UUID session_id;
    private Map<String, Object> session_data;

    public Session_Entry() {
        this.session_id = UUID.randomUUID();
        this.session_data = new HashMap<>();
    }

    public UUID getSessionID() {
        return this.session_id;
    }

    public void put(String key, Object value) {
        this.session_data.put(key, value);
    }

    public Object get(String key) {
        return this.session_data.get(key);
    }
}
