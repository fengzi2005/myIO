package comprehensiveTest2;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo25 {
    public static void main(String[] args) {
        System.out.println("======综合练习一======");
        //综合练习一：随机点名
        //利用hutoll中FileUtil类静态方法file() 创建文件对象
        File nameFile = FileUtil.file(new File("src/comprehensiveTest2"), "name.txt");
        //调用hutoll中FileUtil类静态方法readUtf8Lines() 读取文件内容
        List<String> namelist = FileUtil.readUtf8Lines(nameFile);
        /*随机取出一个元素
          利用Collections工具类打乱集合后取第一个
         */
        Collections.shuffle(namelist);
        //输出抽取到学生的姓名
        System.out.println(namelist.get(0).split("-")[0]);

        System.out.println("======综合练习二======");
        //综合练习二：70%抽男生 30抽女生
        /*
        1.一个集合存储7个1 3个0 抽到1就抽男生 抽到3就抽女生
        2.生成0-1.0中随机数 若小于0.7则抽男生 否则抽女生 利用Math.radom()方法
         */
        //先将集合中男女信息分开成两个集合
        ArrayList<String> boyName = new ArrayList<>();
        ArrayList<String> girlName = new ArrayList<>();
        for (String name : namelist) {
            if ("男".equals(name.split("-")[1])){
                boyName.add(name);
            }else {
                girlName.add(name);
            }
        }

        /*int boyCnt = 0;
        int girlCnt = 0;
        for (int i = 0; i < 10000 ; i++) {
            //生成一个范围在0-1.0的随机数
            double random = Math.random();
            //判断random属于哪个范围
            if(random <= 0.7){
                //抽男生
                Collections.shuffle(boyName);
                System.out.println(boyName.get(0));
                boyCnt++;
            }else {
                Collections.shuffle(girlName);
                System.out.println(girlName.get(0));
                girlCnt++;
            }
        }
        System.out.println("男：" + boyCnt);
        System.out.println("女：" + girlCnt);*/
        //生成一个范围在0-1.0的随机数
        double random = Math.random();
        //判断random属于哪个范围
        if(random <= 0.7){
            //抽男生
            Collections.shuffle(boyName);
            System.out.println(boyName.get(0));
        }else {
            Collections.shuffle(girlName);
            System.out.println(girlName.get(0));
        }


        System.out.println("======综合练习三======");
        //综合练习三：程序第三次运行必定抽取到卢寒春
        //创建一个文件用以记录程序运行的次数
        File cntFile = FileUtil.file(new File("src/comprehensiveTest2"), "cnt.txt");
        //文件中的数据
        List<String> list = FileUtil.readUtf8Lines(cntFile);
        //判断文件中有无内容
        if (list.size() == 0){
            //无内容 则是第一次运行
            list.add("1");
            //写出到文件
            FileUtil.writeUtf8Lines(list,cntFile);
            //随机抽取一个学生姓名
            Collections.shuffle(namelist);
            System.out.println("第一次运行:" + namelist.get(0).split("-")[0]);
        }else {
            //有内容 说明不是第一次运行
            int cnt = Integer.parseInt(list.get(0));
            //次数自增
            cnt++;
            //判断次数
            if(cnt == 3) {
                //已运行两次 本次是第三次运行
                System.out.println("第3次运行:" + "卢寒春");
            }else {
                //本次不是第三次运行
                //随机抽取一个学生
                Collections.shuffle(namelist);
                System.out.println("第" + cnt + "次运行:" + namelist.get(0).split("-")[0]);
            }
            //更新文件中的数据
            list.set(0,cnt + "");
            FileUtil.writeUtf8Lines(list,cntFile);
        }


        System.out.println("======综合练习四======");
        //综合练习三：抽取到的学生不会再被抽到 全部抽完开始新的一轮
        /*
        抽到一个删一个
        全部删完再重新开始
         */

        //创建一个临时文件用于存放数据
        File dest = FileUtil.file(new File("src/comprehensiveTest2"), "tempName.txt");
        //读取文件
        List<String> tempNameList = FileUtil.readUtf8Lines(dest);
        if (tempNameList.size() == 0){
            //临时文件中无姓名 将原文件复制到临时文件中
            FileUtil.copy(nameFile,dest,true);
            //随机抽取一个姓名
            selectName(dest,tempNameList);
        }else {
            //随机抽取一个姓名
            selectName(dest,tempNameList);
        }

        /*for (int i = 0; i < 3 ; i++) {
            //创建一个临时文件用于存放数据
            File dest = FileUtil.file(new File("src/comprehensiveTest2"), "tempName.txt");
            //读取文件
            List<String> tempNameList = FileUtil.readUtf8Lines(dest);
            if (tempNameList.size() == 0){
                //临时文件中无姓名 将原文件复制到临时文件中
                FileUtil.copy(nameFile,dest,true);
                //随机抽取一个姓名
                selectName(dest,tempNameList);
            }else {
                //随机抽取一个姓名
                selectName(dest,tempNameList);
            }
        }*/

    }

    private static void selectName(File dest,List<String> tempNameList) {
        //读取临时文件中的内容
        tempNameList = FileUtil.readUtf8Lines(dest);
        //随机抽取一个姓名
        Collections.shuffle(tempNameList);
        System.out.println(tempNameList.get(0).split("-")[0]);
        //更新文件内容
        //删去抽到的内容
        tempNameList.remove(0);
        //更新文件
        FileUtil.writeUtf8Lines(tempNameList, dest);
    }
}
