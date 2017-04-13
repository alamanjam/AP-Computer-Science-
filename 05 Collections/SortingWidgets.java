	//This code uses raw arrays.  Change it to use ArrayList<E> instead.

   import java.io.*;      //the File class
   import java.util.*;    //the Scanner class
   import java.io.*;      //the File class
   import java.util.*;    //ArrayList & the Scanner class in Java 1.5
     
    public class SortingWidgets
   {
       public static void main(String[] args) throws Exception
      {
         ArrayList<Comparable> apple= input("widget.txt");  
         sort(apple);
         output(apple);
      }
       public static ArrayList<Comparable> input(String filename) throws Exception
      {
         Scanner infile = new Scanner( new File(filename) );
         ArrayList<Comparable> list = new ArrayList<Comparable>();
         int count = 0;
         while(infile.hasNext())    		    // read all data in the file
         {			
         					  
            int x = infile.nextInt();
            int y = infile.nextInt();
            list.add(count, new Widget(x, y));
            count++;
         }
         infile.close();
         return list;
      }
       public static void sort(ArrayList<Comparable> list)
      {
         int maxPos;
         for(int k = 0; k < list.size(); k++)		
         {
            maxPos = findMax(list, list.size() - k - 1);
            swap(list, maxPos, list.size() - k - 1);
         }
      }
       public static int findMax(ArrayList<Comparable> list, int upper)
      {
         int maxPos = 0;
         for(int j = 1; j <= upper; j++)			
            if(list.get(j).compareTo(list.get(maxPos)) > 0)	
               maxPos = j;					
         return maxPos;
      }
       public static void swap(ArrayList<Comparable> list, int a, int b)
      {
         Comparable temp = list.get(a);				
         list.set(a,list.get(b))  ;
         list.set(b, temp);
      }
       public static void output(ArrayList<Comparable> list)
      {
         for(int k = 0; k < list.size(); k++)		//use the for-each loop
            System.out.println(list.get(k));
      }
   }

///////////////////////////////////////////////////////////////

    class Widget implements Comparable<Widget>
   {
   	//data fields
      private String myName;
      private int myPounds, myOunces;
   
   	//constructors
       public Widget()
      {
         myPounds = myOunces = 0;
      }
       public Widget(int x)
      {
         myPounds = x;
         myOunces = 0;
      }
       public Widget(int x, int y)
      {
         myPounds = x;
         myOunces = y;
      }
       public Widget(Widget arg)
      {
         myPounds = arg.getPounds();
         myOunces = arg.getOunces();
      }
   
   	//accessors and modifiers
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
   
   	//other methods
       public int compareTo(Widget w)
      {
        // Widget w = (Widget)arg;     no need to cast
         if(myPounds < w.getPounds())
            return -1;
         if(myPounds > w.getPounds())
            return 1;
         if(myOunces < w.getOunces())
            return -1;
         if(myOunces > w.getOunces())
            return 1;
         return 0;
      }
       public boolean equals(Widget arg)
      {
         return compareTo(arg) == 0;
      }
       public String toString()
      {
         return myPounds + " lbs. " + myOunces + " oz.";
      }
   }