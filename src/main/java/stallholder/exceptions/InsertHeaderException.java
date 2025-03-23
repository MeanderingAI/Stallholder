package stallholder.exceptions;

public class InsertHeaderException extends RuntimeException{
    Exception causedBy;
    String Details;
    public InsertHeaderException(Exception causedBy, String details) {
        super(details);
        this.causedBy = causedBy;
        this.Details = details;
    }
    public Exception getCausedBy() {
        return causedBy;
    }
    public String getDetails() {
        return Details;
    }
}

