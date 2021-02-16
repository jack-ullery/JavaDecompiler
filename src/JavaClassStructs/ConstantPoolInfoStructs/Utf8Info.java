package JavaClassStructs.ConstantPoolInfoStructs;

import JavaClassStructs.ConstantPoolInfo;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import misc.DebugStream;
import misc.StreamFunctions;

/**
 * As defined in:
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.7
 *
 * @author Jack Ullery
 */
public class Utf8Info extends ConstantPoolInfo {

    private static final PrintStream debug = DebugStream.OFF;

    private final byte[] arr;

    public Utf8Info(InputStream data) throws IOException {
        super(data);
        short length = StreamFunctions.readShort(data);
        debug.println("Constant found with length: " + length);
        arr = data.readNBytes(length);
        assertValidBytes();
    }

    private void assertValidBytes() {
        for (byte elem : arr) {
            if (elem == 0 || (0xf0 <= elem && elem <= 0xff)) {
                throw new IllegalArgumentException("Byte (" + elem + "): is out of acceptable range.");
            }
        }
    }

    @Override
    public String toString() {
//        return String.format("Utf8Info: [%s=\"%s\"]", Arrays.toString(arr), new String(arr, StandardCharsets.UTF_8));
        return String.format("\"%s\"", new String(arr, StandardCharsets.UTF_8));
    }

    @Override
    public void findChild(ConstantPoolInfo[] arr) {
        // Do nothing there are no children here
    }
}
