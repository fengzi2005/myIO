package myZipStream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Demo21 {
    public static void main(String[] args) throws IOException {
        //压缩文件夹练习
        //创建两个文件对象 一个表示要压缩的文件夹 一个表示压缩包的位置
        File file1 = new File("src/myZipStream/压缩文件夹练习");
        File dest = file1.getParentFile();
        //因为涉及多层文件夹 所以要递归 压缩包创建不能放在方法里
        String fileName = file1.getName().split("\\.")[0] + ".zip";
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(dest,fileName)));
        //调用压缩方法
        toZip(file1,file1.getName(),zos);
        //释放资源
        zos.close();

    }
    /*
    压缩方法要自己写
    参数一：要压缩的文件
    参数二：记录压缩包内文件相对路径的字符串 因为要递归 得记录这个文件的多层父级文件夹
    参数三：压缩流对象
    因为压缩流对象是在方法外创建的 也就是压缩包已经创建了 所以不用传入dest
     */
    public static void toZip(File file1,String fileName,ZipOutputStream zos) throws IOException {
        //遍历文件夹
        File[] files = file1.listFiles();
        for (File file : files) {
            //判断是文件夹还是文件
            if (file.isFile()){
                //创建对应文件并添加到压缩包中
                ZipEntry ze = new ZipEntry(fileName + "\\" + file.getName());
                zos.putNextEntry(ze);
                //开始写入内容
                FileInputStream fis = new FileInputStream(file);
                int b;
                while ((b = fis.read()) != -1){
                    zos.write(b);//此时zos指向刚刚放入的ZipEntry对象 所以是写到刚刚放入的文件中
                }
                //释放资源
                fis.close();
                zos.closeEntry();
                //此处不要把zos给关了 因为还要递归 zos是在方法外创建的
            }else {
                //是文件夹则递归 但是要更新文件在压缩包内的相对路径
                toZip(file, fileName + "\\" +file.getName() ,zos);
            }
        }

    }
}
