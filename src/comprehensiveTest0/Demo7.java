package comprehensiveTest0;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo7 {
    public static void main(String[] args) throws IOException {
        //综合练习二：文件加密与解密
        //通过^修改文件每一个字节得数据实现加密
        //^:异或 异或两次得原文

        FileInputStream fis1 = new FileInputStream("a.txt");
        FileOutputStream fos1 = new FileOutputStream("ency.txt");

        int b;
        while ((b = fis1.read()) != -1) {
            fos1.write(b ^2);
        }

        fos1.close();
        fis1.close();

        FileInputStream fis2 = new FileInputStream("ency.txt");
        FileOutputStream fos2 = new FileOutputStream("aaa.txt");
        while ((b = fis2.read()) != -1) {
            fos2.write(b ^ 2);
        }
        fos2.close();
        fis2.close();

    }
}
