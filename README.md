Stallholder is a simple web server 

# Install

```
git clone git@github.com:MeanderingAI/Stallholder.git
mvn clean install
```

# Usage

To use the web framework implement request handlers
```
import stallholder.RequestHandler;
import stallholder.MyHttpResponse;
import stallholder.MyHttpRequest

public class TokenHandler extends RequestHandler {
    @Override
    public MyHttpResponse HandleRequest(MyHttpRequest request) {
        ...
    }
}

```

Then create a router and add the routes

```
import stallholder.URINotFound;
import stallholder.Router;
import stallholder.HttpVerb;

...

Router router = new Router(new URINotFound());
TokenHandler tokenHandler = new TokenHandler();
router.AddRoute(new MyHttpRequest(HttpVerb.OPTIONS, "/token"), tokenHandler);
router.AddRoute(new MyHttpRequest(HttpVerb.POST, "/token"), tokenHandler);

```

Finally Define the HTTPServer and launch it
```
import stallholder.ServerConfig;
import stallholder.HTTPServer;

...
ServerConfig sc = new ServerConfig(portNumber, numThreads);
...
HTTPServer server = new HTTPServer(sc, router);
while(true)
{
    try {
        server.handleRequests();
    } catch(IOException e) {
        e.printStackTrace();
    }
}
```
