package javadecompiler;

import JavaClassStructs.AttributeInfo;
import JavaClassStructs.ConstantPoolInfo;
import JavaClassStructs.FieldInfo;
import JavaClassStructs.MethodInfo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import misc.DebugStream;
import misc.OtherFunctions;
import misc.StreamFunctions;

public class JavaDecompiler {

    private static final PrintStream debug = DebugStream.ON;

    private final FileInputStream file;
    private final short majorNumber;
    private final short minorNumber;
    private final ConstantPoolInfo[] pool;
    private final short accessFlags;
    private final short classIndex;
    private final short superIndex;
    private final byte[] interfaces;
    private final FieldInfo[] fields;
    private final MethodInfo[] methods;
    private final AttributeInfo[] attributes;

    public JavaDecompiler(String filename) throws IOException {
        file = StreamFunctions.getStream(filename);
        if (!parseMagicNumber()) {
            throw new IllegalArgumentException("Not a valid Java class");
        }

        minorNumber = readShort();
        majorNumber = readShort();
        short poolSize = (short) (readShort() - 1);
        debug.println("Pool Size: " + Short.toUnsignedInt(poolSize));
        pool = ConstantPoolInfo.readArray(file, poolSize);
        accessFlags = readShort();
        debug.println("Access Flags: " + accessFlags);
        classIndex = readShort();
        debug.println("Class Index: " + classIndex);
        superIndex = readShort();
        debug.println("Super Index: " + superIndex);
        short interfaceCount = readShort();
        debug.println("Interface Count: " + interfaceCount);
        interfaces = readNBytes(interfaceCount);
        short fieldsCount = readShort();
        debug.println("Fields Count: " + fieldsCount);
        fields = FieldInfo.readArray(file, fieldsCount, pool);
        short methodsCount = readShort();
        debug.println("Methods Count: " + methodsCount);
        methods = MethodInfo.readArray(file, methodsCount, pool);
        short attributesCount = readShort();
        debug.println("Attributes Count: " + methodsCount);
        attributes = AttributeInfo.readArray(file, attributesCount, pool);
    }

    private boolean parseMagicNumber() throws IOException {
        byte[] magicNumber = new byte[]{-54, -2, -70, -66};
        byte[] readValue = file.readNBytes(4);
        return Arrays.equals(readValue, magicNumber);
    }

    private short readShort() throws IOException {
        return StreamFunctions.readShort(file);
    }

    private byte[] readNBytes(int n) throws IOException {
        if (n <= 0) {
            debug.println("Reading no bytes!");
            return new byte[0];
        }
        return file.readNBytes(n);
    }

    public short getMajor() {
        return majorNumber;
    }

    public short getMinor() {
        return minorNumber;
    }

    public String getVersion() {
        return OtherFunctions.getJavaVersion(majorNumber);
    }

    public static void main(String[] args) throws IOException {
        for (String url : args) {
            JavaDecompiler kira = new JavaDecompiler(url);
            System.out.println("Kira version: " + kira.getVersion());
        }
    }

}
