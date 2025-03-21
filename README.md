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

public class TokenHandler extends MyHttpResponse {
    
}

```

Then create a router and add the routes

```
import stallholder.URINotFound;
import stallholder.Router;

Router router = new Router(new URINotFound());

router.AddRoute(new MyHttpRequest(HttpVerb.OPTIONS, "/token"), TokenHandler.getInstance());
router.AddRoute(new MyHttpRequest(HttpVerb.POST, "/token"), TokenHandler.getInstance());

```

Finally Define the HTTPServer and launch it
```
```