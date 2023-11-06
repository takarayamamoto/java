package JssSt1205;

public class Taxi extends Car{
    Taxi(String name,String color,int price){
        super(name,color,price);
    }
    
    public void display(){
        System.out.println("This is Taxi!");
        super.display();
    }
}
