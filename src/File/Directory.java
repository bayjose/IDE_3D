/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.Handler;
import gui.Map;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
/**
 *
 * @author Bayjose
 */
public class Directory {
    
    public File[] files;
    public String name;
    public boolean open = false;
    private Handler handler;
    private Rectangle rect;
    private final Rectangle arrow = new Rectangle(0,0,14,9);
    private boolean doubleClick;
    private int curClickTics = 0;
    private final int maxClickTicks = 30;
    
    private int nextLayer;
    
    //images
    private Image arrowDown;
    private Image arrowDownHighlight;
    private Image arrowUp;
    private Image arrowUpHighlight;
    private Image folder;
    private Image Main;
    
    
    //highlight stuff
    private boolean Selected = false;
    private int subIndex = -1;
    private int yOffset =0;
    
    
    private final int Index;
    //layers stuff
    private boolean isMain=true;
    
    
    public Directory(String name, String[] filesName, Handler handler, int indexInFile){
        this.name = name;
        this.handler = handler;
        int index=0;
        this.Index = indexInFile;
        String[] cleanDir;
        for(int i=0; i<filesName.length; i++){
            if(filesName[i].length()>=5){
               index++; 
            }
        }
        cleanDir = new String[index];
        index = 0;
        for(int i=0; i<filesName.length; i++){
            if(filesName[i].length()>=5){
               cleanDir[index] = filesName[i];
               index++; 
            }
        }
        this.files = new File[index];
        index=0;
        int layerIndex = 0;
        for(int i=0; i<filesName.length; i++){
            if(!filesName[i].isEmpty()){
               if(filesName[i].contains("Layer")){
                   this.files[index] = new LayerFile(new Rectangle(0, this.yOffset + (i * 18) + 18, 180, 18),cleanDir[index], handler, layerIndex);
                   
                   layerIndex++;
               }else{
                   this.files[index] = new BasicFile(new Rectangle(0, this.yOffset + (i * 18) + 18, 180, 18),cleanDir[index]);
               }
               index++; 
            }
        }
        this.nextLayer = layerIndex;
        init();
    }
    
    public void init(){
        rect = new Rectangle(0,0,180,18);
        this.arrowDown = handler.di.loadImage("folder/arrowDown.png");
        this.arrowUp = handler.di.loadImage("folder/arrowUp.png");
        this.folder = handler.di.loadImage("folder/Folder.png");
        this.arrowDownHighlight = handler.di.loadImage("folder/arrowDownHl.png");
        this.arrowUpHighlight = handler.di.loadImage("folder/arrowUpHl.png");
        this.Main = handler.di.loadImage("folder/Main.png");
    }
    
    public void render(Graphics g, int x, int y, boolean blue, boolean selected, boolean main){
        this.rect.x = x;
        this.rect.y = y;
        this.arrow.x = x+155;
        this.arrow.y = y+4;
        this.Selected = selected;
        this.yOffset = y;
        this.isMain = main;
        
        if(blue){
            g.setColor(Color.decode("#f3f6fb"));
        }else{
            g.setColor(Color.decode("#ffffff")); 
        }

        g.fillRect(0, y, 180, 18);
        if (selected&&this.subIndex<0) {
            g.setColor(handler.highlightColor);
            g.fillRect(0, y, 180, 18);
        }        
        g.setColor(Color.black);
//        g.drawString(name+" "+this.subIndex, x+14, y+14);
        g.drawString(name, x+14, y+14);
        g.drawImage(folder, x-5, y, null);
        if(this.open){
            if(!selected&&this.subIndex<0){
                g.drawImage(arrowDown, x+158, y+6, null); 
            }else{
               g.drawImage(arrowDownHighlight, x+158, y+6, null);  
            }
            //file background
            //file images
            //file names
            for(int i=0; i<this.files.length; i++){
                if(this.files[i]!=null){
                    if(this.subIndex==i){
                        g.setColor(handler.highlightColor);
                        g.fillRect(0, y+(i*18)+18, 180, 18);
                    }else {
                        if (i % 2 == 0) {
                            g.setColor(Color.decode("#f3f6fb"));
                        } else {
                            g.setColor(Color.decode("#ffffff"));
                        }
                        g.fillRect(0, y + (i * 18) + 18, 180, 18);
                    }

                    g.setColor(Color.BLACK);
                    if(this.files[i].name!=null){
                        g.drawString(this.files[i].name, x+32, y+(i*18)+30);
                    }
                    for(int j=0; j<EnumExtension.values().length; j++){
                        if(this.files[i].name.contains(EnumExtension.values()[j].getID())){
                            handler.di.drawImage(""+EnumExtension.values()[j].image, x+8, y+(i*18)+19, g);
                        }
                    }
                    this.files[i].extraRender(g);
                }
            }
        }else{
            if(!selected&&this.subIndex<0){
                g.drawImage(arrowUp, x+160, y+4, null);
            }else{
                g.drawImage(arrowUpHighlight, x+160, y+4, null);
            }
        }
        if(selected&&this.open){
            if(Handler.bool7){
                g.setColor(Color.red);
                for(int i=0; i<this.files.length; i++){
                    g.drawRect(this.files[i].collision.x, this.files[i].collision.y, this.files[i].collision.width, this.files[i].collision.height);
                }
            }
        }
        
        if(main){
            g.drawImage(Main, x-5, y, null);
        }
    }
    
