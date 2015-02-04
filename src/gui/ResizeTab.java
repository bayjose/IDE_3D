/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.Game;
import Base.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import world.Size;

/**
 *
 * @author Bayjose
 */
public class ResizeTab {
    public int x, y;
    private Image footer;
    private Handler handler;
    private Rectangle collision;
        
    public ResizeTab(int x, int y, Handler handler){
        this.x = x;
        this.y = y;
        this.handler = handler;
        footer = handler.di.loadImage("footer.png");
        this.collision = new Rectangle(198, Game.HEIGHT-139-Size.tileSize, Game.WIDTH-198, Size.tileSize);
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        g.drawImage(footer, collision.x, collision.y, null);
        if(Handler.bool7){
            g.setColor(Color.PINK);
            g.drawRect(collision.x, collision.y, collision.width, collision.height);
        }
    }
}
