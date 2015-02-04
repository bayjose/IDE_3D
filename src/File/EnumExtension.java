/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

/**
 *
 * @author Bayjose
 */
public enum EnumExtension {
    Folder(".dir", "folder/Folder.png"),
    TextFile(".txt","folder/Text.png"),
    Image(".png","folder/Image.png"),
    Object(".obj","folder/Object.png");
    
    
    protected String id;
    protected String image;
    
    EnumExtension(String id, String image){
        this.id = id;
        this.image = image;
    }
    
    public String getID(){
        return this.id;
    }
    
    public String getImage(){
        return this.image;
    }
}
