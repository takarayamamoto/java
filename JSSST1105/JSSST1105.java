package JSSST1105;

import java.util.Scanner;

public class JSSST1105 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a=sc.nextInt();
        if(a==0){
            Player player = new Player("nishino");
            Monster monster = new Slime();
            player.display();
            monster.displayAppearMsg();
            while( monster.hp > 0 ){
                int damage1 = monster.attack();
                player.damaged(damage1);
                
                int damage2 = player.attack();
                monster.damaged(damage2);
                
                player.display();
            }  
        }else{
            Player player = new Player("nishino");
            Monster monster = new Draky();
            player.display();
            monster.displayAppearMsg();
            while( monster.hp > 0 ){
                int damage1 = monster.attack();
                player.damaged(damage1);
                
                int damage2 = player.attack();
                monster.damaged(damage2);
                
                player.display();
            }  
        }      
    }
}
