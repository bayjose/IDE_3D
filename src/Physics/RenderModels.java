/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import Base.Camera;
import Base.DistanceCalculator;
import Base.Handler;
import Entity.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class RenderModels {
    
    public boolean debug=false;
    public LinkedList<Face> facesOnScreen = new LinkedList<Face>();
    
     public void Render(LinkedList<Entity> entitys, Graphics g){
//         GlobalVars.numFaces = 0;
//         Point2D[] tempPoints = new Point2D[entitys.size()];
//         float[] key = new float[entitys.size()];
//         for(int i=0; i<entitys.size(); i++){
//             key[i] = DistanceCalculator.CalculateXYZDifferenceF(entitys.get(i).getModel().offset, Handler.cam.position);
//             tempPoints[i] = new Point2D(key[i], i);
//         }
//         Arrays.sort(key);
//         int[] renderingOrder = new int[entitys.size()];
//         for(int i=0; i<entitys.size(); i++){
//             //setting all the key slots
//             //inverse key
////             float looking = key[(key.length-1)-i];
//             float looking = key[i];
//             for(int j=0; j<tempPoints.length; j++){
//                 if(looking == tempPoints[j].getX()){
//                     renderingOrder[i] = (int) tempPoints[j].getY();
//                     //just set it to a big number
//                     tempPoints[j].setX(-10000);
//                     break;
//                 }
//             }
//         }
       
         for(int i=0; i<entitys.size(); i++){
             for(int j=0; j<entitys.get(i).models.size(); j++){
                 if(entitys.get(i).models.get(j)!=null){
//                     g.drawString("Position", (int)(entitys.get(i).models.get(j).offset.getX()+Camera.globalOffset.getX()), (int)(entitys.get(i).models.get(j).offset.getY()+Camera.globalOffset.getY()));
                    entitys.get(i).models.get(j).render(g);
                 }
             }
         }
//         
//         g.setColor(Color.yellow);
//         g.drawRect((int)Handler.cam.position.getX(), (int)Handler.cam.position.getY(), 1, 1);
    }
    
    public String profileRender(){
        return "Faces:"+GlobalVars.numFaces;
    }
}
