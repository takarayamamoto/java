package JSSST1102;

public class Monster {
    int maxHp;
    int hp;
    int attack;
    String name;
        
    Monster(String name,int maxHp){
        this.name = name;
        this.maxHp = maxHp;
        hp = maxHp;
    }        
    public void displayAppearMsg(){
        System.out.println(name+" appeared!");
    }    
}
