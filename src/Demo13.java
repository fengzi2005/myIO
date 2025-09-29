import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class Demo13 {
    public static void main(String[] args) throws IOException {
        //综合练习二：将出师表排序后写到新的文件中
        //创建缓冲字符流对象
        BufferedReader br = new BufferedReader(new FileReader("csb.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("sortCsb.txt"));
        //创建集合用于存储读取到的字符串
        ArrayList<String> list = new ArrayList<>();
        //调用缓冲字符读入流的readLine()方法逐行读出字符串并存入集合
        String str;
        while ((str = br.readLine()) != null) {
            list.add(str);
        }
        //对list进行排序
        Collections.sort(list);
        //查看排序结果
        //System.out.println(list);
        //遍历集合 将每一行字符串写入新文件
        list.forEach(s -> {
            try {
                bw.write( ( s.split("\\.") )[1] );//此处要加转义字符 否则正则表达式无效
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //使用结束 释放资源
        bw.close();
        br.close();
    }

}
