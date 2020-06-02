package drawingtool.io.transformer;

import drawingtool.io.Document;
import java.io.Writer;

/**
 *
 * @author Joao
 */
public abstract class DocumentTransformer {

    public abstract void transform(Document document, Writer writer);

}
