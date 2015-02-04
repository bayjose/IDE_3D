/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Entity.Entity;
import Entity.Intro;
import Entity.Models;
import Entity.Player;
import Entity.TileEntity;
import Physics.Model;
import Physics.RenderModels;
import Physics.Vector3D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;
import particles.BasicParticles;
import world.Size;

/**
 *
 * @author Bayjose
 */
public class Handler {
    private Random r = new Random();
    public drawImage di = new drawImage();
    
    private Font font = new Font("Arial", Font.BOLD, 32);
    private Font fontnormal = new Font("Arial", Font.PLAIN, 12);
    public LinkedList<BasicParticles> particle = new LinkedList<BasicParticles>();
    public RenderModels renderModels;
    
    //load sprite sheets
    public SpriteSheet terrain;
    
    public GuiHandler guiHandler;
    
    public static Camera cam;
    public LinkedList<Rectangle> solidTiles = new LinkedList<Rectangle>();
    public LinkedList<Entity> entities = new LinkedList<Entity>();
    
    //Show Chunks
    public boolean showChunks=false;
    public static boolean showSolidTiles=false;
    
    public static boolean bool1;
    public static boolean bool2;
    public static boolean bool3;
    public static boolean bool4;
    public static boolean bool5;
    public static boolean bool6;
    public static boolean bool7;
    public static boolean bool8;
    public static boolean bool9;
    public static boolean bool0;
    
    //shooting 
    public boolean shoot=false;
    private int TicksBeforePew=0;
    private int MaxTickPew=1;
    
    public Player player;
    public EnumGameState egs = EnumGameState.Intro;
    private Intro intro;
    
    public Color highlightColor = new Color(255, 0, 0, 128);
    
    public void init(){
        this.cam= new Camera(new Vector3D(Game.WIDTH/2, Game.HEIGHT/2, 0),1, this);
        int scale =100;
        this.guiHandler = new GuiHandler(this);
        this.player = new Player(this);
//        player.getModel().OffsetAllFaces(null);
        this.entities.add(player);
        //load sprite sheets
        this.renderModels = new RenderModels();
        this.intro = new Intro(Models.generateQuad(new Vector3D(0,0,0), Game.WIDTH, Game.HEIGHT),this);
        initEntityArray();
    }
    
    public void tick(){
        //process all particles
        if(egs.equals(EnumGameState.Intro)){
            this.intro.tick();
        }
        for(int i=0; i<this.particle.size(); i++){
            if(this.particle.get(i).getLifespan()>0){
                this.particle.get(i).tick();
            }else{
                 this.particle.remove(i);
            }
        }
        if(egs.equals(EnumGameState.Main)){
            this.guiHandler.tick();
        }
        if(egs.equals(EnumGameState.Test)){
            Handler.cam.tick();
            for (int i=0; i<this.entities.size(); i++) {
                try{
                    if(this.entities.get(i).remove){
                        this.entities.remove(this.entities.get(i));
                    }else{
                            this.entities.get(i).tick();
                            this.entities.get(i).CheckForDead();
                    }
                }catch(Exception e){
                    System.err.println("There was an error ticking an entity");
                }
            }     
        }
    }
      
    public void render(Graphics g){
        if(egs.equals(EnumGameState.Intro)){
            this.intro.Render(g);
        }
        Graphics2D g2d = (Graphics2D)g;
        //map 
        g2d.scale(this.cam.getZoom()/1, this.cam.getZoom()/1);
        
        for(int i=0; i<this.particle.size(); i++){
            this.particle.get(i).render(g);
        }
        if(egs.equals(EnumGameState.Main)){
            this.guiHandler.render(g);
            if(Handler.bool7){
                g.setColor(Color.cyan);
                g.drawString("Layer:"+this.guiHandler.map.editingLayer, 100, 100);
            }
        }
        if(egs.equals(EnumGameState.Test)){
            this.renderModels.Render(entities, g);
            g.setColor(Color.WHITE);
            g.drawString("Loader Entities"+this.entities.size(), 100, 100);
        }
    }
    
    public void initEntityArray(){
        this.entities.clear();
        this.entities.add(this.player);
        for(int l=0; l<this.guiHandler.map.layers.length; l++){
            if(this.guiHandler.map.layers[l]!=null){
                for (int j = 0; j < this.guiHandler.map.getHeight(); j++) {
                    for (int i = 0; i < this.guiHandler.map.getWidth(); i++) {
                        if(this.guiHandler.map.layers[l].layer[i][j].col>0||this.guiHandler.map.layers[l].layer[i][j].row>0){
                            if(this.guiHandler.map.layers[l].layer[i][j]!=null){
                                Model quad = Models.generateQuad(new Vector3D(i * Size.tileSize, j * Size.tileSize, 0), Size.tileSize);
                                quad.assignImageFromSpriteBinder(SpriteBinder.resources.getImage(this.guiHandler.map.layers[l].layer[i][j].col, this.guiHandler.map.layers[l].layer[i][j].row));
                                this.entities.add(new TileEntity(quad, this.guiHandler.map.layers[l].layer[i][j].height, this));
                            }
                        }
                    }
                }
            }
        }
    }
    
}
