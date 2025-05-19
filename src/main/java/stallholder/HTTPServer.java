package stallholder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents an HTTP server.
 */
public class HTTPServer extends ServerSocket {
    private Logger logger = Logger.getLogger(HTTPServer.class.getName());
    private List<RequestThread> threads = null;
    private final ServerConfig config;
    private final Router router;
    private boolean running = false;

    /**
     * Constructor for the HTTP server
     * @param serverConfig Configuration for the server
     * @param router stallholder.Router object
     * @throws IOException if there is an error while processing the request
     */
    public HTTPServer(ServerConfig serverConfig, Router router) throws IOException {
        super(serverConfig.GetPortNumber());
        this.config = serverConfig;
        this.threads = new LinkedList<RequestThread>();
        this.router = router;
    }

    /**
     * Accepts a connection from a client
     * Handles a single request 
     * 
     * @throws IOException from the socket
     */
    private void handleRequest() throws IOException {
        Socket socket = this.accept();
        if(threads.size() >= config.GetNumberOfThreads()) {
            logger.warning("Over the allowed number of threads");
        }

        RequestThread rt = new  RequestThread(socket, router, false);
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

    /**
     * Accepts a connection from a client
     * Handles a single request 
     * 
     * @param debug true to enable debug mode
     * @throws IOException from the socket
     */
    private void handleRequest(boolean debug) throws IOException {
        Socket socket = this.accept();
        if(threads.size() >= config.GetNumberOfThreads()) {
            logger.warning("Over the allowed number of threads");
        }

        RequestThread rt = new RequestThread(socket, router, debug);
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

    /**
     * Handles requests in a loop
     * @throws IOException from the socket
     */
    public void handleRequests() throws IOException{
        while(this.running) {
            try {
                this.handleRequest();
            } catch (IOException e) {
                logger.warning("Error accepting connection: " + e.getMessage());
            }
        }
    }

    /**
     * Handles requests in a loop
     * @param debug true to enable debug mode
     */
    public void handleRequests(boolean debug) {
        if(debug) {
            logger.info("Starting server on port " + config.GetPortNumber());
            logger.info("Number of threads: " + config.GetNumberOfThreads());
        }

        while(this.running) {
            try {
                this.handleRequest(debug);
            } catch (IOException e) {
                logger.warning("Error accepting connection: " + e.getMessage());
            }
        }
    }

    /**
     * Stops the server
     */
    public void stop() {
        this.running = false;
        try {
            this.close();
        } catch (IOException e) {
            logger.warning("Error closing server socket: " + e.getMessage());
        }
    }

    /**
     * Check if all threads are done
     * @return true if all threads are done otherwise return true
     */
    public boolean threadsDone() {
        for(RequestThread rt : threads) {
            if(!rt.done()) {
                return false;
            }
        }
        return true;
    }
}
