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

public class TokenHandler extends RequestHandler {
    private static TokenHandler instance;
    
    @Override
    public MyHttpResponse HandleRequest(MyHttpRequest request) {
        ...
    }

    public static KenHandler getInstance() {
        if(instance == null) {
            instance = new KenHandler();
        }
        return instance;
    }
}

```

Then create a router and add the routes

```
import stallholder.URINotFound;
import stallholder.Router;
import stallholder.MyHttpRequest;
import stallholder.HttpVerb;

...

Router router = new Router(new URINotFound());

router.AddRoute(new MyHttpRequest(HttpVerb.OPTIONS, "/token"), TokenHandler.getInstance());
router.AddRoute(new MyHttpRequest(HttpVerb.POST, "/token"), TokenHandler.getInstance());

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
