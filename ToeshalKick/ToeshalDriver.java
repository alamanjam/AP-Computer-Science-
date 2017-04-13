import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToeshalDriver extends JPanel
{
  
   public static void main(String[] args)throws Exception
   { 
      JFrame frame = new JFrame("ToeshalKick");
      frame.setSize(500, 500);
      frame.setLocation(10, 10);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new ToeshalPanel());
      frame.setVisible(true);
      frame.setResizable(false);     
   }
         
      
}