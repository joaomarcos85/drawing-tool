package drawingtool.util;

/**
 *
 * @author Joao
 */
public class Arrays {

    public static boolean contains(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }
}
