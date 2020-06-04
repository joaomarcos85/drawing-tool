package drawingtool.ui;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComponent;

/**
 *
 * @author Joao
 */
public class GenericCanvas {

    private Canvas canvas = new Canvas();
    private JComponent parentComp;

    public GenericCanvas(JComponent parentComp) {
        this.parentComp = parentComp;
        initCanvas();
    }

    private void initCanvas() {
        //Adds Canvas within the component
        this.parentComp.add(canvas);
        //Leaves the canvas transparent
        canvas.setOpaque(false);
        /*Adds the event responsible for resizing 
        the canvas to the size of the component*/
        this.parentComp.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                GenericCanvas.this.setCanvasSize();
            }
        });

        this.setCanvasSize();
    }

    private void setCanvasSize() {
        canvas.setBounds(0, 0, this.parentComp.getWidth(),
                this.parentComp.getHeight());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

}
