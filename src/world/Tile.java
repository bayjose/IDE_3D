/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class Tile {
    public int id = -1;
    public Rectangle collision;
    public int col = 0, row = 0;
    public int height = 0;
    
    
    public Tile(int x, int y, int col, int row){
        this.collision = new Rectangle(x,y,Size.tileSize, Size.tileSize);
        this.col = col;
        this.row = row; 
    }
    
    public boolean checkForIntersection(Rectangle mouse){
        if(mouse.intersects(this.collision)){
            return true;
        }else
        return false;
    }
    
    public int getID(){
        return this.id;
    }
}
