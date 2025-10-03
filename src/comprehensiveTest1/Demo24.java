package comprehensiveTest1;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo24 {
    public static void main(String[] args) throws IOException {
        //综合练习一:网站姓名爬取  利用hutool工具类爬取数据
        //创建一个StringBuilder对象 拼接读取到的内容
        //创建URL对象(网站)
        URL url1 = new URL("https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0");
        URL url2 = new URL("http://www.haoming8.cn/baobao/10881.html");
        URL url3 = new URL("https://www.cnjiaju.cn/article/183003.html");
        //读取网站内容 利用hutool的HttpUtil类的get()方法
        String firstNamestr = HttpUtil.get(url1.toString());
        String boyNamestr = HttpUtil.get(url2.toString());
        String girlNamestr = HttpUtil.get(url3.toString());
        /*System.out.println(firstNamestr);
        System.out.println(boyNamestr);
        System.out.println(girlNamestr);*/

        //正则爬取 利用ReUyil类的findAll()方法
        /*
        Re是正则表达式regex的意思
        参数一:正则表达式
        参数二:要爬取的字符串
        参数三:爬取正则表达式的第几组
         */
        ArrayList<String> firstNameTemplist = (ArrayList<String>) ReUtil.findAll("([\\u4E00-\\u9FA5]{4})(，|。)", firstNamestr, 1);
        ArrayList<String> boyNameList = (ArrayList<String>) ReUtil.findAll("([\\u4E00-\\u9FA5]{2})(、|。)", boyNamestr, 1);
        ArrayList<String> girlNameTemplist = (ArrayList<String>) ReUtil.findAll("(([\\u4E00-\\u9FA5]{2})(、)){4}[\\u4E00-\\u9FA5]{2}", girlNamestr, 0);

        System.out.println(firstNameTemplist);
        System.out.println(boyNameList);
        System.out.println(girlNameTemplist);
        //处理姓氏集合
        ArrayList<String> firstNameList = getfirstName(firstNameTemplist);
        //处理女孩名字集合
        ArrayList<String> girlNameList = getGirlName( girlNameTemplist);
        //整个数据得到目标集合
        ArrayList<String> list = getList(firstNameList,boyNameList,girlNameList,50,50);
        //打印结果集合
        System.out.println(list);
        //打乱集合
        Collections.shuffle(list);

        //利用hutool的FileUtil类将集合写到文件中去
        //创建文件对象
        File file = FileUtil.file(new File("src/comprehensiveDemo"), "name2.txt");
        //写入文件
        FileUtil.writeUtf8Lines(list,file);

    }


    private static ArrayList<String> getList(ArrayList<String> firstNameList, ArrayList<String> boyNameList, ArrayList<String> girlNameList, int boyCnt, int girlCnt) {
        //创建集合用于存储姓名
        ArrayList<String> list = new ArrayList<>();
        getFinalNameList(firstNameList, boyNameList, boyCnt, "-男-",list);
        getFinalNameList(firstNameList,girlNameList,girlCnt,"-女-",list);
        return list;
    }

    private static void getFinalNameList(ArrayList<String> firstNameList, ArrayList<String> boyNameList, int Cnt, String str,ArrayList<String> list) {
        //创建计数器记录已经生成的名字个数
        int cnt =0;
        //创建随机变量用于生成年龄
        Random r = new Random();
        //男孩姓名生成循环 条件是男孩姓名成个数等于传入的boyCnt
        while (cnt != Cnt) {
            //随机取一个姓和一个男孩名 采用打乱后取第一个的方式
            Collections.shuffle(firstNameList);
            Collections.shuffle(boyNameList);
            //姓名-男-年龄
            String name = firstNameList.get(0) + boyNameList.get(0) + str + r.nextInt(18,28);
            //将生成的姓名添加到集合中
            list.add(name);
            //姓名个数加1
            cnt++;
        }
    }

    private static ArrayList<String> getGirlName(ArrayList<String> girlNameTemplist) {
        //创建新集合以存储女孩名字
        ArrayList<String> girlNameList = new ArrayList<>();
        //遍历集合 处理字符串 存入新集合
        for (String s : girlNameTemplist) {
            //正则分割
            String[] strings = s.split("、");
            //遍历数组存入新集合
            for (String name : strings) {
                girlNameList.add(name);
            }
        }
        //返回新集合
        return girlNameList;
    }

    private static ArrayList<String> getfirstName(ArrayList<String> firstNameTemplist) {
        //创建新集合以存储姓氏
        ArrayList<String> firstNameList = new ArrayList<>();
        //遍历集合 将每个字符串拆成四个姓氏存入新集合
        for (String s : firstNameTemplist) {
            //转化为字符数组
            char[] chars = s.toCharArray();
            //遍历字符数组
            for (char c : chars) {
                //单个字符 + “” 转化为字符串存入新集合
                firstNameList.add(c + "");
            }
        }
        //返回新集合
        return firstNameList;
    }

    private static ArrayList<String> getInfo(String str,Pattern regex) {
        //创建一个集合用于接收结果
        ArrayList<String> list = new ArrayList<>();
        //正则表达式对象调用matcher(String str)进行爬取
        Matcher matcher = regex.matcher(str);
        //循环遍历结果集
        while (matcher.find()){
            //matcher.group(int i); 当前遍历到的结果 i表示获取这个结果的第几组 不传i表示整个结果
            list.add(matcher.group(1));
        }
        return list;
    }

    private static String getString(URL url) throws IOException {
        StringBuilder sb = new StringBuilder();

        //连接网站
        URLConnection conn = url.openConnection();
        //用conn获取字节读入流并用转换流包装
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
        //读取数据
        int b;
        while ((b = isr.read()) != -1) {
            sb.append((char) b);
        }
        return sb.toString();
    }


}
