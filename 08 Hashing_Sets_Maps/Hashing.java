 //mlbillington@fcps.ed, 2-21-2006
 //version 2, 2-28-2007
 //version 3, 3-13-2015
 /*  Assignment:  This hashing program results in collisions.
     You are to implement three different collision schemes: linear 
     probing, rehashing, and chaining.  Then implement a search 
     algorithm that is appropriate for each collision scheme.
 */
import java.util.*;
import javax.swing.*;
public class Hashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  "));//20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));               //15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Rehashing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch (scheme)
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: 
            table = new HashtableRehash(arrayLength);
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
      while( itemNumber != -1 )
      {
         String key = "Item" + itemNumber; 
         table.contains(key);
         itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));                           
      } 
      System.exit(0);
   }
}
/*********************************************/
interface Hashtable
{
   void add(Object obj);
   boolean contains(Object obj);
}
/***************************************************/ 
class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
   public HashtableLinearProbe(int size)
   {
      array = new Object[size];               //constructor
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         array[index] = obj; //assign it
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   public int linearProbe(int index)
   {
      //be sure to wrap around the array.
      while(array[index]!= null)
      {
         if(index==array.length-1)
         {
            index = 0;
         }
         else
         {
            index++;
         }
      }
      return index;
   }
   public boolean contains(Object obj)  
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            System.out.println("Found " + obj + " at index " + index);
            return true;
         }
         else //search for it in a linear probe manner
         {
            while(!array[index].equals(obj))
            {
               if(index==array.length-1)
               {
                  index = 0;
               }
               else
               {
                  index++;
               }
            }
            
         }
      }
      System.out.println(obj + " not found!");
      return false;
   }
}
/*****************************************************/
class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant = 2;
   public HashtableRehash(int size)
   {
      array = new Object[size];                   //constructor
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         array[index] = obj;    //assign it
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   public int rehash(int index)
   {
      constant = 2;
      // find a constant that is relatively prime to the size of the array
      if(array.length%constant == 0)
      {
         for(int x = 0; x < array.length; x++)
         {
            constant++;
            if(isPrime(constant))
            {
               break;
            }
         }
      }
      // rehash
      while(true)
      {
         if(array[index] == null)
         {
            return index;
         }
         else
            index = (index+constant)%array.length;   
      }
   
   }
   public boolean contains(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      String str = (String)array[index];
      String s = (String)obj;
      while(array[index] != null)
      {
         if(array[index]==obj)  //found it
         {
            System.out.println("Found " + obj + " at index " + index);
            return true;
         }
         else //search for it in a rehashing manner
         {
            if(array.length % constant == 0)
            {
               for(int x = 0; x < array.length; x++)
               {
                  constant++;
                  if(isPrime(constant))
                     break;
               }
            }
            for(int x = 0; x < array.length; x++)
            {
               str = (String)array[index];
               if(str.equals(str))
               {
                  System.out.println("Found " + obj + " at index " + index);
                  return true;
               }   
               index = (index+constant)%array.length;
            }            
         }
      }
      System.out.println(obj + " not found!");
      return false;
   }
   boolean isPrime(int n) {
    //check if n is a multiple of 2
      if (n%2==0) 
         return false;
    //if not, then just check the odds
      for(int i=3;i*i<=n;i+=2) {
         if(n%i==0)
            return false;
      }
      return true;
   }

}
/********************************************************/
class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   public HashtableChaining(int size)
   {
      array = new LinkedList[size];                       //constructor
      for(int x = 0; x < size; x++) 
      {
         array[x] = new LinkedList();                       
      }                       
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
   }  
   public boolean contains(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if( !array[index].isEmpty() )
      {
         if(array.length == 0)  //found it
         {
            System.out.println("Found " + obj + " at index " + index);
            return true;
         }
         else //search for it in a chaining manner
         {
            if(array[index].contains(obj))
            {
               System.out.println("Found " + obj + " at index " + index);
               return true;
            }
            else
               return false;
         }
      }
      System.out.println(obj + " not found!");
      return false;
   }
}