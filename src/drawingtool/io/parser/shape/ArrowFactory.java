package drawingtool.io.parser.shape;

import drawingtool.io.ShapeData;
import drawingtool.io.parser.ShapeFactory;
import drawingtool.shapes.Arrow;
import drawingtool.shapes.Shape;

/**
 *
 * @author Joao
 */
public class ArrowFactory extends ShapeFactory {

    @Override
    public Shape create(ShapeData shapeData) throws Exception {
        Arrow arrow = new Arrow(shapeData);
        return arrow;
    }

}
