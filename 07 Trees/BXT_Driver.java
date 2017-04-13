//name:     date:
import java.util.*;
  
/*******************
Represents a binary expression tree.
The BXT can build itself from a postorder expression.  It can
evaluate and print itself. It also prints an inorder string and a preorder string.  
**********************/
class BXT
{
   private int count;
   private TreeNode root;
   public BXT()
   {
      count = 0;
      root = null;
   }
  /*  enter your instance methods here.  
      buildTree, display, inorderTraverse, 
      preorderTraverse, evaluateTree  */
   public void buildTree(String str)
   {
      StringTokenizer st = new StringTokenizer(str);
      Stack<TreeNode> stack = new Stack<TreeNode>(); 
      while(st.hasMoreTokens()) 
      { 
         String token = st.nextToken(); 
         if(!isOperator(token)) 
         { 
            stack.push(new TreeNode(token)); 
         } 
         else 
         { 
            TreeNode t1 = (TreeNode)stack.pop(); 
            TreeNode t2 = (TreeNode)stack.pop(); 
            TreeNode t = new TreeNode(token, t2, t1); 
            stack.push(t); 
         } 
      } 
      root = (TreeNode)stack.pop();
   }
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   private double evaluateNode(TreeNode root)      //recursive
   {
   
      if(isOperator(root.getValue().toString()))
      {
         return computeTerm(root.getValue().toString(), evaluateNode(root.getLeft()), evaluateNode(root.getRight()));
      }
      else
         return Double.parseDouble(root.getValue().toString());
       
   }
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+"))
         return a + b;
      else if(s.equals("-"))
         return a - b;
      else if(s.equals("*"))
         return a * b;
      else
         return a / b; 
   
      
   }
   private boolean isOperator(String s)
   {
      String ops = "+-*/";
      return ops.contains(s);  
   }

   public void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
      display(t.getLeft(), level + 1); //recurse left
   }
   private static void inorderTraverse(TreeNode t)
   {
      if(t == null)
         return; 
         
      inorderTraverse(t.getLeft());    //recurse left
      System.out.print(t.getValue()+ " ");  //inorder visit
      inorderTraverse(t.getRight());   //recurse right
   }
   private static void preorderTraverse(TreeNode t)
   {
      if(t == null)
         return;
      System.out.print(t.getValue() + " ");  //preorder visit
      preorderTraverse(t.getLeft());         //recurse left
      preorderTraverse(t.getRight());        //recurse right
   }
   public void preorderTraverse()
   {
      preorderTraverse(root);
   }
   public void inorderTraverse()
   {
      inorderTraverse(root);
   }
   public void display()
   {
      display(root, 0);
   }

}
/*******************
Driver for a binary expression tree class.
Input: a postfix string, each token separated by a space.
**********************/
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("14 -5 / ");
      postExp.add("20 3 -4 + * ");
      postExp.add("2 3 + 5 / 4 5 - *");
   
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         tree.display();
         System.out.print("Infix order:  ");
         tree.inorderTraverse();
         System.out.print("\nPrefix order:  ");
         tree.preorderTraverse();
         System.out.print("\nEvaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
   }
}

/***************************************
 Postfix Exp: 14 -5 / 
 BXT: 
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20 3 -4 + * 
 BXT: 
 		-4
 	+
 		3
 *
 	20
 Infix order:  20 * 3 + -4 
 Prefix order:  * 20 + 3 -4 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 BXT: 
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
 			2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 */