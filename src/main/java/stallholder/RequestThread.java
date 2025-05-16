package stallholder;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.util.logging.Logger;

import stallholder.exceptions.HandleRequestException;
import stallholder.exceptions.InsertHeaderException;

/**
 * Represents a request thread.
 */
public class RequestThread extends Thread {
    private Logger logger = Logger.getLogger(RequestThread.class.getName());
    private Socket socket = null;
    private MyHttpRequest request = null;
    private MyHttpResponse response = null;
    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader bufferedReader = null;
    private Exception e = null;
    private Router router = null;
    private boolean done = false;
    private boolean debug = false;

    /**
     * Constructor for the request thread
     * @param socket berkley socket we are using the thread manager should provide this
     * @param router stallholder router object
     * @throws IOException socket may throw an exception or during processing of request
     */
    public RequestThread(Socket socket, Router router) throws IOException {
        super();
        this.socket = socket;
        this.router = router;
    }

    /**
     * Constructor for the request thread
     * @param socket berkley socket we are using the thread manager should provide this
     * @param router stallholder router object
     * @param debug true to enable debug mode
     * @throws IOException socket may throw an exception or during processing of request
     */
    public RequestThread(Socket socket, Router router, boolean debug) throws IOException {
        super();
        this.socket = socket;
        this.router = router;
        this.debug = debug;
        if(this.debug) {
            logger.info("Debug mode enabled");
        }
    }

    /**
     * Returns an exception if one was thrown
     * @return returns exception if one was thrown or null
     */
    public Exception getException() {
        return e;
    }

    /**
     * Returns true if the thread is done
     * @return true if the thread is done
     */
    public boolean done() {
        return done;
    }

    /**
     * Returns the request object
     * @return the request object
     */
    public MyHttpRequest getRequest() {
        return request;
    }

    /**
     * Returns the response object
     * @return response object
     */
    public MyHttpResponse getResponse() {
        return response;
    }


    private void readInput() {
        try {
            inputStream = this.socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            this.e = e;
            this.done = true;
            return;
        }

        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);

        request = new MyHttpRequest();

        //read first line (http version information)
        String line = "";
        try {
            line = bufferedReader.readLine();
            request.parseFirstLine(line);
        } catch (IOException | ParseException e) {
            logger.warning(e.getMessage());
            e.printStackTrace();
            this.e = e;
            this.done = true;
            return;
        }


        // Read headers
        try {
            try {
                while (bufferedReader.ready()) {
                    line = bufferedReader.readLine();
                    if(line == null) {
                        break;
                    }
                    if(line.isEmpty()) {
                        break;
                    }
                    request.insertHeader(line);
                }
            } catch (InsertHeaderException | IOException e) {
                throw new HandleRequestException(e, "Error processing buffer; last processed line;\n" + line);
            }
        } catch(HandleRequestException e) {
            e.printStackTrace();
            this.e = e;
            this.done = true;
            return;
        }

        if(this.debug) {
            StringBuilder sb = new StringBuilder();
            sb.append("Recieved a ");
            sb.append(request.getVerb());
            sb.append(" request for ");
            sb.append(request.getRequestURL());
            logger.info(sb.toString());
        }

        if(request.getVerb() == HttpVerb.POST) {
            StringBuilder stringBody = new StringBuilder();
            char[] buffer = new char[8096];
            try {
                while (bufferedReader.ready()) {
                    int charsRead = bufferedReader.read(buffer);
                    stringBody.append(buffer, 0, charsRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                this.e = e;
                this.done = true;
                return;
            }
            request.addToBody(stringBody.toString().trim());
        }

    }

    private void writeOutput() {
        try {
            socket.getOutputStream().write(response.responseHeader());
            socket.getOutputStream().write(response.responseContent());
            socket.getOutputStream().flush();
            socket.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
            this.e = e;
            this.done = true;
            return;
        }

        try {
            inputStreamReader.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            this.e = e;
            this.done = true;
            return;
        }
    }

    private void handleRequest() {
        try {
            response = router.handle(request);
        } catch (IOException e) {
            e.printStackTrace();
            this.e = e;
            this.done = true;
        } 
    }

    @Override
    public void run() {
        readInput();
        if(this.done) {return;}

        handleRequest();
        if(this.done) {return;}

        writeOutput();
        this.done = true;
    }
}
