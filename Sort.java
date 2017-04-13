public class Sort
{
   public static void main(String[] args)
   {
      int n = 10;
      int[] array = new int[n];
      for(int k = 0; k < array.length; k++)
         array[k] = 10-k;	
      print(array);
      array = sort(array);
      print(array);
   }
   public static void print(int[] array)
   {
      for(int theNumber : array )     //doing something to each
         System.out.print(theNumber + " ");
      System.out.println();
   }
   public static int[] sort(int[] intArray)
   {
      int comparisons = 0;
       for (int p = 0; p < intArray.length-1; p++)

          {

              int temp = p;

              for (int q = p+1; q < intArray.length; q++)

                   if (intArray[q] > intArray[temp])
                   {

                        temp = q;
                        comparisons++;
                        }

              swap(intArray, p,temp);
             System.out.println(comparisons);
          }
         return intArray;
   }
   private static void swap(int[] array, int a, int b)
   {
      int temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
}