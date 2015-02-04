/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *this is a file that contains all the loaded sprites 
 * @author Bayjose
 */
public class SpriteBinder {
    //current resources.png file in the dir you are looking at
    public static SpriteSheet resources;
    
    
    public static Image preview;
    public static void init(){
         //load sprite sheets
        try{
            resources=new SpriteSheet(32, 32, 16, 16, "defaultTerrain.png");
            System.out.println("SpriteSheet is big?:"+resources.sprites.length);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public static void updateDirectory(String newDir){
        try{
            resources=new SpriteSheet(32, 32, 16, 16, newDir);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
}
