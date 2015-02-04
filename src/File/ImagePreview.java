/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.EnumMouseState;
import Base.Handler;
import Base.MouseInput;
import Base.MousePositionLocator;
import Base.MouseScroleInput;
import Base.SpriteBinder;
import gui.Layer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.LinkedList;
import world.Size;
import world.Tile;

/**
 *
 * @author Bayjose
 */
public class ImagePreview {
    public int x, y;
    public float offsetY=0;
    public Handler handler;
    public Files file;
    
    private final Image imagePreviewBack;
    private final Image imagePreview;
    
    private final Image scrollBar;
    private final int scrollOffsetX;
    private float scrollOffsetY=0;
    
    private final Rectangle collision;
    
    private Layer layer;
    
    public Tile selected = new Tile(0,0,0,0);
    
    public ImagePreview(int x, int y, Handler handler, Files file){
        this.x=x;
        this.y=y;
        this.handler = handler;
        this.file = file;
        this.imagePreview = handler.di.loadImage("imagePreview.png");
        this.imagePreviewBack = handler.di.loadImage("imagePreview_back.png");
        this.scrollBar= handler.di.loadImage("folder/scrollBar.png");
        System.out.println("Dementions for new Height Chooser thingy:"+(imagePreview.getWidth(null)));
        this.collision = new Rectangle(x,y,imagePreview.getWidth(null),imagePreview.getHeight(null));
        this.scrollOffsetX = imagePreview.getWidth(null)-8;
        this.layer = new Layer(SpriteBinder.resources.columns, SpriteBinder.resources.rows);
        for(int j=0; j<SpriteBinder.resources.rows; j++){
            for(int i=0; i<SpriteBinder.resources.columns; i++){
                this.layer.layer[i][j] = new Tile(i*Size.tileSize, j*Size.tileSize, j, i);
            }
        }
        this.update();
    }
    
    public void render(Graphics g){
        g.drawImage(this.imagePreviewBack, x, y, null);
        for(int j=0; j<SpriteBinder.resources.rows; j++){
            for(int i=0; i<SpriteBinder.resources.columns; i++){
                if(new Rectangle(this.layer.layer[i][j].collision.x+this.x, this.layer.layer[i][j].collision.y+this.y+(int)this.offsetY, Size.tileSize, Size.tileSize).intersects(collision)){
                    g.drawImage(SpriteBinder.resources.getImage(this.layer.layer[i][j].col, this.layer.layer[i][j].row), x+this.layer.layer[i][j].collision.x, (int) (y+3+this.offsetY)+this.layer.layer[i][j].collision.y, null);
                }
            }
        }
        g.drawImage(this.imagePreview, x, y, null);
        g.drawImage(this.scrollBar, x+this.scrollOffsetX-7, (int) (y+scrollOffsetY+2), null);
        g.setColor(new Color(255,255,255,32));
        for(int j=0; j<SpriteBinder.resources.rows; j++){
            for(int i=0; i<SpriteBinder.resources.columns; i++){
                if(new Rectangle(this.layer.layer[i][j].collision.x+this.x, this.layer.layer[i][j].collision.y+this.y+(int)this.offsetY, Size.tileSize, Size.tileSize).intersects(collision)){
                    g.drawRect(x+this.layer.layer[i][j].collision.x, (int) (y+3+this.offsetY)+this.layer.layer[i][j].collision.y, Size.tileSize, Size.tileSize);
                }
            }
        }
        if(Handler.bool7){
            g.setColor(Color.GREEN);
            g.drawRect(collision.x,collision.y,collision.width,collision.height);
        }
    }
    
    public void tick(){
        if(MousePositionLocator.MouseLocation.intersects(this.collision)){
            this.offsetY+=-MouseScroleInput.scrollIndex*8;
            this.scrollOffsetY = (this.imagePreview.getHeight(null)-this.scrollBar.getHeight(null)-4)*Math.abs(this.offsetY/-((SpriteBinder.resources.rows*Size.tileSize)-this.imagePreview.getHeight(null)));
            if(this.offsetY>0){
                this.offsetY = 0;
            }
            if(this.offsetY<-((SpriteBinder.resources.rows*Size.tileSize)-this.imagePreview.getHeight(null))){
                this.offsetY = -((SpriteBinder.resources.rows*Size.tileSize)-this.imagePreview.getHeight(null));
            }
            MouseScroleInput.scrollIndex = 0;
        }
        
    }
    
    public void checkForIntersection(Rectangle mouse){
        if(mouse.intersects(this.collision)){
            for(int j=0; j<SpriteBinder.resources.rows; j++){
                for(int i=0; i<SpriteBinder.resources.columns; i++){
                    if(new Rectangle(this.layer.layer[i][j].collision.x+this.x, (int) (this.layer.layer[i][j].collision.y+this.y+this.offsetY), Size.tileSize, Size.tileSize).intersects(mouse)){
                        MouseInput.ems = EnumMouseState.EditImage;
                        this.selected = this.layer.layer[i][j];
                        return;
                    }
                }
            }
        }
    }
    
    // after: method returns a Collection reference
    public Collection getList(Rectangle mouse){
      Collection list = new LinkedList();
        if (mouse.intersects(this.collision)) {
            for (int j = 0; j < SpriteBinder.resources.rows; j++) {
                for (int i = 0; i < SpriteBinder.resources.columns; i++) {
                    if (new Rectangle(this.layer.layer[i][j].collision.x + this.x, (int) (this.layer.layer[i][j].collision.y + this.y + this.offsetY), Size.tileSize, Size.tileSize).intersects(mouse)) {
                        list.add(this.layer.layer[i][j]);
                    }
                }
            }
        }
      return list;
    }
    
    public void checkForPosition(){
        
    }
    
    public void update(){
        System.out.println("Changing Dir:"+SpriteBinder.resources.path);
    }
}
