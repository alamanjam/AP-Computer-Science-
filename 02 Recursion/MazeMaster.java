//name: Anjam Alam   date: 11/3/15
import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename: ");
      Maze m = new Maze(sc.next());
      m.display();
      m.solve();
      m.display();  
   } 
}
 

class Maze
{
   private final char wall = 'W';
   private final char path = '.';
   private final char start = 'S';
   private final char exit = 'E';
   private final char step = '*';
   private char[][] maze;
   private int startRow, startCol;
   private boolean S_Exists=false, E_Exists=false;

   public Maze(String filename)  
   {
      Scanner infile = null;
      try{
         infile = new Scanner(new File(filename + ".txt"));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
         System.exit(0);
      }
      //read the file
      int x = infile.nextInt();
      int y = infile.nextInt();
      maze = new char[x][y];
      for(int row = 0; row < maze.length;row++)
      {
         String line = infile.next();
         for(int col = 0; col < maze[0].length; col++)
         {
            maze[row][col] = line.charAt(col);
            if(maze[row][col]==start)
            {
               S_Exists=true;
               startRow = row;
               startCol = col;
            }
            if(maze[row][col]==exit)
            {
               E_Exists=true;
            }
         }
      }
   }
   
   public void display()
   {
      for(int x = 0; x < maze.length; x++)
      {
         for(int y = 0; y < maze[0].length; y++)
         {
            System.out.print(maze[x][y]);
         }
         System.out.println();
      }      
      System.out.println();
      System.out.println();
   }
   public void solve()
   {
      /*  1  */
     //solveAndMark(startRow, startCol);
      
      /*  2  */
      //int count = solveAndCount(startRow, startCol);
      //System.out.println("Number of steps = " + count);
      
      /*  3  */
      markThePath(startRow, startCol);
      
      /*  4  */
      if( !markThePath(startRow, startCol) )
         System.out.println("No solution");   
              
      /*  5  */
    // markThePathAndCount(startRow, startCol, 0);
      
   }
   private void solveAndMark(int r, int c)
   {
      if(S_Exists==false || E_Exists==false)
      {
         System.out.print("Maze cannot be solved");
         System.exit(0);
      }
         
      if(r > 0)
         if(maze[r-1][c] == exit)
         {
            return;
         }      
      if(r < maze.length-1)
         if(maze[r+1][c] == exit)
         {
            return;
         }  
      if(c > 0)
         if(maze[r][c-1] == exit)
         {
            return;
         }  
      if(c < maze[0].length-1)
         if(maze[r][c+1]==exit)
         {
            return;
         } 
   
      if(r > 0)
         if(maze[r-1][c] == path)
         {
            maze[r-1][c] = step;
            solveAndMark(r-1, c);
         }      
      if(r < maze.length-1)
         if(maze[r+1][c] == path)
         {
            maze[r+1][c] = step;
            solveAndMark(r+1, c);
         }  
      if(c > 0)
         if(maze[r][c-1] == path)
         {
            maze[r][c-1] = step;
            solveAndMark(r, c-1);
         }  
      if(c < maze[0].length-1)
         if(maze[r][c+1]==path)
         {
            maze[r][c+1] = step;
            solveAndMark(r, c+1);
         } 
         
      return; 
    
   
   }
        
   private int solveAndCount(int r, int c)
   {
      if(S_Exists==false || E_Exists==false)
      {
         System.out.print("Maze cannot be solved");
         System.exit(0);
      }
      if(r > 0)
         if(maze[r-1][c] == exit)
         {
            return 1;
         }      
      if(r < maze.length-1)
         if(maze[r+1][c] == exit)
         {
            return 1;
         }  
      if(c > 0)
         if(maze[r][c-1] == exit)
         {
            return 1;
         }  
      if(c < maze[0].length-1)
         if(maze[r][c+1]==exit)
         {
            return 1;
         } 
      maze[r][c]=step;
      if(r > 0)
         if(maze[r-1][c] == path)
         {
            maze[r-1][c]=step;
            return 1 + solveAndCount(r-1, c);
         }      
      if(r < maze.length-1)
         if(maze[r+1][c] == path)
         {
            maze[r+1][c]=step;
            return 1 + solveAndCount(r+1, c);
         }  
      if(c > 0)
         if(maze[r][c-1] == path)
         {
            maze[r][c-1]=step;
            return 1 + solveAndCount(r, c-1);
         }  
      if(c < maze[0].length-1)
         if(maze[r][c+1]==path)
         {
            maze[r][c+1]=step;
            return 1 +  solveAndCount(r, c+1);
         }  
      return 1;
   }

   private boolean markThePath(int r, int c)
   {
      if(S_Exists==false || E_Exists==false)
      {
         System.out.print("Maze cannot be solved");
         System.exit(0);
      }
      if(c<0||c>maze[0].length-1||r<0||r>maze.length-1)
         return false;
      else if(maze[r][c]==exit)
         return true; 
      else if(maze[r][c]==wall)
         return false;
      else if(maze[r][c]==step) 
         return false; 
      
      else
      {
         maze[r][c]=step;
         if(markThePath(r-1, c)==true||markThePath(r+1, c)==true||markThePath(r, c+1)==true||markThePath(r, c-1)==true)
         {
         
            return true;
         }
         maze[r][c]=path;
         maze[startRow][startCol]=start;
         return false;
      }
   }
   private boolean markThePathAndCount(int r, int c, int count)
   {
      if(S_Exists==false || E_Exists==false)
      {
         System.out.print("Maze cannot be solved");
         System.exit(0);
      }
      if(c<0||c>maze[0].length-1||r<0||r>maze.length-1)
         return false;
      else if(maze[r][c]==exit)
      {
         System.out.println("Number of steps: " + count);
         return true; 
      }
      else if(maze[r][c]==wall)
         return false;
      else if(maze[r][c]==step) 
         return false; 
      
      else
      {
         maze[r][c]=step;
         if(markThePathAndCount(r-1, c, count+1)==true||markThePathAndCount(r+1, c, count+1)==true||markThePathAndCount(r, c+1, count+1)==true||markThePathAndCount(r, c-1, count+1)==true)
         {
            return true;
         }
         maze[r][c]=path;
         maze[startRow][startCol]=start;
         return false;
      }
   }

}

