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
public class SouthWestResizer extends Resizer {

    public SouthWestResizer(Selector oSelector) {
        super(oSelector);
        pointShape = createShape();
    }

    @Override
    public Shape createShape() {
        Rectangle2D selectorShape = selector.getSeletorShape().getBounds2D();
        Rectangle2D rectangle2D = new Rectangle2D.Float(
                (float) (selectorShape.getX() - (getPointSize() / 2)),
                (float) (selectorShape.getY() + selectorShape.getHeight()
                - (getPointSize() / 2)),
                getPointSize(), getPointSize());
        affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(selector.getShapeSource().getAngle()),
                selectorShape.getX() + (selectorShape.getWidth() / 2),
                selectorShape.getY() + selectorShape.getHeight() / 2);

        return (affineTransform.createTransformedShape(rectangle2D));
    }

    @Override
    public int getOriginalCursor() {
        return Cursor.SW_RESIZE_CURSOR;
    }

    @Override
    public Shape getShape() {
        return pointShape;
    }

    @Override
    public void resize(Point msPt) throws NoninvertibleTransformException {
        super.resize(msPt);

        //Executa o Resizer para o Sul
        selector.getResizerForOriginalCursor(Cursor.S_RESIZE_CURSOR).resize(msPt);
        //Executa o Resizer para o Oeste
        selector.getResizerForOriginalCursor(Cursor.W_RESIZE_CURSOR).resize(msPt);
    }

}
