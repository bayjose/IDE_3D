/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package particles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Bayjose
 */
public abstract class BasicParticles {
    public float x, y; 
    private String color="000000";
    public int lifespan;
    private Random r = new Random();
    private int dir = r.nextInt(4)+5;
    public int size;
    private float velX=0.0F;
    private float velY=0.0F;
    
    public BasicParticles(float x, float y, int size, float speed, int lifespan, String color, int dir){
        this.color=color;
        this.lifespan=lifespan;
        this.x=x;
        this.y=y;
        this.size=size;
        if(dir>=0&&dir<=8){
            this.dir=dir;
        }
            if(this.dir==1){
                this.velY-=Math.random();
            }
            if(this.dir==2){
                this.velY+=Math.random();
            }
            if(this.dir==3){
                this.velX-=Math.random();
            }
            if(this.dir==4){
                this.velX+=Math.random();
            }
            if(this.dir==5){
                this.velX+=Math.random();
                this.velY+=Math.random();
            }
            if(this.dir==6){
                this.velX-=Math.random();
                this.velY-=Math.random();
            }
            if(this.dir==7){
                this.velX+=Math.random();
                this.velY-=Math.random();
            }
            if(this.dir==8){
                this.velX-=Math.random();
                this.velY+=Math.random();
            }
            
            this.velX=this.velX*speed;
            this.velY=this.velY*speed;
    }
    
    public void tick(){
        this.extraTick();
        if(this.lifespan>0){
            this.lifespan--;
            this.x+=this.velX;
            this.y+=this.velY;
        }
    }
    
    public abstract void render(Graphics g);
    public abstract void extraTick();
    
    public int getLifespan(){
        return this.lifespan;
    }
}
