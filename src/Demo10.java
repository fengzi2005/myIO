import java.io.*;

public class Demo10 {
    public static void main(String[] args) throws IOException {
        //缓冲流
        //以字节缓冲流和字符缓冲流为例
    /*
    特点：给字节流和字符流增加了缓冲区 大大提高字节流效率
    虽然对字符流提升不大 但字节缓冲流有两个关键方法(字符流本就有缓冲区)
     */

        //字节缓冲输入流
        //1.创建对象 参数一传递要包装得基本流 参数二传递缓冲区长度,不传默认8192
        /*
        细节与FileInputStream类似
         */
        BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream ("aaa.txt"),8192);
        BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream ("b.txt"));
        //2.读取数据
        // 空参read()方法 先获取数据填满缓冲区后一个字节一个字节读
        /*
        读取不到返回-1
        细节与基本流类似
         */
        //定义一个变量用于接收读到的字符编码
        int b;
        while((b = bis1.read()) != -1) {
            System.out.print((char) b);
        }
        //带参read(byte[] bytes)方法
        /*
        先获取数据填满缓冲区后每次读取一个数组长度的字节数
        方法返回本次读取到的字符编码的个数 读取不到返回-1
         */
        //System.out.println();

        //创建一个数组用于存储读取到的字符编码
        byte[] bytes = new byte[2];
        //创建一个变量由于接收方法返回值获取本次读取到的字符编码个数
        int len;
        while ((len = bis2.read(bytes)) != -1){
            System.out.print(new String(bytes,0,len));
        }
        //3.使用完毕 释放资源
        bis2.close();
        bis1.close();

        //字节缓冲输出流
        //1.创建对象
        /*
        参数一接收要包装的基本流 参数二接收缓冲区长度
        不传递参数二默认缓冲区长度为8192
        细节与基本流类似 文件不能存在就创建 已存在就清空
        续写开关由基本流的构造方法控制
         */
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("j.txt",false));
        //2.写出数据
        //write(int b)
        bos.write(97);//表示写出字符编号为97的字符
        //write(byte[] bytes) 与 write(byte[] bytes,int off,int len)
        String str = "bcde";
        bos.write(str.getBytes());
        bos.write(str.getBytes(),0,2);
        //3.使用结束 释放资源
        bos.close();/*
        因为缓冲区机制 当写入较少数据且为释放或手动刷新 则数据没有写到文件中去
        */
    }

}
