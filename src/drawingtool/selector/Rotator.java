package drawingtool.selector;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Joao
 */
public class Rotator {

    Selector selector;
    Shape shape;

    public Rotator(Selector selector) {
        this.selector = selector;
        this.createShape();
    }

    private void createShape() {
        float rotatorSize = 20;
        Rectangle2D selectorShape = selector.getSeletorShape().getBounds2D();
        float x = (float) (selectorShape.getX() + (selectorShape.getWidth() / 2) - (rotatorSize / 2));
        float y = (float) (selectorShape.getY() - 10 - rotatorSize);
        float w = rotatorSize;
        float h = rotatorSize;
        shape = new Ellipse2D.Float(x, y, w, h);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(selector.getShapeSource().getAngle()),
                selectorShape.getX() + (selectorShape.getWidth() / 2),
                selectorShape.getY() + (selectorShape.getHeight() / 2));
        shape = affineTransform.createTransformedShape(shape);
    }

    public Shape getShape() {
        return shape;
    }
    
    public boolean contains(Point2D p) {
        //Converts the mouse position disregarding the zoom
        AffineTransform bt = (AffineTransform) selector.
                getCanvas().getAffineTransform().clone();
        p = bt.transform(p, null);
        return this.getShape().contains(p);
    }

}
