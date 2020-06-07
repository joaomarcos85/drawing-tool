package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import drawingtool.io.ShapeData;
import drawingtool.log.Log;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Joao
 */
public class Image extends Shape {

    private Rectangle2D shape;
    private float angle;
    private boolean resizable = true;
    private BufferedImage img;

    public Image(ShapeData shapeData) {
        super(shapeData);
    }

    public Image() {
        super(new ShapeData());
        createShape();
    }

    public Image(File imgFile, float x, float y, float w, float h) throws
            IOException {
        super(new ShapeData());
        this.createShape(x, y, w, h);
        this.loadImageFile(imgFile);
    }

    public Image(File imgFile, float x, float y, float w, float h, float angle)
            throws IOException {
        this(imgFile, x, y, w, h);
        this.setAngle(angle);
    }

    public Image(InputStream in, float x, float y, float w, float h)
            throws IOException {
        super(new ShapeData());
        this.createShape(x, y, w, h);
        this.loadImageInputStream(in);
    }

    public Image(InputStream in, float x, float y, float w, float h, float angle)
            throws IOException {
        this(in, x, y, w, h);
        this.setAngle(angle);
    }

    private void createShape() {
        this.createShape(0, 0, 0, 0);
    }

    @Override
    protected void createShape(float x, float y, float w, float h) {
        shape = new Rectangle2D.Float(x, y, w, h);
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
        return ParserConstants.TYPE_IMAGE;
    }

    private void loadImageFile(File imgFile) throws FileNotFoundException,
            IOException {
        FileInputStream fis = new FileInputStream(imgFile);
        this.loadImageInputStream(fis);
        fis.close();
    }

    private void loadImageInputStream(InputStream in) throws IOException {
        img = ImageIO.read(in);
    }

    public BufferedImage getImg() {
        return img;
    }

    @Override
    public void paint(Graphics2D g2) {
        if (this.img == null) {
            return;
        }

        g2 = (Graphics2D) g2.create();

        float centerX = 0;
        float centerY = 0;

        if (isResizing()) {
            centerX = Xaxis;
            centerY = Yaxis;
        } else {
            centerX = getX() + (getWidth() / 2);
            centerY = getY() + (getHeight() / 2);
        }
        //Rotate the image
        g2.rotate(Math.toRadians(getAngle()), centerX, centerY);
        //Draw the image
        g2.drawImage(img, (int) this.getX(), (int) this.getY(),
                (int) this.getWidth(), (int) this.getHeight(), null);
        g2.dispose();
    }

}
