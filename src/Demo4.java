import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Demo4 {
    public static void main(String[] args) throws IOException {
        //字符流以FileReader 和 Filewriter 作为使用示例
        //1.创建对象
        //同样有两种构造 文件不存在会直接抛出异常
        FileReader fr1 = new FileReader("e.txt");
        FileReader fr2 = new FileReader(new File("f.txt"));
        //2.读出数据
        /*
        read()空参方法
        默认一个字节一个字节读 遇到中文会读三个字节(UTF-8)
        读到数据后解码并转化为十进制数并返回 想要看到结果得把十进制数强转成char
        如果读取不到字符则返回-1
         */
        int b;
        while((b = fr1.read()) != - 1){

            /*
            System.out.print(b);
            System.out.print((char) b + " ");
             10
             29123燃 31709篝 28779火 20026为 25105我 23432守 26395望
             这两行代码的输出结果能够反映\r\n
             \r表示回车 光标移动到最左边  \n表示换行
             此处因为\r光标移到最左边后  输出\n的十进制数以及\n 会导致第一行的输出全部没了
            */
            System.out.print((char) b);
        }

        /*
        read(char[] buffer) 有参方法 能够提高读取的效率
        *返回值：本次读取到的数据个数 如果本次读不到数据就返回-1
        每次读取会尽量读满数组
        读取到数据进行解码 转化为十进制 再强转为char存入数组 也就是说数组中取出来的就是数据本身(不用强转)
        从数组中获取读取到的数据
         */
        System.out.println();
        System.out.println("=======");
        char[] chars = new char[2];
        int len;//用于接收read(chars)返回的长度
        while((len = fr2.read(chars)) != -1) {
            System.out.print(new String(chars,0,len));//只输出本子读到的数据 否则最后一次输出”？样“
        }

        //3.使用结束要释放资源
        fr1.close();
        fr2.close();


    }
}
