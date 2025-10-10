package comprehensiveTest0;

import java.io.*;

public class Demo6 {
    public static void main(String[] args) throws IOException {
        //综合练习一：拷贝文件夹
        //拷贝就用字节流

        //创建对象
        File f1 = new File("D:\\遍历练习");
        File f2 = new File("copy遍历练习");

        copyFile(f1,f2);

    }
    static void copyFile(File file0,File file1) throws IOException {
        if (file0 == null && file1 == null) {
            return;
        }
        if (file0.isFile()) {
            //1.创建IO流对象
            FileInputStream fis = new FileInputStream(file0);
            FileOutputStream fos = new FileOutputStream(file1);//往文件夹里存而不是存在文件夹上
            //2.读入文件 写出到新文件夹
            byte[] bytes1 = new byte[2];
            int len;
            while ((len =fis.read(bytes1)) != -1){
                fos.write(bytes1,0,len);
            }
            //3.使用完毕 释放资源
            fis.close();
            fos.close();
        }else {
            //要拷贝的是文件夹
            File[] files = file0.listFiles();
            //目的地创建文件夹
            file1.mkdirs();
            //遍历原文件夹
            if (files != null && files.length > 0) {
                for (File file : files) {
                    //重复调用方法 递归实现多层文件夹的拷贝
                    copyFile(file,new File(file1,file.getName()));
                }
            }

        }
    }

}
