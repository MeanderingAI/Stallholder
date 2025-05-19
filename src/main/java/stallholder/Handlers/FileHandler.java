package stallholder.Handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import stallholder.MyHttpRequest;
import stallholder.MyHttpResponse;
import stallholder.RequestHandler;
import stallholder.Enum.ContentType;
import stallholder.Enum.StatusCode;
import stallholder.exceptions.HandleRequestException;

/**
 * Handles requests for files
 */
public class FileHandler extends RequestHandler {
    File file;
    String file_path;
    byte[] file_data;

    /**
     * Constructor for the file handler
     * @param file_path the path to the file
     * @throws FileNotFoundException if the file does not exist
     * @throws IOException if there is an error reading the file
     */
    public FileHandler(String file_path) throws FileNotFoundException, IOException {
        super();
        this.file_path = file_path;
        this.file = new File(this.file_path);
        
        if(!this.file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append("File; ");
            sb.append(this.file_path);
            sb.append(" does not exist");
            throw new FileNotFoundException(sb.toString());
        }
        if(this.file.isDirectory()) {
            throw new RuntimeException("FileHandler cannot handle directories");
        }
        this.file_data = new byte[(int) file.length()];
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(this.file_data);
        }
    }

    /**
     * Handles a request for a file
     * @param request the request to handle
     * @return the response to the request
     * @throws HandleRequestException if the request is invalid
     */
    @Override
    public MyHttpResponse handleRequest(MyHttpRequest request) throws HandleRequestException {
        MyHttpResponse response = new MyHttpResponse();
        ContentType content_type;
        try {
            content_type = ContentType.fromFileName(file.getName());
        } catch(IllegalArgumentException e) {
            throw new HandleRequestException(e, "Invalid file type: " + file.getName());
        }
        response.setCode(StatusCode.OK);
        response.setContent(this.file_data);
        response.setContentType(content_type);
        return response;
    }
}
