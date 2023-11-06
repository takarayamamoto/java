package bluecorner;

import java.util.Random;

import fighter.PokamonFire;

public class PokamonFireSample extends PokamonFire{

    public PokamonFireSample(){
        super(40,5,20,20,15);
    }

    @Override
    public String getName() {
        return "炎サンプル青";
    }
    
    @Override
    public boolean isSpecialAttack(int turn, int opHp, int opPow, int opDef) {
        Random r = new Random();
        if(r.nextInt(100) < 50){
            return true;
        }
        return false;
    }

    @Override
    public int getAttackType(int turn, int opHp, int opPow, int opDef) {
        Random r = new Random();
        if(r.nextInt(100) < 50){
            return FULLPOW_ATTACK;
        }
        return NORMAL_ATTACK;
    }
    
}
