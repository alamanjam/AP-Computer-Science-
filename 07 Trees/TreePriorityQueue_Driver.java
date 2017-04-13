   //name:      date:   
public class TreePriorityQueue_Driver
{
   private static TreePriorityQueue tpq = null;
   public static void main(String[] args)
   {
      tpq = new TreePriorityQueue();
      int[] array = {13, 11, 14, 11, 15, 14, 14};
       //	int[] array = {4, 6, 5, 7}; 
      //  int[] array = {7, 6, 4, 5}; 
         //int[] array = {7, 6, 4, 5, 4, 4};
      //   int[] array = {4, 5, 4, 4, 7, 6, 7, 7};
        
      build( tpq, array );      
      System.out.println("Peek min: " + tpq.peekMin());
      System.out.println("Removing");
      while( !tpq.isEmpty() )
         System.out.println( "     " + tpq.removeMin() );
   }
   public static void build( TreePriorityQueue tpq, int[] array )
   {
      for( int x : array )
         tpq.add(x);
   }
}

class TreePriorityQueue
{
   private TreeNode root;
   
   public TreePriorityQueue()
   {   root = null;  }
   
   //postcondition:  returns true if the priority queue is empty;
   //					  otherwise, returns false
   public boolean isEmpty()
   {  
      return root == null;
   }
   
   //postcondition:  obj has been added to the priority queue
   public void add(Object obj)
   {	
      root = addHelper((Comparable) obj, root);  
   }
   
   //postcondition:  obj has been added to the subtree rooted at t;
   //					  the updated subtree is returned
   private TreeNode addHelper(Comparable obj, TreeNode t)
   {
       if(t == null)
         {
            return new TreeNode(new Item(obj));
         }
         Item item = (Item)t.getValue();  
         int compareValue = item.getData().compareTo(obj);
         if(compareValue == 0)
         {
            item.incrementCount();
         }
         else if(compareValue > 0)
         {
            t.setLeft(addHelper(obj,t.getLeft()));
         }
         else 
         {
            t.setRight(addHelper(obj,t.getRight()));
         }
         return t;
   }
   			
   					
   //precondition:  the priority queue is not empty
   //postcondition:  a data value of smallest priority has been 
   //						removed and returned
   public Object removeMin()
   { 
        TreeNode t = root;
         TreeNode ti = root;
         Object x;
         if(t.getLeft()==null)
         {
            root = t.getRight();
            return t.getValue();
         }
         if(t.getLeft()!=null)
            t = t.getLeft();
         while(t.getLeft()!=null)
         {
            t=t.getLeft();
            ti = ti.getLeft();
         }
         x = t.getValue();
         ti.setLeft(t.getRight());
         return x; 
   }
   
   //precondition:   the priority queue is not empty
   //postcondition: a data value of smallest priority if returned; 
   //					 the priority queue is unchanged
   public Object peekMin()
   {	
        TreeNode current = root;
         while(current.getLeft() != null)
         {
            current = current.getLeft();
         }
         Item item = (Item)current.getValue();
         return item.getData();
   }
   public void display()
   {
      display( root, 0 );
   }
   private void display( TreeNode t, int level )							
   {
   
   }
}
  
class Item
{
   private Comparable data;
   private int count;
   public Item(Comparable d)
   {  
      data = d; 
      count = 1;  
   }
   public void incrementCount()
   {	
      count++; 
   }
   //precondition:  the count of this item is greater than 0;
   public void decrementCount()
   {  
      count--;  
   }
   public int getCount()
   {	
      return count;  
   }
   public Comparable getData()
   {  
      return data;  
   }
   public String toString()
   {
         return "[ " + data + " | " + count + " ]";
   }
}