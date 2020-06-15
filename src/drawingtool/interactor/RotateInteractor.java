package drawingtool.interactor;

import drawingtool.selector.Selector;
import drawingtool.ui.Canvas;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Joao
 */
public class RotateInteractor extends Interactor {

    public RotateInteractor(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        Point msPt = canvas.getMousePosition();
        if (canvas.getShapeSelector() == null || msPt == null
                || canvas.getCursor().getType() != Cursor.HAND_CURSOR) {
            return;
        }

        //Disables the selector painting
        canvas.getShapeSelector().setVisible(false);

        //Gets the center position of the shape for X
        double cX = canvas.getShapeSelector().getShapeSource().getX()
                + (canvas.getShapeSelector().getShapeSource().getWidth() / 2);
        //Gets the center position of the shape for Y
        double cY = canvas.getShapeSelector().getShapeSource().getY()
                + (canvas.getShapeSelector().getShapeSource().getHeight() / 2);
        //Calculate the delta X
        double deltaX = cX - msPt.getX();
        //Calculate the delta Y
        double deltaY = cY - msPt.getY();
        //Calculate the new angle in radians
        double angle = -Math.atan2(deltaX, deltaY);
        //Convert the radians value to degrees
        angle = Math.toDegrees(angle);
        //Ensures that the value will always be positive
        angle = (angle + 360) % 360;
        //Set the new shape angle
        canvas.getShapeSelector().getShapeSource().setAngle((int) angle);
        //Update the canvas
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        if (canvas.getShapeSelector() != null
                && !canvas.getShapeSelector().getShapeSource().isResizing()) {
            //Create a new selector for selected shape  
            canvas.setShapeSelector(new Selector(
                    canvas.getShapeSelector().getShapeSource(), canvas));
            canvas.getShapeSelector().setVisible(true);
            //Update the canvas
            canvas.repaint();
        }
    }

}
