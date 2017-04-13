// name:     date:

import java.util.*;
public class ParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter tests here */
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add("<{5+7}*3>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
      parenExp.add("5+(7*3)");
      parenExp.add("((5+7)*3");
      parenExp.add("[(5+7]*3)");
      parenExp.add("[(5+7)*3])");
      parenExp.add("([(5+7)*3]");
                  
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   public static boolean checkParen(String exp)
   {
      char[] letters = exp.toCharArray();
      Stack<Character> stack = new Stack<Character>();
      for(char letter : letters) {
         if (left.contains("" + letter)) {
            stack.push(letter);
         } 
         else {
            if(right.contains("" + letter))
            {
            if (stack.empty())
               return false;
            if (letter == ')')
               if (!(stack.pop() == '('))
                  return false;
         
            if (letter == '}')
               if (!(stack.pop() == '{'))
                  return false;
            if (letter == ']')
               if (!(stack.pop() == '['))
                  return false;
            if (letter == '>')
               if (!(stack.pop() == '<'))
                  return false;
            }
         }
      }
      if(stack.empty())
         return true;
      return false;
   }   
  
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
