//Anjam Alam, 10/2/15
import java.util.*;
public class LeftRight
{
   public static void main(String[] args)
   {
   
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      String s = sc.next();
      int n = Integer.parseInt(s);
      
      recursive("", n);
      oddDigits("", n);
      superprime(n);
   }
   public static void recursive(String s, int n)
   {
      if(s.length()==n)
         System.out.println(s);
      else 
      {
         recursive(s + "L", n);
         recursive(s + "R", n);
      }
   }
   public static void oddDigits(String s, int n)
   {
      if(s.length()==n)
         System.out.println(s);
      else 
      {
         oddDigits(s + "1", n);
         oddDigits(s + "3", n);
         oddDigits(s + "5", n);
         oddDigits(s + "7", n);
         oddDigits(s + "9", n);
      }
   
   }
   
	//the SuperPrime methods 
   public static void superprime(int n)
   {
      recur(2, n); //try leading 2, etc.
      recur(3, n); //all the primes < 10
      recur(5, n);
      recur(7, n);
   }
   private static void recur(int k, int n)
    { 
      if (n==1)
      {
         if(isPrime(k))
         {
            System.out.println(k);
            
         }
      }
      else
         if(isPrime(k))
         {
            recur(k*10 + 1,n-1);
            recur(k*10 + 3,n-1);
            recur(k*10 + 5,n-1);
            recur(k*10 + 7,n-1);
            recur(k*10 + 9,n-1);
         }
  }
   private static boolean isPrime(int n)
   {
      if(n==2)
         return true;
      for(int i = 2; i < Math.sqrt(n) + 1; i++)
         if(n % i == 0)
            return false;
      if(n==0||n==1)
         return false;
      return true;
   }   
}
