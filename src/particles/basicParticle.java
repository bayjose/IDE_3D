/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package particles;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class basicParticle extends BasicParticles{
    String color;
    int size;
    public basicParticle(float x, float y, int size, float speed, int lifespan, String color, int dir){
        super(x, y, size, speed, lifespan, color, dir);
        this.color=color;
        this.x=x;
        this.y=y;
        this.size=size;
    }
    public void render(Graphics g) {
        g.setColor(Color.decode("#"+this.color));
        g.fillRect((int)x, (int)y, size, size);
    }

    public void extraTick() {
        
    }
    
}
