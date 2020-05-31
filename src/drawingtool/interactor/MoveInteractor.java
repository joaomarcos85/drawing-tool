package drawingtool.interactor;

import drawingtool.Canvas;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import drawingtool.selector.Selector;

/**
 *
 * @author Joao
 */
public class MoveInteractor extends Interactor {

    private float draggingDistX = 0;
    private float draggingDistY = 0;

    public MoveInteractor(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        Point vMsPt = canvas.getMousePosition();
        if (vMsPt == null || canvas.getShapeSelector() == null) {
            return;
        }

        //Save the selected points  inside the shape
        draggingDistX = (float) (vMsPt.getX()
                - canvas.getShapeSelector().getShapeSource().getX());
        draggingDistY = (float) (vMsPt.getY()
                - canvas.getShapeSelector().getShapeSource().getY());

    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        if (canvas.getShapeSelector() != null) {
            if (canvas.getShapeSelector().isMoving()) {
                canvas.getShapeSelector().setMoving(false);
                //Create a new selector for selected shape  
                canvas.setShapeSelector(new Selector(
                        canvas.getShapeSelector().getShapeSource(), canvas));
                canvas.getShapeSelector().setVisible(true);
                //Update the canvas
                canvas.repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        Point vMsPt = canvas.getMousePosition();
        if (vMsPt == null || canvas.getSelectedShape() == null) {
            return;
        }
        final int vCursorType = canvas.getCursor().getType();

        if (vCursorType == Cursor.MOVE_CURSOR) {
            canvas.getShapeSelector().setMoving(true);
            float vX = (float) vMsPt.getX();
            float vY = (float) vMsPt.getY();
            final float vNewX = vX - draggingDistX;
            final float vNewY = vY - draggingDistY;

            //Limits the new position inside the Canvas
            if (vNewX <= 0 || vNewX >= canvas.getWidth()
                    || vNewY <= 0 || vNewY > canvas.getHeight()) {
                //return;
            }

            canvas.getShapeSelector().getShapeSource().setX(vNewX);
            canvas.getShapeSelector().getShapeSource().setY(vNewY);
            //Disables the selector painting 
            canvas.getShapeSelector().setVisible(false);
            //Update the canvas
            canvas.repaint();
        }
    }

}
