package test.This;

/**
    this可以用来区分成员变量和局部变量
*/
public class ThisTest03{

    public static void main(String[] args){

        Manager m1 = new Manager("KING");
        Manager m2 = new Manager();
        m2.setName("FORD");
        System.out.println(m1.getName()); //KING
        System.out.println(m2.getName()); //FORD
    }
}

class Manager{

    //Field
    private String name;

    //Constructor
    Manager(){}

    Manager(String name){
        this.name = name;  //this可以用来区分成员变量和局部变量
    }

    //Method
    //成员方法
    public void setName(String name){
        this.name = name;     //this可以用来区分成员变量和局部变量
    }

    //成员方法
    public String getName(){
        //return name;
        return this.name;   //this可以用来区分成员变量和局部变量
    }

}