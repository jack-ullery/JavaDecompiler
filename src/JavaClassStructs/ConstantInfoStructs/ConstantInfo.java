package JavaClassStructs.ConstantInfoStructs;

import JavaClassStructs.ConstantPoolInfo;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jack Ullery
 */
public abstract class ConstantInfo {

    public ConstantInfo(InputStream data) throws IOException {
        // This constructor requires subclasses to have an InputStream.
    }

    /**
     * For classes that have a child, find its child from the array.
     *
     * @param arr The array that contains this ConstantInfoStruct and its
     * children.
     * @throws ClassCastException when an incorrect ConstantInfo type is pointed
     * to.
     */
    public abstract void findChild(ConstantPoolInfo[] arr);

    void checkBounds(ConstantPoolInfo[] arr, int index) {
        if (index < 0 || index > arr.length) {
            throw new IllegalArgumentException("Constant Pool Index out of bounds");
        } else if (arr[index].info == this) {
            throw new IllegalArgumentException("Unexpected recursive call for ConstantPool entry");
        }
    }

    @Override
    public abstract String toString();
}
