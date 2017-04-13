//toeshal class
import java.awt.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
public class Toeshal
{
   private double xpos;
   private double ypos;
   
   private int shift = 30;
   
   private int frameSize = 500;
   private int frameSize2 = 500;
   
   private double mspd;
   
   private Image image;
   
   public Toeshal(int kickStrength, double mspeed, double x, double y)
   {
      initToeshal(kickStrength, mspeed, x, y);
   }
      
   private void initToeshal(int kickStrength, double mspeed, double x, double y)
   {
      ImageIcon ii = new ImageIcon("toeshalv1.jpg");
      image = ii.getImage();
      xpos = x;
      ypos = y;
      mspd = mspeed;
   }
   
   public Image getImage()
   {
      return image;
   }
   public void setImage(Image img)
   {
      ImageIcon i1mg = new ImageIcon(img);
      image = i1mg.getImage();
   }
   public void setX(int x)
   {
      xpos = x;
   }
   public void setY(int y)
   {
      ypos = y;
   }
   
   public double getX()
   {
      return xpos;
   }
   
   public double getY()
   {
      return ypos;
   }
   
   public void moveUp()
   {
      ypos = ypos - mspd;
   }
   public void moveDown()
   {
      ypos = ypos + mspd;
   }
   public void moveRight()
   {
      xpos = xpos + mspd;
   }
   public void moveLeft()
   {
      xpos = xpos - mspd;
   }
}