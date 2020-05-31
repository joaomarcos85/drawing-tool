package drawingtool.selector;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Joao
 */
public class NorthResizer extends Resizer {

    public NorthResizer(Selector oSelector) {
        super(oSelector);
        pointShape = createShape();
    }

    @Override
    public Shape createShape() {
        Rectangle2D selectorShape = selector.getSeletorShape().getBounds2D();
        Rectangle2D rectangle2D = new Rectangle2D.Float(
                (float) (selectorShape.getX() + (selectorShape.getWidth() / 2)
                - (getPointSize() / 2)),
                (float) (selectorShape.getY() - (getPointSize() / 2)),
                getPointSize(), getPointSize());
        affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(selector.getShapeSource().getAngle()),
                selectorShape.getX() + (selectorShape.getWidth() / 2),
                selectorShape.getY() + selectorShape.getHeight() / 2);

        return (affineTransform.createTransformedShape(rectangle2D));
    }

    @Override
    public int getOriginalCursor() {
        return Cursor.N_RESIZE_CURSOR;
    }

    @Override
    public Shape getShape() {
        return pointShape;
    }

    @Override
    public void resize(Point msPt) throws NoninvertibleTransformException {
        super.resize(msPt);
        if (selector.getShapeSource() == null) {
            return;
        }

        //Converts the mouse position considering the angle of the shape
        Point2D convertedPt = convertToAngle(msPt);

        float yh = selector.getShapeSource().getY() + selector.getShapeSource().getHeight();
        float height = (float) (yh - convertedPt.getY());

        float y = (yh - height);

        //Limits the minimum height
        if (height <= minimumSourceSize) {
            return;
        }

        selector.getShapeSource().setY(y);
        selector.getShapeSource().setHeight(height);
    }
}
