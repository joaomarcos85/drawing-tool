package drawingtool.io.parser.shape;

import drawingtool.io.ShapeData;
import drawingtool.io.parser.ShapeFactory;
import drawingtool.shapes.Rectangle;
import drawingtool.shapes.Shape;

/**
 *
 * @author Joao
 */
public class RectFactory extends ShapeFactory {

    @Override
    public Shape create(ShapeData shapeData) {
        Rectangle arrow = new Rectangle(shapeData);
        return arrow;
    }

}
