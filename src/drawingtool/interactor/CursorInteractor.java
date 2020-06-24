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

        this.canvas.setCursor(Cursor.getPredefinedCursor(
                this.getCurrentCursorType(msPt)));
    }

    private int getCurrentCursorType(Point mousePoint) {
        //Checks if is pending to add a new shape on canvas
        AddShapeInteractor addShapeInteractor = this.canvas.getInteractor(
                AddShapeInteractor.class);
        if (addShapeInteractor.getPendingShape() != null) {
            return Cursor.CROSSHAIR_CURSOR;
        }

        Shape selectedShape = this.canvas.getSelectedShape();
        //Checks if there is a selected shape
        if (selectedShape != null) {
            //Gets the correct cursor type
            int cursor = canvas.getShapeSelector().getCursor(mousePoint);
            if (cursor != -1) {
                return cursor;
            }
        }

        return Cursor.DEFAULT_CURSOR;
    }
}
