package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * A group of functions to deal with InputStreams...
 *
 * @author Jack Ullery
 */
public class StreamFunctions {

    private static final PrintStream debug = DebugStream.OFF;

    public static FileInputStream getStream(String filename) throws FileNotFoundException {
        File fi = new File(filename);
        return new FileInputStream(fi);
    }

    private static void assertLength(byte[] arr, int min) {
        if (arr.length < min) {
            throw new IllegalArgumentException("End of file reached...");
        }
    }

    public static int readInt(InputStream file) throws IOException {
        byte[] arr = file.readNBytes(4);
        assertLength(arr, 4);
        int re = (((arr[0] & 0xFF) << Byte.SIZE | (arr[1] & 0xFF)) << Byte.SIZE | (arr[2] & 0xFF)) << Byte.SIZE | (arr[3] & 0xFF);
        debug.println("Short: " + re);
        return re;
    }

    public static short readShort(InputStream file) throws IOException {
        byte[] arr = file.readNBytes(2);
        assertLength(arr, 2);
        short re = (short) ((arr[0] & 0xFF) << Byte.SIZE | (arr[1] & 0xFF));
        debug.println("Short: " + re);
        return re;
    }

    public static byte readByte(InputStream file) throws IOException {
        byte[] arr = file.readNBytes(1);
        assertLength(arr, 1);
        return arr[0];
    }

    public static int readIntMinus(InputStream file) throws IOException {
        byte[] arr = file.readNBytes(4);
        assertLength(arr, 4);
        int re = ((((arr[0] & 0xFF) << Byte.SIZE | (arr[1] & 0xFF)) << Byte.SIZE | (arr[2] & 0xFF)) << Byte.SIZE | (arr[3] & 0xFF)) - 1;
        debug.println("Short: " + re);
        return re;
    }

    public static short readShortMinus(InputStream file) throws IOException {
        byte[] arr = file.readNBytes(2);
        assertLength(arr, 2);
        short re = (short) (((arr[0] & 0xFF) << Byte.SIZE | (arr[1] & 0xFF)) - 1);
        debug.println("Short: " + re);
        return re;
    }

    public static byte readByteMinus(InputStream file) throws IOException {
        byte[] arr = file.readNBytes(1);
        assertLength(arr, 1);
        return (byte)(arr[0]-1);
    }
}
