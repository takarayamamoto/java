
import java.util.Random;
import java.util.Scanner;


import fighter.Pokamon;
import util.Util;

public class Main {
    private Pokamon blueFighters[];
    private Pokamon redFighters[];
    private int blueWinNum;
    private int redWinNum;
    private int drawNum;
    private Scanner sc;

    /**
     * 定数
     */
    private final int FIGHTER_NUM=2;
    private final int FIGHTER_TOP=0;
    private final int FIGHTER_BOTTOM=1;

    private final int MAX_GAME_COUNT = 3;   //試合の数
    private final int MAX_GAME_TURN = 10;   //1試合のターン数
    private final int MAX_WIN_COUNT = 2;   //勝負が決する勝ち数
    public static void main(String[] args){
        Main main = new Main();

        //サウンドをOFFにしたい場合は以下のコメントを有効にしてください
        //Util.setSoundSetting(Util.SOUND_OFF);

        //////////////////////////
        //ゲーム開始！
        main.game();
    }

    public Main(){
        //勝ち数を初期化
        blueWinNum = 0;
        redWinNum = 0;
        drawNum = 0;
    }
    /**
     * ゲームのメインメソッド
     */
    public void game(){
        sc = new Scanner(System.in);

        ////////////////////////
        //初期表示
        ////////////////////////
        //タイトルを表示
        dispTitle();
        //メンバー表示
        dispMember();
        waitEnter();

        ////////////////////////
        //不正チェック
        ////////////////////////
        if( chkCheat() ){
            return;
        }
        ////////////////////////
        //メインループ
        ////////////////////////
        gameMain();
        
    }

    /**
     * 不正チェック
     * @return true：不正あり false：不正無し
     */
    private boolean chkCheat(){
        //各メンバー不正が無いかのチェック
        boolean isCheatB = false;
        boolean isCheatR = false;
        //青コーナー
        for( Pokamon fighter:blueFighters ){
            if( !fighter.checkParameter() ){
                isCheatB = true;
                break;
            }
        }
        //赤コーナー
        for( Pokamon fighter:redFighters ){
            if( !fighter.checkParameter() ){
                isCheatR = true;
                break;
            }
        }

        if( isCheatB && isCheatR ){
            Util.print("両チームとも不正があります。両者失格（＞＜）");
        }else if( isCheatB ){
            Util.print("[青]%sに不正があります。失格！",bluecorner.Team.getName());
        }else if( isCheatR ){
            Util.print("[赤]%sに不正があります。失格！",redcorner.Team.getName());
        }

        return (isCheatB || isCheatR);
    }

    /**
     * ゲームのメインループ
     */
    private void gameMain(){
        int gameCnt = 0;
        int index;
        Util.soundLoop("maou_bgm_8bit18.wav");
        while(true){
            index = gameCnt%MAX_GAME_COUNT;
            Util.print("-----[第%d試合]-----",(gameCnt+1));
            Util.print("-----[%s VS %s]-----",
                                blueFighters[index].getName(),
                                redFighters[index].getName());
                                
            Util.sound("dodon.wav");
            waitEnter();
            Util.sound("start.wav");

            //1試合を行う
            oneGameMain(blueFighters[index],redFighters[index]);

            //通算成績を表示
            Util.print("-----[通算成績]-----");
            Util.print("[青]%d勝：%s",blueWinNum,bluecorner.Team.getName());
            Util.print("[赤]%d勝：%s",redWinNum,redcorner.Team.getName());
            Util.print("引き分け：%d",drawNum);
            Util.print("-------------------");
            if(blueWinNum == MAX_WIN_COUNT || redWinNum == MAX_WIN_COUNT){
                break;
            }
            gameCnt++;
            if( gameCnt == MAX_GAME_COUNT){
                Util.print("決着がつかないので、どちらかが2勝するまでの延長戦に突入します！");
            }
            waitEnter();
            
        }

        //勝利チーム
        dispWinTeam();
    }

    /**
     * 1試合のメソッド
     * @param blueFighter：青コーナーのファイター
     * @param redFighter：赤コーナーのファイター
     */
    private void oneGameMain(Pokamon blueFighter,Pokamon redFighter){
        int turnNum = 1;
        Pokamon fighter[];

        while(turnNum <= MAX_GAME_TURN){
            Util.print("******[%dターン目]*******",turnNum);
            dispStatus(blueFighter,redFighter);
            waitEnter();

            //どちらが先に攻撃するかを決定する
            fighter = getBattleTurn(blueFighter,redFighter);
            //先行
            if( !attack(turnNum,fighter[FIGHTER_TOP],fighter[FIGHTER_BOTTOM]) ){
                break;
            }
            //後攻
            if(!attack(turnNum,fighter[FIGHTER_BOTTOM],fighter[FIGHTER_TOP])){
                break;
            }

            Util.sleepM();
            turnNum++;
        }
        //勝者の判定
        getWinner(blueFighter,redFighter);
        
    }

