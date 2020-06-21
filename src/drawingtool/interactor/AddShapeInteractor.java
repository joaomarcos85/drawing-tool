package drawingtool.interactor;

import drawingtool.shapes.Shape;
import drawingtool.ui.Canvas;
import java.awt.Cursor;
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
        this.pendingShape.setWidth(0);
        this.pendingShape.setHeight(0);
        //Add the shape to the canvas without repaint
        this.canvas.addShape(pendingShape, false);
        this.canvas.setCursor(Cursor.
                getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
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
        if (pendingShape == null) {
            return;
        }

        if (this.pendingShape.getWidth() <= 0) {
            this.pendingShape.setWidth(100);
        }

        if (this.pendingShape.getHeight() <= 0) {
            this.pendingShape.setHeight(100);
        }
        //Update the canvas
        this.canvas.repaint();
        this.setPendingShape(null);
        this.canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void setPendingShape(Shape pendingShape) {
        this.pendingShape = pendingShape;
    }

    public Shape getPendingShape() {
        return pendingShape;
    }

}
