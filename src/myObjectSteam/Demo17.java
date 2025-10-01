package myObjectSteam;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Demo17 {

    /*
    一次性写出多个对象  读入的时候不知道要读几个
    所以习惯先将对象添加到集合中再序列化写出
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student s1 = new Student(21, "zhangsan", "南京");
        Student s2 = new Student(23, "lisi", "北京");
        Student s3 = new Student(25, "wangwu", "长沙");
        Student s4 = new Student(27, "zhaoliu", "重庆");

        ArrayList<Student> list = new ArrayList<>();
        Collections.addAll(list, s1, s2, s3, s4);

        //创建序列化写出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src//ObjectInputStreamAndObjectOutputSteam//b.txt"));
        //写出集合
        oos.writeObject(list);
        //关流
        oos.close();

        //创建序列化读入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src//ObjectInputStreamAndObjectOutputSteam//b.txt"));
        //读入序列化文件
        ArrayList<Student> students = (ArrayList<Student>) ois.readObject();
        //读入结果
        students.forEach(s -> System.out.println(s));
        //关流
        ois.close();
    }
}
