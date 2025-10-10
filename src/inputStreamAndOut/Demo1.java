package inputStreamAndOut;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo1 {
    public static void main(String[] args) throws IOException {
        //IO流学习
        //字节流 OutputStream 与InputStream 都是抽象类
        //用 FileOutputStream  FileInputStream作为示范样例

        //FileOutputStream
        //1.创建对象
        /*
        细节:方法参数可以直接传递文件地址 也可以转递文件对象
            若文件不存在则在该路径下创建一个文件但父级路径要存在 否则弹出异常
            若文件已存在则会清空文件
            参数二可控制续写开关 不传参数二默认清空已有文件
         */
        FileOutputStream fos1 = new FileOutputStream(new File("a.txt"),true);
        //FileOutputStream fos2 = new FileOutputStream("a.txt");

        //2.write()方法将数据写到文件中
        /*
        write(int b.txt) 一次写一个字节数据
        细节：传入整数但写入文件的是在Ascii码表中该数字对应的字符
         */
        fos1.write(97);
        fos1.write(98);
        fos1.write(99);

        /*
        换行符：
            windows:\r\n
            linux:\n
            Mac:\r
            java存在自动补全机制 当你输入\n时会自动补全为\r\n
         */
        String str = "\r\n";
        byte[] bytes1 = str.getBytes();
        fos1.write(bytes1);

        /*
        write(byte[] b.txt)
        细节：传入字符数组 一次性写入多个字符
         */
        byte[] bytes2 = {100,101,102,103};
        fos1.write(bytes2);

        fos1.write(bytes1);


        /*
        write(byte[] b.txt,int off,int len)
        参数二：起始索引
        参数三：要写入文件的字节数
         */
        byte[] bytes3 = {104,105,106,107};
        fos1.write(bytes3,2,2);

        //3.使用结束要释放资源
        fos1.close();
    }

}
