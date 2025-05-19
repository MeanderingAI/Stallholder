package stallholder.Forms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Form {
    private static Logger logger = Logger.getLogger(Form.class.getName());
    private String formID;
    private Map<String, FormItem> formItems;

    public Form(String request_body) throws IOException {
        formItems = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new StringReader(request_body))) {
            String line;

            line = reader.readLine();
            formID = line;
            //------WebKitFormBoundary1LOYz7DPNQw6Mjm

            StringBuilder formItem = new StringBuilder();

            while((line = reader.readLine()) != null) {
                if(line.startsWith(formID)) {
                    String formItemString = formItem.toString();
                    if(formItemString.isEmpty()) {
                        continue;
                    }
                    FormItem fi = new FormItem(formItemString);
                    formItems.put(fi.getName(), fi);
                    formItem = new StringBuilder();
                } else {
                    formItem.append(line);
                    formItem.append("\n");
                }
            }

            
        } catch (IOException  e) {
            throw e;
        }
    }

    public void saveFiles(String folder_path) {
        for (Map.Entry<String, FormItem> entry : formItems.entrySet()) {
            FormItem fi = entry.getValue();
            if(fi.isFile()) {
                fi.saveFile(folder_path);
            }
        }
    }

    public void saveFiles(String folder_path, boolean debug) {
        for (Map.Entry<String, FormItem> entry : formItems.entrySet()) {
            if(debug) {
                logger.info("Saving file " + entry.getKey());
            }
            FormItem fi = entry.getValue();
            if(fi.isFile()) {
                fi.saveFile(folder_path, debug);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, FormItem> entry : formItems.entrySet()) {
            FormItem fi = entry.getValue();
            sb.append(fi.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
