package drawingtool.interactor;

import drawingtool.shapes.Shape;
import drawingtool.ui.Canvas;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Joao
 */
public class AddShapeInteractor extends Interactor {

    Shape pendingShape;

    public AddShapeInteractor(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        Point msPt = this.canvas.getMousePosition();
        if (this.pendingShape == null || msPt == null) {
            return;
        }
        this.pendingShape.setX(msPt.x);
        this.pendingShape.setY(msPt.y);

        //Add the shape to the canvas
        this.canvas.addShape(pendingShape);
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        Point msPt = this.canvas.getMousePosition();
        if (this.pendingShape == null || msPt == null) {
            return;
        }

        float newWidth = (float) (msPt.getX() - this.pendingShape.getX());
        float newHeight = (float) (msPt.getY() - this.pendingShape.getY());

        this.pendingShape.setWidth(newWidth);
        this.pendingShape.setHeight(newHeight);

        this.canvas.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        if (pendingShape != null) {
            this.setPendingShape(null);
        }
    }

    public void setPendingShape(Shape pendingShape) {
        this.pendingShape = pendingShape;
    }

}
