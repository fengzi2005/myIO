import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Demo8 {
    public static void main(String[] args) throws IOException {
        //综合练习三：修改文件 对文本文件中的数据进行排序
        //1.读入文件
        //创建对象
        FileReader fr = new FileReader("h.txt");
        char[] chars = new char[2];//用于存储读到的数据
        int len;//用于接收读到的数据个数
        StringBuilder sb = new StringBuilder();//用于拼接得到文件中的字符串
        //读取
        while ((len = fr.read(chars)) != -1) {
            sb.append(chars,0,len);
        }
        //分割获取整形数组
        String[] str1 = sb.toString().split("-");

        //排序
        Integer[] str3 = Arrays.stream(str1)
                .map(Integer::parseInt)
                .sorted()
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(str3)); //此方法同样可以实现排序
        Arrays.sort(str1);
        //创建集合并把数都添加到集合中
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : str1) {
            list.add(Integer.parseInt(s));
        }
        //将集合转化为字符串
        String str2 = list.toString()
                .replace(", ", "-");//格式替换

        FileWriter fw = new FileWriter("a.txt");
        fw.write(str2,1,str2.length()-2);//去掉两边的中括号


        //3.使用完毕 释放资源
        fw.close();
        fr.close();


    }
}
