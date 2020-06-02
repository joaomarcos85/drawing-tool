package drawingtool.io.transformer.json;

import drawingtool.io.Document;
import drawingtool.io.ParserConstants;
import drawingtool.io.transformer.DocumentTransformer;
import drawingtool.shapes.Shape;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Joao
 */
public class JSONDocumentTransformer extends DocumentTransformer {

    private final String version = "1.0";

    @Override
    public void transform(Document document, Writer writer) {
        JSONObject jsonResult = new JSONObject();
        ArrayList<Shape> shapes = document.getShapes();

        jsonResult.put(ParserConstants.KEY_VERSION, version);

        JSONArray jsonShapes = new JSONArray();
        for (Shape shape : shapes) {
            jsonShapes.put(getJSONShape(shape));
        }

        jsonResult.put(ParserConstants.KEY_SHAPES, jsonShapes);

        jsonResult.write(writer);
        //System.out.println(jsonResult.toString());
    }

    private JSONObject getJSONShape(Shape shape) {
        JSONObject jsonShape = new JSONObject(shape.getShapeData());

        return jsonShape;
    }

}
