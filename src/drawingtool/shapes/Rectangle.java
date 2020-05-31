package drawingtool.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Joao
 */
public class Rectangle extends Shape {

    private Rectangle2D.Float rect;
    private float angle = 0;
    private boolean resizable = true;

    public Rectangle() {
        rect = new Rectangle2D.Float();
    }

    public Rectangle(float x, float y, float w, float h) {
        rect = new Rectangle2D.Float(x, y, w, h);
    }

    @Override
    public java.awt.Shape getShape() {
        return rect;
    }

    @Override
    public float getX() {
        return (float) rect.getX();
    }

    @Override
    public float getY() {
        return (float) rect.getY();
    }

    @Override
    public float getWidth() {
        return (float) rect.getWidth();
    }

    @Override
    public float getHeight() {
        return (float) rect.getHeight();
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public void setX(float x) {
        rect.setRect(x, getY(), getWidth(), getHeight());
    }

    @Override
    public void setY(float y) {
        rect.setRect(getX(), y, getWidth(), getHeight());
    }

    @Override
    public void setWidth(float width) {
        rect.setRect(getX(), getY(), width, getHeight());
    }

    @Override
    public void setHeight(float height) {
        rect.setRect(getX(), getY(), getWidth(), height);
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
