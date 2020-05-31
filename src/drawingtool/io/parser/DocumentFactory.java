package drawingtool.io.parser;

import drawingtool.io.Document;
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

    protected final HashMap<String, ShapeFactory> factories = new HashMap<>();

    public abstract Document createDocument(InputStream is) throws FactoryException;

    protected abstract Object parse(InputStream is);

}
