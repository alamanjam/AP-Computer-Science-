//objects
import java.awt.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class napsack
{

   private double dx; 
   private double dy;
   
   
   private double xpos;
   private double ypos;
   
   private int shift = 30;
   
   private int frameSize = 500;
   private int frameSize2 = 500;
   
   private double mspd;
   
   private Image image;
   
   private int direction;
   
   private int type;
   
   public napsack(double speed, int type, double x, double y, int dir)
   {
      initNapsack(speed, type, x, y, dir);
   }
   private void initNapsack(double speed, int type, double x, double y, int dir)
   {
      xpos = x;
      ypos = y;
      mspd = speed;
      type = type;
      if(type == 1)
      {
         ImageIcon ii = new ImageIcon("napsack.jpg");
         image = ii.getImage();
      }
      direction = dir;
   }
   public void move()
   {
      if(direction == 1)
      {
         xpos = xpos+mspd;
      }
   }
   public Image getImage()
   {
      return image;
   }
   public double getX()
   {
      return xpos;
   }
   public double getY()
   {
      return ypos;
   }
   public int getType()
   {
      return type;
   }
   
   public void setX(double x)
   {
      xpos = x;
   }
   public void setY(double y)
   {
      ypos = y;
   }
}
   