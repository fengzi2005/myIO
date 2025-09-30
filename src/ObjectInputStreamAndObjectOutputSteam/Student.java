package ObjectInputStreamAndObjectOutputSteam;

import java.io.Serial;
import java.io.Serializable;

public class Student implements Serializable {
    /*
    设置静态版本号 否则文件序列化写出后javabean类发生变化 读入会报错
     */
    @Serial
    private static final long serialVersionUID = -1261007761561278559L;
    String name;
    int age;
    /*
    添加transient修饰表示不参与序列化
     */
    transient String  address;
    //新增成员变量不会报错 只是读入的时候没有这个字段的值 则为默认值
    int cnt;

    public Student(int age, String name,String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", cnt=" + cnt +
                '}';
    }



    public Student() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
