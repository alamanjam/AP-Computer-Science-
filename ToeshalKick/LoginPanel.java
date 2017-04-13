import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class LoginPanel extends JPanel{

   static JFrame loginFrame, menuFrame, signUpFrame, hsFrame, settingsFrame, eogFrame, gameFrame;
   private static Color BACKGROUND = new Color(105, 206, 236);
   private JTextField uname1;
   private JPasswordField pass1;
   private JCheckBox ruser;
   private BufferedImage myImage;
   private Graphics myBuffer;
   private int FRAMEx = 400;
   private int FRAMEy = 450;
   public LoginPanel()
   {
      
      setLayout(new BorderLayout());
   
      JLabel w = new JLabel("ToeshalKick 1.0", SwingConstants.CENTER);
      w.setFont(new Font("Monospaced", Font.BOLD, 40));
      add(w, BorderLayout.NORTH);
      
      JPanel panel1= new JPanel(new GridLayout(1,2));
      add(panel1, BorderLayout.SOUTH);
      
      JPanel panel2 = new JPanel(new BorderLayout());
      add(panel2, BorderLayout.CENTER); 
      
      JPanel panel3 = new JPanel(new GridLayout(6,2));
      panel2.add(panel3, BorderLayout.CENTER);
      JLabel uname = new JLabel("Username: ", SwingConstants.LEFT);
      uname.setFont(new Font("Monospaced", Font.BOLD, 20));
      panel3.add(uname);
      
      Font font1 = new Font("Monospaced", Font.BOLD, 30); 
      
      uname1 = new JTextField(15);
      uname1.setHorizontalAlignment(SwingConstants.LEFT);
      uname1.setDocument(new JTextFieldLimit(15));
      uname1.setFont(font1);
      panel3.add(uname1);
      
      JLabel pass = new JLabel("Password: ", SwingConstants.LEFT);
      pass.setFont(new Font("Monospaced", Font.BOLD, 20));
      panel3.add(pass);
      
      pass1 = new JPasswordField(15);
      pass1.setHorizontalAlignment(SwingConstants.LEFT);
      pass1.setDocument(new JTextFieldLimit(15));
      pass1.setFont(font1);
      panel3.add(pass1);
      
      ruser = new JCheckBox("Remember username?", false);
      ruser.setFocusPainted(false);
      ruser.setFont(new Font("Monospaced", Font.BOLD, 20));
      panel3.add(ruser);
      
      Font f = new Font("Monospaced", Font.BOLD, 20);
      
      JButton signup = new JButton("Sign Up");
      signup.setFocusPainted(false);
      signup.addActionListener(new signupListener());
      signup.setFont(f);
      panel1.add(signup);
      
      JButton login = new JButton("Login");
      login.setFont(f);
      login.setFocusPainted(false);
      login.addActionListener(new loginListener());
      panel1.add(login); 
      
      JButton quit = new JButton("Quit");
      quit.setFont(f);
      quit.setFocusPainted(false);
      quit.addActionListener(new quitListener());
      panel1.add(quit);
       
     
   }
   private class loginListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            Scanner s = new Scanner(new File("passwords.txt"));
            String user = uname1.getText();
            String pass = pass1.getText();
            TreeMap<String, Integer>t = new TreeMap<String, Integer>();
            while(s.hasNext())
            {
               String uname = s.next();
               int password = s.nextInt(); 
               t.put(uname, password);
            }
               
            if(t.containsKey(user))
            {
               if(pass.hashCode() == t.get(user))
               {
                  loginFrame.setVisible(false);
                  if(menuFrame == null)
                  {
                     menuFrame = new JFrame("Main Menu");
                     menuFrame.setVisible(true);
                     menuFrame.setLocation(700,200);
                     menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                     menuFrame.setContentPane(new MenuPanel(loginFrame, menuFrame));//signUp panel needs to know the frames!
                     menuFrame.setSize(400,450);  
                  }            
                  else
                     menuFrame.setVisible(true);
               }
               else
                  JOptionPane.showMessageDialog(null,"Invalid username/password combination. Please try again.");
            }
            else
               JOptionPane.showMessageDialog(null,"Invalid username/password combination. Please try again.");
            pass1.setText("");
         }
         catch(Exception f)
         {
         }
      }
   }
   private class signupListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         loginFrame.setVisible(false);
         if(signUpFrame == null)
         {
            signUpFrame = new JFrame("Signup");
            signUpFrame.setVisible(true);
            signUpFrame.setLocation(700,200);
            signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            signUpFrame.setContentPane(new SignUpPanel(loginFrame, signUpFrame));//signUp panel needs to know the frames!
            signUpFrame.setSize(400,450);  
         }            
         else
            signUpFrame.setVisible(true);
         uname1.setText("");
         pass1.setText("");
      }
   }

   private class quitListener implements ActionListener
   
   {
      public void actionPerformed(ActionEvent e)
      {
         loginFrame.setVisible(false);
        
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
   public static void main(String[] args){//begins everything
      loginFrame = new JFrame("ToeshalKick");
      loginFrame.setSize(400, 450);
      loginFrame.setLocation(700, 200);
      loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      loginFrame.setContentPane(new LoginPanel());
      loginFrame.setVisible(true);
   }
}