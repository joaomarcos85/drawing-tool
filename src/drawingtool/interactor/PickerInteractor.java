package drawingtool.interactor;

import drawingtool.ui.Canvas;
import drawingtool.listener.ShapeListener;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import drawingtool.selector.RelativeResizeCursor;
import drawingtool.selector.Selector;
import drawingtool.shapes.Shape;
import drawingtool.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Joao
 */
public class PickerInteractor extends Interactor {

    public PickerInteractor(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        Point msPt = canvas.getMousePosition();
        if (msPt == null) {
            return;
        }

        //Checks if the current cursor is a resize cursor or a rotate cursor
        int cursor = canvas.getCursor().getType();
        if (Arrays.contains(RelativeResizeCursor.orderedCursors, cursor) ||
                cursor == Cursor.HAND_CURSOR) {
            return;
        }

        Selector newSelector = null;
        ArrayList<Shape> shapes = canvas.getShapes();
        for (int i = shapes.size() - 1; i >= 0; i--) {
            //Checks if the mouse point is inside the shape
            if (shapes.get(i).contains(msPt)) {
                shapes.get(i).setSelected(true);
                //Create a new selector for selected shape  
                newSelector = new Selector(shapes.get(i), canvas);
                int cursorType = newSelector.getCursor(msPt);
                canvas.setCursor(Cursor.getPredefinedCursor(cursorType));
                //Execute the shape selection listener 
                executeSelectListener(shapes.get(i));
                break;
            }
        }
        //Clear the current Selector or define the new Selector 
        canvas.setShapeSelector(newSelector);

        //Update the Canvas
        canvas.repaint();
    }

    private void executeSelectListener(Shape selectedShape) {
        for (ShapeListener shapeListener : this.canvas.getShapeListeners()) {
            shapeListener.onSelect(selectedShape);
        }
    }
}
