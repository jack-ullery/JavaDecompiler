package JavaClassStructs;

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

    final short attribute_name_index;
    final byte[] info;

    public AttributeInfo(InputStream data) throws IOException {
        attribute_name_index = StreamFunctions.readShort(data);
        final int attribute_length = StreamFunctions.readInt(data);
        info = data.readNBytes(attribute_length);
    }

    public static AttributeInfo[] readArray(InputStream data, final short ucount) throws IOException {
        int count = Short.toUnsignedInt(ucount);
        AttributeInfo[] arr = new AttributeInfo[count];
        for (int i = 0; i < count; i++) {
            arr[i] = new AttributeInfo(data);
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AttributeInfo: \n");
        sb.append("Attribute Index: ").append(attribute_name_index);
        sb.append("\nLength: ").append(info.length);
        sb.append("\nInfo: ").append(Arrays.toString(info));
        sb.append('\n').append(Integer.toUnsignedString(attribute_name_index)).append('\n');
        return sb.toString();
    }
}
