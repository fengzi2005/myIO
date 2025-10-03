package huTool;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo22 {
    public static void main(String[] args) throws IOException {
        //hutool工具类 关于io流演示
        //FileUtil类

        //获取文件对象的方法 类名.file()
        /*
        参数：1.直接用字符表示文件路径 得绝对路径带盘符
             2.父类路径(文件/字符串) ＋ 子类位置(字符串)  得相对路径
             3.各个路径名 (可变参数 传多个名字) 绝对路径
             4.父级(文件夹的File对象/字符串） 加 多个文件名 相对路径

         */
        File file1 = FileUtil.file("src/huTool/aaa/bbb/ccc");//相对于类 绝对路径
        File file2 = FileUtil.file("src/huTool","aaa/bbb/ccc");//相对路径
        File file3 = FileUtil.file("src","huTool","aaa","bbb");//相对于类 绝对路径
        File file4 = FileUtil.file(new File("src/huTool"),"aaa", "bbb", "ccc", "ddd");//相对路径
        System.out.println(file1);
        System.out.println(file2);
        System.out.println(file3);
        System.out.println(file4);

        /*File file5 = FileUtil.file(new File("src/huTool"), "aaa", "bbb", "eee");
        FileUtil.touch(file5);*/
        //创建文件方法 类名.touch(File file)
        /*
        父级路径不存在则自动创建文件夹 相当于mkdirs() + createNewFile();
        也就是说作用是文件夹+文件
         */
        FileUtil.touch(file4);

        //写出文件方法
        //1.覆盖方法 类名.writeLines(Collection c,文件对象或文件路径字符串,字符集,续写开关(默认false 可不传))方法
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("aaa");
        list1.add("bbb");
        list1.add("ccc");
        FileUtil.writeLines(list1,file4,"UTF-8");
        //2.续写方法 类名.appendLines(Collection c,文件对象或文件路径字符串,字符集)
        list1.add("ddd");
        FileUtil.appendLines(list1,file4,"UTF-8");

        System.out.println("=========");

        //读入文件方法
        //1.指定字符集读入 类名.readLines(文件对象,字符集,集合(用于存储读到的内容,可不传))
        List<String> list2 = FileUtil.readLines(file4, "UTF-8");
        System.out.println(list2);
        ArrayList<String> list3 = new ArrayList<>();
        FileUtil.readLines(file4,"UTF-8",list3);
        System.out.println(list3);
        //2.以"UTF-8"读入 类名.readUtf8Lines(文件对象,集合(用于存储读取到的内容 可不传))
        List<String> list4 = FileUtil.readUtf8Lines(file4);
        System.out.println(list4);
        ArrayList<String> list5= new ArrayList<>();
        FileUtil.readUtf8Lines(file4,list5);
        System.out.println(list5);

        //拷贝文件或文件夹的方法
        /*
        类名.copy(File src(字符串), File dest(字符串), boolean isOverride)
        复制文件或目录
        情况如下：
        1、src和dest都为目录，则将src目录及其目录下所有文件目录拷贝到dest下
        2、src和dest都为文件，直接复制，名字为dest
        3、src为文件，dest为目录，将src拷贝到dest目录下
        参数三表示是否覆盖
         */
        FileUtil.copy(new File("src/huTool/aaa"),new File("src/huTool/copy"),false);


    }

}
