package drawingtool.io.parser.shape;

import drawingtool.io.ShapeData;
import drawingtool.io.parser.ShapeFactory;
import drawingtool.shapes.Ellipse;
import drawingtool.shapes.Rectangle;
import drawingtool.shapes.Shape;

/**
 *
 * @author Joao
 */
public class EllipseFactory extends ShapeFactory {

    @Override
    public Shape create(ShapeData shapeData) throws Exception {
        Ellipse ellipse = new Ellipse(shapeData);
        return ellipse;
    }

}
