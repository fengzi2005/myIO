import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo9 {
    public static void main(String[] args) throws IOException {
        //字符流的缓冲区机制
        //创建文件读取字符流对象
        FileReader fr = new FileReader("i.txt");
        //调用空参read() 此时会把文件的数据传到缓冲区 (最多8192个数据)
        int read = fr.read();
        //打印第一次读到的字符
        System.out.print((char) read);
        //创建文件写出字符流对象 此时会清空原i.txt文件中的内容
        FileWriter fw = new FileWriter("i.txt");
        int b;
        while ((b = fr.read()) != -1) {
            System.out.print((char) b);
        }
        //仍能打印出原i.txt中的内容(如果内容比较多则只能打印出8192个字符)

        //使用完毕 释放资源
        fr.close();
        fw.close();
    }
}
