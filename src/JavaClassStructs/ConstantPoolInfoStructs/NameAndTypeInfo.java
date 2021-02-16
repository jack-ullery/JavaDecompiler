package JavaClassStructs.ConstantPoolInfoStructs;

import JavaClassStructs.ConstantPoolItem;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.6
 *
 * @author Jack Ullery
 */
public class NameAndTypeInfo extends ConstantPoolItem {

    private Utf8Info child1;
    private Utf8Info child2;
    private final short name_index;
    private final short descriptor_index;

    public NameAndTypeInfo(InputStream data) throws IOException {
        super(data);
        name_index = (short) (StreamFunctions.readShortMinus(data));
        descriptor_index = (short) (StreamFunctions.readShortMinus(data));
    }

    @Override
    public String toString() {
        return String.format("NameAndTypeInfo: [%d, %d]", name_index, descriptor_index);
    }

    @Override
    public void findChild(ConstantPoolItem[] arr) {
        checkRecursion(arr, name_index);
        child1 = (Utf8Info) arr[name_index];
        checkRecursion(arr, descriptor_index);
        child2 = (Utf8Info) arr[descriptor_index];
    }
}
