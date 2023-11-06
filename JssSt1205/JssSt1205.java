package JssSt1205;

public class JssSt1205 {
    public static void main(String[] args){
        Car car1 = new Taxi("Yellow Cab","Yellow",100);
        Car car2 = new Buss("Nishitetsu","Silver", 100);
        
        car1.display();
        System.out.println("----------");
        car2.display();
    }
}