    public int getLength(){
        if(this.open){
            return this.files.length;
        }else{
            return 0;
        }
    }
    
    public void tick(){
        if(this.curClickTics>0){
            this.curClickTics--;
        }
        if(!this.Selected){
            this.subIndex=-1;
        }
    }
    
    public boolean checkForClick(Rectangle mouse){
        //toggle weather the folder is open or not by clicking on arrow
        this.updateCollisions();
        if(mouse.intersects(this.arrow)){
            loop:{
                if(this.open==false){
                    this.open = true;
                    break loop;
                }
                if(this.open==true){
                    this.open = false;
                    break loop;
                }
            }
        }
        //if it is open, check to see what files are clicked on
        if(this.open){           
            loop:{
                for(int i=0; i<this.files.length; i++){
                    if(mouse.intersects(this.files[i].collision)){
                        if(this.handler.guiHandler.files.getSelected()!=this.Index){
                            this.handler.guiHandler.files.setDirectory(this.Index);
                        }
                        if(this.subIndex!=i){
                            this.subIndex = i;
                            if(this.isMain){
                                this.files[i].selectEvent();
                            }
                            break loop;
                        }
                        if(this.isMain){
                            this.files[i].clickEvent();
                        }
                        break loop;
                    }
                }
                this.subIndex = -1;
            }
        }
        //will toggle the openness of the folder when a double click is preformed. 
        if(mouse.intersects(this.rect)){
            if(this.curClickTics > 0){
                loop:{
                    if(this.open==false){
                        this.open = true;
                        this.curClickTics = 0;

                        return true;
                    }
                    if(this.open==true){
                        this.open = false;
                        this.curClickTics = 0;

                        return true;
                    }
                }
            }
            if(this.curClickTics<=0){
                this.curClickTics=this.maxClickTicks;
            }
            return true;
        }else
        return false;
        
    }
    
    public void addFile(String fileName){
        if(!fileName.isEmpty()){
            File[] oldFiles = this.files;
            int index=0;
            int layerIndex = 0;
            this.files = new File[oldFiles.length+1];
            for(int i=0; i<oldFiles.length; i++){
                this.files[i] = oldFiles[i];
                if(this.files[i].name.contains("Layer")){
                    layerIndex++;
                }
                index++;
            }
            if(fileName.contains("Layer")){
                this.files[index] = new LayerFile(new Rectangle(0, this.yOffset + (index * 18) + 18, 180, 18),fileName, handler, layerIndex);
                layerIndex++;
                this.subIndex = this.files.length-1;
                this.files[subIndex].selectEvent();
            }else{
                this.files[index] = new BasicFile(new Rectangle(0, this.yOffset + (index * 18) + 18, 180, 18),fileName);
            }
            this.nextLayer = layerIndex;
        }
    }    

    public int getNextLayer() {
        return this.nextLayer;
    }

    public void updateCollisions() {
        for(int i=0; i<this.files.length; i++){
            this.files[i].collision = new Rectangle(0, this.yOffset + (i * 18) + 18, 180, 18);
        }
    }
}