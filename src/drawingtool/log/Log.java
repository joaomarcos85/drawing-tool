package drawingtool.log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author Joao
 */
public class Log {

    private static final LogManager logManager = LogManager.getLogManager();
    public static final Logger LOGGER = Logger.getLogger("drawingtool");

    static {
        try {
            logManager.readConfiguration(new FileInputStream("./logging.properties"));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error in loading configuration", exception);
        }
    }
}
