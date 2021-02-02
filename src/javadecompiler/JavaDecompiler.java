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
        fields = FieldInfo.readArray(file, fieldsCount);
        short methodsCount = readShort();
        debug.println("Methods Count: " + methodsCount);
        methods = MethodInfo.readArray(file, methodsCount);
        short attributesCount = readShort();
        debug.println("Attributes Count: " + methodsCount);
        attributes = AttributeInfo.readArray(file, attributesCount);
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
        switch (majorNumber) {
            case 61:
                return "Java SE 17";
            case 60:
                return "Java SE 16";
            case 59:
                return "Java SE 15";
            case 58:
                return "Java SE 14";
            case 57:
                return "Java SE 13";
            case 56:
                return "Java SE 12";
            case 55:
                return "Java SE 11";
            case 54:
                return "Java SE 10";
            case 53:
                return "Java SE 9";
            case 52:
                return "Java SE 8";
            case 51:
                return "Java SE 7";
            case 50:
                return "Java SE 6.0";
            case 49:
                return "Java SE 5.0";
            case 48:
                return "JDK 1.4";
            case 47:
                return "JDK 1.3";
            case 46:
                return "JDK 1.2";
            case 45:
                return "JDK 1.1";
            default:
                return "invalid version";
        }
    }

    public static void main(String[] args) throws IOException {
        for (String url : args) {
            JavaDecompiler kira = new JavaDecompiler(url);
            System.out.println("Kira version: " + kira.getVersion());
        }
    }

}
