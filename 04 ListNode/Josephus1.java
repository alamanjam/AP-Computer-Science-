// name:             date:

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
	
public class Josephus1
{
   public static void main(String[] args) throws FileNotFoundException
   {
      	/* run it first with J_numbers.txt  */	
      Scanner sc = new Scanner(new File("J_numbers.txt"));
      Scanner scnames = new Scanner(new File("J_names.txt"));
      Scanner k = new Scanner(System.in);
      System.out.print("Enter the number of names: ");
      int num = k.nextInt();
      System.out.print("Enter the N: ");
      int N = k.nextInt();
      ListNode list = new ListNode(sc.next(), null); 
      list.setNext(list);  
      for(int x = 0; x < num-1; x++)
      {
         insert(list, sc.next());
      }
      print(list);
      for(int y = 0; y < num-1; y++)
      {
         list = remove(list, N);
         print(list);
      }		
      	/* run it next with J_names.txt  */
      System.out.println("\n ****  Now start all over.  *** \n");
      
      ListNode listnames = new ListNode(scnames.next(), null); 
      listnames.setNext(listnames);
      scnames.next();
      for(int x = 0; x < num-1; x++)
      {
         insert(listnames, scnames.next());
      }
      print(listnames);
      for(int y = 0; y < num-1; y++)
      {
         listnames = remove(listnames, N);
         print(listnames);
      }		
   
       
      
   }
   	
      /* removes the node p after counting n nodes.
   	  */      
   private static ListNode remove(ListNode p, int n)
   {
      for (int i = 0; i < n-2; i++)
      {
         p = p.getNext();
      }
      p.setNext(p.getNext().getNext());
      return p.getNext();
   }
   	
      /* prints the circular linked list.
   	  */      
   public static void print(ListNode p)
   {
      if(p == null)
      {
         System.out.println("[]");
         return;
      }
      else
      {
         ListNode temp = p;
         System.out.print(p.getValue() + " ");
         p = p.getNext();
         while (!(p.equals(temp)))
         {
            if (!(p.getNext().equals(temp)))
               System.out.print(p.getValue() +" ");
            else
               System.out.print(p.getValue());
            p = p.getNext();
         }
         System.out.println();
      }
    
   }
   	
    /* helper method to build the list.  Creates the node, then
       inserts it in the circular linked list.
   	 */  
   private static ListNode insert(ListNode p, Object obj)
   {
      ListNode first = p;
      while (!(p.getNext().equals(first)))
      {
         p = p.getNext();
      }
      ListNode n = new ListNode(obj, first);
      p.setNext(n);
      return first;
   }
   
   	
   	/* replaces the value (the string) at the winning node.
          */	
   private static void replaceAt(ListNode p, Object obj, int pos)
   {
      for (int i = 0; i < pos; i++)
      {
         p = p.getNext();
      }
      p.setValue(obj);
   }
}