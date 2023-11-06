package JssSt1201;

public class JssSt1201 {
    public static void main(String[] args){
        Human[] humans = {
            new Human(10,"jon"),
            new Human(12,"Mike"),
            new Human(11,"Ken"),
            new Human(10,"Taro")
        };
        
        for(int i = 0; i < humans.length; i++ ){
            if(humans[i].getAge()==10){
                humans[i].display();
            }
        }
    }
}
