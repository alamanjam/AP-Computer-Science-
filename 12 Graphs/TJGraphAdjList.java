//name:    date:
//for use with Graphs3: EdgeList
//             Graphs4: DFS-BFS
//             Graphs5: EdgeListCities

import java.io.*;
import java.util.*;
/*********************  Graphs 3:  EdgeList *******************************/
interface VertexInterface
{
   public String toString();     //just return the name
   public String getName();
   public ArrayList<Vertex> getAdjacencies();
   public void addEdge(Vertex v);
}  

interface TJGraphAdjListInterface 
{ 
   public List<Vertex> getVertices();
   public Vertex getVertex(int i) ;
   public Vertex getVertex(String vertexName);
   public Map<String, Integer> getVertexMap();
   public void addVertex(String v);
   public void addEdge(String source, String target);
   public String toString();
  
  
   /*********************Graphs 4:  DFS and BFS ****************************/
   public List<Vertex> depthFirstSearch(String name);
   public  List<Vertex> breadthFirstSearch(String name);
   //public  List<Vertex> depthFirstRecur(String name);


   /****************Graphs 5:  EdgeList with Cities  *********/
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   public int edgeCount();
   public boolean isReachable(String source, String target);
   public boolean isConnected();
}
/***********************************************************/
class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
   
   public Vertex(String s)
   {
      name = s;
      adjacencies = new ArrayList<Vertex>();
   }
   public String toString()     //just return the name
   {
      return name;
   }
   public String getName()
   {
      return name;
   }
   public ArrayList<Vertex> getAdjacencies()
   {
      return adjacencies;
   }
   public void addEdge(Vertex v)
   {
      adjacencies.add(v);
   }
}  
/*******************************************************/
public class TJGraphAdjList implements TJGraphAdjListInterface
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   
 /* enter your code here  */
   public List<Vertex> getVertices()
   {
      return vertices;
   }
   public Vertex getVertex(int i)
   {
      String vname = "";
      for(String s : nameToIndex.keySet())
      {
         if(nameToIndex.get(s)==i)
            vname = s;
      }
      return getVertex(vname);
   }
   public Vertex getVertex(String vertexName)
   {
      Vertex vertex = null;
      for(Vertex v : vertices)
      {
         if(v.getName().equals(vertexName))
         {
            vertex = v;
         }
      }
      return vertex;
   }
   public Map<String, Integer> getVertexMap()
   {
      return nameToIndex;
   }
   public void addVertex(String v)
   {
      boolean contains = false;
      Vertex vertex = new Vertex(v);
      for(Vertex vert : vertices)
      {
      if(vert.getName().equals(vertex.getName()))
      contains = true;
      }
      if(contains==false)
      vertices.add(vertex);
      nameToIndex.put(v, vertices.indexOf(vertex));
   }
   public void addEdge(String source, String target)
   {
      for(Vertex v : vertices)
      {
         if(v.getName().equals(source))
         {
            v.addEdge(new Vertex(target));
         }
      }
   }
   public List<Vertex> depthFirstSearch(String name)
   {
      ArrayList<Vertex>reachables = new ArrayList<Vertex>();
      Stack<Vertex> stack = new Stack<Vertex>();
      Vertex v = getVertex(name);
      stack.push(v);  
      while(!stack.isEmpty())
      {
         Vertex ver = stack.pop();
         if(!reachables.contains(ver))
            reachables.add(ver);
         ArrayList<Vertex>list = ver.getAdjacencies();
         for(Vertex vert : list)
         {
            Vertex adj = getVertex(vert.getName());
            if(!reachables.contains(adj))
               stack.push(getVertex(vert.getName()));
         }
      }
      return reachables;
   }
   public  List<Vertex> breadthFirstSearch(String name)
   {
      ArrayList<Vertex>reachables = new ArrayList<Vertex>();
      Queue<Vertex> queue = new LinkedList<Vertex>();
      queue.add(getVertex(name));
      while(!queue.isEmpty())
      {
         Vertex ver = queue.remove();
         if(!reachables.contains(ver))
            reachables.add(ver);
         ArrayList<Vertex>list = ver.getAdjacencies();
         for(Vertex vert : list)
         {
            Vertex adj = getVertex(vert.getName());
            if(!reachables.contains(adj))
               queue.add(getVertex(vert.getName()));
         }
      }
      return reachables;
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException
   {
      Scanner s = new Scanner(new File(fileName));
      while(s.hasNext())
      {
         String city = s.next();
         String edge = s.next();
         addVertex(city);
         addVertex(edge);
         addEdge(city, edge);
      }
   }
   public int edgeCount()
   {
      int count = 0;
      for(Vertex v : vertices)
      {
         for(Vertex vertex : v.getAdjacencies())
         {
            count++;
         }
      }
      return count;
   }
   public boolean isReachable(String source, String target)
   {
      List<Vertex> reachables = depthFirstSearch(source);
      for(Vertex v : reachables)
      {
         if(v.getName().equals(target))
            return true;
      }
      return false;
   }
   public boolean isConnected()
   {
      for(Vertex v : vertices)
      {
         if(depthFirstSearch(v.getName()).size()!=vertices.size()-1)
         return false;
      }
      return true;
   }
   public String toString()
   {
      String s = "";
      for(Vertex v : vertices)
      {
         s += v.getName() + " " + v.getAdjacencies() + '\n';
      }
      return s;
   }
}


