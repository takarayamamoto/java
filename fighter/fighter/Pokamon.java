package fighter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import util.Util;

public abstract class Pokamon {
    private int power;
    private int deffence;
    private int hp;
    private int maxHp;
    private int speed;
    private int criticalRate;
    private boolean spAttackFlag; //必殺技フラグ
    protected SecureRandom number;
    private int spDeffect;
    
    ///////////////////////////////
    //定数
    ///////////////////////////////
    public final static int NORMAL_ATTACK = 0;
    public final static int FULLPOW_ATTACK = 1;
    private final int DAMAGE_OFFSET = 3;
    private final int DAMAGE_RANGE = 7;
    private final int TOTAL_PARAM_VALUE = 100;
    private final int MAX_CRITICAL_RATE = 20;
    protected final int BATTLE_WAIT_TIME_L = 5000;
    protected final int BATTLE_WAIT_TIME_M = 2500;
    protected final int BATTLE_WAIT_TIME_S = 1000;
    private final int SPDEF_NOT_USE = 0;
    private final int SPDEF_IN_USE = 1;
    private final int SPDEF_AFTER_USE = 2;
    private final int SPATTACK_DAMAGE = 5;
    private final int FULL_POW_SUCCESS_RATE = 40;


    public Pokamon(int power,int deffence,int hp,int speed,int criticalRate){
        this.power = power;
        this.deffence = deffence;
        this.hp = hp;
        this.maxHp = hp;
        this.speed = speed;
        this.criticalRate = criticalRate;
        try{
            number = SecureRandom.getInstance("SHA1PRNG");
        }catch(NoSuchAlgorithmException e){
            System.out.println("乱数初期化エラー");
        }
        spAttackFlag = false;   //必殺技を未使用にする
        spDeffect = SPDEF_NOT_USE;
    }
    ///////////////////////////////
    // getter
    //////////////////////////////
    public final int getPower() {
        return power;
    }

    /**
     * 防御力の取得
     * @return
     */
    public final int getDeffence() {
        return deffence;
    }

    /**
     * 現在のHPを取得する
     * @return
     */
    public final int getHp() {
        return hp;
    }

    /**
     * 素早さを取得する
     * @return
     */
    public final int getSpeed() {
        return speed;
    }

    /**
     * クリティカルヒット率を取得する
     * @return
     */
    public final int getCriticalRate() {
        return criticalRate;
    }

    /**
     * すでに必殺技を使ったかどうかを返す
     * @return
     */
    public final boolean isSpAttackFlag() {
        return spAttackFlag;
    }

    /**
     * 必殺技を考慮した防御力を取得する
     * 
     * @return
     */
    public final int getTrueDeffence() {
        if( spDeffect == SPDEF_IN_USE){
            System.out.printf("%sの必殺ディフェンス！",getName());
            spDeffect = SPDEF_AFTER_USE;
            return deffence*2;
        }
        return deffence;
    }
    /**
     * 合計値を返す
     * @return
     */
    public final int getTotalParam(){
        //合計値からプラスした分をマイナスすることでプログラマーがセットした値を返す
        return power + deffence + hp + speed + criticalRate - getParamOffset();
    }

    /**
     * パラメータの不正チェック
     * @return true：チェックOK　false:チェックエラー不正あり
     */
    public final boolean checkParameter(){
        boolean isOK = true;
        int criticalTrueRate = criticalRate - getCriticalRateOffset();
        if( getTotalParam() > TOTAL_PARAM_VALUE ){
            isOK = false;
            System.out.printf("[エラー]パラメータの合計値が%dを超えています\n",TOTAL_PARAM_VALUE);
        }else if( criticalTrueRate > MAX_CRITICAL_RATE ){
            isOK = false;
            System.out.printf("[エラー]クリティカルヒット率が%dを超えています\n",MAX_CRITICAL_RATE);
        }

        return isOK;
    }

    /**
     * 生存確認メソッド
     * @return
     */
    public final boolean isAlive(){
        return hp > 0;
    }
    /**
     * 通常攻撃を仕掛ける
     * @param opp 相手のオブジェクト
     */
    public final void doNormalAttack(Pokamon opp){
        System.out.printf("%sの攻撃！\n",getName());
        Util.sleepM();

        int damage = 0;
        //クリティカルヒットか？
        if( number.nextInt(100) < criticalRate){
            System.out.printf("★★クリティカルヒット！！★★\n");
            Util.sound("critical.wav");
            damage = power;
            Util.sleepS();
        }else{
            //攻撃力
            int attack = (power-DAMAGE_OFFSET) + number.nextInt(DAMAGE_RANGE);
            //ダメージ計算
            damage = getDamage(attack,opp);
            Util.sound("normal.wav");
        }
        //相手にダメージを与える
        opp.doDamaged(damage);
        dispDamageMsg(opp,damage);
    }

