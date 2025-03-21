package stallholder;


import java.io.IOException;

public abstract class RequestHandler {
    public abstract MyHttpResponse HandleRequest(MyHttpRequest request) throws IOException;
}
