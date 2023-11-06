package JSSST1103;

//import JSSST11.Player;

public class JSSST1103 {
    public static void main(String[] args){
        Player player = new Player("nishino");
        Monster monster = new Monster("slime",8,5);
        
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
