package drawingtool.io.parser.json;

import drawingtool.io.JSONDocument;
import drawingtool.io.ParserConstants;
import drawingtool.io.parser.ShapeFactory;
import drawingtool.io.parser.DocumentFactory;
import drawingtool.io.parser.FactoryException;
import drawingtool.io.parser.json.ShapeDataJSON;
import drawingtool.shapes.Shape;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Joao
 */
public class JSONDocumentFactory extends DocumentFactory {

    @Override
    protected JSONObject parse(InputStream is) {
        JSONTokener tokener = new JSONTokener(is);
        JSONObject jsonFile = new JSONObject(tokener);

        return jsonFile;
    }

    @Override
    public JSONDocument createDocument(InputStream is) throws FactoryException {
        try {
            JSONDocument jsonDocument;

            JSONObject parsedDocument = parse(is);

            String version = parsedDocument.getString(ParserConstants.KEY_VERSION);
            ArrayList<Shape> shapes = getShapes(
                    parsedDocument.getJSONArray(ParserConstants.KEY_SHAPES));

            jsonDocument = new JSONDocument(shapes, version);

            return jsonDocument;
        } catch (Exception ex) {
            throw new FactoryException(ex);
        }
    }

    private ArrayList<Shape> getShapes(JSONArray jsonShapes) throws FactoryException {
        try {
            ShapeDataJSON jsonShape;
            String shapeTypeName;
            ShapeFactory shapeFactory;
            ArrayList<Shape> shapes = new ArrayList();

            for (Object obj : jsonShapes) {

                jsonShape = new ShapeDataJSON((JSONObject) obj);
                //Get the shape type name
                shapeTypeName = jsonShape.getString(ParserConstants.TYPE_NAME);
                //Get the the Factory for the shape type name
                shapeFactory = factories.get(shapeTypeName);
                //Check if a factory has been found
                if (shapeFactory == null) {
                    throw new FactoryException("Invalid shape type: " + shapeTypeName);
                }
                //Creates the new shape
                shapes.add(shapeFactory.create(jsonShape));

            }

            //Return the created shapes
            return shapes;
        } catch (Exception ex) {
            throw new FactoryException(ex);
        }
    }
}
