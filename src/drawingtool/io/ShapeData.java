package drawingtool.io;

import java.util.HashMap;

/**
 *
 * @author Joao
 */
public  class ShapeData extends HashMap<String, Object> {

    public String getString(String key, String defaultValue) {
        Object value = this.get(key);
        if (value == null) {
            return defaultValue;
        }

        return String.valueOf(value);
    }

    public String getString(String key) {
        return String.valueOf(this.get(key));
    }

    public int getInt(String key, int defaultValue) {
        return Integer.valueOf(this.getString(key, String.valueOf(defaultValue)));
    }

    public int getInt(String key) {
        return Integer.valueOf(this.getString(key));
    }

    public float getFloat(String key, float defaultValue) {
        return Float.valueOf(this.getString(key, String.valueOf(defaultValue)));
    }

    public float getFloat(String key) {
        return Float.valueOf(this.getString(key));
    }

    public double getDouble(String key, double defaultValue) {
        return Double.valueOf(this.getString(key, String.valueOf(defaultValue)));
    }

    public double getDouble(String key) {
        return Double.valueOf(this.getString(key));
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.valueOf(this.getString(key, String.valueOf(defaultValue)));
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(this.getString(key));
    }

}
