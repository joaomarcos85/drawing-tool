package drawingtool.io.parser.json.shape;

import drawingtool.io.parser.ShapeData;
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
    public Shape create(ShapeData shapeData) {
        Ellipse ellipse = new Ellipse();
        this.setCommonAttributes(ellipse, shapeData);
        return ellipse;
    }

}
