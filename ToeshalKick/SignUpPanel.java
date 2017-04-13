import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class SignUpPanel extends JPanel{

   static JFrame signUpFrame, loginFrame, menuFrame, contentFrame, hsFrame, settingsFrame, eogFrame, gameFrame;
   private JTextField uname1;
   private JPasswordField pass1;
   private static Color BACKGROUND = new Color(105, 206, 236);
   private BufferedImage myImage;
   private Graphics myBuffer;
   private int FRAMEx = 400;
   private int FRAMEy = 450;
   public SignUpPanel(JFrame a, JFrame b)
   {
      loginFrame = a; 
      signUpFrame = b;
      setLayout(new BorderLayout());
   
      JLabel w = new JLabel("Sign Up", SwingConstants.CENTER);
      w.setFont(new Font("Monospaced", Font.BOLD, 40));
      add(w, BorderLayout.NORTH);
      
      JPanel panel1= new JPanel(new GridLayout(1,2));
      add(panel1, BorderLayout.SOUTH);
      
      JPanel panel2 = new JPanel(new BorderLayout());
      add(panel2, BorderLayout.CENTER); 
      
      JPanel panel3 = new JPanel(new GridLayout(6,2));
      panel2.add(panel3, BorderLayout.CENTER);
      JLabel uname = new JLabel("Username:", SwingConstants.LEFT);
      uname.setFont(new Font("Monospaced", Font.BOLD, 20));
      panel3.add(uname);
      
      Font font1 = new Font("Monospaced", Font.BOLD, 30); 
      
      uname1 = new JTextField(15);
      uname1.setHorizontalAlignment(SwingConstants.LEFT);
      uname1.setDocument(new JTextFieldLimit(15));
      uname1.setFont(font1);
      panel3.add(uname1);
      
      JLabel pass = new JLabel("Password:", SwingConstants.LEFT);
      pass.setFont(new Font("Monospaced", Font.BOLD, 20));
      panel3.add(pass);
      
      pass1 = new JPasswordField(15);
      pass1.setHorizontalAlignment(SwingConstants.LEFT);
      pass1.setDocument(new JTextFieldLimit(15));
      pass1.setFont(font1);
      panel3.add(pass1);
      
      Font f = new Font("Monospaced", Font.BOLD, 20);
      
      JButton back = new JButton("Back");
      back.setFont(f);
      back.setFocusPainted(false);
      back.addActionListener(new backListener());
      panel1.add(back); 
      
      JButton signup = new JButton("Sign Up");
      signup.setFocusPainted(false);
      signup.addActionListener(new signupListener());
      signup.setFont(f);
      panel1.add(signup);
      
      
      
      JButton quit = new JButton("Quit");
      quit.setFont(f);
      quit.setFocusPainted(false);
      quit.addActionListener(new quitListener());
      panel1.add(quit);
       
     
   }
   private class backListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         signUpFrame.setVisible(false);
         uname1.setText("");
         pass1.setText("");
         loginFrame.setVisible(true);
      }
   }
   private class signupListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e) 
      {
         try
         {
            Scanner s = new Scanner(new File("passwords.txt"));
            
         
            String u = uname1.getText();
            String pass = pass1.getText();
            if(u.length() < 5 || pass.length() < 5)
            {
               JOptionPane.showMessageDialog(null,"Both username and password must be at least 5 characters long!");
            }
            else
            {
               TreeMap<String, Integer>t = new TreeMap<String, Integer>();
               while(s.hasNext())
               {
                  String uname = s.next();
                  int password = s.nextInt(); 
                  t.put(uname, password);
               }
               
               if(t.containsKey(u))
               {
                  JOptionPane.showMessageDialog(null,"That username already exists! Choose another one.");   
               }
               else
               {
                  t.put(u, pass.hashCode()); 
                  JOptionPane.showMessageDialog(null,"Thank you for signing up!");
                  PrintStream outfile = new PrintStream(new FileOutputStream("passwords.txt"));
                  for(String str : t.keySet())
                  {
                     outfile.println(str);
                     outfile.println(t.get(str));
                  }
                  uname1.setText("");
                  pass1.setText("");
                  signUpFrame.setVisible(false);
                  loginFrame.setVisible(true);
               }
            }
         }
         
         catch(Exception f)
         {
            System.out.print(f.getMessage());
         }
      }
   }
   private class quitListener implements ActionListener
   
   {
      public void actionPerformed(ActionEvent e)
      {
         signUpFrame.setVisible(false);
        
         int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to quit?", "Quit?" , JOptionPane.YES_NO_OPTION);
      
         if (reply == JOptionPane.YES_OPTION) 
         {
            System.exit(0);
         }
         else 
         {
            loginFrame.setVisible(true);
         }
      }
   
   }
}