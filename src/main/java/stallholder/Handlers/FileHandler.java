package stallholder.Handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import stallholder.ContentType;
import stallholder.MyHttpRequest;
import stallholder.MyHttpResponse;
import stallholder.RequestHandler;
import stallholder.StatusCodes;

public class FileHandler extends RequestHandler{
    File file;
    String file_path;
    byte[] file_data;
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

    @Override
    public MyHttpResponse HandleRequest(MyHttpRequest request) {
        MyHttpResponse response = new MyHttpResponse();
        ContentType content_type = ContentType.fromFileName(file.getName());
        response.SetCode(StatusCodes.OK);
        response.SetContent(this.file_data);
        response.SetContentType(content_type);
        return response;
    }
}
