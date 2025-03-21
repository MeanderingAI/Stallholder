package stallholder;

public class URINotFound extends MyHttpResponse {
    public URINotFound() {
        super(StatusCodes.NOT_FOUND);
    }
}
