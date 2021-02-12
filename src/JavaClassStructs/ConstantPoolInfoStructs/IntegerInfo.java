package JavaClassStructs.ConstantPoolInfoStructs;

import JavaClassStructs.ConstantPoolInfo;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.4
 *
 * @author Jack Ullery
 */
public class IntegerInfo extends ConstantPoolInfo {

    private final int bytes;

    public IntegerInfo(InputStream data) throws IOException {
        super(data);
        bytes = StreamFunctions.readInt(data);
    }

    @Override
    public String toString() {
        return String.format("IntegerInfo: [%d]", bytes);
    }

    @Override
    public void findChild(ConstantPoolInfo[] arr) {
        // Do nothing there are no children here
    }
}
