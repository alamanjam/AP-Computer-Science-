import java.util.*;
import java.io.*;
public class McRonald3
{
   private static Queue<Customer> q, q1, q2, q3; 
   public static int longestq, longestwait, wait, newwait, ccount, servicetime1, servicetime2, servicetime3;
   public static double totalwait;
   public static void main(String[] args)
   {
      q = new LinkedList<Customer>();
      q1 = new LinkedList<Customer>();
      q2 = new LinkedList<Customer>();
      q3 = new LinkedList<Customer>();
      servicetime1 = (int)(Math.random()*6)+2;
      servicetime2 = (int)(Math.random()*6)+2;
      servicetime3 = (int)(Math.random()*6)+2;
      for(int x = 1; x <= 1080; x++)
      {
         if(x == 1080 && !q.isEmpty())
         {
            servelast();
         }
         double random = Math.random();   
         if(random < 0.5)
         {
            Customer c = new Customer(x);
            q.add(c);
            ccount++;
         }
            
         if(q.size()>longestq)
            longestq = q.size();
         System.out.println(q);
          
         if(q1.isEmpty()&&!q.isEmpty())
         {
           q1.add(q.remove());
         }
         if(!q1.isEmpty())
         {
            servicetime1--;
         
            if(servicetime1 == 0)
            {
               serve(x, q1);
               q1.remove();
               servicetime1 = (int)(Math.random()*6)+2;
            }
          }
          if(q2.isEmpty()&&!q.isEmpty())
         {
            q2.add(q.remove());
          }
          if(!q2.isEmpty())
         {
            servicetime2--;
            if(servicetime2 == 0)
            {
               serve(x, q2);
               q2.remove();
               servicetime2 = (int)(Math.random()*6)+2;
            }
         }
          if(q3.isEmpty()&&!q.isEmpty())
         {
            q3.add(q.remove());
         }
          if(!q3.isEmpty())
         {
            servicetime3--;
            if(servicetime3 == 0)
            {
               serve(x, q3);
               q3.remove();
               servicetime3 = (int)(Math.random()*6)+2;
            }
         }

      }
      
      
      double avg = totalwait/ccount;
      System.out.println("Total Customers: " + ccount);
      System.out.println("Average Wait Time: " + avg);
      System.out.println("Longest Wait Time: " + longestwait);
      System.out.println("Longest Queue: " + longestq);
   }
   public static void serve(int time, Queue<Customer> q)
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
             servicetime1--;
             if(servicetime1 == 0)
             {
                serve(count, q);
                q.remove();
                servicetime1 = (int)(Math.random()*6)+2;
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
   public int compareTo(Customer c)
   {
   
   }
   public String toString()
   {
      return "" + arrival;
   }
}
