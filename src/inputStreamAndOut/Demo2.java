package inputStreamAndOut;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        //FileInputStream
        //1.创建对象
        /*
        构造方法参数可以传字符串表示文件路径 也可以直接传文件对象
        文件不存在则会抛出异常(区别于FileOutputStream 文件不存在的情况)
        因为读入时文件不存在则数据不存在 关键是数据
         */
        //FileInputStream fis = new FileInputStream(new File("aaa.txt"));
        FileInputStream fis1 = new FileInputStream("b.txt");
        FileInputStream fis2 = new FileInputStream("c.txt");
        FileInputStream fis3 = new FileInputStream("c.txt");

        //2.从文件中读出数据
        /*
        read()空参读取一个字节 返回该字节的Ascii码
        可以用循环 将文件一个一个字节读完
        ***如果读完了 则返回-1
         */
        int b;
        while ((b = fis1.read()) != -1){
            System.out.print(b);
            System.out.println((char) b);
        }
        System.out.println("============");
        /*
        read(byte[] bytes)方法让读取更加高效
        每次最多可以读取bytes.length个字节的数据
        方法返回本次读取到的字节数 (每次读取会尽量读满 但最后一次可能会读不满) 若没有读入字节 则返回-1
        读取完后字节储存在数组中 通过数组获取
         */
        byte[] bytes1 = new byte[2];
        int num;
        int cnt = 1;
        while ((num = fis2.read(bytes1)) != -1 ){
            System.out.println("第" + cnt + "次");
            //System.out.println(Arrays.toString(bytes1));数组里读到的字符对应的十进制编码
            System.out.println(new String(bytes1) + " 读了" + num + "个");
            cnt++;
        }
        /*
        第1次
        ab 读了2个
        第2次
        cd 读了2个
        第3次
        ed 读了1个 读了一个 只覆盖了数组的第一个元素 第二个d是第二次读取时留下的
         */

        System.out.println("============");

        //循环读取代码代码改进 只输出本次读到的
        int len;
        while ((len =fis3.read(bytes1)) != -1) {
            System.out.print(new String (bytes1,0,len));//第二个参数开始索引 第三个参数选择长度(保证只输出新读取的数据)
        }

        //使用完毕释放资源
        fis1.close();
        fis2.close();
        fis3.close();
    }

}
