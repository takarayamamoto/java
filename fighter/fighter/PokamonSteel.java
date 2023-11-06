package fighter;

import util.Util;

public abstract class PokamonSteel extends Pokamon {
    private final static int TYPE_BOUNUS_DEF = 10;
    
    public PokamonSteel(int power,int deffence,int hp,int speed,int criticalRate){
        super(power,deffence+TYPE_BOUNUS_DEF,hp,speed,criticalRate);
    }
    
    /**
     * パラメータのオフセットを返す
     */
    protected final int getParamOffset(){
        return TYPE_BOUNUS_DEF;
    }
    /**
     * クリティカルヒット率のオフセット値を帰す
     */
    protected final int getCriticalRateOffset(){
        return 0;
    }
    /**
     * 必殺技を実行する
     * 鋼：次の攻撃を受けるとき、自分の防御力が倍になる
     * @param opp
     */
    public final void execSpecialAttack(Pokamon opp){
        if( isSpAttackFlag() ){
            return; //必殺技を使用済みの場合は何もしない
        }
        System.out.printf("%sは必殺技（鋼）を使った！\n",getName());
        Util.sleepL();
        System.out.printf("%sは1回だけ防御力が2倍になった！\n",getName());
        Util.sound("stup.wav");
        setSpDeffenceInUse();
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
     * このファイターは必殺攻撃をしない
     */
    protected final boolean isInSpAttack(){ return false; }
    
    /**
     * 鋼タイプは鋼タイプに強く出れる
     */
    protected final boolean isStrongFor(Pokamon opp){
        boolean isStrong = false;
        if( opp instanceof PokamonSteel ) {
            isStrong = true;
        }
        return isStrong;
    }
    
    public final String getTypeName(){ return "鋼";}
}