    /**
     * 勝利者を判定する
     * @param blueFighter
     * @param redFighter
     */
    private void getWinner(Pokamon blueFighter,Pokamon redFighter){
        int blueHp = blueFighter.getHp();
        int redHp = redFighter.getHp();

        if( blueHp > redHp ){
            blueWinNum++;
            Util.print("%sの勝利！！！！", blueFighter.getName());
        }else if( blueHp < redHp ){
            redWinNum++;
            Util.print("%sの勝利！！！！", redFighter.getName());
        }else{
            drawNum++;
            Util.print("この勝負引き分け！！");
        }
    }

    /**
     * どちらが先に責めるかを決める。素早さが同じときは
     * ランダムになるので、1ターンごとに呼ぶ
     * @param blueFighter：青コーナー
     * @param redFighter：赤コーナー
     * @return
     */
    private Pokamon[] getBattleTurn(Pokamon blueFighter,Pokamon redFighter){
        Pokamon fighter[] = new Pokamon[FIGHTER_NUM];
        //素早さを比較する
        if(blueFighter.getSpeed() > redFighter.getSpeed()){
            fighter[FIGHTER_TOP] = blueFighter;
            fighter[FIGHTER_BOTTOM] = redFighter;
        }else if(blueFighter.getSpeed() < redFighter.getSpeed()){
            fighter[FIGHTER_TOP] = redFighter;
            fighter[FIGHTER_BOTTOM] = blueFighter;
        }else{
            //素早さが同じ場合はランダムで決める
            Random r = new Random();
            if(r.nextBoolean()){
                fighter[FIGHTER_TOP] = redFighter;
                fighter[FIGHTER_BOTTOM] = blueFighter;
            }else{
                fighter[FIGHTER_TOP] = blueFighter;
                fighter[FIGHTER_BOTTOM] = redFighter;
            }
        }
        return fighter;
    }

    /**
     * 攻撃処理
     * 
     * @param turn：ターン数
     * @param attack：攻撃者
     * @param opp：攻撃相手
     * @return 攻撃相手が気絶したか
     */
    private boolean attack(int turn,Pokamon attack,Pokamon opp){
        int opHp = opp.getHp();
        int opPow = opp.getPower();
        int opDef = opp.getDeffence();
        boolean oppIsAlive = true;

        if( attack.isSpAttackFlag() == false && 
            attack.isSpecialAttack(turn, opHp, opPow, opDef) ){
                //必殺技が未使用で必殺技を使うか？
                attack.execSpecialAttack(opp);
        }else if( 
            attack.getAttackType(turn, opHp, opPow, opDef) == Pokamon.FULLPOW_ATTACK
            ){
                //フルパワーのアタック！
                attack.doFullPowAttack(opp);
        }else{
            //通常攻撃
            attack.doNormalAttack(opp);
        }

        Util.sleepS();
        //相手の生存確認
        if( !opp.isAlive() ){
            Util.sleepS();
            Util.print("\n%sは気絶した！！！",opp.getName());
            Util.sound("end.wav");
            oppIsAlive = false;
        }

        return oppIsAlive;
    } 

    /**
     * 現在の状態を表示する
     * @param blueFighter：青コーナーのファイター
     * @param redFighter：赤コーナーのファイター
     */
    private void dispStatus(Pokamon blueFighter,Pokamon redFighter){
        Util.print("------------------------------");
        Util.print("[青]HP:%3d [%s]",blueFighter.getHp(),blueFighter.getName());
        Util.print("[赤]HP:%3d [%s]",redFighter.getHp(),redFighter.getName());
        Util.print("------------------------------");
    }

    /**
     * タイトルを表示する
     */
    private void dispTitle(){
        String blueTeam = bluecorner.Team.getName();
        String redTeam = redcorner.Team.getName();

        Util.print("**********[battle start!]**********");
        Util.print("%s VS %s",blueTeam,redTeam);
        Util.print("**********************************");
        Util.sound("jan.wav");
    }

    /**
     * メンバーを表示する
     */
    private void dispMember(){
        String blueTeam = bluecorner.Team.getName();
        String redTeam = redcorner.Team.getName();
        Pokamon blueFighters[] = bluecorner.Team.getFighters();
        Pokamon redFighters[] = redcorner.Team.getFighters();

        this.blueFighters = blueFighters;
        this.redFighters = redFighters;

        Util.print("**********[member]**********");
        Util.print("[青]%s\tVS [赤]%s",blueTeam,redTeam);
        for(int i=0;i < blueFighters.length; i++){
            Util.print("[試合%d]%s[%s]\tVS %s[%s]",
            i+1,
            blueFighters[i].getName(),blueFighters[i].getTypeName(),
            redFighters[i].getName(),redFighters[i].getTypeName());
        }
    }

    /**
     * エンターキー待ちを表示する
     */
    private void waitEnter(){
        Util.print("・・・please press enter・・・");
        sc.nextLine();
    }

    /**
     * 勝利チームの表示
     */
    private void dispWinTeam(){
        String winnerTeam;

        if( blueWinNum > redWinNum ){
            winnerTeam = bluecorner.Team.getName();
        }else{
            winnerTeam = redcorner.Team.getName();
        }

        Util.print("%sの勝利です！", winnerTeam);
        Util.sound("wa-.wav");
        Util.sleepM();
    }
}
