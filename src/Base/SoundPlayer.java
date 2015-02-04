/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.sampled.*;
/**
 *
 * @author Bayjose
 */


public class SoundPlayer {

    /**
     * @param filename the name of the file that is going to be played
     */
    public static void playSound(String filename){
//          InputStream in;
//          try{
//          in = new FileInputStream(new File (filename));
//          AudioStream audios=new AudioStream(in);
//          AudioPlayer.player.start(audios);
//          }catch(Exception e){
//          e.printStackTrace();
//          }
        
    }
    public static void playSound(String filename, float i){
        try{
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(""+filename));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControl
                = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(i); // Reduce volume by 10 decibels.
        clip.start();
        }catch(Exception e){
            
        }
    }



}
