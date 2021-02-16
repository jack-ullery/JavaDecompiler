package JavaClassStructs;

import JavaClassStructs.ConstantPoolInfoStructs.Utf8Info;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import misc.DebugStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7
 *
 * @author Jack Ullery
 */
public class AttributeInfo {

    private static final PrintStream debug = DebugStream.ON;

    final Utf8Info attribute_name;
    final byte[] info;

    public AttributeInfo(InputStream data, ConstantPoolInfo[] constant_pool) throws IOException {
        int attribute_name_index = StreamFunctions.readShort(data) - 1;
        attribute_name = (Utf8Info) constant_pool[attribute_name_index];

        final int attribute_length = StreamFunctions.readInt(data);
        info = data.readNBytes(attribute_length);
    }

    public static AttributeInfo[] readArray(InputStream data, final short ucount, ConstantPoolInfo[] constant_pool) throws IOException {
        int count = Short.toUnsignedInt(ucount);
        AttributeInfo[] arr = new AttributeInfo[count];
        for (int i = 0; i < count; i++) {
            debug.println("Attribute #" + i);
            arr[i] = new AttributeInfo(data, constant_pool);
            debug.println(arr[i]);
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AttributeInfo: \n");
        sb.append("\tAttribute Name: ").append(attribute_name);
        sb.append("\n\tLength: ").append(info.length);
        sb.append("\n\tInfo: ").append(Arrays.toString(info));
        return sb.toString();
    }
}
