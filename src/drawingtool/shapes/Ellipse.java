package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import drawingtool.io.ShapeData;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import org.json.JSONObject;

/**
 *
 * @author Joao
 */
public class Ellipse extends Shape {

    private Ellipse2D shape;
    private float angle;
    private boolean resizable = true;

    public Ellipse(ShapeData shapeData) {
        super(shapeData);
    }

    public Ellipse() {
        super(new ShapeData());
        createShape(0, 0, 0, 0);
    }

    public Ellipse(float x, float y, float w, float h) {
        super(new ShapeData());
        createShape(x, y, w, h);
    }

    @Override
    protected void createShape(float x, float y, float w, float h) {
        shape = new Ellipse2D.Float(x, y, w, h);
    }

    @Override
    public java.awt.Shape getShape() {
        return shape;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public boolean isResizable() {
        return resizable;
    }

    @Override
    public float getX() {
        return (float) shape.getBounds2D().getX();
    }

    @Override
    public void setX(float x) {
        createShape(x, getY(), getWidth(), getHeight());
    }

    @Override
    public float getY() {
        return (float) shape.getBounds2D().getY();
    }

    @Override
    public void setY(float y) {
        createShape(getX(), y, getWidth(), getHeight());
    }

    @Override
    public float getWidth() {
        return (float) shape.getBounds2D().getWidth();
    }

    @Override
    public void setWidth(float width) {
        createShape(getX(), getY(), width, getHeight());
    }

    @Override
    public float getHeight() {
        return (float) shape.getBounds2D().getHeight();
    }

    @Override
    public void setHeight(float height) {
        createShape(getX(), getY(), getWidth(), height);
    }

    @Override
    public String getTypeName() {
        return ParserConstants.TYPE_ELLIPSE;
    }

    @Override
    public void paint(Graphics2D g2) {
        g2 = (Graphics2D) g2.create();
        g2.setColor(Color.red);

        float centerX = 0;
        float centerY = 0;

        if (isResizing()) {
            centerX = Xaxis;
            centerY = Yaxis;
        } else {
            centerX = getX() + (getWidth() / 2);
            centerY = getY() + (getHeight() / 2);
        }

        g2.rotate(Math.toRadians(getAngle()),
                centerX,
                centerY);

        g2.fill(getShape());
        g2.dispose();
    }

}
