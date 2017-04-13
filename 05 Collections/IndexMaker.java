//  Name:      date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String fileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(fileName));
      System.out.print("\nEnter output file name: ");
      fileName = keyboard.nextLine().trim();
      PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));
      DocumentIndex index = new DocumentIndex(); 	
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
}
class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
   public DocumentIndex()
   {
      super();
   }
   public DocumentIndex(int i)
   {
      super(i);
   }
   
   /** calls foundOrInserted, which returns an index.  At that position,  
   updates that IndexEntry's list of line numbers with num. */
   public void addWord(String word, int num)
   {
      int x = foundOrInserted(word);
      this.get(x).add(num);
   }
      
    /** extracts all the words from str, skipping punctuation and whitespace 
    and for each word calls addWord(word, num).  A good way to skip punctuation 
    and whitespace is to use String's split method, e.g., split("[., \"!?]") */
   public void addAllWords(String str, int num) 
   {
      String[] boshal;
      if(str.isEmpty())
         return;
      boshal = str.split("[., \"!?]");
   
      for(String word : boshal)
      {
         if(!word.isEmpty())
         addWord(word, num);
      }
   }
      
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
      int x = 0;
      IndexEntry i = new IndexEntry(word);
      for(IndexEntry ie : this) {
         if(ie.compareTo(i) == 0) {
            return x;
         }
         if(ie.compareTo(i) > 0) {
            this.add(x, i);
            return x;
         }
         x++;
      }
      this.add(x, i);
      return x;
   }      
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
   private String word;
   private ArrayList<Integer> numsList;

     //constructors
   
   public IndexEntry(String w)
   {
      word = w.toUpperCase();
      numsList = new ArrayList<Integer>();
   }
   
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
      if(!numsList.contains(num))
         numsList.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
   public int compareTo(IndexEntry i)
   {
      if(word.compareTo(i.getWord())>0)
         return 1;
      if(word.compareTo(i.getWord())==0)
         return 0;
      if(word.compareTo(i.getWord())<0)
         return -1;
      return -1;
   }
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
      String word1 = word + " ";
      String nums = "";
      for(int x = 0; x < numsList.size(); x++)
      {
         if(x != numsList.size()-1)
            nums += String.valueOf(numsList.get(x)) + ", ";
         else
            nums += String.valueOf(numsList.get(x));
      }
      return word1 + nums;
   }
}

