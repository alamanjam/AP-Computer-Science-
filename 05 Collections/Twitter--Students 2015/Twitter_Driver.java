//Miss Galanos
//version 12.8.2015

import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.2.jar
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;

public class Twitter_Driver
{
   private static PrintStream consolePrint;
   
   public static void main (String []args) throws TwitterException, IOException
   {
      consolePrint = System.out;
      
      // PART 1
      // set up classpath and properties file
             
      TJTwitter bigBird = new TJTwitter(consolePrint);
//          
//       //create message to tweet, then call the tweetOut method
//        String message = "8)";
//       bigBird.tweetOut(message);
//      
//       PART 2
      //Choose a public Twitter user's handle 
         
      Scanner scan = new Scanner(System.in);
         consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
         String twitter_handle = scan.next();
               
         while (!twitter_handle.equals("done"))
         {
               // Print the most popular word they tweet
            bigBird.makeSortedListOfWordsFromTweets(twitter_handle);
            consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.mostPopularWord());
            consolePrint.println();
            consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
            twitter_handle = scan.next();
         }  
      // PART 3
     // bigBird.investigate();
         
         
   }//end main         
         
}//end driver        
         
class TJTwitter 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private List<String> sortedTerms;
   
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      twitter = TwitterFactory.getSingleton(); //connects to Twitter and performs authorizations
      consolePrint = console;
      statuses = new ArrayList<Status>();
      sortedTerms = new ArrayList<String>();   
   }
   
   /******************  Part 1 *******************/
   public void tweetOut(String message) throws TwitterException, IOException
   {
      
      twitter.updateStatus(message);
   
   }
   @SuppressWarnings("unchecked")
   /******************  Part 2 *******************/
   public void makeSortedListOfWordsFromTweets(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      sortedTerms.clear();
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); // Creates file for dedebugging purposes
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      int numberTweets = statuses.size();
      fileout.println("Number of tweets = " + numberTweets);
      fileout = new PrintStream(new FileOutputStream("garbageOutput.txt"));
   
      int count=1;
      for (Status j: statuses)
      {
         fileout.println(count+".  "+j.getText());
         count++;
      }		
         	
     	//Makes a list of words from user timeline
      for (Status status : statuses)
      {			
         String[]array = status.getText().split(" ");
         for (String word : array)
            sortedTerms.add(removePunctuation(word).toLowerCase());
      }	
   					
      // Remove common English words, which are stored in commonWords.txt
      sortedTerms = removeCommonEnglishWords(sortedTerms);
      sortAndRemoveEmpties();
      fileout = new PrintStream(new FileOutputStream("rithik.txt"));
      for (String j: sortedTerms)
      {
         fileout.println(j);
      }	
      
   }
   
   // Sort words in alpha order. You should use your old code from the Sort/Search unit.
   // Remove all empty strings ""
   @SuppressWarnings("unchecked")
   private void sortAndRemoveEmpties()
   {
      SelectionSort.sort(sortedTerms);
      while(sortedTerms.contains(""))
      {
         sortedTerms.remove("");
      }  
   }
   
   // Removes common English words from list
   // Remove all words found in commonWords.txt  from the argument list.
   // The count will not be given in commonWords.txt. You must count the number of words in this method.
   // This method should NOT throw an exception. Use try/catch.
   @SuppressWarnings("unchecked")
   private List removeCommonEnglishWords (List<String> list)
   {	
      try
      {
         Scanner sc = new Scanner(new File("commonWords.txt"));
         ArrayList<String> terms = new ArrayList<String>();
         while(sc.hasNext())
         {
            terms.add(sc.next());
         }
         for(int x = 0; x < list.size(); x++)
         {   
            if(!list.get(x).isEmpty())         
               for(int y = 0; y < terms.size(); y++)
               {
                  if(list.get(x).compareToIgnoreCase((terms.get(y))) == 0||list.get(x).charAt(0)=='@')
                  {
                     list.remove(x);
                     x--;
                     break;
                  }
                  
               }
         }
       
         
         return list;
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      return list;
   }
   
   //Remove punctuation - borrowed from prevoius lab
   //Consider if you want to remove the # or @ from your words. They could be interesting to keep (or remove).
   private String removePunctuation( String s )
   {
      String punct = "!?'.\"<>(),:";
      for(int x = 0; x<s.length(); x++)
      {
         if(punct.contains("" + s.charAt(x)))
         {
            s = s.replace("" + s.charAt(x), "");
         }
         
      }
      return s;
    
   }
   //Should return the most common word from sortedTerms. 
   //Consider case. Should it be case sensitive? The choice is yours.
   @SuppressWarnings("unchecked")
   public String mostPopularWord()
   {
      int count = 0;
      int temp = 0;
      String word = "";
      for(int x = 0; x < sortedTerms.size(); x++)
      {
         int freq = Collections.frequency(sortedTerms, sortedTerms.get(x));
         if(temp < freq)
         {
            temp = freq;
            word = sortedTerms.get(count);
         }
         count += 1;
      }
      return word;   
   }
   

   /******************  Part 3 *******************/
   public void investigate ()
   {
      try
      {
         TJTwitter twitterer = new TJTwitter(consolePrint);
         System.out.println("Please type in twitter handle of person who you would like to tweet for their birthday.");
         Scanner in = new Scanner(System.in);
         String username = in.next();
         User user = twitter.showUser(username); // this line
         String name = user.getName();
         Calendar calendar = Calendar.getInstance();  
         String timeStamp = "" + calendar;
         String message = " @" + username + " " + name + ", Happy Birthday!!!!!!!";
         int count = 2;
         twitterer.tweetOut(message);
      
         long currentTime = System.currentTimeMillis();
         while(true)
         {
            if (System.currentTimeMillis() - currentTime>=(60000))
            {
               currentTime = System.currentTimeMillis();
               String[] endings = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
               String word = "";
               if ( count == 11 || count == 12 || count ==13)
               {
                  word = count + "th";
               }
               else
               {
                  word = count + endings[count % 10];
               }
               String FINALmessage = "@" + username + " " + name + ", Happy Birthday for the " + word + " time 8) "; 
               count++;
               twitterer.tweetOut(FINALmessage);
            }
         }
         
      }
      catch (TwitterException e)
      {
         System.out.println("Twitter not found!");
      }
      catch (IOException e)
      {
         System.out.println("IO EXCEPTION");
      }
   }
   // A sample query to determine how many people in Arlington, VA tweet about the Miami Dolphins
   public void sampleInvestigate ()
   {
      Query query = new Query("Miami Dolphins");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(38.8372839,-77.1082443), 5, Query.MILES);
      query.setSince("2015-12-1");
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
   }  
   
}  

// Consider adding a sorter class here.
class SelectionSort
{
   public static void sort(List<String> list)
   {
      int maxPos;
      for(int k = 0; k < list.size(); k++)		
      {
         maxPos = findMax(list, list.size() - k - 1);
         swap(list, maxPos, list.size() - k - 1);
      }
   }
   public static int findMax(List<String> list, int upper)
   {
      int maxPos = 0;
      for(int j = 1; j <= upper; j++)			
         if(list.get(j).compareTo(list.get(maxPos)) > 0)	
            maxPos = j;					
      return maxPos;
   }
   public static void swap(List<String> list, int a, int b)
   {
      String temp = list.get(a);				
      list.set(a,list.get(b))  ;
      list.set(b, temp);
   }
}
 
