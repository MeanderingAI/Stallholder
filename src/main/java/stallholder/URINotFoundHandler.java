package stallholder;

public class URINotFoundHandler extends RequestHandler {
    private static URINotFoundHandler instance;
    private String stringedResponse;
    
        private URINotFoundHandler() {
            this.stringedResponse = "404 Page not found";
        }
    
        @Override
        public MyHttpResponse HandleRequest(MyHttpRequest request) {
            MyHttpResponse response = new MyHttpResponse(StatusCodes.NOT_FOUND);
            response.SetContent(this.stringedResponse, ContentType.PLAIN);
        return response;
    }

    public static URINotFoundHandler getInstance() {
        if(instance == null) {
            instance = new URINotFoundHandler();
        }
        return instance;
    }
}
