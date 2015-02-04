/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.Handler;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class LayerFile extends File{
    private boolean selected = true;
    public final int key;
    private final Image checkBox_checked;
    private final Image checkBox_unChecked;
    
    private final Handler handler;
    
    /**
     *
     * @param collision collision is where the object exists on the screen
     * @param name name is the string of the directory
     * @param handler handler
     * @param key this is the key number that this layer will toggle
     */
    public LayerFile(Rectangle collision, String name, Handler handler, int key) {
        super(collision, name);
        this.checkBox_checked = handler.di.loadImage("folder/checkBox_checked.png");
        this.checkBox_unChecked = handler.di.loadImage("folder/checkBox_unChecked.png");
        this.handler = handler;
        this.key = key;
    }

    @Override
    public void clickEvent() {
        loop:{
            if(this.selected==false){
                this.selected = true;
                break loop;
            }
            if(this.selected==true){
                this.selected = false;
                break loop;
            }
        }
        if(handler.guiHandler.map.layers[key]!=null){
            handler.guiHandler.map.layers[key].show = this.selected;
        }
    }

    @Override
    public void extraRender(Graphics g) {
        if(this.selected){
            g.drawImage(checkBox_checked, this.collision.x+150, this.collision.y+1, null);
        }else{
            g.drawImage(checkBox_unChecked, this.collision.x+150, this.collision.y+1, null); 
        }
    }
    
    public boolean isVisible(){
        return this.selected;
    }

    @Override
    public void selectEvent() {
        this.handler.guiHandler.map.editingLayer = this.key;
    }
}
