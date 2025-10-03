package comprehensiveTest3;

public class Student {
    String name;
    String gender;
    int age;
    double weight;


    public Student() {
    }
    public Student(String str) {
        String[] sut = str.split("-");
        this.name = sut[0];
        this.gender = sut[1];
        this.age = Integer.parseInt(sut[2]);
        this.weight = Double.parseDouble(sut[3]);
    }


    public Student(String name, String gender, int age, double weight) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * 设置
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        return name + "-" + gender + "-" + age + "-" + weight;
    }
}
