 //Name:   Date:
 //modeling a polynomial using a treeMap
import java.util.*;
public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println(poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
       	   	
      Polynomial poly2 = new Polynomial();
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1); 
      System.out.println(poly2.toString());
       	
      System.out.println(poly.add(poly2));
      System.out.println(poly.multiply(poly2));
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   TreeMap<Integer, Integer> poly = new TreeMap<Integer, Integer>();
   public void makeTerm(Integer exp, Integer coef)
   {
      if(poly.containsKey(exp))
      {
      int x = poly.get(exp);
      poly.put(exp, x + coef);  
      }
      else
      poly.put(exp, coef);
   }
   public double evaluateAt(double x)
   {
      double e = 0; 
      for(Integer i : poly.keySet())
      {
         if(i > 0)
            e += (Math.pow(poly.get(i), i)) * x;
         else 
            e += poly.get(i);
      }
      return e;
   }
   public Polynomial add(Polynomial other)
   {
      Polynomial poly2 = new Polynomial();
      TreeMap<Integer, Integer>temp = new TreeMap<Integer, Integer>();
      TreeMap<Integer, Integer>temp1 = new TreeMap<Integer, Integer>();
      if(other.poly.lastKey()>poly.lastKey())
      {
         temp = other.poly;
         temp1 = poly;
      }
      else
      {
         temp = poly;
         temp1 = other.poly;
      }
      for(int x = 0; x < temp.lastKey() + 1; x++)
      {
         if(temp.containsKey(x) && temp1.containsKey(x))
            poly2.makeTerm(x , temp1.get(x) + temp.get(x));
         else if(temp.containsKey(x) && !temp1.containsKey(x))
            poly2.makeTerm(x, temp.get(x));
         else if(!temp.containsKey(x) && temp1.containsKey(x))
            poly2.makeTerm(x, temp1.get(x));
         else
            continue;
      }
      return poly2;
   }
   public Polynomial multiply(Polynomial other)
   {
      Polynomial poly2 = new Polynomial();
      for(Integer i : poly.keySet())
      {
         for(Integer o : other.poly.keySet())
         {
            poly2.makeTerm(i + o, poly.get(i) * other.poly.get(o));
         }
      }
      return poly2;
   }
   public String toString()
   {
      String p = "";
      String x = "x";
      for(Integer i : poly.keySet())
      {
         if(i == 0)
            p = poly.get(i) + p; 
         if(i == 1)
         {
            if(poly.get(i) == 1) 
               p = x + " + " + p;
            else
               p = poly.get(i) + x + " + " + p;
         }
         if(i > 1)
         {
            if(poly.get(i) == 1)
               p = x + "^" + i + " + " + p; 
            else  
               p = poly.get(i) + x + "^" + i + " + " + p; 
         }
      }
      return p;
   }
}
/*  
expected output
   2x^3 + -4x + 2
   10.0
   2x^4 + x^2 + -5x + -3
   2x^4 + 2x^3 + x^2 + -9x + -1
   4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 */