package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import org.json.JSONObject;

/**
 *
 * @author Joao
 */
public class Text extends Shape {

    private java.awt.Shape shape;
    private float angle = 0;
    private boolean resizable = true;
    private Font font = new Font("Arial", Font.PLAIN, 96);
    private String text = " oao marcos";
    private float x = 0;
    private float y = 0;
    private float width = 100;
    private float height = 20;
    private float fontSize = 10;

    public Text() {

    }

    public Text(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    private void createShape(Graphics2D g2) {
        shape = new Rectangle2D.Float(x, y, width, height);
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

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getFontName() {
        return this.font.getFontName();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setAttributes(JSONObject shapeJSON) {
        shapeJSON.put(ParserConstants.TEXT, this.getText());
        shapeJSON.put(ParserConstants.FONT_NAME, this.getFontName());
    }

    @Override
    public String getTypeName() {
        return ParserConstants.TYPE_TEXT;
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

        createShape(g2);

        g2.setFont(getFont());
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics metrics = font.getLineMetrics(getText(), frc);
        // Try omitting the descent from the height variable.
        float height = metrics.getAscent() + metrics.getDescent();
        double width = font.getStringBounds(getText(), frc).getWidth();
        float w2 = getWidth();
        float h2 = getHeight();
        g2.translate(x, y);
        g2.scale(w2 / width, h2 / height);
        g2.drawString(getText(), 0, (metrics.getAscent() - 1));
        g2.dispose();
    }

}
