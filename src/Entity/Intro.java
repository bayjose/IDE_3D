/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.EnumGameState;
import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Physics.Model;
import java.awt.Graphics;

/**
 *
 * @author Bailey
 */
public class Intro extends Entity{

    private Handler handler;

    private int curTicks = 0;
    private final int maxTicks = 120;
    
    public Intro(Model model, Handler handler) {
        super(model);
        model.assignImageFromSpriteBinder(SpriteBinder.resources.getImage(1, 5));
        model.offset.increaseVelX(Game.WIDTH/2);
        this.handler = handler;
    }

    public void update() {
        if(this.curTicks>=this.maxTicks){
            this.remove = true;
            handler.egs = EnumGameState.Main;
        }
        if(this.curTicks<this.maxTicks){
            this.curTicks++;
        }
    }
    
    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }
    
}
