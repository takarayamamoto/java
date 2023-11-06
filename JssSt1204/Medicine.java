package JssSt1204;

public class Medicine extends Item{
    int age;
    
    
    Medicine(int age,String name,int price,int stock){
        super(name,price,stock);
        this.age = age;
    }
    
    @Override
    public void display(){
        System.out.println("name:"+name);
        System.out.println("price:"+price);
        System.out.println("stock:"+stock);
        System.out.println("age:"+age);
    }
}
