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
public class FireParticle extends BasicParticles{
    public FireParticle(float x, float y, float speed, int lifespan, int dir){
        super(x, y, 60, speed, lifespan, "", dir);
        this.x=x;
        this.y=y;
        
    }
    public void render(Graphics g) {
        g.setColor(new Color(255, 128, 0, 100));
        g.fillOval((int)x+(this.size/2), (int)y+(this.size/2), this.size, this.size);
    }

    public void extraTick() {

    }
    
}
