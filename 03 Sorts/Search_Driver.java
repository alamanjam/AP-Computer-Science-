	//name:    date:
import java.io.*;      //the File 
import java.util.*;    //the Scanner 
import javax.swing.*;  //the JOptionPane
public class Search_Driver  {
   public static void main(String[] args) throws Exception
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter filename: "); 
      String filename = sc.next(); 
      String[] array = input(filename); 
      System.out.print("Enter a word: ");
      String word = sc.next();
      int linear = Searches.linear(array, word);
      int binary = Searches.binary(array, word);
      int linearCount = Searches.linearCount();
      int binaryCount = Searches.binaryCount();
      System.out.println("Linear Search found it at location " + linear + " in " + linearCount+ " comparisons.");
      System.out.println("Binary Search found it at location " + binary + " in " + binaryCount+ " comparisons.");
   
   }
   public static String[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner(new File(filename));
      String[] array = new String[1325];
      for(int k = 0; k < array.length; k++)
         array[k] = infile.next();   
      Selection.sort(array);
      return array;
   }

  
}
	/////////////////////////////////////////////////////////
class Searches
{
   public static int linearCount = 0;
   private static int binaryCount = 0;  
   public static int linearCount()
   {
      return linearCount;
   }    
   public static int binaryCount()
   {
      return binaryCount;
   }
   @SuppressWarnings("unchecked")
   public static int linear(Comparable[] array, Comparable target)
   { 
      for(int x = 0; x< array.length;x++)
      {
         linearCount++;
         if(array[x].compareTo(target)==0)
         {
            return x;  
         }
      }
      return -1;
   }
   @SuppressWarnings("unchecked")
   public static int binary(Comparable[] array, Comparable target)
   {
      int binary = binaryhelper(array, target, 0, array.length-1);
      return binary;
   }
   @SuppressWarnings("unchecked")
   private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
   {
      if(start > end)
      {
         return -1; 
      }
      int compare = (start+end)/2;
      binaryCount +=1;
      if(array[compare].compareTo(target)==0)
      {
         return compare;
      }
      if(array[compare].compareTo(target)>0)
      {
         return binaryhelper( array,target,start, compare-1);
      }
      
      return binaryhelper(array, target,compare+1, end);
   
   }
}
class Selection
{
   public static void sort(double[] array)
   { 
      int maxPos;
      for(int k = 0; k < array.length; k++)
      {
         maxPos = findMax(array, array.length - k);
         swap(array, maxPos, array.length - k - 1);
      }
   }
   private static int findMax(double[] array, int n)
   {
      int index=0;
      for(int x=1 ;x<n;x++)
      {
         if(array[index]<array[x])
         {
            index=x;
         }
      }
      return index;
        
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp=array[b];
      array[b]=array[a];
      array[a]=temp;
   }
   	/***************************************************
   	  for Strings
   	  ***********************************************/
   public static void sort(String[] array)   
   {
      int maxPos;
      for(int k = 0; k < array.length; k++)
      {
         maxPos = findMax(array, array.length - k);
         swap(array, maxPos, array.length - k - 1);
      }
   
   }
   public static int findMax(String[] array, int upper)
   {
      int index=0;
      for(int x=0 ;x<upper;x++)
      {
         if(array[index].compareTo(array[x])<0)
         {
            index=x;
         }
      }
      
      return index;
   
   }
   public static void swap(String[] array, int a, int b)
   {
      String temp= array[a];
      array[a]=array[b];
      array[b]=temp;
   }
   	/***************************************************
   	 for Comparables,
   	      Swap() is for Objects.
   	      make sure that print() is for Objects, too.
   	  ***********************************************/
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
       public static void sort(Comparable[] array)
   {
      int maxPos;
      for(int k = 0; k < array.length; k++)
      {
         maxPos = findMax(array, array.length - k);
         swap(array, maxPos, array.length - k - 1);
      }
   }
   @SuppressWarnings("unchecked")
       public static int findMax(Comparable[] array, int upper)
   {
      int index=0;
      for(int x=0 ;x<upper;x++)
      {
         if(array[index].compareTo(array[x])<0)
         {
            index=x;
         }
      }
      
      return index;
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp= array[a];
      array[a]=array[b];
      array[b]=temp;
   }
}   

