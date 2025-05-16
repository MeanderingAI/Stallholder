package stallholder.Session;

import java.util.Map;
import java.util.UUID;

import stallholder.MyHttpResponse;
import stallholder.exceptions.InsertHeaderException;

/**
 * Represents a set of sessions.
 */
public class Session_Set {
    private Map<UUID, Session_Entry> sessions;

    /**
     * Constructor for the session set
     */
    public Session_Set() {
        this.sessions = new java.util.HashMap<>();
    }

    /**
     * Set the session ID cookie in the response
     * @param response the response object to add a session cookie to 
     * @param uuid the uuid we are going to be setting
     * @throws InsertHeaderException if there is an error setting the header
     */
    private void setSessionIDCookie(MyHttpResponse response, UUID uuid) throws InsertHeaderException {
        StringBuilder sb = new StringBuilder();
        sb.append("session=");
        sb.append(uuid.toString());
        sb.append("; HttpOnly");
        response.SetCookie(sb.toString());
    }

    /**
     * Create a new session entry
     * @param response the response object to add a session cookie to
     * @throws InsertHeaderException if there is an error setting the header
     */
    public Session_Entry newEntry(MyHttpResponse response) throws InsertHeaderException {
        Session_Entry entry = new Session_Entry();
        this.setSessionIDCookie(response, entry.getSessionID());
        this.sessions.put(entry.getSessionID(), entry);
        return entry;
    }

    /**
     * get the session entry
     * @param session_id the session id we are looking for
     * @return the session entry
     */
    public Session_Entry get(UUID session_id) {
        return this.sessions.get(session_id);
    }
}
