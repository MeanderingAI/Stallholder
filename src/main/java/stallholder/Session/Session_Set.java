package stallholder.Session;

import java.text.ParseException;
import java.util.Map;
import java.util.UUID;

import stallholder.MyHttpResponse;

public class Session_Set {
    private Map<UUID, Session_Entry> sessions;

    public Session_Set() {
        this.sessions = new java.util.HashMap<>();
    }

    private void SetSessionIDCookie(MyHttpResponse response, UUID uuid) throws ParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("session=");
        sb.append(uuid.toString());
        sb.append("; HttpOnly");
        response.SetCookie(sb.toString());
    }

    public Session_Entry add(MyHttpResponse response) throws ParseException {
        Session_Entry entry = new Session_Entry();
        this.SetSessionIDCookie(response, entry.getSessionID());
        this.sessions.put(entry.getSessionID(), entry);
        return entry;
    }

    public Session_Entry get(UUID session_id) {
        return this.sessions.get(session_id);
    }
}
