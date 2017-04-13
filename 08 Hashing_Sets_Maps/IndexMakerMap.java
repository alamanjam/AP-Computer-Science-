//  Name:      date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.io.*;
import java.util.*;

public class IndexMakerMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String infileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(infileName));
      String outfileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outfileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }

   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex(); 	
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      //print to the outputFile
      Iterator<String>it = index.keySet().iterator();
      while(it.hasNext())
      System.out.println(it.next() + index.get(it.next()));
      // for(String entry : index.keySet())
//          outputFile.println(entry + " " + index.get(entry));
   }
}

class DocumentIndex extends TreeMap<String, ArrayList<Integer>>
{

   public void addWord(String word, int lineNum)
   {
      if(this.containsKey(word))
      {
         ArrayList<Integer> a = this.get(word);
         a.add(lineNum);
         this.put(word, a); 
      }
      else
      {
         ArrayList<Integer> a = new ArrayList<Integer>();
         a.add(lineNum);
         this.put(word, a);
      }
   }
   
 /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord(word, num).  A good way to skip punctuation 
 and whitespace is to use String's split method, e.g., split("[., \"!?]") */
   public void addAllWords(String str, int lineNum) 
   {
      String[] boshal;
      if(str.isEmpty())
         return;
      boshal = str.split("[., \"!?]");
      for(String word : boshal)
      {
         if(!word.isEmpty())
         addWord(word.toUpperCase(), lineNum);
      }
   }
}


