//name:   date: 
//for use with Graphs6: Dijkstra
//             Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
   public final wVertex target;
   public final double weight;
   
   public Edge(wVertex argTarget, double argWeight) {
      target = argTarget;
      weight = argWeight;
   }
   public wVertex getTarget() 
   {
      return target;
   }
   
   public double getWeight() 
   {
      return weight;
   }
   
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;
 
    /*  enter your code here   */ 
   public wVertex(String s)
   {
      name = s;
      adjacencies = new ArrayList<Edge>();
   }
   public String toString()  
   {
      return name;
   } 
   public String getName()
   {
      return name;
   }
   public double getMinDistance()
   {
      return minDistance;
   }
   public void setMinDistance(double m)
   {
      minDistance = m;
   }
   public wVertex getPrevious()         //Graphs 7
   {
      return previous;
   } 
   public void setPrevious(wVertex v)   //Graphs 7
   {
      previous = v;
   }
   public ArrayList<Edge> getAdjacencies()
   {
      return adjacencies;
   }
   public int compareTo(wVertex other)
   {
      if(minDistance < other.getMinDistance())
      {
         return -1;
      }
      return 1;
   }  
}

interface wVertexInterface 
{
   public String toString();
   
   public String getName();
   
   public double getMinDistance();
   
   public void setMinDistance(double m);
   
   public wVertex getPrevious();         //Graphs 7
    
   public void setPrevious(wVertex v);   //Graphs 7
   
   public ArrayList<Edge> getAdjacencies();
   
   public int compareTo(wVertex other);
}


public class TJGraphAdjListWeighted implements TJGraphAdjListWeightedInterface
{
   private ArrayList<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
  
   /*  enter your code here   */ 
   public List<wVertex> getVertices()
   {
      return vertices; 
   }
   public wVertex getVertex(int i)
   {
      String vname = "";
      for(String s : nameToIndex.keySet())
      {
         if(nameToIndex.get(s)==i)
            vname = s;
      }
      return getVertex(vname);
   }
   public wVertex getVertex(String vertexName)
   {
      wVertex vertex = null;
      for(wVertex v : vertices)
      {
         if(v.getName().equals(vertexName))
         {
            vertex = v;
         }
      }
      return vertex;
   }
   public void addVertex(String v)
   {
      boolean contains = false;
      wVertex vertex = new wVertex(v);
      for(wVertex vert : vertices)
      {
         if(vert.getName().equals(vertex.getName()))
            contains = true;
      }
      if(contains==false)
         vertices.add(vertex);
      nameToIndex.put(v, vertices.indexOf(vertex));
   } 
   public void addEdge(String source, String target, double weight)
   {
      for(wVertex v : vertices)
      {
         if(v.getName().equals(source))
         {
            v.getAdjacencies().add(new Edge(new wVertex(target), weight));
         }
      }
   }
   public void minimumWeightPath(String vertexName)   //Dijkstra's
   {
      PriorityQueue<wVertex>pq = new PriorityQueue<wVertex>();
      wVertex source = null;
      for(wVertex w : vertices)
      {
         if(w.getName().equals(vertexName))
         {
            w.setMinDistance(0);
            source = w;
         }
      } 
      pq.add(source);
      while(!pq.isEmpty())
      {
         wVertex wv = pq.remove();
         List<Edge>list = getVertex(wv.getName()).getAdjacencies();
         for(Edge edge : list)
         {
            wVertex target = getVertex(edge.getTarget().getName());
            double weight = wv.getMinDistance() + edge.getWeight();
            double mindistance = target.getMinDistance();
            if(weight < mindistance)
            {
               target.setMinDistance(weight);
               wVertex temp = new wVertex(edge.getTarget().getName());
               temp.setMinDistance(weight);
               pq.add(temp);
               target.setPrevious(getVertex(wv.getName()));
            }
            
            
         }
      }
   }
   public List<wVertex> getShortestPathTo(String vertexName)
   {
      ArrayList<wVertex>path = new ArrayList<wVertex>();
      wVertex vertex = getVertex(vertexName);
      wVertex temp = vertex;
      while(temp.getPrevious()!=null)
      {
         path.add(temp.getPrevious());
         temp = temp.getPrevious();
      }
      path.add(0, vertex);
      Collections.reverse(path);
      return path;
   }
   public List<wVertex> getShortestPathTo(wVertex v)
   {
      return getShortestPathTo(v.getName());
   }
   public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException
   {
      Scanner cities = new Scanner(vertexNames);
      Scanner data = new Scanner(edgeListData);
      TJGraphAdjListWeighted graph = new TJGraphAdjListWeighted();
      int size = cities.nextInt();
      for(int x = 0; x < size; x++)
      {
         graph.addVertex(cities.next());
      }
      while(data.hasNext())
      {
         graph.addEdge(data.next(), data.next(), data.nextDouble());
      }
      return graph;
   }
}    
interface TJGraphAdjListWeightedInterface 
{
   public List<wVertex> getVertices();
   
   public wVertex getVertex(int i);
   
   public wVertex getVertex(String vertexName);
   
   public void addVertex(String v);
   
   public void addEdge(String source, String target, double weight);
     
   public void minimumWeightPath(String vertexName);   //Dijkstra's
   
   /*  Graphs 7 */
   public List<wVertex> getShortestPathTo(String vertexName);
       
   public List<wVertex> getShortestPathTo(wVertex v);
    
   public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException;
 
}