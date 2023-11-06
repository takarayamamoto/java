package JssSt1301;

import java.util.Scanner;

public class AnimalRace {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Animal anima=new Animal();

        anima.sp(3);

        int num = sc.nextInt();
        int num1=num/10000%10;
        int num2=num/1000%10;
        int num3=num/100%10;
        int num4=num/10%10;
        int num5=num/1%10;
        int sum=num1+num2+num3+num4+num5;

        System.out.println("human="+num1+" dog="+num2+" cat="+num3+" horse="+num4+" capybara="+num5);
        for(int i=0;i<sum;i++){
            anima.display();
        }
    }
}
