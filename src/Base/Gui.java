/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import java.awt.Graphics;


/**
 *
 * @author Bayjose
 */
public abstract class Gui {
    public int x,y;
    public Handler handler;
    public boolean remove=false;
    
    public Gui(int x, int y, Handler handler){
        this.x=x;
        this.y=y;
        this.handler=handler;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
