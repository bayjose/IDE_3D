/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import Base.Handler;

/**
 *
 * @author Bailey
 */
public abstract class Collision {
    private Handler handler;
    
    public Collision(Handler handler){
        this.handler = handler;
    }
    
    public abstract boolean collide(Handler handler);
}
