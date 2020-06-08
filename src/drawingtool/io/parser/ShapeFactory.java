package drawingtool.io.parser;

import drawingtool.io.ShapeData;
import drawingtool.shapes.Shape;

/**
 *
 * @author Joao
 */
public abstract class ShapeFactory {

    public abstract Shape create(ShapeData shapeData) throws Exception;

}
