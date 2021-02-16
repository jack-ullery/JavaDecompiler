package JavaClassStructs.ConstantPoolInfoStructs;

import JavaClassStructs.ConstantPoolInfo;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.1
 *
 * @author Jack Ullery
 */
public class ClassInfo extends ConstantPoolInfo {

    private Utf8Info child;
    private final short name_index;

    public ClassInfo(InputStream data) throws IOException {
        super(data);
        name_index = (short) (StreamFunctions.readShort(data) - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ClassInfo: \n");
        sb.append("Name Index: ").append(name_index).append('\n');
        return sb.toString();
    }

    @Override
    public void findChild(ConstantPoolInfo[] arr) {
        checkRecursion(arr, name_index);
        child = (Utf8Info) arr[name_index];
    }
}