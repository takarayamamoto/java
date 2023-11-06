package JSSST1105;

import java.util.Random;

public class Player {
    int level;
    int maxHp;
    int hp;
    String name;
    Random r;

    Player(String name){
        this.name = name;
        level = 1;
        maxHp = 20;
        hp = 20;
        r = new Random(50000);
    }

    //？？？（[JSS-ST11-01]で追加したdisplayメソッド
    public void display(){
        System.out.println("-----------------------");
        System.out.println("name : "+name);
        System.out.println("HP   : "+hp);
        System.out.println("Lv.  : "+level);
        System.out.println("-----------------------");
    } 

    public void damaged(int damage){
        hp -= damage;
        System.out.println(damage+" point for "+name);
        if(hp<=0){
            System.out.println(name+" is dead");
        }
    }

    public int attack(){
        int damage = 1+r.nextInt(10);

        return damage;
    }
}
