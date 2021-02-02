package JavaClassStructs.ConstantInfoStructs;

import java.io.IOException;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.1
 *
 * @author Jack Ullery
 */
public class ClassInfo extends ConstantInfo {

    private final short name_index;

    public ClassInfo(InputStream data) throws IOException {
        super(data);
        name_index = StreamFunctions.readShort(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ClassInfo: \n");
        sb.append("Name Index: ").append(name_index).append('\n');
        return sb.toString();
    }
}
