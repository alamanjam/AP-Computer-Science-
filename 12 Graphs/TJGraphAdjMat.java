//name:   date:   
//for use with Graphs0: Intro
//             Graphs1: Wardhall
//             Graphs2: Floyd
import java.util.*;
import java.io.*;

public class TJGraphAdjMat implements AdjacencyMatrix //,Warshall,Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   
   public int size;
   /*  enter your code here  */  
   public TJGraphAdjMat(int s)
   {
      size = s;
      grid = new int[s][s];
      vertices = new TreeMap<String, Integer>();
   }
   public void addEdge(int source, int target)
   {
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target)
   {
      grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to)
   {
      if(grid[from][to]>0 && grid[from][to]<9999)
         return true;
      return false;
   }
   public void displayGrid()
   {
      for(int x = 0; x < grid.length; x++)
      {
         for(int y = 0; y < grid[0].length; y++)
         {
            System.out.print(grid[x][y] + " ");
         }
         System.out.println();
      }
   }
   public int edgeCount()
   {
      int count = 0;
      for(int x = 0; x < grid.length; x++)
      {
         for(int y = 0; y < grid[0].length;y++)
         {
            if(isEdge(x, y))
               count++;
         }
      }
      return count;
   }
   public List<Integer> getNeighbors(int source)
   {
      ArrayList<Integer>neighbors = new ArrayList<Integer>();
      for(int x = source; x < source+1; x++)
      {
         for(int y = 0; y < grid[0].length; y++) 
         {
            if(isEdge(x, y))
               neighbors.add(y);
         }
      }
      return neighbors;
   }
   public boolean isEdge(String from, String to)
   {
      int x = vertices.get(from);
      int y = vertices.get(to);
      return isEdge(x, y);
   }
   public Map<String, Integer> getVertices()
   {
      return vertices;
   }     
   public void readNames(String fileName) throws FileNotFoundException
   {
      Scanner s = new Scanner(new File(fileName));
      int size = s.nextInt();
      for(int x = 0; x < size; x++)
      {
         vertices.put(s.next(), x);
      }
   }
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner s = new Scanner(new File(fileName));
      int size = s.nextInt();
      for(int x = 0; x < size; x++)
      {
         for(int y = 0; y < size; y++)
         {
            grid[x][y]=s.nextInt();
         }
      
      }
   }
   public void displayVertices()
   {
      for(String s : vertices.keySet())
      {
         System.out.println(vertices.get(s) + "-" + s);
      }
      System.out.println();
   }
   public void allPairsReachability()
   {
      int size = grid.length;
      for(int x = 0; x< size; x++)
      {
         for(int y = 0; y< size;y++)
         {
            for(int z = 0; z< size;z++)
            {
               if(isEdge(x, y) && isEdge(y, z))
                  grid[x][z]=1;
            }
         }
      }
   }
   //EXTENSION
   public Set<String> getReachables(String s)
   {
      TreeSet<String>reachables = new TreeSet<String>();
      int index = vertices.get(s);
      List<Integer>neighbors = getNeighbors(index);
      for(int i : neighbors)
      {
         for(String city : vertices.keySet())
         {
            if(vertices.get(city)==i)
               reachables.add(city);
         }
      }
      return reachables;
   }
   public int getCost(int from, int to)
   {
   return grid[from][to];
   }
   public int getCost(String from, String to)
   {
   return grid[vertices.get(from)][vertices.get(to)];
   }
   public void allPairsWeighted()
   {
   int size = grid.length;
      for(int x = 0; x< size; x++)
      {
         for(int y = 0; y< size;y++)
         {
            for(int z = 0; z< size;z++)
            {
                grid[y][z] = Math.min(getCost(y, z), getCost(y, x) +  getCost(x, z));
            }
         }
      }
   }
}

interface AdjacencyMatrix
{
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public void displayGrid();
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   
   
  /**********  User-friendly    **************/
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
 /************* end  User-friendly   **************/
}

interface Warshall
{
   public void allPairsReachability();
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}
