package stallholder.exceptions;

/**
 * Exception thrown when there is an error handling a request.
 */
public class HandleRequestException extends RuntimeException {
    /**
     * Exception that caused this exception.
     */
    Exception causedBy = null;
    /**
     * Details about the exception.
     */
    String Details = null;

/**
     * Constructor for the InsertHeaderException.
     * @param causedBy the exception that caused this exception
     */
    public HandleRequestException(Exception causedBy) {
        super(causedBy);
        this.causedBy = causedBy;
    }

    /**
     * Constructor for the InsertHeaderException.
     * @param causedBy the exception that caused this exception
     * @param details additional details about the exception
     */
    public HandleRequestException(Exception causedBy, String details) {
        super(details);
        this.causedBy = causedBy;
        this.Details = details;
    }
    
    /**
     * Returns the exception that caused this exception.
     * @return the exception that caused this exception
     */
    public Exception getCausedBy() {
        return causedBy;
    }

    /**
     * Returns the details of the exception.
     * @return the details of the exception
     */
    public String getDetails() {
        return Details;
    }
}
