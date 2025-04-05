package stallholder.Handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import stallholder.ContentType;
import stallholder.MyHttpRequest;
import stallholder.MyHttpResponse;
import stallholder.RequestHandler;
import stallholder.StatusCodes;
import stallholder.Utils;

public class FolderHandler extends RequestHandler {
    String folder_path;
    public FolderHandler(String folder_path) {
        super();
        this.folder_path = folder_path;
    }


    public byte[] readFileToByteArray(File file) throws IOException {
        byte[] fileData = new byte[(int) file.length()];
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(fileData);
        }
        return fileData;
    }

    @Override
    public MyHttpResponse HandleRequest(MyHttpRequest request) throws IOException {
        MyHttpResponse response = new MyHttpResponse();

        String file_name = Utils.getLastTokenInPath(request.getRequestURL());
        String full_path = this.folder_path + "/" + file_name;
        File f = new File(full_path);
        if(f.exists()) {
            if(f.isDirectory()) {
                response.SetCode(StatusCodes.FORBIDDEN);
                response.SetContent("Directory access is forbidden", ContentType.PLAIN);
                return response;
            } else {
                response.SetCode(StatusCodes.OK);
                response.SetContent(Files.readAllBytes(Paths.get(full_path)));
                response.SetContentType(ContentType.fromFileName(file_name));
            }
            
        } else {
            response.SetCode(StatusCodes.NOT_FOUND);
            response.SetContent("File not found", ContentType.PLAIN);
        }


        return  response;
    }
}

