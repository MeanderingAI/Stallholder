package stallholder;

import java.util.logging.Logger;

/**
 * Represents the server configuration.
 */
public class ServerConfig {
    private static Logger logger = Logger.getLogger(ServerConfig.class.getName());

    private Integer portNumber;
    private Integer numThreads;

    /**
     * Constructor for the server configuration
     * @param portNumber the port number to listen on 
     * @param numThreads the number of threads to listen on the port
     */
    public ServerConfig(int portNumber, int numThreads) {
        try {
            this.portNumber = portNumber;
            this.numThreads = numThreads;
        } catch(NumberFormatException e) {
            logger.warning(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Returns the port number
     * @return the port number
     */
    public int GetPortNumber() {
        return this.portNumber;
    }

    /**
     * Returns the number of threads
     * @return  the number of threads
     */
    public int GetNumberOfThreads() {
        return this.numThreads;
    }
}
