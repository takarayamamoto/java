package redcorner;

import fighter.Pokamon;

public class Team {
    private static final int MAX_FIGHTER = 3;
    
    public static String getName(){
        //TODO:ここにチーム名を入れる
        return "チーム名";
    }

    public static Pokamon[] getFighters(){
        Pokamon[] fighters = new Pokamon[MAX_FIGHTER];

        fighters[0] = new PokamonEsperSample();
        fighters[1] = new PokamonSteelSample();
        fighters[2] = new PokamonVoltSample();

        return fighters;
    }
}
