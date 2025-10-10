package bufferStream;

import java.io.*;

public class Demo12 {
    public static void main(String[] args) throws IOException {
        //综合练习一：比较四种拷贝方式的用时

        //1.方式一：字节流 一个字节一个字节地拷贝
        long l1 = System.currentTimeMillis();
        FileInputStream fis1 = new FileInputStream("csb1.txt");
        FileOutputStream fos1 = new FileOutputStream("copy1.txt");
        int b1;
        while ((b1 = fis1.read()) != -1) {
            fos1.write(b1);
        }
        fos1.close();
        fis1.close();
        long l2 = System.currentTimeMillis();
        System.out.println("方式一用时：" + (l2-l1) + "毫秒");
        //2.方式二：字节流一个字符数组一个为字符数组地拷贝
        long l3 = System.currentTimeMillis();
        FileInputStream fis2 = new FileInputStream("csb1.txt");
        FileOutputStream fos2 = new FileOutputStream("copy2.txt");
        byte[] bytes = new byte[100];
        int len1;
        while((len1 = fis2.read(bytes)) != -1){
            fos2.write(bytes,0,len1);
        }
        fos2.close();
        fis2.close();
        long l4 = System.currentTimeMillis();
        System.out.println("方式二用时：" + (l4-l3) + "毫秒");

        //方式三：字符流一个字符一个字符
        long l5 = System.currentTimeMillis();
        FileReader fr1 = new FileReader("csb1.txt");
        FileWriter fw1 = new FileWriter("copy3.txt");
        int b2;
        while ((b2 = fr1.read()) != -1) {
            fw1.write((char)b2);
        }
        fw1.close();
        fr1.close();
        long l6 = System.currentTimeMillis();
        System.out.println("方式三用时：" + (l6-l5) +"毫秒");

        //方式四：字符流一个字符数组一个字符数组地读
        long l7 = System.currentTimeMillis();
        FileReader fr2 = new FileReader("csb1.txt");
        FileWriter fw2 = new FileWriter("copy4.txt");
        char[] chars = new char[100];
        int len2;
        while ((len2 = fr2.read(chars)) != -1) {
            fw2.write(chars,0,len2);
        }
        fw2.close();
        fr2.close();
        long l8 = System.currentTimeMillis();
        System.out.println("方式四用时：" + (l8-l7) +"毫秒");
    }
}
