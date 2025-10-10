package myPrintStream;

import java.io.*;

public class Demo18 {
    public static void main(String[] args) throws IOException {
        //打印流
        //
        /*
        PrintStream:字节打印流
        PrintWriter:字符打印流
        打印流特点：只能获取文件地址 不能操作数据源
        意思是只能写出不能读入
         */

        //字节打印流
        /*
        特点：无缓冲区
         */
        //1.创建对象
        /*
        构造方法1.传递一个OutputStream对象 参数二可传入是否自动刷新(无意义),参数三可传入编码方式
        构造方法2.传递一个字符串表示文件地址 参数二可传入编码方法(字符串或者Charset.ofname())
        构造方法3.传递一个File对象 参数二可传入编码方法(字符串或者Charset.ofname())
         */
        PrintStream ps1 = new PrintStream(new FileOutputStream("src//myPrintStream//a.txt"),false,"UTF-8");//关了自动刷新也会写到文件中去因为根本没缓冲区
        /*PrintStream ps2 = new PrintStream("src//PrintStreamAndPrintWriter//a.txt","UTF-8");
        PrintStream ps3 = new PrintStream(new File("src//PrintStreamAndPrintWriter//a.txt"),"UTF-8");*/
        //2.调用方法写出内容
        //(1)write(int i)字节流原方法 写出整数i对应的字符
        ps1.write(97);
        //(2)print()打印出数据原内容
        ps1.print("远离家乡 不胜唏嘘");
        ps1.print(97);//打印97而不是a
        //(3)println() 额外多了一个换行
        ps1.println("幻化成秋叶");

        //3.释放资源
        ps1.close();


        //字符打印流
        /*
        特点:有缓冲区 println()方法会自动刷新
         */
        //1.创建对象
        /*
        构造方法1.传递一个Writer对象 参数二可传递是否自动刷新
        构造方法2.传递一个OutputStream对象 参数二可传递是否自动刷新 参数三可传递编码方式(只能用Charset.ofname()指定)
        构造方法3.传递一个字符串表示文件地址 参数二可传递编码方式(用字符串或者用Charset.ofname()指定)
        构造方法4.传递一个File对象 参数二可传递编码方式(用字符串或者用Charset.ofname()指定)
        3和4不常用 因为指定编码方式在实际开发中没什么意义
         */
        PrintWriter pw = new PrintWriter(new FileWriter("src//myPrintStream//b.txt"),true);

        //2.调用方法写出数据
        //(1)write() 字符流原方法
        pw.write(97);
        //(2) print() 打印
        pw.print("山歌好比春江水");
        //(3) println() 打印+刷新(要打开自动刷新)+换行
        pw.println("不怕滩险弯又多");

        //3.释放资源
        pw.close();

    }
}
