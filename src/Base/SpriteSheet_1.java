package Base;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet_1{
   BufferedImage spriteSheet; 
   
   public int width;
   public int height;
   public int rows;
   public int columns;
   public BufferedImage[] sprites;
   
   public SpriteSheet_1(int width, int height, int rows, int columns, String path) throws IOException {
      this.spriteSheet= ImageIO.read(this.getClass().getResource("/"+path));
      this.width = width;
      this.height = height;
      this.rows = rows;
      this.columns = columns;
      this.sprites= new BufferedImage[rows * columns];
      
      for(int i = 0; i < rows; i++) {
         for(int j = 0; j < columns; j++) {
            sprites[(i * columns) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
         }
      }
   }
   
   public BufferedImage getImage(int col, int row){
       int index = (col+(row*columns));
       if(index<this.sprites.length){
           return this.sprites[index];
       }else
           
       return null;
   }

}