
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import misc.StreamFunctions;

/**
 * Used for experimenting with random things.
 *
 * @author Jack Ullery
 */
public class TempTester {

    public static void main(String[] args) throws Exception {
////        String url = "D:\\Documents\\NetBeansProjects\\OLDTV\\build\\classes\\oldtv\\OLDTV.class";
//        String url = "D:\\Documents\\NetBeansProjects\\Debuggable\\build\\classes\\debuggable\\DebugStream.class";
//        InputStream data = StreamFunctions.getStream(url);
//        byte[] arr = data.readAllBytes();
//        for(int i = 0; i < arr.length; i++){
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        
        InputStream bs = new ByteArrayInputStream(new byte[]{-127, 0, 0, 0});
        System.out.println(StreamFunctions.readInt(bs));
    }
}
