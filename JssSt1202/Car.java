package JssSt1202;

public class Car {
    String name;
    String color;
    int price;
    
    Car(String name,String color,int price){
        this.name = name;
        this.color = color;
        this.price = price;
    }
    
    public void display(){
        System.out.println("name:"+name);
        System.out.println("color:"+color);
        System.out.println("price:"+price);
    }
}
