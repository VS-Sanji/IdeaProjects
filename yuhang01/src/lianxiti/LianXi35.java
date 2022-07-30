package lianxiti;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * 1、请使用序列化和反序列化机制，完成学生信息管理系统。
 *
 * 系统打开时显示以下信息：
 * 欢迎使用学生信息管理系统，请认真阅读以下使用说明：
 * 请输入不同的功能编号来选择不同的功能：
 * [1]查看学生列表
 * [2]保存学生
 * [3]删除学生
 * [4]查看某个学生详细信息
 *
 * --------------------------------------------------------------------
 * 学生信息列表展示
 * 学号			姓名			性别
 * ------------------------------------
 * 1			zhangsan		男
 * 2			lisi			女
 *
 * --------------------------------------------------------------------
 * 查看某个学生详细信息
 * 学号：1
 * 姓名：张三
 * 生日：1990-10-10
 * 性别：男
 * 邮箱：zhangsan@123.com
 *
 * ---------------------------------------------------------------------
 * 删除学生时，需要让用户继续输入删除的学生编号，根据编号删除学生。
 *
 * 注意：请使用序列化和反序列化，以保证关闭之后，学生数据不丢失。
 * 学生数据要存储到文件中。
 */
public class LianXi35 {
    public static void main(String[] args) {

    }

    public static void StuManager(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用学生信息管理系统,请认真阅读以下使用说明");
        System.out.println("请输入不同的功能编号来选择不同的功能");
        System.out.println("[1] 查看学生列表");
        System.out.println("[2] 保存学生");
        System.out.println("[3] 删除学生");
        System.out.println("[4] 查看某个学生详细信息");

        int i = scanner.nextInt();
        switch (i) {
            case 1:
                
            case 2:
                loadStudent();
            case 3:
                
            case 4:
                
        }


    }

    private static void loadStudent() {
        List<StudentIo> list = new ArrayList<>();

    }


    public static void Serialize(List<StudentIo> list){
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("StudentIo",true));
            out.writeObject(list);

            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static List<StudentIo> Deserialize(File file){
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            List<StudentIo> list = (List<StudentIo>)in.readObject();
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return null;
    }

}

class StudentIo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private int age;

    private int no;

    private boolean sex;

    private String birthday;

    private String emailAddress;

    public StudentIo() {
    }

    public StudentIo(String name, int age, int no, boolean sex, String birthday, String emailAddress) {
        this.name = name;
        this.age = age;
        this.no = no;
        this.sex = sex;
        this.birthday = birthday;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getbirthday() {
        return birthday;
    }

    public void setbirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentIo studentIo = (StudentIo) o;
        return age == studentIo.age && no == studentIo.no && sex == studentIo.sex && Objects.equals(name, studentIo.name) && Objects.equals(birthday, studentIo.birthday) && Objects.equals(emailAddress, studentIo.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, no, sex, birthday, emailAddress);
    }

    @Override
    public String toString() {
        return "StudentIo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", no=" + no +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}