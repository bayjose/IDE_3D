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
public class Collision_None extends Collision{

    public Collision_None(Handler handler) {
        super(handler);
    }

    public boolean collide(Handler handler) {
       return false;
    }
    
}