    /**
     * 全力攻撃を行う
     * @param opp 相手のオブジェクト
     */
    public final void doFullPowAttack(Pokamon opp) {
        System.out.printf("%sは全力切りを行った！\n",getName());
        Util.sleepL();
        //攻撃成功？
        if( number.nextInt(100) < FULL_POW_SUCCESS_RATE ){
            //攻撃力
            int attck = (int)(power * 1.5);
            //ダメージ計算
            int damage = getDamage(attck,opp);
            //相手にダメージを与える
            opp.doDamaged(damage);
            dispDamageMsg(opp,damage);
            Util.sound("normal.wav");
        }else{
            //攻撃失敗・・・
            System.out.println("攻撃は失敗した！");
            Util.sound("suka.wav");
        }
    }
    /**
     * 必殺技フラグを使用済みにする
     */
    protected final void setSpAttackUsed(){
        spAttackFlag = true;
    }

    /**
     * ダメージを受けるメソッド
     * @param damage
     */
    protected final void doDamaged(int damage){
        hp -= damage;
        if( hp < 0 ){
            hp = 0;
        }
    }
    /**
     * ダメージを計算して返す
     * 
     * @param attack 攻撃力
     * @param opp 敵のオブジェクト
     * @return
     */
    protected final int getDamage(int attack,Pokamon opp){

        //相手の防御力を取得
        int oppDef = opp.getTrueDeffence();
        if( isStrongFor(opp) ){
            //弱点の時は防御力が半減する
            oppDef /= 2;
        }
        //ダメージ計算
        int damage = attack - oppDef;
        if( damage < 1 ){
            damage = 1;
        }
        if( isInSpAttack() ){
            damage += SPATTACK_DAMAGE;
        }
        
        return damage;
    }

    /**
     * 体力回復処理
     * 
     * @param point
     */
    protected final void recoverHp(int point){
        if( !isRecoverHp() ){
            //不正防止のため、体力回復可能かをチェック
            return ;
        }
        hp += point;
        if( hp > maxHp ){
            hp = maxHp;
        }
    }

    /**
     * ダメージを表示する
     * 
     * @param opp
     * @param damage
     */
    protected void dispDamageMsg(Pokamon opp,int damage){
        System.out.printf("%sは%sに%dのダメージを与えた！\n",getName(),opp.getName(),damage);
    }

    /**
     * 必殺ディフェンスを「使用中」にする
     */
    protected void setSpDeffenceInUse(){
        if( spDeffect == SPDEF_NOT_USE){
            spDeffect = SPDEF_IN_USE;
        }
    }
    ///////////////////////////////
    // abstractメソッド
    //////////////////////////////

    /**
     * ファイターの名前を取得する
     * @return ファイターの名前
     */
    public abstract String getName();

    /**
     * 必殺技を使うかどうかを返す
     * @param turn ターン数
     * @param opHp 相手の残りHP
     * @param opPow 相手の攻撃力
     * @param opDef 相手の守備力
     * @return true（必殺技を使う）/false（必殺技を使わない）
     */
    public abstract boolean isSpecialAttack(int turn,int opHp,int opPow,int opDef);

    /**
     * 攻撃タイプ（通常攻撃or全力攻撃）を指定する
     * @param turn ターン数
     * @param opHp 相手の残りHP
     * @param opPow 相手の攻撃力
     * @param opDef 相手の守備力
     * @return NORMAL_ATTACK(0)通常攻撃 / FULLPOW_ATTACK(1)全力攻撃
     */
    public abstract int getAttackType(int turn,int opHp,int opPow,int opDef);

    /**
     * 必殺技を実行する
     * @param opp 相手のオブジェクト
     */
    public abstract void execSpecialAttack(Pokamon opp);


    /**
     * 特別攻撃かを判定する
     * @return
     */
    protected abstract boolean isInSpAttack();

    /**
     * 自分から見て相手が弱点かどうかを返す
     * @param opp 相手のオブジェクト
     * @return true:私から見て相手は弱点です false:私から見て相手は弱点ではありません
     */
    protected abstract boolean isStrongFor(Pokamon opp);

    //タイプ別のサブクラスでのみ使用
    public abstract String getTypeName();
    protected abstract int getParamOffset();
    protected abstract int getCriticalRateOffset();
    public abstract boolean isRecoverHp();
}
