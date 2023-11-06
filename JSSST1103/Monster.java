package JSSST1103;

import java.util.Random;

public class Monster {
    int maxHp;
    int hp;
    int attack;
    String name;
    Random r;

    Monster(String name,int maxHp,int attack){
        this.name = name;
        this.maxHp = maxHp;
        this.attack = attack;
        hp = maxHp;
        r = new Random(1000);
    }

    public void displayAppearMsg(){
        System.out.println(name+" appeared!");
    }

    public int attack(){
        int damage = 1+r.nextInt(attack);

        return damage;
    }
    public void damaged(int damage){
        hp -= damage;
        System.out.println(damage+" point for "+name);
        if(hp<=0){
            System.out.println(name+" is dead");
        }
    }   
}
