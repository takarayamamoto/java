package fighter;

import util.Util;

public abstract class PokamonWater extends Pokamon {
    private final static int TYPE_BOUNUS_CR = 10;
    
    public PokamonWater(int power,int deffence,int hp,int speed,int criticalRate){
        super(power,deffence,hp,speed,criticalRate+TYPE_BOUNUS_CR);
    }
    
    /**
     * パラメータのオフセットを返す
     */
    protected final int getParamOffset(){
        return TYPE_BOUNUS_CR;
    }
    /**
     * クリティカルヒット率のオフセット値を帰す
     */
    protected final int getCriticalRateOffset(){
        return TYPE_BOUNUS_CR;
    }

    /**
     * 必殺技を実行する
     * 水：必ずクリティカルヒットになる
     * @param opp
     */
    public final void execSpecialAttack(Pokamon opp){
        if( isSpAttackFlag() ){
            return; //必殺技を使用済みの場合は何もしない
        }

        System.out.printf("%sは必殺技（水）を使った！\n",getName());
        Util.sleepL();
        System.out.printf("★★クリティカルヒット！！★★\n");
        Util.sound("water.wav");
        Util.sleepS();
        //攻撃力
        int attck = getPower();
        //ダメージを与える
        opp.doDamaged(attck);
        //必殺技を使用済みにする
        setSpAttackUsed();
        dispDamageMsg(opp,attck);
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
     * 水タイプは炎タイプに強く出れる
     */
    protected final boolean isStrongFor(Pokamon opp){
        boolean isStrong = false;
        if( opp instanceof PokamonFire ) {
            isStrong = true;
        }
        return isStrong;
    }
    public final String getTypeName(){ return "水";}
}
