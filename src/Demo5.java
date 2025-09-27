import java.io.FileWriter;
import java.io.IOException;

public class Demo5 {
    public static void main(String[] args) throws IOException {
        //FileWriter 使用示例
        //1.创建对象
        /*
        有两种构造方法
        参数二是续写开关 默认是false (不传参的话就按默认值)
        文件不存在则会创建 但是父级路径要存在否则抛出异常
         */
        FileWriter fw = new FileWriter("g.txt",false);
        //2.写入数据

        /* write()方法返回值均为void
        write(int c) 写入整数c对应的字符
         */
        fw.write(97);
        fw.flush();
        fw.write(25105);
        fw.write("\r\n");
        /*
        write(String str) 写入一个字符串
        write(String str,int off, int len) 写出一个字符串
        第二个参数是开始索引
        第三个参数是写入的字符数
         */
        String str = "还是他？";
        fw.write(str);
        fw.write(str,0,1);
        fw.write("\r\n");


        /*
        write(char[] chuf) 写出一个字符数组
        write(char[] chuf,int off,int len) 选择性写出一个字符数组
        第二个参数是起始索引 第三个参数是写出的字符数
         */
        char[] chars = {'q','c','d'};
        fw.write(chars);
        fw.write(chars,0,1);
        fw.write("\r\n");

        //3.使用完毕 释放资源
        /*
        此处如果最后没有释放 则有可能不会写入 因为写入的数据还在缓冲区(没有超过8192个数据,满了会自动刷新)
        调用close()方法时如果缓冲区有数据则会先刷新再释放
        也可以手动调用flush()方法进行刷新
         */
        fw.close();
    }
}
