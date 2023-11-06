package fighter;

import util.Util;

public abstract class PolamonGrass extends Pokamon{
    private final static int TYPE_BOUNUS_HP = 20;
    private final int RECOVER_HP = 30;  
    private boolean recoverHpOKFlag = true;

    public PolamonGrass(int power,int deffence,int hp,int speed,int criticalRate){
        super(power,deffence,hp+TYPE_BOUNUS_HP,speed,criticalRate);
    }

    /**
     * パラメータのオフセットを返す
     */
    protected final int getParamOffset(){
        return TYPE_BOUNUS_HP;
    }
    /**
     * クリティカルヒット率のオフセット値を帰す
     */
    protected final int getCriticalRateOffset(){
        return 0;
    }
    /**
     * 必殺技を実行する
     * 草：HPが30回復する
     * @param opp
     */
    public final void execSpecialAttack(Pokamon opp){
        if( isSpAttackFlag() ){
            return; //必殺技を使用済みの場合は何もしない
        }
        
        System.out.printf("%sは必殺技（草）を使った！\n",getName());
        Util.sleepL();
        System.out.printf("HPが%d回復した！\n",RECOVER_HP);
        Util.sound("recover.wav");
        Util.sleepS();
        //体力回復
        recoverHp(RECOVER_HP);

        //必殺技を使用済みにする
        setSpAttackUsed();
    }

    
    public final boolean isRecoverHp(){
        boolean result = recoverHpOKFlag;
        recoverHpOKFlag = false;

        return result;
    }
    
    /**
     * このファイターは必殺攻撃をしない
     */
    protected final boolean isInSpAttack(){ return false; }
    
    /**
     * 草タイプは雷タイプに強く出れる
     */
    protected boolean isStrongFor(Pokamon opp){
        boolean isStrong = false;
        if( opp instanceof PokamonVolt ) {
            isStrong = true;
        }
        return isStrong;
    }
    
    public final String getTypeName(){ return "草";}
}
