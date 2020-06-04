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
    private drawingtool.shapes.Shape shapeSource;
    private ArrayList<Resizer> vResizers = new ArrayList();
    private java.awt.Shape seletorShape;
    private boolean visible = true;
    private boolean moving = false;
    private Canvas canvas;

    public Selector(drawingtool.shapes.Shape shape, Canvas canvas) {
        this.shapeSource = shape;
        this.canvas = canvas;

        loadResizers();
    }

    public void reloadSelector() {
        loadResizers();
    }

    public void updateSelectorZoom() {
        reloadSelector();
    }

    public void loadResizers() {
        createSelectorShape();
        northResizerShape = new NorthResizer(this);
        southResizerShape = new SouthResizer(this);
        eastResizerShape = new EastResizer(this);
        westResizerShape = new WestResizer(this);
        northEastResizerShape = new NorthEastResizer(this);
        northWestResizerShape = new NorthWestResizer(this);
        southEastResizerShape = new SouthEastResizer(this);
        southWestResizerShape = new SouthWestResizer(this);

        vResizers.clear();
        vResizers.add(northResizerShape);
        vResizers.add(southResizerShape);
        vResizers.add(eastResizerShape);
        vResizers.add(westResizerShape);
        vResizers.add(northEastResizerShape);
        vResizers.add(northWestResizerShape);
        vResizers.add(southEastResizerShape);
        vResizers.add(southWestResizerShape);
    }

    public ArrayList<Shape> getPointsShapes() {
        ArrayList<Shape> vShape = new ArrayList();
        for (Resizer vResizer : getResizers()) {
            vShape.add(vResizer.getShape());
        }
        return vShape;
    }

    public ArrayList<Resizer> getResizers() {
        return vResizers;
    }

    private void createSelectorShape() {
        Rectangle2D rectangle2D = new Rectangle2D.Float(
                getShapeSource().getX() * canvas.getZoom(),
                getShapeSource().getY() * canvas.getZoom(),
                getShapeSource().getWidth() * canvas.getZoom(),
                getShapeSource().getHeight() * canvas.getZoom());
//        AffineTransform vAffineTransform = new AffineTransform();
//        vAffineTransform.rotate(Math.toRadians(getShapeSource().getAngle()),
//                (getShapeSource().getX() * canvas.getZoom())
//                + ((getShapeSource().getWidth() * canvas.getZoom()) / 2),
//                (getShapeSource().getY() * canvas.getZoom())
//                + ((getShapeSource().getHeight() * canvas.getZoom()) / 2));

        this.seletorShape =rectangle2D; 
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
        //Verifica se o objeto permite redimensionamento
        if (shapeSource.isResizable()) {
            //Verifica se está em cima de um dos pontos e redimensionamento
            for (Resizer resizer : getResizers()) {
                if (resizer.contains(oMousePoint)) {
                    return resizer.getCursor();
                }
            }
        }
        //Verifica se o ponto está dentro da forma
        if (getShapeSource().contains(oMousePoint)) {
            return Cursor.MOVE_CURSOR;
        }

        return -1;
    }

    public Resizer getResizerForCursor(int oCursorType) {
        //Procura um Resizer para o typo de cursor informado
        for (Resizer vResizer : getResizers()) {
            if (vResizer.getCursor() == oCursorType) {
                return vResizer;
            }
        }

        return null;
    }

    public Resizer getResizerForOriginalCursor(int oCursorType) {
        //Procura um Resizer para o typo de cursor informado
        for (Resizer resizer : getResizers()) {
            if (resizer.getOriginalCursor() == oCursorType) {
                return resizer;
            }
        }

        return null;
    }

    public Canvas getCanvas() {
        return canvas;
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
        
        AffineTransform vAffineTransform = new AffineTransform();
        vAffineTransform.rotate(Math.toRadians(getShapeSource().getAngle()),
                (getShapeSource().getX() * canvas.getZoom())
                + ((getShapeSource().getWidth() * canvas.getZoom()) / 2),
                (getShapeSource().getY() * canvas.getZoom())
                + ((getShapeSource().getHeight() * canvas.getZoom()) / 2));
      
        g2.draw(vAffineTransform.createTransformedShape(getSeletorShape()));

        //Draws the resizers points
        for (Resizer resizer : getResizers()) {
//            AffineTransform affineTransform = new AffineTransform();
//            float newX = (float) (resizer.getShape().getBounds().getX() * canvas.getZoom());
//            float newY = (float) (resizer.getShape().getBounds().getY() * canvas.getZoom());
//            affineTransform.translate(newX - resizer.getShape().getBounds().getX(),
//                    newY - resizer.getShape().getBounds().getY());
//
//            Shape shape = affineTransform.createTransformedShape(resizer.getShape());
//            System.out.println("usou: " + resizer.getShape().getBounds().height);
            g2.setColor(new Color(240, 240, 240));
            g2.fill(resizer.getShape());
            g2.setColor(new Color(255, 0, 0));
            g2.draw(resizer.getShape());
        }
        g2.dispose();
    }

}
