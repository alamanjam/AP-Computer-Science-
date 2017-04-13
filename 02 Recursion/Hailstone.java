//Author: Anjam Alam 
//Date:  10/5/15
import java.util.Scanner;
  
public class Hailstone
{
   public static void main(String[] args)
   {
      System.out.println("Hailstone Numbers!");
      System.out.print("Enter the start value: ");
      Scanner sc = new Scanner(System.in);
      int start = sc.nextInt();
      int count = hailstone(start);
      System.out.println(" takes " + count + " steps." );
      int count2 = hailstone(start, 1);
      System.out.println(" takes " + count2 + " steps." );
   }
      //recursive, counts the steps with a variable
   public static int hailstone(int n, int count)
   {
      if(n==1)
         return count;
      else 
      {
         if(n%2==0)
            return hailstone(n/2, count+1);
         else
            return hailstone(3*n+1, count+1);
      }
   }
		//recursive, counts the steps without a variable
   public static int hailstone(int n)
   {
      if(n==1)
         return 1;
      else 
      {
         if(n%2==0)
            return 1 + hailstone(n/2);
         else
            return 1 + hailstone(3*n+1);
      }
   }
}