package fighter;

import util.Util;

public abstract class PokamonVolt extends Pokamon{
    private final static int TYPE_BOUNUS_SP = 10;
    private final int SPATTACK_RATE = 10;
    
    public PokamonVolt(int power,int deffence,int hp,int speed,int criticalRate){
        super(power,deffence,hp,speed+TYPE_BOUNUS_SP,criticalRate);
    }

    /**
     * パラメータのオフセットを返す
     */
    protected final int getParamOffset(){
        return TYPE_BOUNUS_SP;
    }
    /**
     * クリティカルヒット率のオフセット値を帰す
     */
    protected final int getCriticalRateOffset(){
        return 0;
    }

    /**
     * 必殺技を実行する
     * 雷：10%の確率で相手のHPを0にする
     * @param opp
     */
    public final void execSpecialAttack(Pokamon opp){
        if( isSpAttackFlag() ){
            return; //必殺技を使用済みの場合は何もしない
        }
        System.out.printf("%sは必殺技（雷）を使った！\n",getName());
        Util.sleepL();
        //10%で相手が気絶
        if( number.nextInt(100) < SPATTACK_RATE){
            System.out.println("★★★必殺技が成功した！相手のHPは0になった！★★★");
            Util.sound("volt.wav");
            opp.doDamaged(1000000); //即死ダメージ
        }else{
            System.out.println("必殺技が失敗した！！");
            Util.sound("suka.wav");
        }
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
     * 雷タイプは水タイプに強く出れる
     */
    protected final boolean isStrongFor(Pokamon opp){
        boolean isStrong = false;
        if( opp instanceof PokamonWater ) {
            isStrong = true;
        }
        return isStrong;
    }
    
    public final String getTypeName(){ return "雷";}
}
