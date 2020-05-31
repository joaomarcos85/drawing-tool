package drawingtool;

import drawingtool.log.Log;
import java.util.logging.Level;
import javax.swing.SwingUtilities;

/**
 *
 * @author Joao
 */
public class Main {

    public static void main(String[] args) {
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmShapeEditor().setVisible(true);
            }
        });
    }
}
