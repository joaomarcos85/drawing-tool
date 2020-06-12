package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import drawingtool.io.ShapeData;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.IOException;

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
    private Color fgColor = Color.yellow;
    private ShapeData shapeData;

    public Shape(ShapeData shapeData) throws Exception {
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

    public void paintCommonAttributes(Graphics2D g2) {
        g2.setColor(this.getBackgroundColor());
    }

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

    public Color getForegroundColor() {
        return fgColor;
    }

    public void setForegroundColor(Color fgColor) {
        this.fgColor = fgColor;
    }

    public ShapeData getShapeData() throws Exception {
        this.shapeData.put(ParserConstants.X, this.getX());
        this.shapeData.put(ParserConstants.Y, this.getY());
        this.shapeData.put(ParserConstants.WIDTH, this.getWidth());
        this.shapeData.put(ParserConstants.HEIGHT, this.getHeight());
        this.shapeData.put(ParserConstants.ANGLE, this.getAngle());
        this.shapeData.put(ParserConstants.TYPE_NAME, this.getTypeName());
        this.shapeData.put(ParserConstants.BACKGROUND_COLOR, this.getBackgroundColor());
        this.shapeData.put(ParserConstants.FOREGROUND_COLOR, this.getForegroundColor());
        return shapeData;
    }

    protected void loadShapeData(ShapeData shapeData) throws Exception {
        this.createShape(0, 0, 0, 0);
        this.setX(shapeData.getFloat(ParserConstants.X, 0));
        this.setY(shapeData.getFloat(ParserConstants.Y, 0));
        this.setWidth(shapeData.getFloat(ParserConstants.WIDTH, 0));
        this.setHeight(shapeData.getFloat(ParserConstants.HEIGHT, 0));
        this.setAngle(shapeData.getFloat(ParserConstants.ANGLE, 0));
        this.setBackgroundColor(shapeData.
                getColorFromRgb(ParserConstants.BACKGROUND_COLOR, Color.black));
        this.setForegroundColor(shapeData.
                getColorFromRgb(ParserConstants.FOREGROUND_COLOR, Color.black));
    }

}
