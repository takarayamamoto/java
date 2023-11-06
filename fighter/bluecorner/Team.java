package bluecorner;

import fighter.Pokamon;

public class Team {
    private static final int MAX_FIGHTER = 3;
    
    public static String getName(){
        //TODO:ここにチーム名を入れる
        return "チーム名";
    }

    public static Pokamon[] getFighters(){
        Pokamon[] fighters = new Pokamon[MAX_FIGHTER];

        //ここに自作したポカモンクラスを出したい順序に書きます
        fighters[0] = new PokamonFireSample();
        fighters[1] = new PokamonWaterSample();
        fighters[2] = new PokamonGrassSample();

        return fighters;
    }
}
