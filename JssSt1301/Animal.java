package JssSt1301;

public class Animal {
    private final static int FIELD_LEN = 50;

    int pos;
    int speed;

    //int ani[]=new int[5];

    public void sp(int a){
        pos=0;
        speed=a;
    }
    
    public void display(){
        for(int i = 0; i < FIELD_LEN; i++ ){
            if(i==pos){
                System.out.print("A");
            }else{
                System.out.print("=");
            }
        }
        System.out.print("\n");
    }
}
