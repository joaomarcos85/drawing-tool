package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import drawingtool.io.ShapeData;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 *
 * @author Joao
 */
public abstract class Shape implements java.io.Serializable {

    private boolean isSelected = false;
    private boolean resizing = false;
    public float Xaxis = 0;
    public float Yaxis = 0;
    private Color bgColor = Color.yellow;
    private ShapeData shapeData;

//    protected Shape() {
//        this(new ShapeData());
//    }
    public Shape(ShapeData shapeData) {
        this.shapeData = shapeData;
        this.loadShapeData(shapeData);
    }

    public abstract java.awt.Shape getShape();

    public abstract float getX();

    public abstract void setX(float x);

    public abstract float getY();

    public abstract void setY(float y);

    public abstract float getWidth();

    public abstract void setWidth(float width);

    public abstract float getHeight();

    public abstract void setHeight(float height);

    public abstract float getAngle();

    public abstract void setAngle(float angle);

    public abstract boolean isResizable();

    public abstract void paint(Graphics2D g2);

    public abstract String getTypeName();

    protected abstract void createShape(float x, float y, float width, float height);

    public boolean contains(Point2D p) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(getAngle()),
                getX() + (getWidth() / 2),
                getY() + getHeight() / 2);

        return (affineTransform.createTransformedShape(this.getShape()).contains(p));
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isResizing() {
        return resizing;
    }

    public void setResizing(boolean isResizing) {
        this.resizing = isResizing;

        if (isResizing) {
            Xaxis = getX() + (getWidth() / 2);
            Yaxis = getY() + (getHeight() / 2);
        }
    }

    public Color getBackgroundColor() {
        return bgColor;
    }

    public void setBackgroundColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public ShapeData getShapeData() {
        this.shapeData.put(ParserConstants.X, this.getX());
        this.shapeData.put(ParserConstants.Y, this.getY());
        this.shapeData.put(ParserConstants.WIDTH, this.getWidth());
        this.shapeData.put(ParserConstants.HEIGHT, this.getHeight());
        this.shapeData.put(ParserConstants.ANGLE, this.getAngle());
        this.shapeData.put(ParserConstants.TYPE_NAME, this.getTypeName());
        return shapeData;
    }

    protected void loadShapeData(ShapeData shapeData) {
        this.createShape(0, 0, 0, 0);
        this.setX(shapeData.getFloat(ParserConstants.X, 0));
        this.setY(shapeData.getFloat(ParserConstants.Y, 0));
        this.setWidth(shapeData.getFloat(ParserConstants.WIDTH, 0));
        this.setHeight(shapeData.getFloat(ParserConstants.HEIGHT, 0));
        this.setAngle(shapeData.getFloat(ParserConstants.ANGLE, 0));
    }

}
