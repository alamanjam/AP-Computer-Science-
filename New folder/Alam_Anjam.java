2S, import java.io.*;
import java.util.*;
public class Alam_Anjam
{
   public static String[][] matrix;
   public static String[] array;
   public static String num;
   public static void main(String [] args) throws FileNotFoundException
   {
      Scanner s = new Scanner(new File("input.txt"));
      
      while(s.hasNextLine())
      {
         String line = s.nextLine();
         if(line.isEmpty())
            break;
            
         matrix = new String[6][6];
         String [][]m = new String[4][4];
         num = line.substring(0, 3);
         array = line.substring(3).split(", ");
         for(int x = 0; x < array.length; x++)
         {
         array[x] = array[x].trim();
         }
         for(int x = 0; x < 4; x++)
         {
            int num = Integer.parseInt(array[x]);
            int row = num/6;
            int y = row*6;
            row--;
            int col = num - y - 2; 
            m[row][col] = "+";
         }
         int num = Integer.parseInt(array[4]);
         int end = (num * 2) + 5;
         for(int x = 5; x < end; x += 2)
         {
            
            String letter = array[x];
            int row = (Integer.parseInt(array[x + 1]))/6;
            int y = 6*row;
            int col = ((Integer.parseInt(array[x + 1])) - y) - 1; 
            if(col < 0)
            {
               col = 5;
               row--;
            }
            
            matrix[row][col] = letter;
         } 
         abc(matrix, m);
      }
   }
   public static void abc(String [][] m, String [][]m1)
   {
      int row = 0;
      int col = 0;
      for(int x = 0; x < m.length; x++)
      {
         for(int y = 0; y < m[0].length;y++)
         {
            if(m[x][y]!="+"&&m[x][y]!=null)
            {
               if(x == 0)
               {
                  row = 0;
                  if(y <= 4)
                     col = y - 1;
                  else
                     col = y;
                  if(m1[row][col] != null)
                  {
                     while(m1[row][col] != null)
                     {
                        if(m[x][y].equals(m1[row][col]))
                           break;
                        row++;
                     }
                  }
                  
               }
               if(x == 1)
               {
                  row = 0;
                  if(y == 0)
                     col = y;
                  else
                     col = y - 2;
                  if(m1[row][col] != null)
                  {
                     while(m1[row][col] != null)
                     {
                        if(m[x][y].equals(m1[row][col]))
                           break;
                        col++;
                     }
                  }
               
               }
               if(x == 2)
               {
                  row = 1;
                  if(y == 0)
                  {
                     col = y;
                     if(m1[row][col] != null)
                     {
                        while(m1[row][col] != null)
                        {
                           if(m[x][y].equals(m1[row][col]))
                              break;
                           col++;
                        }
                     }
                  }
                  else
                  {
                     col = y - 2;
                     if(m1[row][col] != null)
                     {
                        while(m1[row][col] != null)
                        {
                           if(m[x][y].equals(m1[row][col]))
                              break;
                           col--;
                        }
                     }
                  }
               
               }
               if(x == 3)
               {
                  row = 2;
                  if(y == 0)
                  {
                     col = y;
                     if(m1[row][col] != null)
                     {
                        while(m1[row][col] != null)
                        {
                           if(m[x][y].equals(m1[row][col]))
                              break;
                           col++;
                        }
                     }
                  }
                  else
                  {
                     col = y - 2;
                     if(m1[row][col] != null)
                     {
                        while(m1[row][col] != null)
                        {
                           if(m[x][y].equals(m1[row][col]))
                              break;
                           col--;
                        }
                     }
                  }
               
               }
               if(x == 4)
               {
                  row = 3;
                  if(y == 0)
                  {
                     col = y;
                     if(m1[row][col] != null)
                     {
                        while(m1[row][col] != null)
                        {
                           if(m[x][y].equals(m1[row][col]))
                              break;
                           col++;
                        }
                     }
                  }
                  else
                  {
                     col = y - 2;
                     if(m1[row][col] != null)
                     {
                        while(m1[row][col] != null)
                        {
                           if(m[x][y].equals(m1[row][col]))
                              break;
                           col--;
                        }
                     }
                  }
               }
               if(x == 5)
               {
                  row = 3;
                  col = y-1;
                  if(m1[row][col] != null)
                  {
                     while(m1[row][col] != null)
                     {
                        if(m[x][y].equals(m1[row][col]))
                           break;
                        row--;
                     }
                  }
               }
               m1[row][col]=m[x][y];
            }  
         }
      }
      abc1(m1);
   }
   public static void abc1(String[][]m)
   {
      boolean done = false;
      String [][]temp = new String[4][4];
      while(done==false)
      {
         for(int i = 0; i< m.length; i++){
            for (int j = 0; j < m[0].length; j++){
               temp[i][j] = m[i][j];
            }
         }
         for(int x = 0; x < temp.length; x++)
         {
            for(int y = 0; y < temp[0].length; y++)
            {
               if(temp[x][y]==null)
               {
                  String letter = "";
                  int rand = (int)(Math.random()*3+1);
                  if(rand == 1)
                     letter = "A";
                  if(rand == 2)
                     letter = "B";
                  if(rand == 3)
                     letter = "C";
                  temp[x][y] = letter;
               }
            }
         }
         boolean done1 = true;
         for (int row = 0; row < temp.length; row++)
         {
            for (int col = 0; col < temp[row].length; col++)
            {
               String s = temp[row][col];
               for (int otherCol = col + 1; otherCol < temp.length; otherCol++)
               {
                  if (s.equals(temp[row][otherCol]))
                  {
                     done1=false;
                  }
               }
            }
         }
         if(done1==true)
         {
            for (int col = 0; col < temp[0].length; col++)
            {
               for (int row = 0; row < temp.length; row++)
               {
                  String s = temp[row][col];
                  for (int otherRow = row + 1; otherRow < temp.length; otherRow++)
                  {
                     if (s.equals(temp[otherRow][col]))
                     {
                        done1=false;
                     }
                  }
               }
            }
         
         }
         if(done1 == true)
            done = true;
      }
      m = temp;
      System.out.print(num);
      for(int x = 0; x < m.length; x++)
      {
         for(int y = 0; y < m[0].length; y++)
         {
         if(!m[x][y].equals("+"))
         System.out.print(m[x][y]);
         }
      }
      System.out.println();
   }
}