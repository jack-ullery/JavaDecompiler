package JavaClassStructs.ConstantInfoStructs;

import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.6
 *
 * @author Jack Ullery
 */
public class NameAndTypeInfo extends ConstantInfo {

    private final short name_index;
    private final short descriptor_index;

    public NameAndTypeInfo(InputStream data) throws IOException {
        super(data);
        name_index = StreamFunctions.readShort(data);
        descriptor_index = StreamFunctions.readShort(data);
    }

    @Override
    public String toString() {
        return String.format("NameAndTypeInfo: [%d, %d]", name_index, descriptor_index);
    }

}
