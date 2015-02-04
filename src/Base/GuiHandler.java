/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import File.Files;
import File.ImagePreview;
import gui.HeightBox;
import gui.ResizeTab;
import gui.Map;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class GuiHandler {
    
    private Handler handler;
    
    //images
    private Image header;
    private Image scrollBarFiles;
    
    //buttons
    private Button play;
    private Button addDir;
    private Button addFile;
    private Button save;
    
    public Files files;
    private ImagePreview imagePreview;
    public Map map;
    private ResizeTab entityTab;
    public HeightBox heightBox ;
    
    public LinkedList<Button> button = new LinkedList<Button>();
    
    public GuiHandler(Handler handler){
        this.handler=handler;
        init();
    }
    
    public void init(){
        header = handler.di.loadImage("header.png");
        scrollBarFiles = handler.di.loadImage("scrollBarFiles.png");
        play = new PlayButton(this.handler, 81,0);
        this.files = new Files(handler, 0, 0);
        this.addDir = new NewFolderButton(this.handler, 47, 0, this.files);
        this.addFile = new NewLayerButton(this.handler, 13, 0, this.files);
        this.save = new SaveButton(this.handler, 115, 0);
        
        //add buttons
        this.button.add(play);
        this.button.add(addDir);
        this.button.add(addFile);
        this.button.add(save);
        
        //init other panesl
        this.imagePreview = new ImagePreview(198,Game.HEIGHT-139,this.handler,this.files);
        this.map = new Map(198, 36, this.handler, this.imagePreview, this.files);
        this.entityTab = new ResizeTab(0,0,this.handler);
        this.heightBox = new HeightBox(198+533,Game.HEIGHT-139,handler);
    }
    
    public void render(Graphics g){
        this.imagePreview.render(g);
         this.map.render(g);

         g.drawImage(header, 0, 0, null);
        g.drawImage(scrollBarFiles, 180, 54, null); 
        for(int i=0; i<this.button.size(); i++){
            this.button.get(i).Render(g);
        }
        this.files.render(g);
        this.entityTab.render(g);
        this.heightBox.render(g);
    }
    
    public void tick(){
        this.files.tick();
        this.imagePreview.tick();
        this.map.tick();
        this.entityTab.tick();
        this.heightBox.tick();
    }
    
    public void checkForButtonPress(Rectangle mouse){
        for(int i=0; i<this.button.size(); i++){
            this.button.get(i).checkForOvverlap(mouse);
        }
        this.files.checkInput(mouse);
        this.map.checkForIntersection(mouse);
        this.imagePreview.checkForIntersection(mouse);
        this.heightBox.checkCollision(mouse);
    }    

    public void updateDir() {
        this.imagePreview.update();
    }

}
