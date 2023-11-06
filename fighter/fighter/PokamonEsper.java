package fighter;

import util.Util;

public abstract class PokamonEsper extends Pokamon{
    
    private int spAttackEffectCnt;
    
    private final int SPATTACK_NOT_USE = -1;
    private final int SPATTACK_USE_MAX = 3;
    private final static int TYPE_BOUNUS_ATT = 3;
    private final static int TYPE_BOUNUS_DEF = 3;
    private final static int TYPE_BOUNUS_SP = 3;

    public PokamonEsper(int power,int deffence,int hp,int speed,int criticalRate){
        super(
            power+TYPE_BOUNUS_ATT,
            deffence+TYPE_BOUNUS_DEF,
            hp,
            speed+TYPE_BOUNUS_SP,
            criticalRate);

        spAttackEffectCnt = SPATTACK_NOT_USE;
    }
    
    /**
     * パラメータのオフセットを返す
     */
    protected final int getParamOffset(){
        return TYPE_BOUNUS_ATT+TYPE_BOUNUS_DEF+TYPE_BOUNUS_SP;
    }

    /**
     * クリティカルヒット率のオフセット値を帰す
     */
    protected final int getCriticalRateOffset(){
        return 0;
    }
    /**
     * 必殺技を実行する
     * 超：次ターンから３ターンの間
     * 　　相手に与えるダメージは計算結果＋５になる。
     * 　　この＋５は防御力の影響を受けない
     * @param opp
     */
    public final void execSpecialAttack(Pokamon opp){
        if( isSpAttackFlag() ){
            return; //必殺技を使用済みの場合は何もしない
        }
        System.out.printf("%sは必殺技（超）を使った！\n",getName());
        Util.sleepM();
        System.out.printf("これ以降の3ターンはダメージが＋５されます！\n");
        Util.sound("stup.wav");
        Util.sleepS();
        spAttackEffectCnt = SPATTACK_USE_MAX;
        //必殺技を使用済みにする
        setSpAttackUsed();
    }

    /**
     * このファイターは体力回復不可
     * @return
     */
    public final boolean isRecoverHp(){
        return false;
    }

    /**
     * 必殺攻撃中かを返す
     */
    protected final boolean isInSpAttack(){
        boolean isInSpAttack = false;
        if( spAttackEffectCnt > 0){
            spAttackEffectCnt--;
            isInSpAttack = true;
        }
        return isInSpAttack;
    }

    /**
     * 超タイプは超タイプに強く出れる
     */
    protected final boolean isStrongFor(Pokamon opp){
        boolean isStrong = false;
        if( opp instanceof PokamonEsper ){
            isStrong = true;
        }
        return isStrong;
    }

    public final String getTypeName(){ return "超";}
}
