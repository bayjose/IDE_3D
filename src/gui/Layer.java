/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import world.Tile;

/**
 *
 * @author Bayjose
 */
public class Layer {
    public Tile[][] layer;
    public boolean show = true;
    
    public Layer(int width, int height){
        layer = new Tile[width][height];
    }
}
