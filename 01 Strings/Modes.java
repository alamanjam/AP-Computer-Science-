	//Name: Anjam Alma
	//Date: 9/9/2015
public class Modes
{
   public static void main(String[] args)
   {
      int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
      display(tally);
      int[] modes = calculateModes(tally);
      display(modes);
      int sum = 0;
      for(int k = 0; k < tally.length; k++)
         sum += tally[k];
      System.out.println("kth \tindex"); 
      for(int k = 1; k <= sum; k++)
         System.out.println(k + "\t\t" + kthDataValue(tally, k));
   }
   public static int[] calculateModes(int[] tally)
   {
      int count = 0;
      
      
      
      int max = findMax(tally);
      for(int x = 0; x<tally.length;x++)
      {
         if(tally[x]==max)
         {
            count++;
         }
      }
      int count1 = 0;
      int []array= new int[count];
      for(int y=0;y<tally.length;y++)
      {
         if(tally[y]==max)
         {
            array[count1]=y;
            count1++;
         }
      }
      
      return array;
   }
   public static int kthDataValue(int[] tally, int k)
   {
      int x = 0;
      int count = tally[0];
      while(count < k)
      { 
         x++;
         count += tally[x];
      }
   
      return x;
   }
   
   public static int findMax(int[] nums)
   {
      int pos = 0;
      for(int k = 1; k < nums.length; k++)
         if(nums[k] > nums[pos])
            pos = k;
      return nums[pos];
   }
   public static void display(int[] args)
   {
      for(int k = 0; k < args.length; k++)
         System.out.print(args[k] + " ");
      System.out.println();
      System.out.println();
   }
}