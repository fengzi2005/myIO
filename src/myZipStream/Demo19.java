package myZipStream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Demo19 {
    public static void main(String[] args) throws IOException {
        //解压缩流再尝试
        //1.创建两个文件对象 一个表示要解压的文件 一个表示解压路径
        File file1 = new File("D:\\解压缩流练习.zip");
        File dest = new File("src\\myZipStream");
        //2.调用解压方法
        toFile(file1,dest);
    }

    /*
    解压方法要自己写 两个参数：1.要解压的文件 2.解压路径
     */
    public static void toFile(File file1, File dest) throws IOException {
        //创建解压缩流对象 构造方法包装一个解压文件的FileInputStream对象
        ZipInputStream zis = new ZipInputStream(new FileInputStream(file1));
        //解压缩流对象主要成员方法
        /*
        1.getNextEntry() 返回一个ZipEntry对像 内容是压缩文件内的文件和文件夹路径(相对)
        2.read() 读取调用方法1后指向文件的内容
         */
        /*System.out.println(zis.getNextEntry());  //解压缩流练习/
        System.out.println(zis.getNextEntry());    //解压缩流练习/aaa.txt
        System.out.println(zis.getNextEntry());    //解压缩流练习/bbb.txt
        System.out.println(zis.getNextEntry());  //解压缩流练习/ccc/
        System.out.println(zis.getNextEntry());  //解压缩流练习/ccc/eee.rtf
        System.out.println(zis.getNextEntry());  //解压缩流练习/ddd/
        System.out.println(zis.getNextEntry());  //解压缩流练习/ddd/ffff.bmp
        System.out.println(zis.getNextEntry());  //null (文件读取结束) */
        //创建一个ZipEntry对象用于接收方法返回值
        ZipEntry ze;
        //利用循环重复调用方法1
        while ((ze = zis.getNextEntry()) != null){
            //判断 是文件夹还是文件
            if (ze.isDirectory()) {
                //是文件夹 则在解压路径创建一个文件夹
                new File(dest,ze.toString()).mkdirs();
            }else {
                //是文件 则创建FileOutputStream对象准备写出到解压路径
                /*
                写出用创建的FileOutputStream对象
                读入用的是解压缩流对象 即zis.read()
                 */
                FileOutputStream fos = new FileOutputStream(new File(dest, "\\" +ze));
                int b;
                while ((b = zis.read()) != -1){
                    fos.write(b);
                }
                //释放资源
                //关写出字符流
                fos.close();
            }
            //关解压缩流的一个Entry 意思是 一轮循环结束 则这个Entry操作结束了
            zis.closeEntry();
        }
        //释放资源 循环结束 意味着整个压缩包 读取完毕 关掉解压缩流
        zis.close();
    }
}
