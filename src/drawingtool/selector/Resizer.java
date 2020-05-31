package drawingtool.selector;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 *
 * @author Joao
 */
public abstract class Resizer {

    public float pointSize = 10;
    final int minimumSourceSize = 7;
    Selector selector;
    Shape pointShape;

    RelativeResizeCursor relativeCursor = new RelativeResizeCursor();
    AffineTransform affineTransform;

    public Resizer(Selector selector) {
        this.selector = selector;
    }

    public abstract Shape createShape();

    public abstract int getOriginalCursor();

    public abstract Shape getShape();

    public boolean contains(Point2D p) {
        //Converts the mouse position disregarding the zoom
        AffineTransform bt = (AffineTransform) selector.
                getCanvas().getAffineTransform().clone();
        p = bt.transform(p, null);
        return getShape().contains(p);
    }

    public void resize(Point msPt) throws NoninvertibleTransformException{
        //Indica que houve modificação no JSVGCanvas
//        vSelector.getSVGCanvas().setModified(true);
    }

    public int getCursor() {
        return relativeCursor.getAngledCursor(getOriginalCursor(),
                selector.getShapeSource().getAngle());
    }

    public float getPointSize() {
        return pointSize;
    }

    public void setPointSize(float pointSize) {
        this.pointSize = pointSize;
    }

    /**
     * Converts the point considering the angle of the current shape
     *
     * @param pt The point
     * @return java.awt.geom.Point2D - The converted Point
     */
    public Point2D convertToAngle(Point2D pt) throws NoninvertibleTransformException {
        AffineTransform bt = new AffineTransform();
        bt.rotate(Math.toRadians(selector.getShapeSource().getAngle()),
                selector.getShapeSource().Xaxis,
                selector.getShapeSource().Yaxis);

        return bt.createInverse().transform(pt, null);
    }

}
