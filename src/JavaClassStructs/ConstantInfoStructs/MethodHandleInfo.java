package JavaClassStructs.ConstantInfoStructs;

import JavaClassStructs.ConstantPoolInfo;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.8
 *
 * @author Jack Ullery
 */
public class MethodHandleInfo extends ConstantInfo {

    private final byte reference_kind;
    private final short descriptor_index;

    public MethodHandleInfo(InputStream data) throws IOException {
        super(data);
        reference_kind = StreamFunctions.readByte(data);
        descriptor_index = (short) (StreamFunctions.readShort(data) - 1);
    }

    @Override
    public String toString() {
        return String.format("MethodhandleInfo: [%d, %d]", reference_kind, descriptor_index);
    }

    @Override
    public void findChild(ConstantPoolInfo[] arr) {
        // Do nothing there are no children here
    }
}
