package drawingtool.selector;

import java.awt.Cursor;

/**
 *
 * @author Joao
 */
public class RelativeResizeCursor {

    public static int[] orderedCursors = new int[]{
        Cursor.N_RESIZE_CURSOR,
        Cursor.NE_RESIZE_CURSOR,
        Cursor.E_RESIZE_CURSOR,
        Cursor.SE_RESIZE_CURSOR,
        Cursor.S_RESIZE_CURSOR,
        Cursor.SW_RESIZE_CURSOR,
        Cursor.W_RESIZE_CURSOR,
        Cursor.NW_RESIZE_CURSOR
    };

    public synchronized int getAngledCursor(int oRefCursor, float oAngle) {
        final int vCurrentPosition = positionOf(oRefCursor);
        int vNewPosition = (int) (vCurrentPosition + (oAngle / 45));

        if (vNewPosition >= orderedCursors.length) {
            vNewPosition -= orderedCursors.length;
        }
        return orderedCursors[vNewPosition];
    }

    private int positionOf(int oCursor) {
        //Procura o cursor na lista de cursores
        for (int i = 0; i < orderedCursors.length; i++) {
            if (orderedCursors[i] == oCursor) {
                //Retorna a posição do cursor
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid cursor");
    }
}
