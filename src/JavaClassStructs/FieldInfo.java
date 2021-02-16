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
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.5
 *
 * @author Jack Ullery
 */
public class FieldInfo {

    private static final PrintStream debug = DebugStream.ON;
    private static final PrintStream debug2 = DebugStream.ON;

    final short access_flags;
    final Utf8Info name;
    final Utf8Info descriptor;
    final AttributeInfo[] attributes;

    public FieldInfo(InputStream data, ConstantPoolInfo[] constant_pool) throws IOException {
        access_flags = StreamFunctions.readShort(data);
        debug2.println("Access_Flags: "+access_flags);
        int name_index = StreamFunctions.readShort(data) - 1;
        debug2.println("Name_Index: "+name_index);
        name = (Utf8Info) constant_pool[name_index];
        debug2.println("Name: "+name);
        int descriptor_index = StreamFunctions.readShort(data) - 1;
        descriptor = (Utf8Info) constant_pool[descriptor_index];
        final short attributes_count = StreamFunctions.readShort(data);
        attributes = AttributeInfo.readArray(data, attributes_count, constant_pool);
    }

    public static FieldInfo[] readArray(InputStream data, final short ucount, ConstantPoolInfo[] constant_pool) throws IOException {
        int count = Short.toUnsignedInt(ucount);
        debug.println("Reading " + count + " FieldInfo classes.");
        FieldInfo[] arr = new FieldInfo[count];
        for (int i = 0; i < count; i++) {
            debug.println("Field #" + i);
            arr[i] = new FieldInfo(data, constant_pool);
            debug.println(arr[i]);
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Access Flags: ").append(access_flags);
        sb.append("\n\tName Index: ").append(name);
        sb.append("\n\tDescriptor Index: ").append(descriptor);
        sb.append("\n\tAttributes: ").append(Arrays.toString(attributes)).append("\n");
        return sb.toString();
    }
}
