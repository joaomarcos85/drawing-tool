package drawingtool.selector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import drawingtool.ui.Canvas;

/**
 *
 * @author Joao
 */
public class Selector {

    private Resizer northResizerShape;
    private Resizer southResizerShape;
    private Resizer eastResizerShape;
    private Resizer westResizerShape;
    private Resizer northEastResizerShape;
    private Resizer northWestResizerShape;
    private Resizer southEastResizerShape;
    private Resizer southWestResizerShape;
    private Rotator rotator;
    private drawingtool.shapes.Shape shapeSource;
    private final ArrayList<Resizer> resizers = new ArrayList();
    private java.awt.Shape seletorShape;
    private boolean visible = true;
    private boolean moving = false;
    private Canvas canvas;

    public Selector(drawingtool.shapes.Shape shape, Canvas canvas) {
        this.shapeSource = shape;
        this.canvas = canvas;
        this.loadSelector();
    }

    public void reloadSelector() {
        loadSelector();
    }

    public void updateSelectorZoom() {
        reloadSelector();
    }

    public void loadSelector() {
        createSelectorShape();
        northResizerShape = new NorthResizer(this);
        southResizerShape = new SouthResizer(this);
        eastResizerShape = new EastResizer(this);
        westResizerShape = new WestResizer(this);
        northEastResizerShape = new NorthEastResizer(this);
        northWestResizerShape = new NorthWestResizer(this);
        southEastResizerShape = new SouthEastResizer(this);
        southWestResizerShape = new SouthWestResizer(this);
        this.rotator = new Rotator(this);

        resizers.clear();
        resizers.add(northResizerShape);
        resizers.add(southResizerShape);
        resizers.add(eastResizerShape);
        resizers.add(westResizerShape);
        resizers.add(northEastResizerShape);
        resizers.add(northWestResizerShape);
        resizers.add(southEastResizerShape);
        resizers.add(southWestResizerShape);
    }

    public ArrayList<Shape> getPointsShapes() {
        ArrayList<Shape> shape = new ArrayList();
        for (Resizer vResizer : getResizers()) {
            shape.add(vResizer.getShape());
        }
        return shape;
    }

    public ArrayList<Resizer> getResizers() {
        return resizers;
    }

    private void createSelectorShape() {
        Rectangle2D rectangle2D = new Rectangle2D.Float(
                getShapeSource().getX() * canvas.getZoom(),
                getShapeSource().getY() * canvas.getZoom(),
                getShapeSource().getWidth() * canvas.getZoom(),
                getShapeSource().getHeight() * canvas.getZoom());

        this.seletorShape = rectangle2D;
    }

    public Shape getSeletorShape() {
        return seletorShape;
    }

    public drawingtool.shapes.Shape getShapeSource() {
        return shapeSource;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean paintable) {
        this.visible = paintable;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getCursor(Point oMousePoint) {
        //Checks if the selected shape is resizable
        if (shapeSource.isResizable()) {
            //Checks if the cursor is over one of the resize points
            for (Resizer resizer : getResizers()) {
                if (resizer.contains(oMousePoint)) {
                    return resizer.getCursor();
                }
            }
        }
        //Checks if the cursor is inside the selected shape
        if (this.getShapeSource().contains(oMousePoint)) {
            return Cursor.MOVE_CURSOR;
        }

        //Checks if the cursor is inside the rotator
        if (this.getRotator().contains(oMousePoint)) {
            return Cursor.HAND_CURSOR;
        }

        return -1;
    }

    public Resizer getResizerForRotatedCursor(int oCursorType) {
        /*Look for a resizer for the cursor type 
        considering the rotation of the shape*/
        for (Resizer resizer : this.getResizers()) {
            if (resizer.getCursor() == oCursorType) {
                return resizer;
            }
        }

        return null;
    }

    public Resizer getResizerForOriginalCursor(int oCursorType) {
        /*Search for a resizer for the type of cursor
        considering the cursor that the resizer was created*/
        for (Resizer resizer : this.getResizers()) {
            if (resizer.getOriginalCursor() == oCursorType) {
                return resizer;
            }
        }

        return null;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Rotator getRotator() {
        return this.rotator;
    }

    public void paint(Graphics2D g2) {
        if (!isVisible()) {
            return;
        }
        g2 = (Graphics2D) g2.create();

        AffineTransform at = g2.getTransform();
        at.setToScale(1, 1);
        at.setToRotation(Math.toRadians(0));
        g2.setTransform(at);

        g2.setColor(new Color(147, 147, 147));
        g2.setStroke(new BasicStroke(1.0f));

        float cx = (getShapeSource().getX() * this.canvas.getZoom())
                + ((getShapeSource().getWidth() * this.canvas.getZoom()) / 2);
        float cy = (getShapeSource().getY() * this.canvas.getZoom())
                + ((getShapeSource().getHeight() * this.canvas.getZoom()) / 2);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(getShapeSource().getAngle()),
                cx, cy);

        //Draws the bounds of selector
        g2.draw(affineTransform.createTransformedShape(this.getSeletorShape()));
        
        this.getRotator().paint(g2);

        //Draws the resizers points
        for (Resizer resizer : getResizers()) {
            g2.setColor(new Color(240, 240, 240));
            g2.fill(resizer.getShape());
            g2.setColor(new Color(255, 0, 0));
            g2.draw(resizer.getShape());
        }
        g2.dispose();
    }

}
