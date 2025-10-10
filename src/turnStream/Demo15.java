package turnStream;

import java.io.*;
import java.nio.charset.Charset;

public class Demo15 {
    public static void main(String[] args) throws IOException {
        //转换流演示
        /*
        转换流使用场景：
        1.指定字符集读取数据(淘汰)
        2.字节流中想要使用字符流的方法
         */

        //1.按指定字符编码读取

        //JDK8 以前：利用转换流
        //字符转换流 把字节流包装成字符流
        /*
        参数一：要包装的字节流 参数二：指定字符集 (用字符串表示)
         */
        InputStreamReader isr1 = new InputStreamReader(new FileInputStream("gbk.txt"),"GBK");
        OutputStreamWriter osw1 = new OutputStreamWriter(new FileOutputStream("copygbk.txt"),"UTF-8");
        int b1;
        while ((b1 = isr1.read()) != -1){
            osw1.write(b1);
        }

        isr1.close();
        osw1.close();

        //JDK8以后： 利用字符流
        /*
        用FileReader 和 FileWriter  构造方法第二个参数传入字符集
        字符集不能直接传字符串 要通过Charset.forName("GBK")方法 获取字符集
        其底层是创建转换流对象 FileRear 是 InputStreamReader 的子类
        FileWriter同理
         */

        FileReader fr = new FileReader("gbk.txt", Charset.forName("GBK"));
        FileWriter fw = new FileWriter("copy2gbk.txt",Charset.forName("GBK"));

        int b2;
        while ((b2 = fr.read()) != -1) {
            fw.write(b2);
        }

        //用完关流
        fw.close();
        fr.close();

        //2.练习：用字节流读取文件 不能出现乱码 读取的时候一行一行读
        /*
        思考：1)字节流读取中文会出现乱码 所以要利用转换流转为字符流
            2）读的时候一行一行读是缓冲流特有的方法
         */
        //创建字节流
        FileInputStream fis = new FileInputStream("gbkcsb.txt");
        //创建转换流 解决乱码
        InputStreamReader isr2 = new InputStreamReader(fis,"GBK");
        //创建缓冲流 解决一行读取
        BufferedReader br1 = new BufferedReader(isr2);

        //利用嵌套可简化代码
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("gbkcsb.txt"),"GBK"));

        //利用FlieReader
        BufferedReader br3 = new BufferedReader(new FileReader("gbkcsb.txt",Charset.forName("GBK")));

        //读取内容并打印
        String str;
        while ((str = br1.readLine()) != null) {
            System.out.println(str);
        }
        System.out.println("=============");
        while ((str = br2.readLine()) != null) {
            System.out.println(str);
        }
        System.out.println("=============");
        while ((str = br3.readLine()) != null) {
            System.out.println(str);
        }



    }
}
