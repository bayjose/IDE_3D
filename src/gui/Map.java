/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.EnumMouseState;
import Base.Game;
import Base.Handler;
import Base.KeyInput;
import Base.MouseInput;
import Base.MousePositionLocator;
import Base.SpriteBinder;
import File.Files;
import File.ImagePreview;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.Scanner;
import world.Size;
import world.Tile;

/**
 *
 * @author Bayjose
 */
public class Map {
    
    //location on the screen where the map is rendered 
    private final int x;
    private final int y;
    private int xOffset = 0;
    private int yOffset = 0;
    //width and height of the map 
    private int width;
    private int height;
    //collison 
    private Rectangle collision;
    //refrences to the tools that need to be used
    private final Handler handler;
    private final ImagePreview files;
    
    //a lits of all the arrays that can overlay the screen 16 layers at most
    private int layerSize = 16;
    public Layer[] layers  = new Layer[layerSize];
    
    public int editingLayer = 0;
    
    private final int trackSpeed = 2;
    
    public static boolean showTileBoundries = true;

    
    public Map(int x, int y, Handler handler, ImagePreview files, Files dataFiles){
        this.x=x;
        this.y=y;
        this.handler = handler;
        this.files = files;
        //subject to change based on what the window size of the place you are developing is 
        collision = new Rectangle(x, y, Game.WIDTH-198, Game.HEIGHT-176-Size.tileSize);
        //iniitalizing the map arrays
        try{
            System.out.println(dataFiles.getPropertiesPath());
            Scanner s1 = new Scanner(new File(dataFiles.getPropertiesPath()));
            this.width = s1.nextInt();
            this.height = s1.nextInt();
            int layersToInit = s1.nextInt();
            s1.close();
            for(int i=0; i<layersToInit; i++){
                this.initializeArray(i);
            }
            //populate the array
            for(int l=0; l<layersToInit; l++){
                try{
                Scanner s2 = new Scanner(new File(dataFiles.path+dataFiles.getDirectory(dataFiles.getSelected()).name+"/Layer"+l+".txt"));  
                for(int j = 0; j<this.height; j++){
                    for(int i = 0; i<this.width; i++){
                        this.layers[l].layer[i][j].row = s2.nextInt();
                        this.layers[l].layer[i][j].col = s2.nextInt();
                        this.layers[l].layer[i][j].height = s2.nextInt();
                    }
                }
                s2.close();
                }catch(Exception e2){
                    e2.printStackTrace();
                }
            }
        }catch(Exception e){
            this.width = 0;
            this.height = 0;
            e.printStackTrace();
        }
//        
    }
    
