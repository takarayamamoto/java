package JssSt1203;

public class Item {
    String name;
    int price;
    int stock;
    
    Item(String name,int price,int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    void addStock(int a){
        this.stock+=a;
    }
    
    void display(){
        System.out.println("name:"+name);
        System.out.println("price:"+price);
        System.out.println("stock:"+stock);
    }
}
