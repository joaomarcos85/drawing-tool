package drawingtool.listener;

import java.util.EventListener;

/**
 *
 * @author Joao
 */
public abstract class ShapeListener implements EventListener {

    public abstract void onSelect(drawingtool.shapes.Shape shape);
}
