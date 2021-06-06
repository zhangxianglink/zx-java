package nio.chapter;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile accessFile = new RandomAccessFile("/Users/nuc/Documents/mysql-copy.md", "rw");
        // channel 挑选实现类
        FileChannel channel = accessFile.getChannel();
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        // buffer 挑选实现类
        ByteBuffer buffer = ByteBuffer.allocate(128);
        CharBuffer charBuffer = CharBuffer.allocate(128);
        // 写入缓存区
        int write = channel.read(buffer);
        while (write != -1){
            // 转换读取模式
            buffer.flip();
            decoder.decode(buffer,charBuffer,false);
            charBuffer.flip();
            System.out.println(charBuffer.toString());
            charBuffer.clear();
            buffer.clear();
            write = channel.read(buffer);
        }
        channel.close();
    }
}
