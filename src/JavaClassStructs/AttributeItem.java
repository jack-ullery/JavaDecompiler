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
public class AttributeItem {

    private static final PrintStream debug = DebugStream.OFF;

    final Utf8Info attribute_name;
    final byte[] info;

    public AttributeItem(InputStream data, ConstantPoolItem[] constant_pool) throws IOException {
        short attribute_name_index = StreamFunctions.readShortMinus(data);
        attribute_name = (Utf8Info) constant_pool[attribute_name_index];
        final int attribute_length = StreamFunctions.readInt(data);
        info = data.readNBytes(attribute_length);
    }

    public static AttributeItem[] readArray(InputStream data, final short ucount, ConstantPoolItem[] constant_pool) throws IOException {
        int count = Short.toUnsignedInt(ucount);
        AttributeItem[] arr = new AttributeItem[count];
        for (int i = 0; i < count; i++) {
            debug.println("Attribute #" + i);
            arr[i] = new AttributeItem(data, constant_pool);
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
