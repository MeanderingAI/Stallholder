package stallholderTest;

import org.junit.jupiter.api.Test;

import stallholder.HTTPServer;
import stallholder.Router;
import stallholder.ServerConfig;
import stallholder.MyHttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class HTTPServerTest {
    private static final Logger logger = Logger.getLogger(HTTPServerTest.class.getName());

    class MyHttpReceiver extends Thread{
        HTTPServer server;
        MyHttpReceiver(Router router) throws IOException {
            ServerConfig config = new ServerConfig(2222, 1);
            server = new HTTPServer(config, router);
        }

        @Override
        public void run() {
            server.handleRequests(true);
        }

        public void end() {
            server.stop();
        }
    }


    @Test
    void testExample() {
        Router router = new Router();
        router.addRoute("/", request -> {
            return new MyHttpResponse("Hello, World!");
        });

        MyHttpReceiver receiver = null;
        try {
            receiver = new MyHttpReceiver(router);
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false, "Failed to start server");
        }
        
        URI url;
        HttpClient client;
        HttpRequest request;
        try {
            url = new URI("http://localhost:2222/");
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(url)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            assertEquals("Hello, World!", response.body());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false, "Failed to create URI");
        }
            
        receiver.end();

        try{
            receiver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            assertTrue(false, "Failed to join server thread");
        }
    } 
}
