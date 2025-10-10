package bufferStream;

import java.io.*;

public class Demo14 {
    public static void main(String[] args) throws IOException {
        //综合练习：记录文件运行次数 前三次提示免费 第四次提示收费
        /*
        IO流使用习惯:随用随创建 用完即关闭
         */
        //创建缓冲字符读入流对象
        BufferedReader br = new BufferedReader(new FileReader("cnt.txt"));
        //读取文件中的内容
        String str = br.readLine();
        //用完 关流
        br.close();
        //创建缓冲字符写出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("cnt.txt"));
        /*
        此处必须先调用br的readLine()方法让cnt.txt文件中的内容读入缓冲区才能创建写出流对象
        否则文件内容丢失
         */
        //创建变量用于接收程序已经运行了几次
        int cnt;
        //判断
        /*
        1)文件为空 表示没运行过
        2)文件不为空 获取已运行过的数据
         */
        if (str == null){
            //文件为空
            bw.write("第1次运行");
            System.out.println("第1次运行免费");
        }else{
            //文件不为空
            cnt = Integer.parseInt(str.split("第")[1].split("次")[0]);//获取已运行次数
            if (cnt < 3){
                //已运行次数没有3次 免费
                cnt++;
                System.out.println("第" + cnt + "次运行免费");
            }else {
                //超过三次 收费
                cnt++;
                System.out.println("您的免费次数已用完");
            }
            //更新已运行次数
            bw.write("第" + cnt + "次运行");
        }

        //用完关流
        bw.close();

    }
}
