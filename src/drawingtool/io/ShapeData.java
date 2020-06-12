package drawingtool.io;

import drawingtool.util.ColorUtil;
import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author Joao
 */
public class ShapeData extends HashMap<String, Object> {

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

    public Color getColorFromRgb(String key) {
        return this.getColorFromRgb(key, null);
    }

    public Color getColorFromRgb(String key, Color defaultValue) {
        return ColorUtil.stringToColor(this.getString(key, null));
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

    public Object put(String key, Color value) {
        return super.put(key, ColorUtil.colorToStringRgb(value));
    }

}
