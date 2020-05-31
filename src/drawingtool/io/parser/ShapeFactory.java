package drawingtool.io.parser;

import drawingtool.io.ParserConstants;
import drawingtool.shapes.Shape;

/**
 *
 * @author Joao
 */
public abstract class ShapeFactory {

    public abstract Shape create(ShapeData shapeData);

    protected final void setCommonAttributes(Shape shape, ShapeData shapeData) {
        //Gets the default shape data
        float x = shapeData.getFloat(ParserConstants.X, 0);
        float y = shapeData.getFloat(ParserConstants.Y, 0);
        float width = shapeData.getFloat(ParserConstants.WIDTH, 100);
        float height = shapeData.getFloat(ParserConstants.HEIGHT, 100);
        float angle = shapeData.getFloat(ParserConstants.ANGLE, 0);

        //Sets the datas
        shape.setX(x);
        shape.setY(y);
        shape.setWidth(width);
        shape.setHeight(height);
        shape.setAngle(angle);
    }

}
