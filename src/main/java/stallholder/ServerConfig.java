package stallholder;

import java.util.logging.Logger;

public class ServerConfig {
    private static Logger logger = Logger.getLogger(ServerConfig.class.getName());

    private Integer portNumber;
    private Integer numThreads;

    public ServerConfig(int portNumber, int numThreads) {
        try {
            this.portNumber = portNumber;
            this.numThreads = numThreads;
        } catch(NumberFormatException e) {
            logger.warning(e.getMessage());
            System.exit(1);
        }
    }

    public int GetPortNumber() {
        return this.portNumber;
    }

    public int GetNumberOfThreads() {
        return this.numThreads;
    }
}