    public void render(Graphics g){
        g.setColor(handler.highlightColor);
        for(int i=0; i<this.layers.length; i++){
            if(this.layers[i]!=null&&this.layers[i].show){
                for(int j = 0; j<this.height; j++){
                    for(int k=0; k<this.width; k++){
                        if(this.layers[i]!=null){
                            if(this.layers[i].layer[k][j]!=null){
                                if(this.layers[i].layer[k][j].collision!=null){
                                    if(new Rectangle(this.layers[i].layer[k][j].collision.x+this.xOffset, this.layers[i].layer[k][j].collision.y+this.yOffset, Size.tileSize, Size.tileSize).intersects(this.collision)){
                                        try{
                                            if(Map.showTileBoundries){
                                                g.drawRect(this.layers[i].layer[k][j].collision.x+this.xOffset, this.layers[i].layer[k][j].collision.y+this.yOffset, Size.tileSize-1, Size.tileSize-1);
                                            }
                                            if(SpriteBinder.resources!=null){
                                                g.drawImage(SpriteBinder.resources.getImage(this.layers[i].layer[k][j].col, this.layers[i].layer[k][j].row), this.layers[i].layer[k][j].collision.x+this.xOffset, this.layers[i].layer[k][j].collision.y+this.yOffset, null);
                                                g.setColor(Color.black);
                                            }
                                        }catch(Exception e){
                                            
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //height
        if(MouseInput.ems.equals(EnumMouseState.EditHeight)){
            for (int j = 0; j < this.height; j++) {
                for (int k = 0; k < this.width; k++) {
                    if (this.layers[this.editingLayer] != null) {
                        if (this.layers[this.editingLayer].layer[k][j] != null) {
                            if (this.layers[this.editingLayer].layer[k][j].collision != null) {
                                if (new Rectangle(this.layers[this.editingLayer].layer[k][j].collision.x + this.xOffset, this.layers[this.editingLayer].layer[k][j].collision.y + this.yOffset, Size.tileSize, Size.tileSize).intersects(this.collision)) {
                                    try {
                                        if (SpriteBinder.resources != null) {
                                            g.setColor(Color.WHITE);
                                            g.setFont(new Font("Ariel", Font.PLAIN, 24));
                                            g.drawString(""+this.layers[this.editingLayer].layer[k][j].height,this.layers[this.editingLayer].layer[k][j].collision.x + this.xOffset, this.layers[this.editingLayer].layer[k][j].collision.y + this.yOffset+Size.tileSize);
                                            g.setFont(new Font("Ariel", Font.PLAIN, 12));
                                        }
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //cross hairs in the center of the screen
        g.setColor(new Color(0,0,0,128));
        g.fillRect(this.collision.width/2 + this.x - (4/2), this.collision.height/2 + this.y - (16/2), 4, 16);
        g.fillRect(this.collision.width/2 + this.x - (16/2), this.collision.height/2 + this.y - (4/2), 16, 4);
        
        if(Handler.bool7){
            g.setColor(Color.cyan);
            g.drawRect(this.collision.x, this.collision.y, this.collision.width, this.collision.height);
        }
    }

    public void tick() {
        if(MouseInput.IsPressed&&!MouseInput.IsRightClick){
            if(MousePositionLocator.MouseLocation.intersects(this.collision)){
                if(this.layers[editingLayer]!=null){
                    for(int j=0; j<this.height; j++){
                        for(int i=0; i<this.width; i++){
                            if(MousePositionLocator.MouseLocation.intersects(new Rectangle(this.layers[editingLayer].layer[i][j].collision.x+this.xOffset, this.layers[editingLayer].layer[i][j].collision.y+this.yOffset, Size.tileSize, Size.tileSize))){
                                if(MouseInput.ems.equals(EnumMouseState.EditImage)){
                                    this.layers[editingLayer].layer[i][j].col = this.files.selected.col;
                                    this.layers[editingLayer].layer[i][j].row = this.files.selected.row;
                                }
                                if(MouseInput.ems.equals(EnumMouseState.EditHeight)){
                                    this.layers[editingLayer].layer[i][j].height= handler.guiHandler.heightBox.getSelected();
                                }
                            }
                        }
                    }
                }
            }
        }
        if(KeyInput.S==true){
            if(this.yOffset-this.trackSpeed>-((Size.tileSize*this.height)-this.collision.height)){
                this.yOffset-=this.trackSpeed;
            }
        }
        if(KeyInput.W==true){
            if(this.yOffset+this.trackSpeed<0){
                this.yOffset+=this.trackSpeed;
            }
        }
        if(KeyInput.D==true){
            if(this.xOffset-this.trackSpeed>-((Size.tileSize*this.width)-this.collision.width)){
                this.xOffset-=this.trackSpeed;
            }
        }
        if(KeyInput.A==true){
            if(this.xOffset+this.trackSpeed<0){
                this.xOffset+=this.trackSpeed;
            }
        }
    }
    
    public void initializeArray(int slot){
        if(slot<layerSize&&slot>=0){
            this.layers[slot] = new Layer(this.width, this.height);
            for(int j=0; j<this.height; j++){
                for(int i=0; i<this.width; i++){
                    //texture to use when loading a new layer, curently set to (0,0)
                    this.layers[slot].layer[i][j] = new Tile(x+(i*Size.tileSize), y+(j*Size.tileSize), 0, 0);
                }
            }
        }
    }
        
    public void checkForIntersection(Rectangle mouse){
//        System.out.println(0);
        if(this.layers[editingLayer]!=null){
//            System.out.println(1);
            if(mouse.intersects(this.collision)){
//                System.out.println(2);
                for(int j=0; j<this.height; j++){
                    for(int i=0; i<this.width; i++){
                        if(mouse.intersects(new Rectangle(this.layers[editingLayer].layer[i][j].collision.x+this.xOffset, this.layers[editingLayer].layer[i][j].collision.y+this.yOffset, Size.tileSize, Size.tileSize))){
//                            System.out.println(3);
                            if(MouseInput.ems.equals(EnumMouseState.EditImage)){
                                this.layers[editingLayer].layer[i][j].col = this.files.selected.col;
                                this.layers[editingLayer].layer[i][j].row = this.files.selected.row;
                            }
                            if(MouseInput.ems.equals(EnumMouseState.EditHeight)){
                                this.layers[editingLayer].layer[i][j].height= handler.guiHandler.heightBox.getSelected();
                            }
                        }
                    }
                }
            }
        }else{
            this.initializeArray(editingLayer);
        }
    }
    
    public void clearAllLayers(Files dataFiles){
        for(int i=0; i<this.layers.length; i++){
            this.layers[i] = null;
        }
        //iniitalizing the map arrays
        try{
            System.out.println(dataFiles.getPropertiesPath());
            Scanner s1 = new Scanner(new File(dataFiles.getPropertiesPath()));
            this.width = s1.nextInt();
            this.height = s1.nextInt();
            int layersToInit = s1.nextInt();
            s1.close();
            for(int i=0; i<layersToInit; i++){
                this.initializeArray(i);
            }
            //populate the array
            for(int l=0; l<layersToInit; l++){
                try{
                Scanner s2 = new Scanner(new File(dataFiles.path+dataFiles.getDirectory(dataFiles.getSelected()).name+"/Layer"+l+".txt"));  
                for(int j = 0; j<this.height; j++){
                    for(int i = 0; i<this.width; i++){
                        this.layers[l].layer[i][j].row = s2.nextInt();
                        this.layers[l].layer[i][j].col = s2.nextInt();
                        this.layers[l].layer[i][j].height = s2.nextInt();
                    }
                }
                s2.close();
                }catch(Exception e2){
                    e2.printStackTrace();
                }
            }
        }catch(Exception e){
            this.width = 0;
            this.height = 0;
            e.printStackTrace();
        }
    }
    
    public void saveAllLayers(){
        for(int i=0; i<this.layers.length; i++){
            try {
                if(this.layers[i]!=null){
                    String path = (this.handler.guiHandler.files.path+""+this.handler.guiHandler.files.getDirectory(this.handler.guiHandler.files.getSelected()).name+"/Layer"+i+".txt");
                    System.out.println(path);
                    Scanner s1 = new Scanner(new File(path));
                    for(int j = 0; j<this.height; j++){
                        for(int k=0; k<this.width; k++){

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void switchMap(){
        
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getHeight(){
        return this.height;
    }
}
