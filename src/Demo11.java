import java.io.*;

public class Demo11 {
    public static void main(String[] args) throws IOException {
        //字符缓冲流
        //字符缓冲输入流
        System.out.println("===缓冲字符输入流===");
        //1.创建对象
        /*
        参数一：要包装的基本流
        参数二：设置缓冲区的大小 默认8192
        流创建的细节与基本流类似
         */
        BufferedReader br1 = new BufferedReader(new FileReader("f.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("e.txt"));
        //2.读入数据

        //read()一个字符一个字符地读
        /*
        方法返回读到的字符对应的编码
        读不到返回-1
         */
        //创建一个变量用于接收读取到的字符编码
        int b;
        while((b = br1.read()) != -1){
            System.out.print((char) b);
        }

        //read(char[] chars) 与 read(char[] chars,int off,int len)
        /*
        方法与基本流类似
        字符数组中存储的是读入的字符
         */
        System.out.println();
        System.out.println("=======");

        //***readline
        /*
        缓冲字符输入流特有的方法
        整行读取 直到遇到换行符 但不读取换行符
        方法返回读取到的字符串 读取不到返回null
         */
        //定义一个变量用于接收读取到的字符串
        String str;
        while((str = br2.readLine()) != null){
            System.out.print(str);
        }

        //缓冲字符输出流
        System.out.println();
        System.out.println("===缓冲字符输出流===");
        //1.创建对象
        /*
        参数一：要包装的基本流
        参数二：设置缓冲区的大小 默认8192
        细节与基本流类似
         */
        BufferedWriter bw = new BufferedWriter(new FileWriter("k.txt",false),8192);

        //2.写入数据
        //方法返回值均为void
        /*
        与基本流一样有
        write(int i)
        write(String str)
        write(String str, int off,int len)
        write(char[] chars)
        write(char[] chars,int off,int len)
         */

        //***newLine()字符缓冲输出流特有的方法
        /*
        实现跨平台换行 会根据程序运行的系统写出一个相应的换行符
         */
        bw.write("山歌好比春江水");
        bw.newLine();
        bw.write("不怕滩险弯又多");
        //3.使用完毕 释放资源
        bw.close();
    }
}
