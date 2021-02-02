package JavaClassStructs.ConstantInfoStructs;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jack Ullery
 */
public abstract class ConstantInfo {
    public ConstantInfo(InputStream data) throws IOException{
        // This constructor requires subclasses to have an InputStream.
    }
    
    @Override
    public abstract String toString();
}
