    /* M.L. Billington, 10/02/2006.
    Uses the helper classes Selection and Insertion. 
	 Students are to write the Selection and Insertion classes.
    */
import java.util.*;
import java.io.*;
public class Sorts
{
   public static void main(String[] args) throws Exception
   {
        //Part 1, for doubles
      int n = (int)(Math.random()*100);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();	
      print(array);
      System.out.println("*************  *************");
      Selection.sort(array);
      //Insertion.sort(array);
      print(array);
         
      	//Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      String[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
      print(arrayStr);
      System.out.println("*************  *************");
      Selection.sort(arrayStr);
        //Insertion.sort(arrayStr);
      print(arrayStr);
   }
   public static void print(double[] a)
   {
         // for(int k = 0; k < a.length; k++)
            // System.out.println(a[k]);
      for(double d : a)
         System.out.println(d);
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object item : papaya)     //for-each
         System.out.println( item );
   }
}
   //*******************************************************************
  //Name:              Date:
  //The Selection class will have methods sort(), findMax() and swap().
  //Three versions of each method will have to be written, to work 
  //for doubles, Strings, and Comparables.
  
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

//**********************************************************
  //Name:              Date:
  //The Insertion class 
  //write enough methods (sort() and shift()) to handle doubles and Comparables.
class Insertion
{
   //doubles
   public static void sort(double[] array)
   {
      for(int x = 1; x < array.length; x++)
      {
         shift(array, x, array[x]);
      }
   }
   private static void shift(double[] array, int index, double value)
   {
      for(int x = index; x >=1; x--)
      {
         if(array[x]<array[x-1])
         {   
            array[x]=array[x-1];
            array[x-1]=value;
         
         }
      }     
   }
   //Comparables
   @SuppressWarnings("unchecked")
    public static void sort(Comparable[] array)
   {
      for(int x = 1; x < array.length; x++)
      {
         shift(array, x, array[x]);
      }
   }
   @SuppressWarnings("unchecked")
   private static void shift(Comparable[] array, int index, Comparable value)
   {
      for(int x = index; x >=1; x--)
      {
         if((array[x].compareTo(array[x-1]))<0)
         {   
            array[x]=array[x-1];
            array[x-1]=value;
         
         }
      }
   }
}