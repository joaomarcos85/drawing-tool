package drawingtool.io.parser;

/**
 *
 * @author Joao
 */
public interface ShapeData {

    public String getString(String key, String defaultValue);

    public String getString(String key);

    public int getInt(String key, int defaultValue);

    public int getInt(String key);

    public float getFloat(String key, float defaultValue);

    public float getFloat(String key);

    public double getDouble(String key, double defaultValue);

    public double getDouble(String key);

    public boolean getBoolean(String key, boolean defaultValue);

    public boolean getBoolean(String key);
}
