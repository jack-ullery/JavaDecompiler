package javadecompiler;

import JavaClassStructs.AttributeItem;
import JavaClassStructs.ConstantPoolItem;
import JavaClassStructs.FieldItem;
import JavaClassStructs.MethodItem;
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
    private final ConstantPoolItem[] pool;
    private final short accessFlags;
    private final short classIndex;
    private final short superIndex;
    private final byte[] interfaces;
    private final FieldItem[] fields;
    private final MethodItem[] methods;
    private final AttributeItem[] attributes;

    public JavaDecompiler(String filename) throws IOException {
        file = StreamFunctions.getStream(filename);
        if (!parseMagicNumber()) {
            throw new IllegalArgumentException("Not a valid Java class");
        }

        minorNumber = StreamFunctions.readShort(file);
        majorNumber = StreamFunctions.readShort(file);
        short poolSize = StreamFunctions.readShortMinus(file);
        debug.println("Pool Size: " + Short.toUnsignedInt(poolSize));
        pool = ConstantPoolItem.readArray(file, poolSize);
        accessFlags = StreamFunctions.readShort(file);
        debug.println("Access Flags: " + accessFlags);
        classIndex = StreamFunctions.readShort(file);
        debug.println("Class Index: " + classIndex);
        superIndex = StreamFunctions.readShort(file);
        debug.println("Super Index: " + superIndex);
        short interfaceCount = StreamFunctions.readShort(file);
        debug.println("Interface Count: " + interfaceCount);
        interfaces = readNBytes(interfaceCount);
        short fieldsCount = StreamFunctions.readShort(file);
        debug.println("Fields Count: " + fieldsCount);
        fields = FieldItem.readArray(file, fieldsCount, pool);
        short methodsCount = StreamFunctions.readShort(file);
        debug.println("Methods Count: " + methodsCount);
        methods = MethodItem.readArray(file, methodsCount, pool);
        short attributesCount = StreamFunctions.readShort(file);
        debug.println("Attributes Count: " + methodsCount);
        attributes = AttributeItem.readArray(file, attributesCount, pool);
    }

    private boolean parseMagicNumber() throws IOException {
        byte[] magicNumber = new byte[]{-54, -2, -70, -66};
        byte[] readValue = file.readNBytes(4);
        return Arrays.equals(readValue, magicNumber);
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
