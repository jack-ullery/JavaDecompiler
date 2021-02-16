package JavaClassStructs.ConstantPoolInfo;

import JavaClassStructs.ConstantPoolItem;
import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.3
 *
 * @author Jack Ullery
 */
public class StringInfo extends ConstantPoolItem {

    private Utf8Info child;
    private final short string_index;

    public StringInfo(InputStream data) throws IOException {
        super(data);
        string_index = (short) (StreamFunctions.readShortMinus(data));
    }

    @Override
    public String toString() {
        return String.format("StringInfo: [%d]", string_index);
    }

    @Override
    public void findChild(ConstantPoolItem[] arr) {
        checkRecursion(arr, string_index);
        child = (Utf8Info) arr[string_index];
    }
}
