package drawingtool.interactor;

import drawingtool.shapes.Shape;
import drawingtool.ui.Canvas;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Joao
 */
public class DeleteInteractor extends Interactor {

    public DeleteInteractor(Canvas canvas) {
        super(canvas);
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        Shape selectedShape = this.canvas.getSelectedShape();

        if (evt.getKeyCode() == KeyEvent.VK_DELETE && selectedShape != null) {
            //Confirm the removing
            int option = JOptionPane.showConfirmDialog(canvas,
                    "Delete the selected shape?", "Delete shape",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                this.removeShape(selectedShape);
            }
        }
    }

    private void removeShape(Shape shape) {
        //Remove the shape
        canvas.removeShape(shape);
        //Clear the current selector
        canvas.setShapeSelector(null);
    }

}
