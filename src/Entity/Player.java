/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Base.Handler;
import Base.KeyInput;
import Base.SpriteBinder;
import Physics.Collision;
import Physics.Collision_None;
import Physics.GravityHandler;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Color;
import java.awt.Graphics;
import world.Size;

/**
 *
 * @author Bayjose
 */
public class Player extends Entity{

    public boolean left=false;
    public boolean right=false;
    public boolean up=false;
    public boolean down=false;
    public boolean forward=false;
    public boolean backward=false;
    
    private Handler handler;
    
    public static float angleZ=0.0f;
    private final float speed = 1f;

    public boolean camZoomIn = false;
    public boolean camZoomOut = false;

    private Collision collision;
    
    public Player(Handler handler){
        super(Models.generateQuad(new Vector3D(128,-Size.tileSize,0), Size.tileSize));
        this.handler=handler;
        this.gravity = GravityHandler.None;
        this.vecForward = new Vector3D(0,0,0);
        Model display = Models.generateQuad(new Vector3D(this.getModel().offset.getX(), this.getModel().offset.getY(), this.getModel().offset.getZ()-Size.tileSize), Size.tileSize);
        display.RotateZOnlyPoints(90);
        handler.entities.add(new ModelTester(display));
        this.models.add(display);
        this.collision = new Collision_None(handler);
    }
    
    public void update() {
 
        if(!Handler.bool8){
            if(KeyInput.W){
                if(this.getModel().AbsoluteAnlgeZ<0){
                    for(int i=0; i<handler.entities.size(); i++){
                        this.handler.entities.get(i).RotateZ(speed);
                        Player.angleZ = (float) this.getModel().AbsoluteAnlgeZ;
                    }
                }
            }
            if(KeyInput.S){
                if(this.getModel().AbsoluteAnlgeZ>-90){
                    for(int i=0; i<handler.entities.size(); i++){
                        this.handler.entities.get(i).RotateZ(-speed);
                        Player.angleZ = (float) this.getModel().AbsoluteAnlgeZ;
                    }
                }
            }
        }else{
            if(KeyInput.W){
                for(int i=0; i<handler.entities.size(); i++){
                    this.translateAllZWithRespectToAngle(-0.01f);
                }
            }
            if(KeyInput.S){
                for(int i=0; i<handler.entities.size(); i++){
                    this.translateAllZWithRespectToAngle(0.01f);
                }
            }
            if(KeyInput.A){
                for(int i=0; i<handler.entities.size(); i++){
                    this.traslateAllX(-0.01f);
                }
            }
            if(KeyInput.D){
                for(int i=0; i<handler.entities.size(); i++){
                    this.traslateAllX(0.01f);
                }
            }
        }
//            if(Handler.bool6){
//                Entity tile = new Tile(Models.generateQuad(new Vector3D(128, 128, 0), (int)(Math.random()*Size.tileSize*3)));
//                tile.getModel().assignImageFromSpriteBinder(SpriteBinder.resources.getImage((int)(Math.random()*16), (int)(Math.random()*16)));
//                tile.gravity = GravityHandler.Down;
//                tile.vecForward = new Vector3D((int)(Math.random()*16), -(int)(Math.random()*16), 0);
//                handler.entities.add(tile);
//            }
    
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("ZedPos:"+this.getModel().AbsoluteAnlgeZ, 100, 100);
    }

    public void dead() {
        
    }
    
}
