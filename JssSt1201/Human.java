package JssSt1201;

public class Human {
    int age;
    String name;
    
    Human(int age,String name){
        this.age = age;
        this.name = name;
    }
    
    public int getAge(){
        return age;
    }
    
    public void display(){
        System.out.println("I'm "+name);
    }
}
