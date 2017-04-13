import java.io.*;
import java.util.*;
public class Alam_Anjam
{
   public static String [] array;
   public static boolean hasPlus = false;
   public static String num;
   public static void main(String [] args) throws FileNotFoundException
   {
      Scanner s = new Scanner(new File("input.txt"));
      while(s.hasNextLine())
      {
         array = s.nextLine().split(",");
         int x = array[0].indexOf(" ");
         num = array[0].substring(0, x) + " ";
         String str = array[0].substring(x , array[0].length());
         if(str.contains("+"))
         hasPlus = true;
         else 
         hasPlus = false;
         float f = Float.parseFloat(str);
         int l = Integer.parseInt(array[1].trim());
         int d = Integer.parseInt(array[2].trim());
         STR(f, l, d);
      }
   }

   public static void STR(float f, int length, int decimal)
   {
      String dec = Integer.toString(decimal);
      String format = "%." + dec + "f";
      String fin = String.format(format, f);
      if(hasPlus == true)
      fin = "+" + fin;
      if(length > fin.length())
      {
          while(fin.length() != length)
          fin = "#" + fin;
       }
       if(length < fin.length())
      {
          String hash = "";
          int deci = decimal;
          while(deci != 0)
          {
          hash += "#";
          deci--;
          }
          hash = "." + hash;
          while(hash.length() != length)
          {
          hash = "#" + hash;
          }
          fin = hash;
       }
      System.out.println(num + fin);
   }
}