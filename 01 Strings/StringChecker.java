//2008 Syllabus "A", Question #4.  
//The Design Question.
public class StringChecker
{
   public static void main(String [] args)
   {
      System.out.println("-------Part A ---------------");
      Checker broccoliChecker = new SubstringChecker("broccoli");
      System.out.println(broccoliChecker.accept("broccoli")); 				//true
      System.out.println(broccoliChecker.accept("I like broccoli")); 	//true
      System.out.println(broccoliChecker.accept("carrots are great")); 	//false
      System.out.println(broccoliChecker.accept("Broccoli Bonanza")); 	//false
     
      System.out.println("-------Part B ---------------");
      Checker bChecker = new SubstringChecker("beets");
      Checker cChecker = new SubstringChecker("carrots");
      Checker bothChecker = new AndChecker(bChecker, cChecker);
      Checker aChecker = new SubstringChecker("artichokes");
      Checker veggies = new AndChecker(bothChecker, aChecker);
      System.out.println(bothChecker.accept("I love beets and carrots")); 	//true
      System.out.println(bothChecker.accept("beets are great")); 				//false
      System.out.println(veggies.accept("artichokes, beets, and carrots"));//true
     
      System.out.println("-------Part C ---------------");
      Checker artiChecker = new SubstringChecker("artichokes");
      Checker kChecker = new SubstringChecker("kale");
      Checker yummyChecker;
      yummyChecker = new AndChecker(new NotChecker(aChecker), new NotChecker(kChecker)); /* write this line */  
         System.out.println(yummyChecker.accept("chocolate truffles")); 		//true
      System.out.println(yummyChecker.accept("kale is great")); 				//false
      System.out.println(yummyChecker.accept("Yuck: artichokes & kale")); 	//false 
   }
}
//examines strings and accepts those strings that meet a particular criterion.
interface Checker
{
/** @param text a string to consider for acceptance
* @return true if this Checker accepts text; false otherwise
*/
   boolean accept(String text);
}

// Part A  -- SubstringChecker
class SubstringChecker implements Checker
{
   private String sentence = "";
   public SubstringChecker(String s)
   {
      sentence = s;
   }
   public boolean accept(String text)
   {
      if(text.contains(sentence))
         return true;
      else 
         return false;
   }
}

// Part B  -- AndChecker

class AndChecker implements Checker
{
   private Checker ch1;
   private Checker ch2;
   public AndChecker(Checker c1, Checker c2)
   {
      ch1 = c1;
      ch2 = c2;
   }
   public boolean accept(String text)
   {
      if(ch1.accept(text) && ch2.accept(text))
      return true;
      else
      return false;
   }
} 
class NotChecker implements Checker
{
   private Checker checker1;
   /*A one-parameter constructor that takes one Checker object
	*/
   public NotChecker(Checker chk1)
   {
      checker1 = chk1;
   }
/*
* returns true if and only if its Checker object does NOT accept the string
*/
   public boolean accept(String text)
   {
      return !checker1.accept(text);
   }
}

/*   Sample Run   


-------Part A ---------------
true
true
false
false
-------Part B ---------------
true
false
true
-------Part C ---------------
true
false
false
  
*/