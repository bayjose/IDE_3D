/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Bayjose
 */
public class KeyInput extends KeyAdapter{
    
    Handler handler;
    public static boolean W = false;
    public static boolean A = false;
    public static boolean S = false;
    public static boolean D = false;
            
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        

        if(key == KeyEvent.VK_EQUALS){
            this.handler.cam.setZoom(handler.cam.getZoom()+1);
        }
       
        
        if(key == KeyEvent.VK_0){
            loop:{
                if(this.handler.bool0){
                    this.handler.bool0=false;
                    break loop;
                }
                if(this.handler.bool0==false){
                    this.handler.bool0=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_1){
            loop:{
                if(this.handler.bool1){
                    this.handler.bool1=false;
                    break loop;
                }
                if(this.handler.bool1==false){
                    this.handler.bool1=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_2){
            loop:{
                if(this.handler.bool2){
                    this.handler.bool2=false;
                    break loop;
                }
                if(this.handler.bool2==false){
                    this.handler.bool2=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_3){
            loop:{
                if(this.handler.bool3){
                    this.handler.bool3=false;
                    break loop;
                }
                if(this.handler.bool3==false){
                    this.handler.bool3=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_4){
            loop:{
                if(this.handler.bool4){
                    this.handler.bool4=false;
                    break loop;
                }
                if(this.handler.bool4==false){
                    this.handler.bool4=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_5){
            loop:{
                if(this.handler.bool5){
                    this.handler.bool5=false;
                    break loop;
                }
                if(this.handler.bool5==false){
                    this.handler.bool5=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_6){
            loop:{
                if(this.handler.bool6){
                    this.handler.bool6=false;
                    break loop;
                }
                if(this.handler.bool6==false){
                    this.handler.bool6=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_7){
            loop:{
                if(this.handler.bool7){
                    this.handler.bool7=false;
                    break loop;
                }
                if(this.handler.bool7==false){
                    this.handler.bool7=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_8){
            loop:{
                if(this.handler.bool8){
                    this.handler.bool8=false;
                    break loop;
                }
                if(this.handler.bool8==false){
                    this.handler.bool8=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_9){
            loop:{
                if(this.handler.bool9){
                    this.handler.bool9=false;
                    break loop;
                }
                if(this.handler.bool9==false){
                    this.handler.bool9=true;
                    break loop;
                }
            
            }
        }

        if(key == KeyEvent.VK_MINUS){
            this.handler.cam.setZoom(handler.cam.getZoom()-1);
        }
        
        if(key == KeyEvent.VK_SPACE){
            handler.shoot=true;
        }
        
            if(key == KeyEvent.VK_W){
                KeyInput.W = true;
            }
            if(key == KeyEvent.VK_S){
                KeyInput.S = true;
            }
            if(key == KeyEvent.VK_A){
                KeyInput.A = true;
            }
            if(key == KeyEvent.VK_D){
                KeyInput.D = true;
            }

    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            KeyInput.W = false;
        }
        if(key == KeyEvent.VK_S){
            KeyInput.S = false;
        }
        if(key == KeyEvent.VK_A){
            KeyInput.A = false;
        }
        if(key == KeyEvent.VK_D){
            KeyInput.D = false;
        }
        
        if(key == KeyEvent.VK_SPACE){
            handler.shoot=false;
        }
    }
    
}
