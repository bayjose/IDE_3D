/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Physics.Model;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Bayjose
 */
public class ModelTester extends Entity{
    
    float rotX = (float) Math.random();
    float rotY = (float) Math.random();
    float rotZ = (float) Math.random();
    
    private Random r = new Random();
    private int col = r.nextInt(11);
    private int row = r.nextInt(5);
    
    
    public ModelTester(Model model) {
        super(model);
    }

    public void update() {
        this.getModel().RotateXOnlyPoints(rotX);
        this.getModel().RotateYOnlyPoints(rotY);
        this.getModel().RotateZOnlyPoints(rotZ);
    }

    public void render(Graphics g) {

    }
    
    public void dead() {
        
    }
    
}
