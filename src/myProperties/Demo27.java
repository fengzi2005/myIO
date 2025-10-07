package myProperties;

import java.io.*;
import java.util.Properties;

public class Demo27 {
    public static void main(String[] args) throws IOException {
        //配置文件 properties类 演示
    /*
    是双列集合 map集合的子类 简单理解是双列集合＋文件读写方法
     */
        //基本方法
        Properties properties1 = new Properties();
        properties1.put("aaa","bbb");
        properties1.put("ccc","ddd");
        properties1.put("eee","fff");
        properties1.put("ggg","hhh");
        System.out.println(properties1);
        //写出到文件 store()方法
        /*
        参数一：字节输出流或字符输出流 用于指定写出到哪个文件 文件后缀名要写.properties
        参数二：在文件头部添加的注释
         */
        FileOutputStream fos = new FileOutputStream("src/myProperties/a.properties");
        properties1.store(fos,"test");

        //读入配置文件 (文件内容格式要求严格 不加空格 不加分号)
        /*
        参数为读入字符流或读入字节流
         */
        Properties properties2 = new Properties();
        FileInputStream fis = new FileInputStream("src/myProperties/a.properties");
        properties2.load(fis);
        System.out.println(properties2);

    }


}
