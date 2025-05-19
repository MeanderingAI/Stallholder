package stallholder.Handlers;

import java.util.function.Function;

import stallholder.MyHttpRequest;
import stallholder.MyHttpResponse;
import stallholder.RequestHandler;
import stallholder.exceptions.HandleRequestException;


public class LambdaFunctionHandler extends RequestHandler {
    private final Function<MyHttpRequest, MyHttpResponse> function;

    public LambdaFunctionHandler(Function<MyHttpRequest, MyHttpResponse> function) {
        this.function = function;
    }

    @Override
    public MyHttpResponse handleRequest(MyHttpRequest request) throws HandleRequestException {
        return function.apply(request);
    }
}
