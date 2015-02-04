/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package particles;

import Base.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class targetParticle extends BasicParticles{
    int index=0;
    int xx, yy;
    private Handler handler;
    private boolean target=false;
    public targetParticle(float x, float y, int xx, int yy, int lifespan, int index, int dir, Handler handler){
        super(x, y, 0, 1, lifespan, "000000", dir);
        this.x=x;
        this.y=y;
        this.xx=xx;
        this.yy=yy;
        this.handler=handler;
        this.index=index;
//        this.size=handler.nodes.returnSubImage(index, 3).getWidth(null);
    }
    public void render(Graphics g) {
//        g.drawImage(handler.nodes.returnSubImage(index, 3), (int)x, (int)y, null);
    }

    public void extraTick() {
//        System.out.println("Lifespan:"+this.lifespan);
        if(this.lifespan<=1&&target==false){
            this.lifespan++;
            target=true;
        }
        
        
        if(new Rectangle((int)x, (int)y, this.size, this.size).intersects(new Rectangle(this.xx, this.yy, 16, 16))){
            this.lifespan=0;
        }else{
            if(this.target==true){
            if(this.xx>this.x){
                this.x+=1.5F;
            }
            if(this.xx<this.x){
                this.x-=1.5F;
            }
            if(this.yy>this.y){
                this.y+=1.5F;
            }
            if(this.yy<this.y){
                this.y-=1.5F;
            }
            this.lifespan++;
            }
        }
    }
    
}
