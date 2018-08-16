import sun.nio.ch.FileKey;

import java.io.File;

/**
 * @author Devin13 on 2018/8/16.
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) {
        File file = new File("D:\\WXinfo\\180815FCH9D2K58H\\face\\wx53c29c7db099c421..9GyUJbI3hklC99efe15a909d6aaf1a9a9d46ff7dd895.png");
        if (file.getParentFile().mkdirs()) {
            System.out.println("创建成功");
        }
    }
}
