package drawingtool.interactor;

import drawingtool.ui.Canvas;
import drawingtool.log.Log;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.NoninvertibleTransformException;
import java.util.logging.Level;
import drawingtool.selector.Selector;

/**
 *
 * @author Joao
 */
public class ResizeInteractor extends Interactor {

    public ResizeInteractor(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        if (canvas.getShapeSelector() != null) {
            if (canvas.getShapeSelector().getShapeSource().isResizing()) {
                canvas.getShapeSelector().getShapeSource().setResizing(false);
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
        Point msPt = canvas.getMousePosition();
        if (msPt == null || canvas.getShapeSelector() == null) {
            return;
        }

        final int vCursorType = canvas.getCursor().getType();

        drawingtool.selector.Resizer resizer = canvas.getShapeSelector().getResizerForRotatedCursor(vCursorType);
        if (resizer != null) {
            try {
                if (!canvas.getShapeSelector().getShapeSource().isResizing()) {
                    canvas.getShapeSelector().getShapeSource().setResizing(true);
                }
                resizer.resize(msPt);
                //Disables the selector painting
                canvas.getShapeSelector().setVisible(false);
                //Update the canvas
                canvas.repaint();
            } catch (NoninvertibleTransformException ex) {
                Log.LOGGER.log(Level.SEVERE, "Error resizing the shape", ex);
            }
        }
    }
}
