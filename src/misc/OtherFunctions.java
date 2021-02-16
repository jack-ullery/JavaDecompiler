package misc;

import JavaClassStructs.ConstantPoolItem;

/**
 *
 * @author Jack Ullery
 */
public class OtherFunctions {

    public static String getJavaVersion(short majorNumber) {
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
}
