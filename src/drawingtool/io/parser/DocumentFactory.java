package drawingtool.io.parser;

import drawingtool.io.Document;
import drawingtool.io.ParserConstants;
import drawingtool.io.parser.shape.ArrowFactory;
import drawingtool.io.parser.shape.EllipseFactory;
import drawingtool.io.parser.shape.ImageFactory;
import drawingtool.io.parser.shape.RectFactory;
import drawingtool.io.parser.shape.TextFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 *
 * @author Joao
 */
public abstract class DocumentFactory {

    public DocumentFactory() {
        factories.put(ParserConstants.TYPE_ARROW, new ArrowFactory());
        factories.put(ParserConstants.TYPE_ELLIPSE, new EllipseFactory());
        factories.put(ParserConstants.TYPE_IMAGE, new ImageFactory());
        factories.put(ParserConstants.TYPE_RECT, new RectFactory());
        factories.put(ParserConstants.TYPE_TEXT, new TextFactory());
    }

    protected final HashMap<String, ShapeFactory> factories = new HashMap<>();

    public abstract Document createDocument(InputStream is) throws FactoryException;

    protected abstract Object parse(InputStream is);

}
