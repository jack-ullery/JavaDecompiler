package JavaClassStructs.ConstantPoolInfoStructs;

import JavaClassStructs.ConstantPoolItem;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.2
 *
 * @author Jack Ullery
 */
public class MethodrefInfo extends ConstantPoolItem {

    private ClassInfo child1;
    private NameAndTypeInfo child2;
    private final short class_index;
    private final short name_and_type_index;

    public MethodrefInfo(InputStream data) throws IOException {
        super(data);
        class_index = (short) (StreamFunctions.readShortMinus(data));
        name_and_type_index = (short) (StreamFunctions.readShortMinus(data));
    }

    @Override
    public String toString() {
        return String.format("MethodrefInfo: [%d, %d]", class_index, name_and_type_index);
    }

    @Override
    public void findChild(ConstantPoolItem[] arr) {
        checkRecursion(arr, class_index);
        child1 = (ClassInfo) arr[class_index];
        checkRecursion(arr, name_and_type_index);
        child2 = (NameAndTypeInfo) arr[name_and_type_index];
    }
}
