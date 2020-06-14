package drawingtool.interactor;

import drawingtool.ui.Canvas;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import drawingtool.shapes.Shape;

/**
 *
 * @author Joao
 */
public class CursorInteractor extends Interactor {

    public CursorInteractor(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
        Point msPt = canvas.getMousePosition();
        if (msPt == null || canvas.getShapeSelector() == null) {
            return;
        }
        int cursorType = -1;
        for (Shape shape : canvas.getShapes()) {
            if (shape.isSelected()) {
                int cursor = canvas.getShapeSelector().getCursor(msPt);
                if (cursor != -1) {
                    cursorType = cursor;
                    break;
                }
            }
        }

        if (cursorType == -1) {
            cursorType = Cursor.DEFAULT_CURSOR;
        }
        canvas.setCursor(Cursor.getPredefinedCursor(cursorType));
    }
}
