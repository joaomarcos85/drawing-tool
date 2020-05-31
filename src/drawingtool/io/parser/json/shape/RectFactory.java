package drawingtool.io.parser.json.shape;

import drawingtool.io.parser.ShapeData;
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
        Rectangle arrow = new Rectangle();
        this.setCommonAttributes(arrow, shapeData);
        return arrow;
    }

}
