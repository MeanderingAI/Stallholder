package stallholder;

import java.text.ParseException;
import java.util.HashMap;

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
        String[] tokens = line.split(":");
        if(tokens.length <= 1) {
            throw new ParseException(line, 0);
        }
        if(this.containsKey(tokens[0])) {
            throw new IllegalArgumentException("Already have that header, something went wrong.");
        }
        this.put(tokens[0], tokens[1]);
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
