package JavaClassStructs;

import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import misc.DebugStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.5
 *
 * @author Jack Ullery
 */
public class FieldInfo {

    private static final PrintStream debug = DebugStream.OFF;

    final short access_flags;
    final short name_index;
    final short descriptor_index;
    final AttributeInfo[] attributes;

    public FieldInfo(InputStream data) throws IOException {
        access_flags = StreamFunctions.readShort(data);
        name_index = StreamFunctions.readShort(data);
        descriptor_index = StreamFunctions.readShort(data);
        final short attributes_count = StreamFunctions.readShort(data);
        attributes = AttributeInfo.readArray(data, attributes_count);
    }

    public static FieldInfo[] readArray(InputStream data, final short ucount) throws IOException {
        int count = Short.toUnsignedInt(ucount);
        debug.println("Reading " + count + " FieldInfo classes.");
        FieldInfo[] arr = new FieldInfo[count];
        for (int i = 0; i < count; i++) {
            arr[i] = new FieldInfo(data);
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Access Flags: ").append(access_flags);
        sb.append("\nName Index: ").append(name_index);
        sb.append("\nDescriptor Index: ").append(descriptor_index);
        sb.append("\nAttributes: ").append(Arrays.toString(attributes)).append("\n");
        return sb.toString();
    }
}
