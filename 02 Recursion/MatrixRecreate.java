// Name: Anjam Alam  
// Date: 10/25/15

public class MatrixRecreate
{
   public static void main(String[] args)
   {
      int[][] matrix = create();
      int[] rowcount = new int[matrix.length];
      int[] colcount = new int[matrix[0].length];
      count(matrix, rowcount, colcount);
      display(matrix, rowcount, colcount);
      re_create(rowcount, colcount);
   }
   public static int[][] create()
   {
      int x = (int)((Math.random()*5)+2);
      int y = (int)((Math.random()*5)+2);
      int[][]matrix = new int[x][y];
      for(int row = 0; row < matrix.length;row++)
      {
         for(int col = 0; col < matrix[0].length; col++)
         {
            matrix[row][col] = (int)(Math.random()*2);
         }
      }
      return matrix;
   }
   public static void count(int[][] matrix, int[] rowcount, int[] colcount)
   {
      int count = 0;
      int count1= 0;
      int count2 = 0;
      int count3 = 0;
      for(int row = 0; row < matrix.length;row++)
      {
         count1 = 0;
         for(int col = 0; col < matrix[0].length; col++)
         {
            if(matrix[row][col]==1)
            {
               count1++;
               rowcount[count] = count1;
            
            }
         }
         count++;
      }
             
      for(int col = 0; col < matrix[0].length; col++)
      {
         count3 = 0;
         for(int row = 0; row < matrix.length;row++)
            if(matrix[row][col]==1)
               count3++;
         colcount[count2++]=count3;
         
      }
      
      
   } 
  
   public static void display(int[][] matrix, int[] rowcount, int[] colcount)
   {
      System.out.println();
      System.out.print("  ");
      for(int i=0;i<colcount.length;i++)
      {
         System.out.print(colcount[i]);
         
      }
      System.out.println("  ");
      System.out.print("  ");
      for(int x = 0; x< colcount.length;x++)
         System.out.print("-");
      System.out.println();
      for(int i=0;i<rowcount.length;i++)
      {
         System.out.print(rowcount[i]+"|");
         for(int j=0;j<colcount.length;j++)
         {
            System.out.print(matrix[i][j]);
         }
         System.out.println();
      }
   }
   public static void re_create(int[] rowcount, int[] colcount)
   {
      recur(new int[rowcount.length][colcount.length], rowcount, colcount, 0, 0);
   }
   private static void recur(int[][] m, int[] rowcount, int[] colcount, int row, int col) //recursive helper method
   {
      if(compare(m, rowcount, colcount))    //base case: if new matrix works
      {
         display(m, rowcount, colcount);    //we're done!
         System.exit(0);
      }
      else
      {
         if(row >=m.length)
            return;
         int nrow=row;
         int ncol=col+1;
         if(ncol>= m[row].length)
         {
            nrow++;
            ncol=0;
         }
         m[row][col]=1;
         recur(m,rowcount,colcount,nrow,ncol);
         m[row][col]=0;
         recur(m,rowcount,colcount,nrow,ncol);
      }
   }
   private static boolean compare(int[][] m, int[] rowcount, int[] colcount)
   {
      int rowarray[] = new int[rowcount.length];
      int colarray[] = new int[colcount.length];
      count(m,rowarray,colarray);
      for(int x=0;x<rowarray.length;x++)
      {
         if(rowarray[x]!=rowcount[x]) 
            return false;
      }
      for(int y=0;y<colarray.length;y++)
      {
         if(colarray[y]!=colcount[y]) 
            return false;
      }
      return true;
   }
}
