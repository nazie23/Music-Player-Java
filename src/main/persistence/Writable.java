package persistence;

import org.json.JSONObject;

public interface Writable {
    // returns this as a JSON object

    JSONObject toJson();
}
