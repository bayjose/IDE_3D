/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Bayjose
 */
public class drawImage {

    
    public void drawImage(String path, int x, int y, Graphics g){
            Image theImage;
            ImageIcon img = new ImageIcon(this.getClass().getResource("/"+path));
            theImage = img.getImage();
            g.drawImage(theImage, x, y, null);
        
    }
    
    public Image loadImage(String path){
        Image theImage;
        ImageIcon img = new ImageIcon(this.getClass().getResource("/"+path));
        theImage = img.getImage();
        return theImage;
    }
    
    public Image loadImageFromExternalSource(String path){
        Image theImage;
        ImageIcon img = new ImageIcon(path);
        theImage = img.getImage();
        return theImage;
    }
    
    public void drawImageFromImage(Image img, int x, int y, Graphics g){
        g.drawImage(img, x, y, null);
    }
    
    /**
 * Rotates an image. Actually rotates a new copy of the image.
 * 
 * @param img The image to be rotated
 * @param angle The angle in degrees
 * @return The rotated image
 */

    
    
}
