package redcorner;

import java.util.Random;

import fighter.PokamonEsper;

public class PokamonEsperSample extends PokamonEsper{

    public PokamonEsperSample(){
        super(20,20,20,20,20);
    }

    @Override
    public String getName() {
        return "超サンプル赤";
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
