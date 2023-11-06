package redcorner;

import java.util.Random;

import fighter.PokamonSteel;

public class PokamonSteelSample extends PokamonSteel{
    
    public PokamonSteelSample(){
        super(20,20,20,20,20);
    }

    @Override
    public String getName() {
        return "鋼サンプル赤";
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
