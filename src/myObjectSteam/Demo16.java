package myObjectSteam;

import java.io.*;

public class Demo16 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化流和反序列化流
        //序列化流:将对象序列化写出到文件中
        //反序列化:将序列化对象读入到程序中
        /*
        细节：
        1.被序列化的对象必须实现标记接口Serializable 否则序列化写出的时候报错
        2.序列化写出后的文件内容不能更改 否则读入的时候会报错
        3.对象序列化写出到文件后 如果该javabean类被修改 则版本号发生改变 读入的时候版本号不同会报错
        解决办法:自定义一个静态版本号 使得版本号保持不变 就不会对不上 没有值的成员变量记为null
        4.若有某个成员变量不想参与到序列化写出中 则用scient修饰
        那么序列化文本中将不会含有该成员变量的信息 所以读入的时候该成员变量为null
         */

        Student s1 = new Student(21,"zhangsan","南京");

        //输出
        //1.创建序列化输出流对象

        //序列化流本身是高级流 属于字节流 构造方法中要包装一个字节流

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src//ObjectInputStreamAndObjectOutputSteam//a.txt"));
        //2.调用writeObject将s1序列化写出到文件中
        oos.writeObject(s1);
        //3.用完关流
        oos.close();

        //读入
        //1.创建序列化读入流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src//ObjectInputStreamAndObjectOutputSteam//a.txt"));
        //2.调用readObject()方法读入对象序列化文本
        Student s2 = (Student) ois.readObject();
        System.out.println(s2);
        //3.用完关流
        ois.close();


    }
}
