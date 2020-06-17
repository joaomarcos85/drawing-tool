package drawingtool.ui;

import drawingtool.interactor.CursorInteractor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import drawingtool.interactor.Interactor;
import drawingtool.interactor.MoveInteractor;
import drawingtool.interactor.PickerInteractor;
import drawingtool.interactor.ResizeInteractor;
import drawingtool.interactor.RotateInteractor;
import drawingtool.io.AbstractDocument;
import drawingtool.listener.ShapeListener;
import drawingtool.io.Document;
import drawingtool.selector.Selector;
import drawingtool.shapes.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Joao
 */
public class Canvas extends javax.swing.JPanel {

    private final ArrayList<Interactor> interactors = new ArrayList<>();
    private final ArrayList<ShapeListener> shapeListener = new ArrayList<>();
    private Selector shapeSelector;
    private float zoomFactor = 1.0f;
    private AffineTransform at;
    private Document document = new AbstractDocument();

    public Canvas() {
        setZoom(zoomFactor);
        prepareInteractors();

        //Adds the interactors
        this.addInteractor(new CursorInteractor(this));
        this.addInteractor(new PickerInteractor(this));
        this.addInteractor(new ResizeInteractor(this));
        this.addInteractor(new MoveInteractor(this));
        this.addInteractor(new RotateInteractor(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (document == null) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        g2.setTransform(at);
        //Draw the shapes
        for (Shape shape : document.getShapes()) {
            shape.paint(g2);
        }

        //Draw de selector
        if (shapeSelector != null) {
            shapeSelector.paint(g2);
        }
    }

    private void prepareInteractors() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                //Execute the interactors
                for (Interactor interactor : interactors) {
                    interactor.mousePressed(evt);
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                //Execute the interactors
                for (Interactor interactor : interactors) {
                    interactor.mouseReleased(evt);
                }
            }

        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                //Execute the interactors
                for (Interactor interactor : interactors) {
                    interactor.mouseMoved(evt);
                }
            }

            @Override
            public void mouseDragged(MouseEvent evt) {
                //Execute the interactors
                for (Interactor interactor : interactors) {
                    interactor.mouseDragged(evt);
                }
            }
        });
        
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                //Execute the interactors
                for (Interactor interactor : interactors) {
                    interactor.keyTyped(evt);
                }
            }

            @Override
            public void keyPressed(KeyEvent evt) {
                //Execute the interactors
                for (Interactor interactor : interactors) {
                    interactor.keyPressed(evt);
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                //Execute the interactors
                for (Interactor interactor : interactors) {
                    interactor.keyReleased(evt);
                }
            }
        });
    }

    public Selector getShapeSelector() {
        return shapeSelector;
    }

    public void setShapeSelector(Selector shapeSelector) {
        this.shapeSelector = shapeSelector;
    }

    private void addInteractor(Interactor interactor) {
        this.interactors.add(interactor);
    }

    public boolean removeInteractor(Interactor interactor) {
        return this.interactors.remove(interactor);
    }

    public void addShapeListener(ShapeListener listener) {
        this.shapeListener.add(listener);
    }

    public boolean removeShapeListener(ShapeListener listener) {
        return this.shapeListener.remove(listener);
    }

    public ArrayList<ShapeListener> getShapeListeners() {
        return this.shapeListener;
    }

    public ArrayList<Interactor> getInteractors() {
        return interactors;
    }

    public void addShape(Shape shape) {
        this.document.getShapes().add(shape);
        //Update the canvas
        this.repaint();
    }

    public void removeShape(Shape shape) {
        this.document.getShapes().remove(shape);
        //Update the canvas
        this.repaint();
    }

    public ArrayList<Shape> getShapes() {
        return this.document.getShapes();
    }

    public drawingtool.shapes.Shape getSelectedShape() {
        for (Shape shape : document.getShapes()) {
            if (shape.isSelected()) {
                return shape;
            }
        }
        return null;
    }

    public void setZoom(float zoomFactor) {
        this.zoomFactor = zoomFactor;
        at = new AffineTransform();
        at.scale(this.zoomFactor, this.zoomFactor);

        if (this.getShapeSelector() != null) {
            this.getShapeSelector().updateSelectorZoom();
        }
        this.repaint();
    }

    public float getZoom() {
        return zoomFactor;
    }

    public AffineTransform getAffineTransform() {
        return at;
    }

    @Override
    public Point getMousePosition() throws HeadlessException {
        //Converts the mouse position considering the zoom
        Point2D convertedPt = super.getMousePosition();
        if (convertedPt == null) {
            return null;
        }
        try {
            AffineTransform bt = at.createInverse();
            convertedPt = bt.transform(convertedPt, null);
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }
        return new Point((int) convertedPt.getX(), (int) convertedPt.getY());
    }

    public void setDocument(Document document) {
        this.document = document;
        this.setShapeSelector(null);
        this.repaint();
    }

    public Document getDocument() {
        return document;
    }

}
