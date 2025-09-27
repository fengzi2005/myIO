import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo3 {

    public static void main(String[] args){
        //字节流小练习：拷贝文件(拷贝文件就用字节流)
        //1.创建对象
        //2.FileInputStream对象读取数据
        //3.FileOutputStream对象写出数据
        //4.使用完毕释放资源 **先创建后释放**(本处使用了try...catch 自动释放资源机制)
        try(FileInputStream fis = new FileInputStream("b.txt");
            FileOutputStream fos = new FileOutputStream("d.txt")){
            byte[] bytes1 = new byte[2];
            int len;
            while ((len = fis.read(bytes1)) != -1) {
                fos.write(bytes1,0,len);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //try...catch自动释放资源机制的另一种写法
        /*FileInputStream fis = new FileInputStream("b.txt");
        FileOutputStream fos = new FileOutputStream("d.txt");
        try (fos;fis){

        }catch (IOException e){
            e.printStackTrace();
        }*/
    }
}
