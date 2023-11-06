package JssSt1205;

public class Buss extends Car{
    Buss(String name,String color,int price){
        super(name,color,price);
    }
    
    public void display(){
        System.out.println("This is Buss!");
        super.display();
    }
}
