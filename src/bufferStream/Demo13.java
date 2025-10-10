package bufferStream;

import java.io.*;
import java.util.*;

public class Demo13 {
    public static void main(String[] args) throws IOException {
        //综合练习二：将出师表排序后写到新的文件中
        //创建缓冲字符流对象
        BufferedReader br = new BufferedReader(new FileReader("csb.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("sortCsb.txt"));
        //创建集合用于存储读取到的字符串
        ArrayList<String> list = new ArrayList<>();
        Map<Integer,String> m = new TreeMap<>();//方式二 用双列集合TreeMap实现自动排序
        //调用缓冲字符读入流的readLine()方法逐行读出字符串并存入集合
        String str;
        while ((str = br.readLine()) != null) {
            list.add(str);
            //方式二
            m.put(Integer.parseInt(str.split("\\.")[0]),
                    str.split("\\.")[1]);
        }
        //对list进行排序
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i1 = Integer.parseInt(o1.split("\\.")[0]);
                int i2 = Integer.parseInt(o2.split("\\.")[0]);
                return i1 - i2;
            }
        });
        //查看排序结果
        //System.out.println(list);
        //方式二：
        Set<Integer> integers = m.keySet();
        integers.forEach(i -> System.out.println(m.get(i)));
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
