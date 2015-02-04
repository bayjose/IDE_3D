/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.FileMover;
import Base.Game;
import Base.Handler;
import Base.MouseInput;
import Base.SpriteBinder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bayjose
 */
public class Files {
    
    
    public final String path = "File/";
    private Directory[] directories = new Directory[0];
    private int selectedDirectory=0;
    private int Main = 0;
    
    private Rectangle collision;
    
    //images
    private Image fileHeader;

    
    private Handler handler;
    private int x, y;
    
    public int Offset = 0;
    
    public Files(Handler handler, int x, int y){
        this.x=x;
        this.y=y;
        this.handler = handler;
        this.fileHeader = handler.di.loadImage("fileHeader.png");
        this.collision = new Rectangle(0,36,180,Game.HEIGHT-y);
        refresh();
        testDirs();
        if(this.directories.length>0){
            this.directories[this.Main].open = true;
        }else{
            //prompt a display explaining the engine
        }
        this.updateAllDirectoryCollisions();
    }
    
    public void tick(){
        for(int i=0; i<this.directories.length; i++){
            if(i<this.directories.length){
                if(this.directories[i]!=null){
                    this.directories[i].tick();
                }
            }
        }
    }
    
    public void render(Graphics g){
        Offset = 0;
        g.setColor(Color.WHITE);
        g.fillRect(this.collision.x, this.collision.y, this.collision.width, this.collision.height);
        g.drawImage(fileHeader, 0, 36, null);
        for(int i=0; i<this.directories.length; i++){
            int temp = (i+Offset);
            boolean blue = false;
            if (temp % 2 == 0) {
               blue = true;
            } else {
               blue = false;
            }
            boolean isMain = false;
            if(this.Main==i){
                isMain=true;
            }else{
                isMain=false;
            }
            if(this.selectedDirectory==i){
                if(this.directories[i]!=null){
                    this.directories[i].render(g, 10, 54+(i*18)+(Offset*18),blue,true,isMain);
                }
            }else{
                if(this.directories[i]!=null){
                    this.directories[i].render(g, 10, 54+(i*18)+(Offset*18),blue,false,isMain);
                }
            }
            if(this.directories[i]!=null){
                Offset += (this.directories[i].getLength());
            }
        }
        
        if(Handler.bool7){
            g.setColor(Color.cyan);
            g.drawRect(this.collision.x, this.collision.y, this.collision.width, this.collision.height);
        }
    }
    
    public void refresh(){
        try {
            String[] dirs;
            int lenght=0;
            int numDirs =0;
            String allData = "";
            Scanner s1 = new Scanner(new File(this.path+"exampleFiles.txt"));
            try{
                while(true){
                    String line = s1.nextLine();
                    allData = allData+line+" ";
                    if(line.contains(".dir")){
                        numDirs++;
                    }
                    lenght++;
                }
            }catch(Exception e){
                System.out.println("Lenght:"+lenght+" Dirs:"+numDirs);
                this.directories = new Directory[numDirs];
                dirs = new String[numDirs];
                
            }
            dirs = allData.split(".dir");

            for(int i=0; i<numDirs; i++){
                String[] data = dirs[i].split(" ");
                String[] cutData = new String[data.length-1];
                for(int j=0; j<data.length-1; j++){
                    cutData[j] = data[j];
                }
                this.directories[i] = new Directory(data[data.length-1],cutData,this.handler,i);
            }
            
        } catch (FileNotFoundException ex) {
            System.err.println("There was a problem reading:"+this.path+"exampleFiles.txt");
        }
    }
    
    public void checkData(){
        for(int i=0; i<this.directories.length; i++){
            System.out.println("Dir:"+i+" ----------------");
            for(int j=0; j<this.directories[i].files.length; j++){
                System.out.println(this.directories[i].files[j]);
            }
        }
    }
    
    //change Directory
    public void checkInput(Rectangle mouse){
        if(mouse.intersects(this.collision)){
            for(int i=0; i<this.directories.length; i++){
                if(this.directories[i].checkForClick(mouse)){
                    selectedDirectory = i;
                    if(MouseInput.IsRightClick){
                        this.Main = i;
                        SpriteBinder.updateDirectory(path+this.directories[i].name+"/resources.png");
                        updateAllDirectoryCollisions();
                        handler.guiHandler.updateDir();
                        //temp
                        handler.guiHandler.map.clearAllLayers(this);
                    }
                    break;
                }
            }
        }
    }

