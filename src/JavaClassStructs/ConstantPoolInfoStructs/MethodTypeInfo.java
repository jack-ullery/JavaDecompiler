package JavaClassStructs.ConstantPoolInfoStructs;

import JavaClassStructs.ConstantPoolInfo;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.9
 *
 * @author Jack Ullery
 */
public class MethodTypeInfo extends ConstantPoolInfo {

    private Utf8Info child;
    private final short descriptor_index;

    public MethodTypeInfo(InputStream data) throws IOException {
        super(data);
        descriptor_index = (short) (StreamFunctions.readShort(data) - 1);
    }

    @Override
    public String toString() {
        return String.format("MethodTypeInfo: [%d]", descriptor_index);
    }

    @Override
    public void findChild(ConstantPoolInfo[] arr) {
        checkRecursion(arr, descriptor_index);
        child = (Utf8Info) arr[descriptor_index];
    }
}
