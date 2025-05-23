package stallholder.exceptions;

/**
 * Exception thrown when there is an error inserting a header.
 * This exception extends RuntimeException and provides additional details about the error.
 */
public class InsertHeaderException extends RuntimeException{
    /**
     * Exception that caused this exception.
     */
    Exception causedBy;
    /**
     * Details about the exception.
     */
    String Details;

    /**
     * Constructor for the InsertHeaderException.
     * @param causedBy the exception that caused this exception
     */
    public InsertHeaderException(Exception causedBy) {
        super(causedBy);
        this.causedBy = causedBy;
    }

    /**
     * Constructor for the InsertHeaderException.
     * @param causedBy the exception that caused this exception
     * @param details additional details about the exception
     */
    public InsertHeaderException(Exception causedBy, String details) {
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

