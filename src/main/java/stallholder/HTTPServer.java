package stallholder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class HTTPServer extends ServerSocket {
    private Logger logger = Logger.getLogger(HTTPServer.class.getName());
    private List<RequestThread> threads = null;
    private final ServerConfig config;
    private final Router router;

    public HTTPServer(ServerConfig serverConfig, Router router) throws IOException {
        super(serverConfig.GetPortNumber());
        this.config = serverConfig;
        this.threads = new LinkedList<RequestThread>();
        this.router = router;
    }


    public void handleRequest() throws IOException {
        Socket socket = this.accept();
        if(threads.size() >= config.GetNumberOfThreads()) {
            logger.warning("Over the allowed number of threads");
        }

        RequestThread rt = new  RequestThread(socket, router);
        rt.start();

        boolean foundSpot = false;
        for(int i = 0; i < threads.size(); ++i) {
            if(threads.get(i).done()) {
                threads.set(i, rt);
                foundSpot = true;
                break;
            }
        }
        if(!foundSpot) {
            threads.add(rt);
        }
    }

    public void handleRequest(boolean debug) throws IOException {
        Socket socket = this.accept();
        if(threads.size() >= config.GetNumberOfThreads()) {
            logger.warning("Over the allowed number of threads");
        }

        RequestThread rt = new  RequestThread(socket, router);
        rt.start();

        boolean foundSpot = false;
        for(int i = 0; i < threads.size(); ++i) {
            if(threads.get(i).done()) {
                threads.set(i, rt);
                foundSpot = true;
                break;
            }
        }
        if(!foundSpot) {
            threads.add(rt);
        }
    }

    public void handleRequests() {
        while(true) {
            try {
                this.handleRequest();
            } catch (IOException e) {
                logger.warning("Error accepting connection: " + e.getMessage());
            }
        }
    }

    public void handleRequests(boolean debug) {
        System.out.println("Starting server on port " + config.GetPortNumber());
        System.out.println("Number of threads: " + config.GetNumberOfThreads());

        while(true) {
            try {
                this.handleRequest();
            } catch (IOException e) {
                logger.warning("Error accepting connection: " + e.getMessage());
            }
        }
    }

    public boolean threadsDone() {
        for(RequestThread rt : threads) {
            if(!rt.done()) {
                return false;
            }
        }
        return true;
    }
}
