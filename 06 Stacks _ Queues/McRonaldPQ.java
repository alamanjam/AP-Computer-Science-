import java.util.*;
import java.io.*;
public class McRonaldPQ
{
   private static PriorityQueue<Customer> q;
   private static Queue<Customer> q1, q2, q3; 
   public static int servicetime1, servicetime2, servicetime3, scount, fcount, socount, jcount, wait, longestfwait, longestswait, longestsowait, longestjwait;
   public static double totalswait, totalsowait, totaljwait, totalfwait;
   public static void main(String[] args)
   {
      q = new PriorityQueue<Customer>();
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
         String grad = "";
         if(random < 0.25)
         {
            double gradrandom = Math.random();
            if(gradrandom < 0.25)
            {
               grad = "freshman";
               fcount++;
            }
            else if(gradrandom > 0.25 && gradrandom < 0.5)
            {
               grad = "sophomore";
               socount++;
            }
            else if(gradrandom > 0.50 && gradrandom < 0.75)
            {
               grad = "junior";
               jcount++;
            }
            else if(gradrandom > 0.75)
            {
               grad = "senior";
               scount++;
            }
         }   
         Customer c = new Customer(x, grad);
         q.add(c);
          
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
      
      
      double favg = totalfwait/fcount;
      double soavg = totalsowait/socount;
      double javg = totaljwait/jcount;
      double savg = totalswait/scount;
      System.out.println("Seniors: ");
      System.out.println("Total Served: " + scount);
      System.out.println("Average Wait Time: " + savg);
      System.out.println("Longest Wait Time: " + longestswait);
      System.out.println("Juniors: ");
      System.out.println("Total Served: " + jcount);
      System.out.println("Average Wait Time: " + javg);
      System.out.println("Longest Wait Time: " + longestjwait);
      System.out.println("Sophomores: ");
      System.out.println("Total Served: " + socount);
      System.out.println("Average Wait Time: " + soavg);
      System.out.println("Longest Wait Time: " + longestsowait);
      System.out.println("Freshmen: ");
      System.out.println("Total Served: " + fcount);
      System.out.println("Average Wait Time: " + favg);
      System.out.println("Longest Wait Time: " + longestfwait);
   }
   public static void serve(int time, Queue<Customer> q)
   {
      Customer c = q.peek();
      if(c.getGrad() == "freshman")
      {
         wait =  time - c.getArrival();
         totalfwait+= wait;
         if(wait > longestfwait)
            longestfwait = wait;
      }
      if(c.getGrad() == "sophomore")
      {
         wait =  time - c.getArrival();
         totalsowait+= wait;
         if(wait > longestsowait)
            longestsowait = wait;
      }
      if(c.getGrad() == "junior")
      {
         wait =  time - c.getArrival();
         totaljwait+= wait;
         if(wait > longestjwait)
            longestjwait = wait;
      }
      if(c.getGrad() == "senior")
      {
         wait =  time - c.getArrival();
         totalswait+= wait;
         if(wait > longestswait)
            longestswait = wait;
      }
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
          //if(!q.isEmpty())
             //System.out.println(q);
      }
   }
}
class Customer implements Comparable<Customer>
{
   int arrival;
   String grad;
   public Customer(int x, String g)
   {
      arrival = x;
      grad = g;
   }
   public int getArrival()
   {
      return arrival;
   }
   public void setArrival(int a)
   {
      arrival = a;
   }
   public String getGrad()
   {
      return grad;
   }
   public int compareTo(Customer c)
   {
      if(c.getGrad() == grad)
      {
         return 0;
      }
     
      else if(c.getGrad() == "senior")
      {
         return 1;
      }
     
      else if(c.getGrad() == "junior")
      {
         if(grad == "senior")
         {
            return -1;
         }
         
         else
         {
            return 1;
         }
      }
     
      else if(c.getGrad() == "sophomore")
      {
         if(grad == "freshman")
         {
            return 1;
         }
         else
         {
            return -1;
         }
      }
     
      else
      {
         return -1;
      }

   }
   public String toString()
   {
      return "" + arrival;
   }
}
