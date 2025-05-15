package stallholder;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Logger;

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

    public RequestThread(Socket socket, Router router) throws IOException {
        this.socket = socket;
        this.router = router;
    }

    public RequestThread(Socket socket, Router router, boolean debug) throws IOException {
        this.socket = socket;
        this.router = router;
        this.debug = debug;
        if(this.debug) {
            logger.info("Debug mode enabled");
        }
    }

    public Exception getException() {
        return e;
    }

    public boolean done() {
        return done;
    }

    public MyHttpRequest getRequest() {
        return request;
    }

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
        } catch(IOException | ParseException | IllegalArgumentException e) {
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
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            this.e = parseException;
            this.done = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            this.e = throwables;
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
