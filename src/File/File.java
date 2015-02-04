/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public abstract class File {
    public Rectangle collision;
    public String name;
    
    public File(Rectangle collision, String name){
        this.collision = collision;
        this.name = name;
    }
    
    public abstract void selectEvent();
    public abstract void clickEvent();
    public abstract void extraRender(Graphics g);
    
}
