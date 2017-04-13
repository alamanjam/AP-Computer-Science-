// Name:    Date:
import java.util.*;
import java.io.*;
public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the file name: ");
      String fileName = sc.next();
      //String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Set<Character> lower = new HashSet<Character>();
      Set<Character> capital = new HashSet<Character>();
      Set<Character> other = new HashSet<Character>();
      Set<Character> clower = new HashSet<Character>();
      Set<Character> ccapital = new HashSet<Character>();
      Set<Character> cother = new HashSet<Character>();
      Scanner sc = new Scanner(new File(fn));
      int count = 0;
      while(sc.hasNextLine())
      {
         String s = sc.nextLine();
         System.out.println(s);
         for(int x = 0; x < s.length(); x++)
         {
            char c = s.charAt(x);
            if(!Character.isLetter(c))
               other.add(c);
            else
            {
               if(Character.isUpperCase(c))
                  capital.add(c);
               else
                  lower.add(c);
            }
         }
         System.out.println("Lower Case: " + lower);
         System.out.println("Upper Case: " + capital);
         System.out.println("Other: " + other);
         System.out.println();
         if(count == 0)
            clower.addAll(lower);
         else
            clower.retainAll(lower);
         if(count == 0)
            ccapital.addAll(capital);
         else
            ccapital.retainAll(capital);
         if(count == 0)
            cother.addAll(other);
         else
            cother.retainAll(other);
         lower.clear();
         capital.clear();
         other.clear();
         count++;
      }
      System.out.println("Common Lower Case: " + clower);
         System.out.println("Common Upper Case: " + capital);
         System.out.println("Common Other: " + cother);
   }
}