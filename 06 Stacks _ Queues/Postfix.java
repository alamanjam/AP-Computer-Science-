   //name:     date:
   
import java.util.*;
public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postExp = new ArrayList<String>();
      /*  enter your code here  */
      postExp.add("345*+");
      postExp.add("34*5+");
      postExp.add("45+53*-");
      postExp.add("34+56+*");
      postExp.add("345+*2-5/");
      postExp.add("812*+93/-");
      
      
      for( String pf : postExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   public static int eval(String postfix)
   {
      char[]boshal = postfix.toCharArray();
      Stack<Integer>stack = new Stack<Integer>();
      int result, num1, num2;
      for(int x = 0; x < boshal.length; x++)
      {
         if(!isOperator(boshal[x]))
            stack.push(Character.getNumericValue((boshal[x])));
         else
         {
            num2 = stack.pop();
            num1 = stack.pop();
            result = eval(num1, num2, boshal[x]);
            stack.push(result);
         }
      }
      return stack.pop();
   }
   public static int eval(int a, int b, char ch)
   {
      if(ch=='+')
         return a+b;
      if(ch=='-')
         return a-b;
      if(ch=='/')
         return a/b;
      if(ch=='*')
         return a*b;
         return a;
   }
   public static boolean isOperator(char ch)
   {
      if(ch=='+'||ch=='-'||ch=='/'||ch=='*')
         return true;
      return false;
   }
   /*Postfix  -->  Evaluate
345*+           23
34*5+           17
45+53*-         -6
34+56+*         77
345+*2-5/               5
812*+93/-               7
*/
}