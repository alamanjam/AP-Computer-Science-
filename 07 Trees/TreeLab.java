//name:    date:  
import java.util.*;         //for the queue interface
public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop"; 
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      display(root, 0);
      
      System.out.print("\nPreorder: ");
      preorderTraverse(root);
      System.out.print("\nInorder: ");
      inorderTraverse(root);
      System.out.print("\nPostorder: ");
      postorderTraverse(root);
      
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Grandparents = " + countGrands(root));
      System.out.println("Only childs = " + countOnlys(root));	
      
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Width = " + width(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
      
      System.out.println("\nBy Level: ");
      displayLevelOrder(root);
   }
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
   
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   public static void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
      display(t.getLeft(), level + 1); //recurse left
   }
   public static void preorderTraverse(TreeNode t)
   {
      if(t == null)
         return;
      System.out.print(t.getValue() + " ");  //preorder visit
      preorderTraverse(t.getLeft());         //recurse left
      preorderTraverse(t.getRight());        //recurse right
   }
   public static void inorderTraverse(TreeNode t)
   {
      if(t == null)
         return; 
         
      inorderTraverse(t.getLeft());    //recurse left
      System.out.print(t.getValue()+ " ");  //inorder visit
      inorderTraverse(t.getRight());   //recurse right
   }
   public static void postorderTraverse(TreeNode t)
   {
      if(t == null)
         return;  
      postorderTraverse(t.getLeft());         //recurse left
      postorderTraverse(t.getRight());        //recurse right      
      System.out.print(t.getValue() + " ");  //postorder visit
      
   }
   public static int countNodes(TreeNode t)
   {
      int count = 0;
      if(t==null)
         return 0;
      else
         count += 1 + countNodes(t.getLeft())+ countNodes(t.getRight());
      return count;
   }
   public static int countLeaves(TreeNode t)
   {
      if(t == null) 
         return 0; 
      if(t.getLeft() == null && t.getRight() == null) 
         return 1;
      return countLeaves(t.getRight()) + countLeaves(t.getLeft());
   }
   public static int countGrands(TreeNode t)
   {
      if(t == null) 
         return 0;  
      int count = 0; 
      count += countGrands(t.getLeft()); 
      if(height(t) >= 2) 
      {
         count++; 
      }
      count += countGrands(t.getRight()); 
      return count;
   }
   public static int countOnlys(TreeNode t)
   {
      int count = 0; 
      if(t == null) 
      {
         return 0;    
      }
      
      count += countOnlys(t.getLeft()); 
      if((t.getLeft() == null && t.getRight() != null) || (t.getLeft() != null && t.getRight() == null)) 
      {
         count++; 
      }
      count += countOnlys(t.getRight()); 
      return count; 
   }
   public static int height(TreeNode t)
   {
      if(t == null)
      {
         return -1;
      }
      int left = height(t.getLeft());
      int right = height(t.getRight());
      return  1 + Math.max(left, right);
   }
      /* "width" is the longest path from leaf to leaf */
   public static int width(TreeNode t)
   {
      if(t == null)
      {
         return 0;
      }
      int h = height(t.getLeft()) + height(t.getRight());
      int left = width(t.getLeft());
      int right =  width(t.getRight());
               
      return Math.max(Math.max(left,right), h);
   }
   public static Object min(TreeNode t)
   {
      if(t == null) 
         return null; 
      Comparable min = (Comparable)t.getValue(); 
      Comparable left = (Comparable)min(t.getLeft()); 
      if(left != null) 
      {
         if(min.compareTo(left) > 0) 
            min = left; 
      }
      Comparable right = (Comparable)min(t.getRight()); 
      if(right != null) 
      {
         if(min.compareTo(right) > 0) 
            min = right; 
      }
      return min; 
   }
   public static Object max(TreeNode t)
   {
      if(t == null) 
      {
         return null; 
      }
      Comparable max = (Comparable)t.getValue(); 
      Comparable left = (Comparable)max(t.getLeft()); 
      if(left != null) 
      {
         if(max.compareTo(left) < 0) 
         {
            max = left; 
         }
      }
      Comparable right = (Comparable)max(t.getRight()); 
      if(right != null) 
      {
         if(max.compareTo(right) < 0) 
         {
            max = right; 
         }
      }
      return max; 
   }
      /* this method is not recursive.  Use a local queue
   	to store the children of the current node.*/
   public static void displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> q = new LinkedList<TreeNode>(); 
      q.add(t); 
      while(!q.isEmpty()) 
      { 
         TreeNode n = q.remove(); 
         System.out.print(n.getValue()); 
         if(n.getLeft() != null) 
         {
            q.add(n.getLeft()); 
         }
         if(n.getRight() != null) 
         {
            q.add(n.getRight()); 
         }
      } 
   
   }
}

/***************************************************
	  
   ----jGRASP exec: java Lab01
 
 			E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Grandparents = 5
 Only childs = 3

 Height of tree = 5
 Width = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC   
*******************************************************/
	  /* TreeNode class for the AP Exams */

class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
