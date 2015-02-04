/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author Bailey
 */
public class SaveButton extends Button{

    public SaveButton(Handler handler, int x, int y) {
        super("play", handler, x, y, 34, 34);
    }

    public void Event(Handler handler) {
        handler.guiHandler.files.saveInitializedLayers();
        handler.initEntityArray();
    }
    
}
