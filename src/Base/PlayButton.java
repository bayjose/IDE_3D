/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author Bayjose
 */
public class PlayButton extends Button{

    public PlayButton(Handler handler, int x, int y) {
        super("play", handler, x, y, 34, 34);
    }

    public void Event(Handler handler) {
        loop:{
            if(handler.egs.equals(EnumGameState.Main)){
                handler.egs = EnumGameState.Test;
                break loop;
            }
            if(handler.egs.equals(EnumGameState.Test)){
                handler.egs = EnumGameState.Main;
                break loop;
            }
        }
    }
    
}
