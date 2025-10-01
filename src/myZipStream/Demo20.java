package myZipStream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Demo20 {
    public static void main(String[] args) throws IOException {
        //压缩文件练习
        //创建两个文件对象 一个表示要压缩的文件 一个表示压缩包的路径(位置)
        File file1 = new File("src\\myZipStream\\压缩文件练习.txt");
        //压缩到与文件同个父级路径下 调用getParentFile()方法可以获取父级路径(压缩包位置)
        File dest = file1.getParentFile();
        //压缩方法
        toZip(file1,dest);
    }

    public static void toZip(File file1,File dest) throws IOException {
        //获取压缩文件名
        String fileName = file1.getName()
                .split("\\.")[0] + ".zip";//换后缀

        // 创建压缩流对象 构造方法包装一个压缩文件的写出字符流对象
        /*
        dest只是压缩路径 加上压缩后的文件名才是压缩文件
        实际是创建压缩包
         */
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(dest,fileName)));
        //创建ZipEntry对象 构造方法内传递一个文件名
        //实际是创建压缩包内的内容 文件或文件夹
        ZipEntry ze = new ZipEntry(file1.getName());
        //调用putNextEntry()方法将ZipEntry对象添加到压缩包内
        /*且添加完后zos的指针指向这个文件(夹)
        此时调用write()方法 是写到刚刚放入的这个文件中
         */
        zos.putNextEntry(ze);
        // 压缩文件创建完成开始写入内容
        //创建字节读入流对象
        FileInputStream fis = new FileInputStream(file1);
        int b;
        while ((b =fis.read()) != -1){
            zos.write(b);
        }
        //释放资源
        fis.close();
        zos.closeEntry();
        zos.close();
    }
}
