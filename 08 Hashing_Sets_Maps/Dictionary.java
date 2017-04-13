   //Name:     Date:
import java.io.*;
import java.util.*;
public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {  
      }
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      TreeMap<String, Set<String>> d = new TreeMap<String, Set<String>>();
      while(infile.hasNext())
      {
         String eng = infile.next();
         String esp = infile.next();
         add(d, eng, esp);
      } 
      return d;
   }
   private static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      if(dictionary.containsKey(word))
      {
         Set<String> s = dictionary.get(word);
         s.add(translation);
         dictionary.put(word, s);
      }
      else
      {
         Set<String> s = new TreeSet<String>();
         s.add(translation);
         dictionary.put(word, s);
      }
   }
   public static void display(Map<String, Set<String>> m)
   {
      for(String word : m.keySet())
      {
         System.out.println(word + " " + m.get(word));
      }
      System.out.println();
   }
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      TreeMap<String, Set<String>> d = new TreeMap<String, Set<String>>(); 
      for(String word : dictionary.keySet())
      {
         Set<String> s = dictionary.get(word);
         for(String w : s)
         {
            add(d, w, word);
         }
      }
      return d;
   }
}
      /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/