// name:        date: 
import java.util.*;
import java.io.*;
public class Huffman
{
    public static void main(String[] args) throws IOException
   {
   	//Read the string
      //Make a frequency table of the letters
   	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   	//        node into a priority queue (or a min-heap).     
   	//Use the priority queue of nodes to build the Huffman tree
   	//Process the string letter-by-letter and search the tree for the 
   	//       letter.  As you go, build the binary path, where going 
   	//       left is 0 and going right is 1.  
   	//Write the binary path to the hard drive as message.xxx.txt
   	//Write the scheme to the hard drive as scheme.xxx.txt
      Scanner s = new Scanner(System.in);
      System.out.print("Message? ");
      String message = s.nextLine();
      HashMap<Character, Integer> freq = freqTable(message);
      HuffmanTreeNode tree = buildTree(freq);
      System.setOut(new PrintStream(new FileOutputStream("message.xxx.txt")));
      PrintStream outfile = new PrintStream(new FileOutputStream("scheme.xxx.txt"));
      TreeMap<String, Character> scheme = new TreeMap<String, Character>();
      String path = "";
      for(int x = 0; x < message.length(); x++)
      {
         char c = message.charAt(x);
         binaryPath(scheme, tree, c, path);
      }
      for(String p : scheme.keySet())
      {
         outfile.println(scheme.get(p) + p);
      }
   }

   public static HashMap freqTable(String message)
   {
      HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
      for(int x = 0; x < message.length(); x++)
      {
         char c = message.charAt(x);
         if(freq.containsKey(c))
         {
            int f = freq.get(c);
            f++;
            freq.put(c, f);
         }
         else
         {
            freq.put(c, 1); 
         }
      }
      return freq;
   }
   public static HuffmanTreeNode buildTree(HashMap<Character, Integer> freq)
   {
      PriorityQueue<HuffmanTreeNode> queue = new PriorityQueue<HuffmanTreeNode>();
      for(char c : freq.keySet())
      {
         HuffmanTreeNode h = new HuffmanTreeNode(c, freq.get(c));
         queue.add(h);
      }
      while(queue.size() != 1)
      {
         HuffmanTreeNode first = queue.remove();
         HuffmanTreeNode second = queue.remove();
         HuffmanTreeNode third = new HuffmanTreeNode('*', first.getFreq()+ second.getFreq(), first, second);
         queue.add(third);
      }
      return queue.remove();
   }
   public static String binaryPath(TreeMap scheme, HuffmanTreeNode tree, char c, String path) throws FileNotFoundException
   {
      if(tree == null)
         return path;
      if (tree.getLeft()!=null)
      {
         binaryPath(scheme, tree.getLeft(), c, path+"0");
      }
      if (tree.getRight()!=null)   
      {
         binaryPath(scheme, tree.getRight(), c,  path+"1");  
      }
      if (tree.getLeft()==null && tree.getRight()==null && tree.getLetter()==c)
      {
         scheme.put(path, c);
         System.out.print(path);               
      }
      return "";
   }
}
/*
  * This node stores two values.  
  * The compareTo method must ensure that the lowest frequency has the highest priority.
  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private char letter;
   private int freq;
   private HuffmanTreeNode left, right;
   public HuffmanTreeNode(char c, int x)
   {
      letter = c;
      freq = x;
   }
   public HuffmanTreeNode(char c, int x, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      letter = c; 
      freq = x;
      left = initLeft; 
      right = initRight; 
   }
   public int compareTo(HuffmanTreeNode h)
   {
      if(freq < h.getFreq())
         return -1;
      if(freq == h.getFreq())
         return Character.toString(h.getLetter()).compareTo(Character.toString(this.getLetter()));
      else
         return 1;
   }
   public int getFreq()
   {
      return freq;
   }
   public char getLetter()
   {
      return letter;
   }
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }

   public String toString()
   {
      return letter + " : " + freq;
   }
}
