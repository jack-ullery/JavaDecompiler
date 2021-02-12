package JavaClassStructs.ConstantInfoStructs;

import JavaClassStructs.ConstantPoolInfo;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5
 *
 * @author Jack Ullery
 */
public class DoubleInfo extends ConstantInfo {

    private final int hi_bytes;
    private final int lo_bytes;

    public DoubleInfo(InputStream data) throws IOException {
        super(data);
        hi_bytes = StreamFunctions.readInt(data);
        lo_bytes = StreamFunctions.readInt(data);
    }

    @Override
    public String toString() {
        return String.format("DoubleInfo: [%d, %d]", hi_bytes, lo_bytes);
    }

    @Override
    public void findChild(ConstantPoolInfo[] arr) {
        // Do nothing there are no children here
    }
}
