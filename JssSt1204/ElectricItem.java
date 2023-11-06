package JssSt1204;

public class ElectricItem extends Item{
    int wattage;
    
    ElectricItem(int wattage,String name,int price,int stock){
        super(name,price,stock);
        this.wattage = wattage;
    }
    
    @Override
    public void display(){
        System.out.println("name:"+name);
        System.out.println("price:"+price);
        System.out.println("stock:"+stock);
        System.out.println("wattage:"+wattage);
    }
}
