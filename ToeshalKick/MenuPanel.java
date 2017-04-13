import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class MenuPanel extends JPanel{

   static JFrame menuFrame, loginFrame, contentFrame, hsFrame, settingsFrame, eogFrame, gameFrame;
   private static Color BACKGROUND = new Color(105, 206, 236);
   private BufferedImage myImage;
   private Graphics myBuffer;
   private int FRAMEx = 400;
   private int FRAMEy = 450;
   public MenuPanel(JFrame a, JFrame b)
   {
      loginFrame = a;
      menuFrame = b;
      setLayout(new BorderLayout());
   
      JPanel panel = new JPanel(new BorderLayout(0,-200));
      add(panel, BorderLayout.CENTER);
      panel.setBackground(BACKGROUND);
   
      JLabel w = new JLabel("", SwingConstants.CENTER);
      ImageIcon cr = new ImageIcon("crossyroad.png");
      w.setIcon(cr);
      panel.add(w, BorderLayout.CENTER);
     
      
      JPanel panel1= new JPanel(new GridLayout(2,2));
      add(panel1, BorderLayout.SOUTH);
      panel1.setBackground(BACKGROUND);
      
      JButton play = new JButton("Play");
      Font f = new Font("Monospaced", Font.BOLD, 20);
      play.setFont(f);
      play.setFocusPainted(false);
      play.setForeground(Color.GREEN.darker());
     // play.addActionListener(new playListener());
      panel1.add(play); 
   
      
      JButton hs = new JButton("High Scores");
      //hs.addActionListener(new hsListener());
      hs.setFont(f);
      hs.setFocusPainted(false);
      panel1.add(hs);
      
      JButton settings = new JButton("Settings");
      settings.setFont(f);
      settings.setFocusPainted(false);
      //settings.addActionListener(new settingsListener());
      panel1.add(settings); 
      
      JButton quit = new JButton("Quit");
      quit.setFont(f);
      quit.setFocusPainted(false);
      //quit.addActionListener(new quitListener());
      panel1.add(quit);
       
     
   }
 //   private class playListener implements ActionListener
//    {
//       public void actionPerformed(ActionEvent e)
//       {
//          //hide menu frame
//          menuFrame.setVisible(false);
//         
//       
//                   //show content frame, or make a new one if it doesn't exist yet
//          if(contentFrame == null)
//          {
//             contentFrame = new JFrame("Game Options");
//             contentFrame.setVisible(true);
//             contentFrame.setLocation(700,200);
//             contentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             contentFrame.setContentPane(new ContentPanel(menuFrame, contentFrame));//content panel needs to know the frames!
//             contentFrame.setSize(400,450);  
//          }            
//          else
//             contentFrame.setVisible(true); 
//       }
//    }
//    private class hsListener implements ActionListener 
//    {
//       public void actionPerformed(ActionEvent e) 
//       {
//          //hide menu frame
//          menuFrame.setVisible(false);
//          
//          //show content frame, or make a new one if it doesn't exist yet
//          if(hsFrame == null)
//          {
//             hsFrame = new JFrame("High Scores");
//             hsFrame.setVisible(true);
//             hsFrame.setLocation(700,200);
//             hsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             hsFrame.setContentPane(new HSPanel(menuFrame, hsFrame));//content panel needs to know the frames!
//             hsFrame.setSize(400,510);  
//          }            
//          else
//          {
//             HSPanel.label2.setText("");
//             hsFrame.setVisible(true);
//          } 
//       }
//    }
//    private class settingsListener implements ActionListener
//    {
//       public void actionPerformed(ActionEvent e)
//       {  
//          SettingsPanel.count = 0;
//          //hide menu frame
//          menuFrame.setVisible(false);
//          //show content frame, or make a new one if it doesn't exist yet
//          if(settingsFrame == null)
//          {
//             settingsFrame = new JFrame("Settings");
//             settingsFrame.setVisible(true);
//             settingsFrame.setLocation(700,200);
//             settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             settingsFrame.setContentPane(new SettingsPanel(menuFrame, gameFrame, settingsFrame));//content panel needs to know the frames!
//             settingsFrame.setSize(450,450);  
//          }            
//          else
//             settingsFrame.setVisible(true); 
//             
//       }
//    }
// 
//    private class quitListener implements ActionListener
//    
//    {
//       public void actionPerformed(ActionEvent e)
//       {
//          menuFrame.setVisible(false);
//         
//          int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to quit?", "Quit?" , JOptionPane.YES_NO_OPTION);
//       
//          if (reply == JOptionPane.YES_OPTION) 
//          {
//             System.exit(0);
//          }
//          else 
//          {
//             menuFrame.setVisible(true);
//          }
//       }
   
  // }
}