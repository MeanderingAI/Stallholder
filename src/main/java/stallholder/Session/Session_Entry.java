package stallholder.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a session entry.
 * 
 * This class is used to store session data for a user.
 */
public class Session_Entry {
    private UUID session_id;
    private Map<String, Object> session_data;

    /**
     * Constructor for the session entry
     */
    public Session_Entry() {
        this.session_id = UUID.randomUUID();
        this.session_data = new HashMap<>();
    }

    /**
     * get the session id
     * @return the session id
     */
    public UUID getSessionID() {
        return this.session_id;
    }

    /**
     * Add a variable to the session entry
     * @param key the string key used to retrieve the value
     * @param value the Object we are adding to the session
     */
    public void put(String key, Object value) {
        this.session_data.put(key, value);
    }

    /**
     * Get a variable from the session entry
     * 
     * @param key of the object we are trying to retrieve
     * @return the object we stored
     */
    public Object get(String key) {
        return this.session_data.get(key);
    }
}
