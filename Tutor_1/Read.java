import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;

public class Read {
    
    private static File file = new File("TestFile.txt");
    private static int sleepTime = 3000;

    public static void main(String[] args) throws IOException {
        if (!file.canRead() && !file.isFile() && !file.exists()){
            throw new IOException("There is no such file or file is not readable");
        }
        try (MappedBuffer mbf = new MappedBuffer(file, 32) ){
            MappedByteBuffer buffer = mbf.getBuffer();

            while (true){
                buffer.rewind();
                int lastTaskCode = buffer.getInt();
                if(lastTaskCode == Task.Read.getTaskCode()){
                    Thread.sleep(sleepTime);
                    continue;
                }

                int val1 = buffer.getInt();
                int val2 = buffer.getInt();
                buffer.rewind();
                buffer.putInt(Task.Read.getTaskCode());
                int sum = val1 + val2;
                System.out.println("Sum: " + sum);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
