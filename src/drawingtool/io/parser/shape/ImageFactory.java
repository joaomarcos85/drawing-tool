package drawingtool.io.parser.shape;

import drawingtool.io.ShapeData;
import drawingtool.io.parser.ShapeFactory;
import drawingtool.shapes.Image;
import drawingtool.shapes.Shape;

/**
 *
 * @author Joao
 */
public class ImageFactory extends ShapeFactory {

    @Override
    public Shape create(ShapeData shapeData) throws Exception {
        Image image = new Image(shapeData);
        return image;
    }

}
