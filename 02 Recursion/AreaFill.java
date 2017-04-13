   //name: Banjam Alam 
   //date:
   
import java.util.Scanner;
import java.io.*;
public class AreaFill
{
   public static char[][] grid = null;
   
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Filename: ");
      String filename = sc.next();
      grid = read(filename);
      display(grid);
      System.out.print("\nEnter ROW COL to fill from: ");
      int row = sc.nextInt();
      int col = sc.nextInt(); 
      fill(grid, row, col, grid[row][col]);
      display(grid);
         // Extension
          //int count = fillAndCount(grid, row, col, grid[row][col]);
          //display(grid);
          //System.out.println("count = " + count);
      System.out.println();
      sc.close();
   }
   public static char[][] read(String filename)throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(filename));
      int x = sc.nextInt();
      int y = sc.nextInt();
      char[][]boshal = new char[x][y];
      for(int row = 0; row < x; row++)
      {
         String line = sc.next();
         for(int column = 0; column < y; column++)
         {
            boshal[row][column]= line.charAt(column);
         }
      }
      return boshal;
   }
   public static void display(char[][] g)
   {    
      for(int x = 0; x < g.length; x++)
      {
         for(int y = 0; y < g[0].length; y++)
         {
            System.out.print(g[x][y]);
         }
         System.out.println();
      }      
   }
   public static void fill(char[][] g, int r, int c, char ch) //recursive method
   {      
   
      g[r][c] = '*';
      if(r > 0)
         if(g[r-1][c] == ch)
         {
            g[r-1][c] = '*';
            fill(g, r-1, c, ch);
         }      
      if(r < g.length-1)
         if(g[r+1][c] == ch)
         {
            g[r+1][c] = '*';
            fill(g, r+1, c, ch);
         }  
      if(c > 0)
         if(g[r][c-1] == ch)
         {
            g[r][c-1] = '*';
            fill(g, r, c-1, ch);
         }  
      if(c < g[0].length-1)
         if(g[r][c+1]==ch)
         {
            g[r][c+1] = '*';
            fill(g, r, c+1, ch);
         } 
      return; 
    
   }        
   	// Extension-- count and return the number of asterisks
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
    return 0;
   }
}
