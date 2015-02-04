 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import Entity.Player;
import Physics.Vector3D;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class Camera {
    public int zoom=1;
    public Rectangle WindowSize;
    private Handler handler;
    public static Vector3D globalOffset = new Vector3D(0,0,-800 );
    
    
    public Vector3D position;
    
    
    private float speed = 0.1f;
    
    public final float viewRange = 1024;
    public final float optimalRender = 128;
    
    public Camera(Vector3D position, int zoom, Handler handler){
        this.position = position;
        this.handler = handler;
        this.zoom=zoom;
        this.WindowSize= new Rectangle(0, 0, Game.WIDTH/zoom, Game.HEIGHT/zoom);
    }
    
    public void tick(){
        
    }
    
    public int getZoom(){
        return this.zoom;
    }
    
    public void setZoom(int z){
        this.zoom=z;
        System.out.println("Zoom "+zoom);
    }
    

}
