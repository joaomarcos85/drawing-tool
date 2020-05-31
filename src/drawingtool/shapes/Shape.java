package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import org.json.JSONObject;

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

    public Shape() {
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

    public abstract void setAttributes(JSONObject shapeJSON);

    public abstract String getTypeName();

    public final JSONObject getJSON() {
        JSONObject shapeJSON = new JSONObject();
        setCommonAttributes(shapeJSON);
        setAttributes(shapeJSON);
        return shapeJSON;
    }

    private void setCommonAttributes(JSONObject shapeJSON) {
        shapeJSON.put(ParserConstants.TYPE_NAME, this.getTypeName());
        shapeJSON.put(ParserConstants.X, this.getX());
        shapeJSON.put(ParserConstants.Y, this.getY());
        shapeJSON.put(ParserConstants.WIDTH, this.getWidth());
        shapeJSON.put(ParserConstants.HEIGHT, this.getHeight());
        shapeJSON.put(ParserConstants.ANGLE, this.getAngle());
    }

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
        } else {
            //float newX = (getX() + (getWidth() / 2)) - Xaxis;

            //setX(getX()+newX);
        }
    }

    public Color getBackgroundColor() {
        return bgColor;
    }

    public void setBackgroundColor(Color bgColor) {
        this.bgColor = bgColor;
    }

}
