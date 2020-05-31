package drawingtool.io.parser.json;

import drawingtool.io.parser.ShapeData;
import org.json.JSONObject;

/**
 *
 * @author Joao
 */
public class ShapeDataJSON implements ShapeData {

    JSONObject jsonObject;

    public ShapeDataJSON(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String getString(String key, String defaultValue) {
        return this.jsonObject.optString(key, defaultValue);
    }

    @Override
    public String getString(String key) {
        return this.jsonObject.getString(key);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return this.jsonObject.optInt(key, defaultValue);
    }

    @Override
    public int getInt(String key) {
        return this.jsonObject.getInt(key);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return this.jsonObject.optFloat(key, defaultValue);
    }

    @Override
    public float getFloat(String key) {
        return this.jsonObject.getFloat(key);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return this.jsonObject.optDouble(key, defaultValue);
    }

    @Override
    public double getDouble(String key) {
        return this.jsonObject.getDouble(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return this.jsonObject.optBoolean(key, defaultValue);
    }

    @Override
    public boolean getBoolean(String key) {
        return this.jsonObject.getBoolean(key);
    }
}
