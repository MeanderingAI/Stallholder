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

import static org.junit.jupiter.api.Assertions.*;

public class HTTPServerTest {
    class MyHttpReceiver implements Runnable{
        HTTPServer server;
        MyHttpReceiver(Router router) throws IOException {
            ServerConfig config = new ServerConfig(2222, 1);
            server = new HTTPServer(config, router);
        }

        @Override
        public void run() {
            try {
                server.handleRequests();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
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
            receiver.run();
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
            System.out.println("Sending request to " + url);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(200, response.statusCode());
            assertEquals("Hello, World!", response.body());
            System.out.println("Response: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false, "Failed to create URI");
        }

        
        
        System.out.println("Stopping server...");
        receiver.stop();

    } 
}
