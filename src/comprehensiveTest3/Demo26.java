package comprehensiveTest3;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo26 {
    public static void main(String[] args) {
        //综合练习：带权重的随机点名 被点过的同学下次被点的概率降低
        //几何概型：一开始 数轴上1-1.0每个同学占长度为0.01
        //计算总权重
        //读取文件内容
        File nameFile = FileUtil.file(new File("src/comprehensiveTest3"), "name.txt");
        List<String> nameList = FileUtil.readUtf8Lines(nameFile);
        //累加权重 并创建学生集合
        ArrayList<Student> stuList = new ArrayList<>();
        double totalWeitht = 0;
        for (String s : nameList) {
            totalWeitht += Double.parseDouble(s.split("-")[3]);
            stuList.add(new Student(s));
        }
        //System.out.println(totalWeitht);
        // 遍历学生集合 计算每个学生被点到名的概率存储在集合中
        ArrayList<Double> probList = new ArrayList<>();
        double tempWeight = 0;
        for (Student student : stuList) {
            tempWeight += student.weight;
            probList.add(tempWeight/totalWeitht);
        }
        //System.out.println(probList);
        //0-1.0随机生成一个数
        double random = Math.random();
        /*
        Collections.Collections.binarySearch(probList, random)
        二分搜索
        找的到返回元素索引
        找不到 返回值 = -插入点 -1
         */
        //获取插入点就可以知道抽到谁
        System.out.println(random);
        int index = -Collections.binarySearch(probList,random) - 1;
        System.out.println(index);
        //获取被抽到的学生
        Student selectedStudent = stuList.get(index);
        //打印抽到的学生
        System.out.println(selectedStudent.name);
        //更新学生的权重
        selectedStudent.setWeight(selectedStudent.getWeight()/2);
        //更新学生集合
        stuList.set(index,selectedStudent);
        //更新文件
        FileUtil.writeUtf8Lines(stuList,nameFile);


    }

}
