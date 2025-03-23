package stallholder;

import java.text.ParseException;
import java.util.HashMap;

import stallholder.exceptions.InsertHeaderException;

/**
 * Holds http headers
 */
public class MyHttpHeaders extends HashMap<String, String> {
    
    /**
     * @param line line to parse into the header object
     * @throws ParseException
     * @throws IllegalArgumentException
     */
    public void insertHeader(String line) throws ParseException, IllegalArgumentException
    {   
        int first_section_idx = line.indexOf(':');
        if(first_section_idx == -1) {
            throw new InsertHeaderException(new Exception("No colon found"), line);
        }
        String first_section = line.substring(0, first_section_idx);
        if(this.containsKey(first_section)) {
            throw new IllegalArgumentException("Already have that header, something went wrong.");
        }
        String second_section = line.substring(first_section_idx + 1);
        this.put(first_section, second_section);
    }

    /**
     * String representation of the headers
     * @return String of headers
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Entry<String, String> node : this.entrySet()) {
            sb.append(node.getKey());
            sb.append(": ");
            sb.append(node.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }
}
