package JSSST11;

public class Player {
    int level;
    int maxHp;
    int hp;
    String name;

    Player(String name){
        this.name = name;
        level = 1;
        maxHp = 20;
        hp = 20;
    }

    public void display(){
        System.out.println("-----------------------");
        System.out.println("name : "+name);
        System.out.println("HP   : "+hp);
        System.out.println("Lv.  : "+level);
        System.out.println("-----------------------");
    }    
}