    public void addDir(String input) {        
        if(!input.isEmpty()){
            String[] inputData = input.split(" ");
            input = inputData[0];
            Directory[] oldDir = this.directories;
            this.directories = new Directory[oldDir.length+1];
            int index=0;
            for(int i=0; i<oldDir.length; i++){
                this.directories[i] = oldDir[i];
//                System.out.println("Old Dir "+i+" has "+oldDir[i].files.length+" files.");
                index++;
            }
            this.directories[index] = new Directory(input, new String[]{"resources.png", "properties.txt", "Layer0.txt"}, this.handler,index);
            File dir = new File(this.path+input);
            dir.mkdir();
            try {
                FileMover.copyFile(new File("defaultTerrain.png"), new File(this.path+input+"/resources.png"));
            } catch (IOException ex) {
                Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            }
            //populate dir with properties file 
            try {
                PrintWriter out = new PrintWriter(this.path +input+ "/properties.txt");
                out.println(16);
                out.println(16);
                out.println(1);
                out.close();
            } catch (Exception e) {
                System.err.println("There was a problem createing properties file:" + this.path + "/resources.png");
            }
            this.saveIndex();
            this.refresh();
        }
    }
    
    public void saveIndex(){
        try {
            PrintWriter out = new PrintWriter(this.path+"exampleFiles.txt");
            for(int i=0; i<this.directories.length; i++){
                for(int j=0; j<this.directories[i].files.length; j++){
                    out.println(this.directories[i].files[j].name);                    
                }
                out.println(this.directories[i].name+".dir");
            }
            out.close();
        } catch (Exception e) {
            System.err.println("There was a problem saving file:"+this.path+"exampleFiles.txt");
        }
    }
    
    public void saveInitializedLayers(){
            for(int j=0; j<this.directories[this.Main].files.length; j++){
                //save all of the sub files within the directory
                if(this.directories[this.Main].files[j].name.contains("Layer")){
                    try {
                        PrintWriter outFile = new PrintWriter(this.path + this.directories[this.Main].name+"/"+this.directories[this.Main].files[j].name);
                        if(handler.guiHandler.map.layers[((LayerFile)this.directories[this.Main].files[j]).key]!=null){
                            //when loading the map go through the data row, by column, not column by row, increase j first, then i
                            int width = handler.guiHandler.map.getWidth();
                            int height = handler.guiHandler.map.getHeight();
                            for(int k = 0; k<height; k++){
                                for(int l=0; l<width; l++){
                                    //picture is saved row, col so load it that way
                                    outFile.println(handler.guiHandler.map.layers[((LayerFile)this.directories[this.Main].files[j]).key].layer[l][k].row+" "+handler.guiHandler.map.layers[((LayerFile)this.directories[this.Main].files[j]).key].layer[l][k].col+" "+handler.guiHandler.map.layers[((LayerFile)this.directories[this.Main].files[j]).key].layer[l][k].height);
                                }
                            }
                        }
                        outFile.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            try{
                PrintWriter outProperties = new PrintWriter(this.path + this.directories[this.Main].name+"/properties.txt");
                outProperties.println(handler.guiHandler.map.getWidth());
                outProperties.println(handler.guiHandler.map.getHeight());
                outProperties.println(this.getDirectory(this.getSelected()).getNextLayer());
                outProperties.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
    }
    
    public void testDirs(){
        System.out.println("Testing Dirs-----------------");
        for(int i=0; i<this.directories.length; i++){
            loop:{
                try {
                    Scanner s1 = new Scanner(new File(this.path+this.directories[i].name));
                    s1.close();
                } catch (FileNotFoundException ex) {
                    System.out.println(this.path+this.directories[i].name+" Dose not exist!");
                    break loop;
                }
                System.out.println(this.path+this.directories[i].name+" Exists");
            }   
        }
    }
    
    public Directory getDirectory(int index){
        if(index<this.directories.length){
            return this.directories[index];
        }
        return null;
    }
    
    public int getSelected(){
        return this.selectedDirectory;
    }
    
    public void setDirectory(int i){
        this.selectedDirectory  = i;
    }
    
    public void updateAllDirectoryCollisions(){
        for(int i=0; i<this.directories.length; i++){
            this.directories[i].updateCollisions();
        }
    }
    
    public String getPropertiesPath(){
        return this.path + this.directories[this.Main].name + "/properties.txt";
    }

}
