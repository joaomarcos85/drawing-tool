package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import drawingtool.io.ShapeData;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Joao
 */
public class Text extends Shape {

    private java.awt.Shape shape;
    private float angle;
    private boolean resizable = true;
    private float x;
    private float y;
    private float width;
    private float height;
    private String text;
    private String fontName;
    private int fontStyle;
    private Font font = null;

    public Text(ShapeData shapeData) throws Exception {
        super(shapeData);
    }

    public Text() throws Exception {
        super(new ShapeData());
    }

    public Text(float x, float y, float w, float h) throws Exception {
        super(new ShapeData());
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override
    protected void createShape(float x, float y, float width, float height) {
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

    private Font getFont() {
        //Checks if the font is already created
        if (this.font == null || !this.font.getFontName().equals(this.getFontName())
                || this.font.getStyle() != this.fontStyle) {
            //Creates a new Font
            font = new Font(this.getFontName(), this.getFontStyle(), 96);
        }
        return font;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
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
    public String getTypeName() {
        return ParserConstants.TYPE_TEXT;
    }

    @Override
    public ShapeData getShapeData() throws Exception {
        ShapeData data = super.getShapeData();
        data.put(ParserConstants.TEXT, this.getText());
        data.put(ParserConstants.FONT_NAME, this.getFontName());
        data.put(ParserConstants.FONT_STYLE, this.getFontStyle());

        return data;
    }

    @Override
    protected void loadShapeData(ShapeData shapeData) throws Exception {
        super.loadShapeData(shapeData);
        this.setText(shapeData.getString(ParserConstants.TEXT, "Example"));
        this.setFontName(shapeData.getString(ParserConstants.FONT_NAME, "Arial"));
        this.setFontStyle(shapeData.getInt(ParserConstants.FONT_STYLE, Font.PLAIN));
    }

    @Override
    public void paint(Graphics2D g2) {
        g2 = (Graphics2D) g2.create();
        //Paint the common attributes
        this.paintCommonAttributes(g2);

        this.createShape(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        Font font = this.getFont();
        g2.setFont(font);
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics metrics = font.getLineMetrics(this.getText(), frc);
        // Try omitting the descent from the height variable.
        float height = metrics.getAscent() + metrics.getDescent();
        double width = font.getStringBounds(this.getText(), frc).getWidth();
        float w2 = getWidth();
        float h2 = getHeight();
        g2.translate(x, y);
        g2.scale(w2 / width, h2 / height);
        g2.drawString(this.getText(), 0, (metrics.getAscent() - 1));
        g2.dispose();
    }

}
