//Name:          date:  
public class Sentence_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      Sentence s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
         
      s = new Sentence( "A Santa lived as a devil at NASA." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
           
         
      s = new Sentence( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
            
         
      s = new Sentence( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
         
      s = new Sentence( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
         
      s = new Sentence( "naitian" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();   
      // Lots more test cases.  Test every line of code.  Test
      // the extremes, test the boundaries.  How many test cases do you need?
        
   }
}
class Sentence
{
   private static String mySentence = "";
   private int myNumWords = 1;
      
      //Constructor.  Creates sentence from String str.
      //						Finds the number of words in sentence.
      //Precondition:  Words in str separated by exactly one blank.
   public Sentence( String str )
   {
      mySentence = str; 
      for(int x = 0;x < str.length();x++)
      {
         if(str.charAt(x)==' ')
         {
            myNumWords++;
         }
      }
   }
      
   public int getNumWords()
   {  
      return myNumWords;  
   }
      
   public String getSentence()
   {
      return mySentence; 
   }
      
      //Returns true if mySentence is a palindrome, false otherwise.
   public static  boolean isPalindrome()
   {
      String s;
      s = removeBlanks(mySentence);
      s = removePunctuation(s);
      s = lowerCase(s);
      return isPalindrome(s, 0 , s.length()-1);
   
   }
      //Precondition: s has no blanks, no punctuation, and is in lower case.
      //Returns true if s is a palindrome, false otherwise.
   private static boolean isPalindrome(String s , int start, int end)
   {  
      s = removeBlanks(s);
      s = removePunctuation(s);
      s = lowerCase(s);
      if(start >= end)
         return true; 
      if(s.charAt(start) == s.charAt(end))
      
         return isPalindrome(s, start+1, end-1);
      else
         return false;
   
   }
      //Returns copy of String s with all blanks removed.
      //Postcondition:  Returned string contains just one word.
   private static String removeBlanks( String s )
   {  
      String st = "";
      for(int x = 0;x < s.length();x++)
      {
         if(!(s.charAt(x)==' '))
         {
            st += s.charAt(x);
         }
      }
      return st;
        
   }
      
      //Returns copy of String s with all letters in lowercase.
      //Postcondition:  Number of words in returned string equals
      //						number of words in s.
   private static String lowerCase( String s )
   {  
      return s.toLowerCase();
   }
      
      //Returns copy of String s with all punctuation removed.
      //Postcondition:  Number of words in returned string equals
      //						number of words in s.
   public static String removePunctuation( String s )
   { 
      String punct = "!@?'.\"<>(),";
      for(int x = 0; x<s.length(); x++)
      {
         if(punct.contains("" + s.charAt(x)))
         {
            s = s.replace("" + s.charAt(x), "");
         }
      }
      return s;
   }
}
