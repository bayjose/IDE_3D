/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import File.Files;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayjose
 */
public class NewLayerButton extends Button{

    private Files files;
    private Handler handler;
    
    public NewLayerButton(Handler handler, int x, int y, Files file) {
        super("Layer", handler, x, y, 34, 34);
        this.files = file;
        this.handler = handler;
    }

    public void Event(Handler handler) {
        this.files.getDirectory(this.files.getSelected()).addFile("Layer"+files.getDirectory(files.getSelected()).getNextLayer()+".txt");
        this.files.saveIndex();
        this.handler.guiHandler.map.initializeArray(files.getDirectory(files.getSelected()).getNextLayer());
    }
    
}
