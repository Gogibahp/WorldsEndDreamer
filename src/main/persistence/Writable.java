package persistence;

import org.json.*;

public interface Writable {
    // CLASS LEVEL COMMENT: Lets objects be converted into JSON

    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
