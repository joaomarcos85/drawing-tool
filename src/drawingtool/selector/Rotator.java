package drawingtool.selector;

import drawingtool.log.Log;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author Joao
 */
public class Rotator {

    Selector selector;
    Shape shape;
    private static BufferedImage rotateIcon;
    private AffineTransform affineTransform;

    public Rotator(Selector selector) {
        this.selector = selector;
        this.loadRotateIcon();
        this.createShape();
    }

    private void createShape() {
        Rectangle2D selectorShape = selector.getSeletorShape().getBounds2D();
        float x = (float) (selectorShape.getX()
                + (selectorShape.getWidth() / 2) - (rotateIcon.getWidth() / 2));
        float y = (float) (selectorShape.getY() - 10 - rotateIcon.getHeight());
        float w = rotateIcon.getWidth();
        float h = rotateIcon.getHeight();
        shape = new Ellipse2D.Float(x, y, w, h);
        affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(selector.getShapeSource().getAngle()),
                selectorShape.getX() + (selectorShape.getWidth() / 2),
                selectorShape.getY() + (selectorShape.getHeight() / 2));
        shape = affineTransform.createTransformedShape(shape);
    }

    private void loadRotateIcon() {
        //Checks if the icon is already loaded
        if (rotateIcon == null) {
            try {
                //Load the rotate icon
                ImageInputStream stream = new MemoryCacheImageInputStream(
                        this.getClass().getResourceAsStream(
                                "/drawingtool/resource/rotate-icon.png"));
                rotateIcon = ImageIO.read(stream);
            } catch (IOException ex) {
                Log.LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    public Shape getShape() {
        return shape;
    }

    public boolean contains(Point2D p) {
        //Converts the mouse position disregarding the zoom
        AffineTransform bt = (AffineTransform) selector.
                getCanvas().getAffineTransform().clone();
        p = bt.transform(p, null);
        return this.getShape().contains(p);
    }

    public void paint(Graphics2D g2) {
        g2 = (Graphics2D) g2.create();
        Rectangle2D selectorShape = selector.getSeletorShape().getBounds2D();
        int x = (int) (selectorShape.getX()
                + (selectorShape.getWidth() / 2) - (rotateIcon.getWidth() / 2));
        int y = (int) (selectorShape.getY() - 10 - rotateIcon.getHeight());
        int w = rotateIcon.getWidth();
        int h = rotateIcon.getHeight();

        g2.transform(affineTransform);
        g2.drawImage(rotateIcon, x, y,
                w, h, null);
    }

}
