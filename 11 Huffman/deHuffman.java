// Name:              Date:
import java.util.Scanner;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode t = new TreeNode(null);
      TreeNode current = t;
      while(huffLines.hasNextLine())
      {
         current = t;
         String s = huffLines.nextLine();
         char letter = 'l'; 
         for(int x = 0; x < s.length(); x++)
         {
            char c = s.charAt(x);
            if(x == 0)
               letter = c; 
            else if(x == s.length()-1)
            {
               if(c == '0')
                  current.setLeft(new TreeNode(letter, null, null));
               else
                  current.setRight(new TreeNode(letter, null, null));
               current = t;
            } 
            else
            {
               if(c == '0')
               {
                  if(current.getLeft() == null)
                     current.setLeft(new TreeNode(null, null, null));
                  current = current.getLeft();
               }
               else
               {
                  if(current.getRight() == null)
                     current.setRight(new TreeNode(null, null, null));
                  current = current.getRight();
               }
            }
         
         }
      }
      return t;
   }
   public static String dehuff(String text, TreeNode root)
   {
      String boshal = "";
      TreeNode current = root;
      for(int x = 0; x < text.length(); x++)
      {
         char c = text.charAt(x);
         if(current.getValue() != null)
         {
            boshal += current.getValue();
            current = root;
         }
         if(c == '0')
         {
            current = current.getLeft();
         }
         else
         {
            current = current.getRight();
         }
      }
      boshal += current.getValue();
      return boshal;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
