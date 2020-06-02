package drawingtool.io.parser.json;

import drawingtool.io.ShapeData;
import java.util.Iterator;
import org.json.JSONObject;

/**
 *
 * @author Joao
 */
public class ShapeDataJSON extends ShapeData {

    public ShapeDataJSON(JSONObject jsonObject) {
        //Gets the shapes keys
        Iterator<String> keys = jsonObject.keys();
        String key;
        //Adds keys and values to ShapeData
        while (keys.hasNext()) {
            key = keys.next();
            this.put(key, jsonObject.get(key));
        }
    }
}
