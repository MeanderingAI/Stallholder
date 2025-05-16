package stallholder.CSRF;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Class representing a CSRF token entry.
 */
public class CSRF_Entry {
    String token;
    Date expires = null;

    /**
     * Constructor for the CSRF_Entry class.
     */
    public CSRF_Entry() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, 30);
        this.expires = calendar.getTime();
        this.token = UUID.randomUUID().toString();
    }

    /**
     * Checks if the CSRF token is expired.
     * @return true if the token is expired, false otherwise
     */
    public boolean isExpired() {
        Date now = new Date();
        return now.after(this.expires);
    }

    /**
     * Returns the CSRF token.
     * @return the CSRF token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Returns the expiration date of the CSRF token.
     * @return the expiration date
     * 
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.token);
    }
}
