package drawingtool.shapes;

import drawingtool.io.ParserConstants;
import drawingtool.io.ShapeData;
import drawingtool.util.Base64;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

/**
 *
 * @author Joao
 */
public class Image extends Shape {

    private Rectangle2D shape;
    private float angle;
    private boolean resizable = true;
    private BufferedImage img;

    public Image(ShapeData shapeData) throws Exception {
        super(shapeData);
    }

    public Image() throws Exception {
        super(new ShapeData());
        createShape();
    }

    public Image(File imgFile, float x, float y, float w, float h) throws
            Exception {
        super(new ShapeData());
        this.createShape(x, y, w, h);
        this.loadImageFile(imgFile);
    }

    public Image(File imgFile, float x, float y, float w, float h, float angle)
            throws Exception {
        this(imgFile, x, y, w, h);
        this.setAngle(angle);
    }

    public Image(InputStream in, float x, float y, float w, float h)
            throws Exception {
        super(new ShapeData());
        this.createShape(x, y, w, h);
        this.loadImageInputStream(in);
    }

    public Image(InputStream in, float x, float y, float w, float h, float angle)
            throws Exception {
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
    public ShapeData getShapeData() throws Exception {
        //Get the default data
        ShapeData data = super.getShapeData();
        Base64 base64encoder = new Base64();
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        //Gets the image bytes
        ImageIO.write(this.getImg(), "png", bais);
        //Encode the byte to base64
        String base64 = base64encoder.encodeToString(bais.toByteArray(), false);
        bais.close();
        //Sets the base64 data in ShapeData
        data.put(ParserConstants.IMAGE_DATA, base64);
        return data;
    }

    @Override
    protected void loadShapeData(ShapeData shapeData) throws Exception {
        super.loadShapeData(shapeData);
        Base64 base64encoder = new Base64();
        //Gets the image string base64
        String base64str = shapeData.getString(ParserConstants.IMAGE_DATA);
        //Decode the base64 to bytes
        byte[] bytes = base64encoder.decodeFast(base64str);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        /*Creates a memory cache instead of using the default cache on disk.
          The default cache is on disk and is very slow to read the first time.
          Using MemoryCacheImageInputStream is equivalent to ImageIO.setUseCache(false),
          but ImageIO.setUseCache(false) sets the in-memory cache for the program globally*/
        ImageInputStream stream = new MemoryCacheImageInputStream(bais);
        //Creates the image
        this.img = ImageIO.read(stream);
        bais.close();
    }

    @Override
    public void paint(Graphics2D g2) {
        if (this.img == null) {
            return;
        }

        g2 = (Graphics2D) g2.create();
        //Paint the common attributes
        this.paintCommonAttributes(g2);

        //Draw the image
        g2.drawImage(img, (int) this.getX(), (int) this.getY(),
                (int) this.getWidth(), (int) this.getHeight(), null);
        g2.dispose();
    }

}
