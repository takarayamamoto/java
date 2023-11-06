package fighter;

import util.Util;

public abstract class PokamonFire extends Pokamon{
    private final static int TYPE_BOUNUS_ATT = 5;
    private final int SPATTACK_RATE = 50;

    public PokamonFire(int power,int deffence,int hp,int speed,int criticalRate){
        super(power+TYPE_BOUNUS_ATT,deffence,hp,speed,criticalRate);
    }

    /**
     * パラメータのオフセットを返す
     */
    protected final int getParamOffset(){
        return TYPE_BOUNUS_ATT;
    }
    /**
     * クリティカルヒット率のオフセット値を帰す
     */
    protected final int getCriticalRateOffset(){
        return 0;
    }
    /**
     * 必殺技を実行する
     * 火：攻撃力が2倍になる
     * @param opp
     */
    public final void execSpecialAttack(Pokamon opp){
        if( isSpAttackFlag() ){
            return; //必殺技を使用済みの場合は何もしない
        }
        System.out.printf("%sは必殺技（火）を使った！\n",getName());
        if( number.nextInt(100) < SPATTACK_RATE){
            Util.sound("fire.wav");
            Util.sleepL();
            //攻撃力
            int attck = getPower() * 2;
            //ダメージ計算
            int damage = getDamage(attck,opp);
            //ダメージを与える
            opp.doDamaged(damage);
            //必殺技を使用済みにする
            setSpAttackUsed();
            dispDamageMsg(opp,attck);
        }else{
            System.out.printf("必殺技が失敗した！");
            Util.sound("suka.wav");
        }
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
     * 炎タイプは草、鋼タイプに強く出れる
     */
    protected final boolean isStrongFor(Pokamon opp){
        boolean isStrong = false;
        if(
            ( opp instanceof PolamonGrass ) ||
            ( opp instanceof PokamonSteel )
        ){
            isStrong = true;
        }
        return isStrong;
    }
    
    public final String getTypeName(){ return "炎";}
}
