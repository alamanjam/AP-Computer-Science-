  //name:   date: 
import java.util.*;
public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  -->  Postfix  -->  Evaluate");
      /*enter code here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3+4*5");
      infixExp.add("3*4+5");
      infixExp.add("(4+5)-5*3");
      infixExp.add("(3+4)*(5+6)");
      infixExp.add("(3*(4+5)-2)/5");
      infixExp.add("8+1*2-9/3");
   
      for( String s : infixExp )
      {
         String pf = infixToPostfix(s);
         System.out.println(s + "       " + pf + "       " + Postfix.eval(pf));
      }
   }
   public static String infixToPostfix(String infix)
   {
      String result = "";
      Stack<Character>stack = new Stack<Character>();
      boolean done = false; 
      for(int x = 0; x < infix.length(); x++)
      {
         char ch = infix.charAt(x); 
         if(!isOperator(ch)&&!isParen(ch))
            result+=ch;
         if(ch=='(')
            stack.push(ch);
         if(ch==')')
         {
            while(stack.peek()!='(')
            {
               if(isOperator(stack.peek()))
               result+=stack.pop();
            }
            stack.pop();
          }
         if(isOperator(ch))
         {
            while(!done)
            {
               if(stack.empty())
               {
                  stack.push(ch);
                  break;
               }
               if(stack.peek()=='(')
               {
                  stack.push(ch);
                  break;
               }
               if(isLower(stack.peek(), ch))
               {
                  stack.push(ch);
                  break;
               }
               if(stack.peek()!='(')
               {
                  result+=stack.pop();
               }
            }
         }
      }
      while(!stack.empty())
      {
         if(stack.peek()!='('||stack.peek()!=')')
         {
            result+=stack.pop();
         }
         else
         {
            stack.pop();
         }
      }
      return result;
   }
	//returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      if(c1=='+'&&c2=='*')
         return true;
      if(c1=='-'&&c2=='*')
         return true;
      if(c1=='+'&&c2=='/')
         return true;
      if(c1=='-'&&c2=='/')
         return true;
      return false;
   }
   public static boolean isOperator(char ch)
   {
      if(ch=='+'||ch=='-'||ch=='/'||ch=='*')
         return true;
      return false;
   }
   public static boolean isParen(char ch)
   {
      if(ch=='('||ch==')')
         return true;
      return false;
   }
}
	
	/*
 Infix  -->  Postfix  -->  Evaluate
 3+4*5       345*+       23
 3*4+5       34*5+       17
 (4+5)-5*3       45+53*-       -6
 (3+4)*(5+6)       34+56+*       77
 (3*(4+5)-2)/5       345+*2-5/       5
 8+1*2-9/3       812*+93/-       7
	*/
