/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Handler;
import Base.SpriteBinder;
import Physics.GravityHandler;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;
import java.util.Random;
import world.Size;

/**
 *
 * @author Bayjose
 */
public class TileEntity extends Entity{
   
    public TileEntity(Model model, float height, Handler handler) {
        super(model);
        this.gravity = GravityHandler.ZedAxis;
        this.vecForward = new Vector3D((float)Math.random(),(float)Math.random(),(float)Math.random());
        if(height>0){
            //back
            Model back = Models.generateQuad(new Vector3D(model.offset.getX(), model.offset.getY(), model.offset.getZ()-(Size.tileSize+((height-1)*Size.tileSize))), Size.tileSize);
            back.assignImageFromSpriteBinder(model.getTexture());
            back.RotateZOnlyPoints(90);
            handler.entities.add(new Tile(back));
            Model front = Models.generateQuad(new Vector3D(model.offset.getX(), model.offset.getY()+Size.tileSize, model.offset.getZ()-(Size.tileSize+((height-1)*Size.tileSize))), Size.tileSize);
            front.assignImageFromSpriteBinder(model.getTexture());
            front.RotateZOnlyPoints(90);
            handler.entities.add(new Tile(front));
            //top
            Model top = Models.generateQuad(new Vector3D(model.offset.getX(), model.offset.getY(), model.offset.getZ()-(Size.tileSize+((height-1)*Size.tileSize))), Size.tileSize);
            top.assignImageFromSpriteBinder(model.getTexture());
            handler.entities.add(new Tile(top));
            
            //last
            this.getModel().assignImageFromSpriteBinder(SpriteBinder.resources.getImage(0, 0));
        }
    }

    public void update() {
        
    }

    public void render(Graphics g) {

    }
    
    public void dead() {
        
    }
    
}
