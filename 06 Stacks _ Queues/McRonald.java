import java.util.*;
import java.io.*;
public class McRonald
{
   private static Queue<Customer> q; 
   public static int longestq, longestwait, wait, newwait, ccount, servicetime;
   public static double totalwait;
   public static void main(String[] args)
   {
      q = new LinkedList<Customer>();
      servicetime = (int)(Math.random()*6)+2;
      for(int x = 1; x <= 1080; x++)
      {
         if(x == 1080 && !q.isEmpty())
         {
            servelast();
         }
         int random = (int)(Math.random()*5)+1;   
         if(random == 5)
         {
            Customer c = new Customer(x);
            q.add(c);
            ccount++;
         }
         if(q.size()>longestq)
         longestq = q.size();
         System.out.println(q);
          
         if(!q.isEmpty())
         {
            servicetime--;
            if(servicetime == 0)
            {
               serve(x);
               q.remove();
               servicetime = (int)(Math.random()*6)+2;
            }
         }
      }
      
      
      double avg = totalwait/ccount;
      System.out.println("Total Customers: " + ccount);
      System.out.println("Average Wait Time: " + avg);
      System.out.println("Longest Wait Time: " + longestwait);
      System.out.println("Longest Queue: " + longestq);
   }
   public static void serve(int time)
   {
      Customer c = q.peek();
      wait =  time - c.getArrival();
      totalwait+= wait; 
      if(wait > longestwait)
         longestwait = wait;
         }
   public static void servelast()
   {
   int count = 1080;
      while(!q.isEmpty())
      {
          if(!q.isEmpty())
         {
            servicetime--;
            if(servicetime == 0)
            {
               serve(count);
               q.remove();
               servicetime = (int)(Math.random()*6)+2;
            }
         
         }
         count++;
         if(!q.isEmpty())
         System.out.println(q);
      }
   }
   
}
 class Customer
{
   int arrival;
   public Customer(int x)
   {
      arrival = x;
   }
   public int getArrival()
   {
      return arrival;
   }
   public void setArrival(int a)
   {
      arrival = a;
   }
   public String toString()
   {
      return "" + arrival;
   }
}