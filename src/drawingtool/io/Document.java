package drawingtool.io;

import drawingtool.shapes.Shape;
import java.util.ArrayList;

/**
 *
 * @author Joao
 */
public abstract class Document {

    private ArrayList<Shape> shapes;
    private String version;

    protected Document(ArrayList<Shape> shapes, String docVersion) {
        this.shapes = shapes;
        this.version = docVersion;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public String getVersion() {
        return version;
    }

}
