package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import drawingtool.io.ShapeData;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Joao
 */
public class Arrow extends Shape {

    private GeneralPath shape;
    private float angle;
    private boolean resizable = true;

    public Arrow(ShapeData shapeData) throws Exception {
        super(shapeData);
    }

    public Arrow() throws Exception {
        super(new ShapeData());
        createShape();
    }

    public Arrow(float x, float y, float w, float h) throws Exception {
        super(new ShapeData());
        createShape(x, y, w, h);
    }

    public Arrow(float x, float y, float w, float h, float angle) throws Exception {
        this(x, y, w, h);
        setAngle(angle);
    }

    private void createShape() {
        shape = new GeneralPath();
    }

    @Override
    protected void createShape(float x, float y, float w, float h) {
        createShape();
        shape.moveTo(x, y + (h / 3));
        shape.lineTo(x + (w / 3) + (w / 3), y + (h / 3));
        shape.lineTo(x + (w / 3) + (w / 3), y);
        shape.lineTo(x + w, y + (h / 2));
        shape.lineTo(x + (w / 3) + (w / 3), y + h);
        shape.lineTo(x + (w / 3) + (w / 3), y + (h / 3) + (h / 3));
        shape.lineTo(x, y + (h / 3) + (h / 3));
        shape.lineTo(x, y + (h / 3));
        shape.closePath();

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
        return ParserConstants.TYPE_ARROW;
    }

    @Override
    public void paint(Graphics2D g2) {
        g2 = (Graphics2D) g2.create();
        //Paint the common attributes
        this.paintCommonAttributes(g2);

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
