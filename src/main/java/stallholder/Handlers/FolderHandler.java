package stallholder.Handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import stallholder.MyHttpRequest;
import stallholder.MyHttpResponse;
import stallholder.RequestHandler;
import stallholder.Utils;
import stallholder.Enum.ContentType;
import stallholder.Enum.StatusCode;
import stallholder.exceptions.HandleRequestException;

/**
 * Handles requests for files in a folder
 */
public class FolderHandler extends RequestHandler {
    String folder_path;

    /**
     * Constructor for the folder handler
     * @param folder_path the path to the folder
     */
    public FolderHandler(String folder_path) {
        super();
        this.folder_path = folder_path;
    }


    /**
     * Reads a file into a byte array
     * @param file the file to read
     * @return the byte array of the file
     * @throws IOException if there is an error reading the file
     */
    public byte[] readFileToByteArray(File file) throws IOException {
        byte[] fileData = new byte[(int) file.length()];
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(fileData);
        }
        return fileData;
    }

    /**
     * Handles a request for a file in the folder
     * @param request the request to handle
     * @return the response to the request
     * @throws HandleRequestException if there is an error reading the file
     */
    @Override
    public MyHttpResponse handleRequest(MyHttpRequest request) throws HandleRequestException {
        MyHttpResponse response = new MyHttpResponse();

        String file_name = Utils.getLastTokenInPath(request.getRequestURL());
        String full_path = this.folder_path + "/" + file_name;
        File f = new File(full_path);
        if(f.exists()) {
            if(f.isDirectory()) {
                response.setCode(StatusCode.FORBIDDEN);
                response.setContent("Directory access is forbidden", ContentType.PLAIN);
                return response;
            } else {
                response.setCode(StatusCode.OK);
                try {
                    response.setContent(Files.readAllBytes(Paths.get(full_path)));
                } catch (IOException e) {
                    throw new HandleRequestException(e, "Error reading file: " + full_path);
                }
                response.setContentType(ContentType.fromFileName(file_name));
            }
            
        } else {
            response.setCode(StatusCode.NOT_FOUND);
            response.setContent("File not found", ContentType.PLAIN);
        }


        return  response;
    }
}

