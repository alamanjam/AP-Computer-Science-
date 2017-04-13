 //Name:      Date:
 // use for-each loops or iterators, not regular for-loops
import java.io.*;
import java.util.*;
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
      // pre: an array of just int values 
   	// post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer>boshal = new ArrayList<Integer>();
      for(int n : rawNumbers)
      {
         boshal.add(n);
      }
      return boshal;
   }
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String>boshal = new ArrayList<String>();
      for(String n : rawWords)
      {
         boshal.add(n);
      }
      return boshal;
   }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a)
   {
      int count = 0;
      for(int n : a)
      {
         if(n < 0)
            count++;
      }
      return count;
   }
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a)
   {
      double sum = 0;
      for(int n : a)
      {
         sum +=n;
      }
      double avg = sum/a.size();
      return avg;
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
      ListIterator<Integer> lit = a.listIterator();
      while (lit.hasNext()) {
         int str = lit.next();
      
         if (str < 0)
            lit.set(0);    
      }
   
      
      return a;
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
       
     ListIterator<Integer> lit = a.listIterator();
      while (lit.hasNext()) {
         int str = lit.next();
      
         if (str == 0)
            lit.remove();
      }
   
      
      return a;
   
   }
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return ArrayList without duplicate movie titles
		// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList<String> boshal = new ArrayList<String>();
         
      for(String s : a)
      {
         if(!boshal.contains(s))
            boshal.add(s);
      }
      return boshal;
   }
   
}

