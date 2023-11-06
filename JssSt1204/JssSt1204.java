package JssSt1204;

public class JssSt1204 {
    public static void main(String[] args){
        ElectricItem item1 = new ElectricItem(100,"DVD Player",15000,30);
        Medicine item2 = new Medicine(3,"Bufferin",800,50);
        
        item1.display();
        System.out.println("---------");
        item2.display();
    }
}
