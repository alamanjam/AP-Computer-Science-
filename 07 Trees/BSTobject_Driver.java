 //name:    date:  
import java.util.*;
import java.io.*;
//////////////////////////////////
interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );     //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );  //returns the object, not a Node<E>
   public int size();
   public String toString();
}
//////////////////////////////////
public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberWidgets = 10;
   public static void main( String[] args ) 
   {
   //day one 
      tree = new BSTobject<String>();
      build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty()); 		
   	//day two
   	//	Your assignment the second day is to finish the BSTobject class.  
   	//	Specifically, prompt the user for a string, put the characters into a BST, 
   	//	call toString on this tree, and print the size of the tree.
      BSTobject<String> tree1 = new BSTobject<String>();
   	System.out.print("Enter a String: ");
      Scanner sc = new Scanner(System.in);
      String s = sc.next();
      build(tree1, s);
      System.out.print(tree1);
   	System.out.println("Size: " + tree1.size());      
      
      //day two, Widgets			
   	//	Next, fill your BST with 10 Widget objects from widgets.txt.  Display the tree. 
   	//	Then prompt the user to enter pounds and ounces.  If the tree contains that 
   	//	Widget, delete it, of course restoring the BST order. Display the new tree 
   	// and its size. If the tree does not contain that Widget, print "Not found".
      treeOfWidgets = new BSTobject<Widget>();
      build(new File("widget.txt"));
      System.out.print(treeOfWidgets);
   	System.out.print("Delete which object? ");
      Widget w = new Widget(sc.nextInt(), sc.nextInt());
      if(treeOfWidgets.contains(w))
      {
      treeOfWidgets.delete(w);
      System.out.print(treeOfWidgets);
      System.out.print("Size: " + treeOfWidgets.size());
      }
      else	
      System.out.print("Not found");
      
   }
   public static void build(BSTobject<String> tree, String str)
   {
     /* your code goes here */
    for(int x = 0; x < str.length(); x++)
    {
    tree.add("" + str.charAt(x));
    }
   }
   public static void build(File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
         System.exit(0);
      }
      Widget w = null;
      for(int i = 0; i < 10; i++)   
      {
      w = new Widget(infile.nextInt(), infile.nextInt());
      treeOfWidgets.add(w);
      }
   }
}
////////////////////////////////
class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
    // 2 fields 
    // 1 default constructor
   private Node<E> root;
   private int size;  
   public BSTobject()
   {
    root = null;
    size = 0;
   }  
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
    //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
        if(t == null)
         return new Node<E>(obj);
      else if(obj.compareTo(t.getValue())<0)
         t.setLeft(add(t.getLeft(), obj));
      else if(obj.compareTo(t.getValue())>0|| obj.compareTo(t.getValue())==0)
         t.setRight(add(t.getRight(), obj));
       return t; 
   }
   /* implement the interface here.  Use TreeNode as an example,
    but root is a field. You need add, contains, isEmpty, 
    delete, size, and toString.  */
   public boolean contains(E node)
   {
   Node<E> current = root;
   while(current != null)
      {
         int compare = node.compareTo(current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false; 
   }
   public boolean isEmpty()
   {
   return size == 0;
   }
   public E delete(E obj)
   {
      Node<E>current = root;
      Node<E>parent = current;
      while(current != null)
      {
         int compare = obj.compareTo(current.getValue());
         if (compare == 0) 
         {
               
            if(current.getLeft() == null && current.getRight() == null) 
            {
               parent.setLeft(null);
               current = null;
               size--;
               return root.getValue();
            }
            if(current.getLeft() != null && current.getRight() == null) {
               current = current.getLeft();
               parent.setLeft(current);
               size--;
               return root.getValue();
            }
            if(current.getLeft() == null && current.getRight() != null)
             {
               current = current.getRight();
               parent.setLeft(current);
               size--;
               return root.getValue();
            }
            if(current.getLeft() != null && current.getRight() != null) 
            {
               if(current.getLeft().getRight() != null)
                {
                  Node<E> temp = current;
                  Node<E> t = current;
                  current = current.getLeft();
                  while(current.getRight() != null) 
                  {
                     temp = current;
                     current = current.getRight();
                  }
                  if(current.getLeft() == null && current.getRight() == null) 
                  {
                     t.setValue(temp.getRight().getValue());
                     temp.setRight(null);
                  }
                  if(current.getLeft() != null)
                   {
                     t.setValue(temp.getRight().getValue());
                     temp.setRight(current.getLeft());
                  }
                  size--;
                  return root.getValue();
               }
               if(current.getLeft().getRight() == null)
               {
                  parent.setLeft(current.getLeft());
                  parent.getLeft().setRight(current.getRight());
                  size--;
                  return root.getValue();
               }
            }
         }
         if (compare < 0) {
            parent = current;
            current = current.getLeft();
         }
         if (compare > 0) {
            parent = current;
            current = current.getRight();
         }
      }
      return root.getValue();  //never reached
   }
   public int size()
   {
   return size;
   }
   private String s;
   public String toString()
      {
        s = "";
         return display(root, 0);
      }
     
      private String display(Node<E> t, int level)
      {
         
         if(t == null)
            return "";
         
         display(t.getRight(), level + 1); //recurse right
         for(int k = 0; k < level; k++)
            s += "\t";
         s += t.getValue().toString() + "" + "\n";
         display(t.getLeft(), level + 1); //recurse left
         return s;
      }
    /***************************private inner class **************/  
   private class Node<E>
   {
      // 3 fields 
         
      // 2 constructors, one-arg and three-arg
      private E value; 
      private Node<E> left, right;
   
      public Node(E initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
      public Node(E initValue, Node<E> initLeft, Node<E> initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
       //methods--Use TreeNode as an example. See the cheat sheet.
      public E getValue()
      { 
         return value; 
      }
   
      public Node<E> getLeft() 
      { 
         return left; 
      }
   
      public Node<E> getRight() 
      { 
         return right; 
      }
   
      public void setValue(E theNewValue) 
      { 
         value = theNewValue; 
      }
   
      public void setLeft(Node<E> theNewLeft) 
      { 
         left = theNewLeft;
      }
   
      public void setRight(Node<E> theNewRight)
      { 
         right = theNewRight;
      }
   
   }
}
