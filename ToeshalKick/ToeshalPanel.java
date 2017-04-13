import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;


public class ToeshalPanel extends JPanel
{
   private static final int FRAME = 500;
   private static final int FRAME2 = 500;
   private Graphics myBuffer;
   private Timer t;
   private Toeshal toe;
   private napsack nap;
   private int points;
   private int highscore;
   private Scanner infile;
   
   public ToeshalPanel() throws Exception
   {
      toe = new Toeshal(20, 10, 400, 200);
      nap = new napsack(1, 1, 0, 200, 1);
      System.setOut(new PrintStream(new FileOutputStream("highscores.txt")));
      infile = new Scanner(new File("highscores.txt"));
      t = new Timer(5, new Listener());
      t.start();
      this.addKeyListener(new Key());
      setFocusable(true);
   }
   public void paintComponent(Graphics g)
   {
     // try
      //{
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
     
      g2d.drawImage(nap.getImage(), (int)nap.getX(), (int)nap.getY(), 50, 50, null);
      
      g2d.drawImage(toe.getImage(), (int)toe.getX(), (int)toe.getY(), 50, 50, null);
      
      Font f = new Font("SansSerif", Font.BOLD, 20);
      g.setFont(f);
      g.setColor(Color.BLACK);
      if(nap.getX() > 500)
      {
      g.drawString("YOU LOST!", 250, 250);
      //if(infile.nextInt() < points)
      //highscore = points;
      //System.out.print(highscores);
      restart();
      }
      
      //g.drawString("High Score: "+ infile.nextInt(), 300, 100);
      g.drawString("Boyas kicked: "+points, 300, 400);
      }
      //catch(FileNotFoundException e)
      //{
      //}
      
   
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         nap.move();
         collide((int)nap.getX(), (int)nap.getY(), toe, nap);
         
         
         repaint();
      }
   }

   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_UP && toe.getY() != 0)
         {
            toe.moveUp();
         }
         if(e.getKeyCode() == KeyEvent.VK_DOWN && toe.getY() != 420)
         {
            toe.moveDown();
         }
         if(e.getKeyCode() == KeyEvent.VK_LEFT)
         {
            toe.moveLeft();
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT)
         {
            toe.moveRight();
         }
      }
   }  
   public void collide(int x, int y, Toeshal to, napsack n)
   {  
      int topleftx = (int)to.getX()-10;
      int toplefty = (int)to.getY()-10;
      
      int bottomrightx = topleftx+50;
      int bottomrighty = toplefty+50;
      
      if((x < bottomrightx && x > topleftx) && (y < bottomrighty && y > toplefty))
      {
         points++;
         int ypos = (int)((Math.random()*400)+10);
         n.setX(50);
         n.setY(ypos);
      }
   }
   private void restart()
      {
      nap.setX(0);
      nap.setY(200);
      toe.setX(400);
      toe.setY(200);
      points = 0;

      t.stop();
      t.setInitialDelay(1000);
      

      t.start();
                 
      }

         
}
      