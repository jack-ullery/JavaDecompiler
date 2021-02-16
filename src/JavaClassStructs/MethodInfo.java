package JavaClassStructs;

import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import misc.DebugStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.6
 *
 * @author Jack Ullery
 */
public class MethodInfo {

    private static final PrintStream debug = DebugStream.OFF;

    final short access_flags;
    final short name_index;
    final short descriptor_index;
    final AttributeInfo[] attributes;

    public MethodInfo(InputStream data, ConstantPoolInfo[] constant_pool) throws IOException {
        access_flags = StreamFunctions.readShort(data);
        name_index = StreamFunctions.readShort(data);
        descriptor_index = StreamFunctions.readShort(data);
        final short attributes_count = StreamFunctions.readShort(data);
        attributes = AttributeInfo.readArray(data, attributes_count, constant_pool);
    }

    public static MethodInfo[] readArray(InputStream data, final short ucount, ConstantPoolInfo[] constant_pool) throws IOException {
        int count = Short.toUnsignedInt(ucount);
        debug.println("Reading " + count + " MethodInfo classes.\n");
        MethodInfo[] arr = new MethodInfo[count];
        for (int i = 0; i < count; i++) {
            debug.println("Class " + i + ":");
            arr[i] = new MethodInfo(data, constant_pool);
            debug.println(arr[i]);
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Access Flags: ").append(access_flags);
        sb.append("\n\tName Index: ").append(name_index);
        sb.append("\n\tDescriptor Index: ").append(descriptor_index);
        sb.append("\n\tAttribute Count: ").append(attributes.length);
        sb.append("\n\tAttributes: ").append(Arrays.toString(attributes)).append("\n");
        return sb.toString();
    }
}
