package stallholder.CSRF;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class CSRF_Entry {
    String token;
    Date expires = null;

    public CSRF_Entry() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, 30);
        this.expires = calendar.getTime();
        this.token = UUID.randomUUID().toString();
    }

    public boolean isExpired() {
        Date now = new Date();
        return now.after(this.expires);
    }

    public String getToken() {
        return this.token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.token);
    }
}
