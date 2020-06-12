package drawingtool.util;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Joao
 */
public class ColorUtil {

    public static Color stringToColor(String rgb) {
        if (rgb == null) {
            return null;
        }
        Pattern c = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Matcher m = c.matcher(rgb);

        if (m.matches()) {
            return new Color(Integer.valueOf(m.group(1)), // r
                    Integer.valueOf(m.group(2)), // g
                    Integer.valueOf(m.group(3))); // b 
        }
        return null;
    }

    public static String colorToStringRgb(Color color) {
        if(color == null){
            return null;
        }
        return "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    }

}
