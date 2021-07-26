package nio.chapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumpCodeTest {

    public static void main(String[] args) throws Exception {
        extracted2();
    }
    private static void extracted2() throws IOException {
        Path path = Paths.get("C:\\Users\\xiangzhang\\Desktop\\hootin\\迁移\\qa.sql");
        String str = new String(Files.readAllBytes(path));
        String pattern = "[A-Z][a-z]";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(str);
        while (matcher.find()){
            String group = matcher.group();
            str = str.replaceAll(group,"_" + group.toLowerCase());
        }
        String drop_table_if_exists_ = str.replaceAll("-- fmnd.", "drop table if exists ");
        String definition = drop_table_if_exists_.replace("definition", ";");
        System.out.println(definition);
    }

}
