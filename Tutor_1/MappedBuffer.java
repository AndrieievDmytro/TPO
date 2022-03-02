import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;


public class MappedBuffer implements AutoCloseable {

    private RandomAccessFile accessFile;
    private MappedByteBuffer mappedByteBuffer;

    @Override
    public void close() throws Exception {
        accessFile.close();
        
    }

    public MappedBuffer(File file, int buffer) throws IOException{
        try {
            accessFile = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = accessFile.getChannel();
            mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, 32);
            mappedByteBuffer.order(ByteOrder.nativeOrder());                        
        } catch (Throwable e) {
            throw e;
        }
    }

    public RandomAccessFile getAccessFile(){
        return accessFile;
    }

    public MappedByteBuffer getBuffer(){
        return mappedByteBuffer;
    }
}
