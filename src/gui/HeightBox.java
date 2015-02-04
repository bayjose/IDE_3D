package gui;

import Base.EnumMouseState;
import Base.Handler;
import Base.MouseInput;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Bailey
 */
public class HeightBox {
    Handler handler;
    public Rectangle collision;
    public Rectangle[] heights = new Rectangle[17];
    private Image stripes;
    private int Selected = 0;
    
    public HeightBox(int x, int y, Handler handler){
        this.collision = new Rectangle(x,y,470,198);
        for(int i=0; i<this.heights.length; i++){
            this.heights[i] = new Rectangle(x+(i*27),y,27,198);
        }
        this.handler = handler;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        for(int i=0; i<this.heights.length; i++){
            if (i%2==0) {
                g.setColor(Color.decode("#B0C4DE"));
            } else {
                g.setColor(Color.decode("#ffffff"));
            }
            g.fillRect(this.heights[i].x, this.heights[i].y, this.heights[i].width, this.heights[i].height);
            g.setColor(Color.BLACK);
            g.drawString(i+"", this.heights[i].x+(27/2), this.heights[i].y+(198/2));
        }
        g.setColor(handler.highlightColor);
        g.fillRect(this.heights[Selected].x, this.heights[Selected].y, this.heights[Selected].width, this.heights[Selected].height);
    }
    
    public int checkCollision(Rectangle mouse){
        if(mouse.intersects(this.collision)){
            for(int i=0; i<this.heights.length; i++){
                if(mouse.intersects(this.heights[i])){
                    MouseInput.ems = EnumMouseState.EditHeight;
                    this.Selected = i;
                    return i;
                }
            }
        }
        return this.Selected;
    }
    
    public int getSelected(){
        return this.Selected;
    }
}
