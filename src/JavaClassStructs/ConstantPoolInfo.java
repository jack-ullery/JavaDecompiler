package JavaClassStructs;

import JavaClassStructs.ConstantInfoStructs.ConstantInfo;
import JavaClassStructs.ConstantInfoStructs.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import misc.DebugStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4
 *
 * @author Jack Ullery
 */
public class ConstantPoolInfo {

    private static final PrintStream debug = DebugStream.ON;
    private static final PrintStream error = DebugStream.ERROR;

    final byte tag;
    final ConstantInfo info;

    public ConstantPoolInfo(InputStream data) throws IOException {
        tag = StreamFunctions.readByte(data);
        info = readInfo(tag, data);
    }

    public static ConstantPoolInfo[] readArray(InputStream data, final short ucount) throws IOException {
        int count = Short.toUnsignedInt(ucount);
        ConstantPoolInfo[] arr = new ConstantPoolInfo[count];
        for (int i = 0; i < count; i++) {
            debug.println("Constant #" + i);
            arr[i] = new ConstantPoolInfo(data);
            debug.println(arr[i]);
        }
        debug.println("End of constant pool!");
        return arr;
    }

    // Populates the info array with bytes from the InputStream based on the tag
    private ConstantInfo readInfo(short tag, InputStream data) throws IOException {
        switch (tag) {
            case 1:
                return new Utf8Info(data);
            case 3:
                return new IntegerInfo(data);
            case 4:
                return new FloatInfo(data);
            case 5:
                return new LongInfo(data);
            case 6:
                return new DoubleInfo(data);
            case 7:
                return new ClassInfo(data);
            case 8:
                return new StringInfo(data);
            case 9:
                return new FieldrefInfo(data);
            case 10:
                return new MethodrefInfo(data);
            case 11:
                return new InterfaceMethodrefInfo(data);
            case 12:
                return new NameAndTypeInfo(data);
            case 15:
                return new MethodHandleInfo(data);
            case 16:
                return new MethodTypeInfo(data);
            case 18:
                return new InvokeDynamicInfo(data);
            default:
                throw new IllegalArgumentException(String.format("Invalid tag (%d) recieved", tag));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ConstantPoolInfo: \n");
        sb.append("Tag: ").append(tag);
        sb.append("\nInfo: ").append(info).append('\n');
        return sb.toString();
    }
}
