//name:    date:  

import java.io.*;      //the File class
import java.util.*;    //the Scanner class
   
public class Widgets
{
   public static final int numberOfWidgets = 57;
   public static void main(String[] args) throws Exception
   {
      Comparable[] array = input("widget.txt");
      Selection.sort(array);
      print(array);
   }
          	
   public static Comparable[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner( new File(filename) );
      Comparable[] array = new Widget[numberOfWidgets];
      for(int k = 0; k < numberOfWidgets; k++)
      {
         array[k]=new Widget(infile.nextInt(),infile.nextInt());
      }
      infile.close();
      return array;
   
   }
   	  
   public static void print(Object[] mango)
   {
      for(int x = 0; x < mango.length; x++)
         System.out.println(mango[x].toString()); 
   }
}
   /////////////////////////////////////////////////////
	//name:    date:

class Widget implements Comparable<Widget>
{
   private int myPounds, myOunces;
   public Widget()
   {
      myPounds = myOunces = 0;
   }
   public Widget(int x, int y)
   {
      myPounds = x;
      myOunces = y;
   }
   public int getPounds()
   {
      return myPounds;
   }
   public int getOunces()
   {
      return myOunces;
   }
   public void setPounds(int x)
   {
      myPounds = x;
   }
   public void setOunces(int x)
   {
      myOunces = x;
   }
   public int compareTo(Widget w)
   {
      if(myPounds < w.getPounds())
         return -1;
      if(myPounds > w.getPounds())
         return 1;
      if(myOunces < w.myOunces)
         return -1;
      if(myOunces > w.getOunces())
         return 1;
      return 0;
   }
   public boolean equals(Widget w) 
   {
   if((myPounds==w.getPounds())&&(myOunces==w.getOunces()))
   return true;
   else
   return false;
   }
   public String toString()
   {
      return myPounds + " lbs., " + myOunces + " oz.";
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

