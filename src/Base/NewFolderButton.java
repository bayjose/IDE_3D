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
public class NewFolderButton extends Button{
    private Files files;
    public NewFolderButton(Handler handler, int x, int y, Files files) {
        super("newFolder", handler, x, y, 34, 34);
        this.files = files;
    }

    public void Event(Handler handler) {
        files.addDir(JOptionPane.showInputDialog(""));
    }
    
}
