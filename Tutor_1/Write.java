import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.Random;

public class Write {
    private static File file = new File("TestFile.txt");
    private static int sleepTime = 3000;
    private static int itteration = 5;    

    // public static  boolean runInfinetly(int itter){
    //     return itter <= 0 ? true : false; 
    // }

    public static void main(String[] args) {

        try {
            file.delete();
        } catch (SecurityException e) {}

        try (MappedBuffer mbf = new MappedBuffer(file, 32) ){
            MappedByteBuffer buffer = mbf.getBuffer();
            Random random = new Random();
            int i = itteration;
            boolean runInfinetly = (i <= 0);
            while( runInfinetly || i > 0){
                buffer.rewind();
                int lastTaskCode = buffer.getInt();
                if(lastTaskCode == Task.Write.getTaskCode()){
                    Thread.sleep(sleepTime);
                    continue;
                }
                System.out.println("Writing");
                int val1 = random.nextInt();
                int val2 = random.nextInt();

                buffer.rewind();
                buffer
                    .putInt(Task.Write.getTaskCode())
                    .putInt(val1)
                    .putInt(val2);

                if(runInfinetly == false){
                    i--;
                }
            }
            System.out.println("Process finished");
        } catch (Throwable e) {
            e.printStackTrace();
        } 
    }
}
