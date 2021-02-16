package JavaClassStructs.ConstantPoolInfoStructs;

import JavaClassStructs.ConstantPoolInfo;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.10
 *
 * @author Jack Ullery
 */
public class InvokeDynamicInfo extends ConstantPoolInfo {

    private NameAndTypeInfo child;
    private final short bootstrap_method_attr_index;
    private final short name_and_type_index;

    public InvokeDynamicInfo(InputStream data) throws IOException {
        super(data);
        bootstrap_method_attr_index = (short) (StreamFunctions.readShort(data) - 1);
        name_and_type_index = (short) (StreamFunctions.readShort(data) - 1);
    }

    @Override
    public String toString() {
        return String.format("InvokeDynamicInfo: [%d, %d]", bootstrap_method_attr_index, name_and_type_index);
    }

    @Override
    public void findChild(ConstantPoolInfo[] arr) {
        checkRecursion(arr, name_and_type_index);
        child = (NameAndTypeInfo) arr[name_and_type_index];
    }
}
