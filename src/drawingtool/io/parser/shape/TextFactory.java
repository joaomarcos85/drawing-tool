package drawingtool.io.parser.shape;

import drawingtool.io.ShapeData;
import drawingtool.io.parser.ShapeFactory;
import drawingtool.shapes.Rectangle;
import drawingtool.shapes.Shape;
import drawingtool.shapes.Text;

/**
 *
 * @author Joao
 */
public class TextFactory extends ShapeFactory {

    @Override
    public Shape create(ShapeData shapeData) {
        Text arrow = new Text(shapeData);
        return arrow;
    }

}
