package stallholder.Forms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.logging.Logger;

import stallholder.Enum.ContentType;

public class FormItem {
    private static Logger logger = Logger.getLogger(FormItem.class.getName());
    private String name;
    private String filename;
    private ContentType contentType;

    private String contentString;

    public FormItem(String content) throws IOException {

        try (BufferedReader reader = new BufferedReader(new StringReader(content))) {
            String line;

            line = reader.readLine();
            //first line is 
            String[] tokens = line.split(";");
            for (String token : tokens) {
                token = token.strip();
                if (token.startsWith("name")) {
                    this.name = token.split("=")[1].replaceAll("\"", "");
                } else if (token.startsWith("filename")) {
                    this.filename = token.split("=")[1].replaceAll("\"", "");
                }
            }

            line = reader.readLine().strip();
            if(line != "") {
                // Content-Type: image/png
                String[] tokens2 = line.split(":");
                if(tokens2.length < 2) {
                    throw new IOException("Invalid Content-Type header " + line);
                }
                String contentTypeString = tokens2[1].strip();
                this.contentType = ContentType.getContentType(contentTypeString);
            }

            while((line = reader.readLine()) == "") {
                //skip empty lines
            }

            StringBuilder contentBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                contentBody.append(line);
                contentBody.append("\n");
            }
            this.contentString = contentBody.toString();
        } catch (IOException e) {
            throw e;
        }        
    }

    public String getName() {
        return name;
    }

    public boolean isFile() {
        return filename != null;
    }   

    public void saveFile(String folder_path) {
        String file_path = folder_path + "/" + filename;
        try {
            Files.write(Paths.get(file_path), Collections.singletonList(this.contentString), StandardOpenOption.CREATE);
            System.out.println("String written using Files.write");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    ------WebKitFormBoundary9SGL9JTZAHj6DPTt
//    Content-Disposition: form-data; name="image4c6npc1gz0"; filename="4c6npc1gz0.jpg"
//    Content-Type: image/png

    public void saveFile(String folder_path, boolean debug) {
        String file_path = folder_path + "/" + filename;
        if(debug) {
            logger.info("Saving file " + file_path);
        }
        try {
            Files.write(Paths.get(file_path), Collections.singletonList(this.contentString), StandardOpenOption.CREATE);
            System.out.println("String written using Files.write");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Content-Disposition: form-data; name="caption"

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FormItem: \n");
        sb.append("Name: " + name + "\n");
        if (filename != null) {
            sb.append("Filename: " + filename + "\n");
        }
        /* 
        if (contentType != null) {
            sb.append("Content-Type: " + contentType.toString() + "\n");
        }
        sb.append("Content: " + contentString + "\n");
            */
        return sb.toString();
    }

}
