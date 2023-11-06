package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Util {
    public final static int BATTLE_WAIT_TIME_L = 6000;
    public final static int BATTLE_WAIT_TIME_M = 3000;
    public final static int BATTLE_WAIT_TIME_S = 1000;
    public final static boolean SOUND_ON = true;
    public final static boolean SOUND_OFF = false;
    private static boolean soundSetting = SOUND_ON;

    public static void print(String s,Object ... args){
        String msg = s+"\n";
        System.out.printf(msg,args);
    }
    
    public static void sleep(long timeoutMillis){
        try{
            Thread.sleep(timeoutMillis);
        }catch(InterruptedException e){
            System.out.printf("[warn]%s",e.getMessage());
        }
    }

    public static void sleepL(){
        sleep(BATTLE_WAIT_TIME_L);
    }
    public static void sleepM(){
        sleep(BATTLE_WAIT_TIME_M);
    }
    public static void sleepS(){
        sleep(BATTLE_WAIT_TIME_S);
    }

    public static void setSoundSetting(boolean setting){
        soundSetting = setting;
    }
    
    public static void sound(String fileName){
        sound(fileName,0);
    }
    public static void soundLoop(String fileName){
        sound(fileName,Clip.LOOP_CONTINUOUSLY);
    }
    
    public static void sound(String fileName,int loop){
        if( !soundSetting ){
            return;
        }
        AudioInputStream ais = null;
        try {
            Path p1 = Paths.get("sound\\"+fileName);
            Path p2 = p1.toAbsolutePath();

            ais = AudioSystem.getAudioInputStream(new File(p2.toString()));
            AudioFormat af = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, af);
            Clip clip = (Clip)AudioSystem.getLine(info);
            clip.open(ais);
            clip.loop(loop);
            clip.flush();
            //while(clip.isActive()) {
            //    Thread.sleep(10);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
