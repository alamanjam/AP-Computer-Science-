///name:    date:
//first program on queues.
import java.io.*;
import java.util.*;
public class SongQueue
{
   private static Scanner infile;
   private static Queue<String> songQueue;
   private static BufferedReader bufferRead;
   
   public static void main(String[] args) throws Exception
   {
      fillPlayList();
      printSongList();
      infile = new Scanner(System.in);
      String prompt = "\tAdd song (A), Play song (P), Delete song (D), Quit (Q):  ";
      System.out.print(prompt);
      String str = infile.next().toUpperCase();
      while(!str.equals("Q"))
      { 
         processRequest( str );
         System.out.print(prompt);
         str = infile.next().toUpperCase();;
      } 
      System.out.println();
      System.out.println("No more music for you today.  Goodbye!");
      infile.close();
   }
   public static void fillPlayList()throws IOException
   {
      songQueue = new LinkedList();
      Scanner songFile = new Scanner(new File("songs.txt"));
      while(songFile.hasNext())
      {
         String s = songFile.nextLine();
         int x = s.indexOf('-');
         songQueue.add(s.substring(0, x-1));
      }
   }
   public static void processRequest(String str)
   {
      if(str.equals("A"))
      {
         add();
         printSongList();
      }
      if(str.equals("P"))
      {
         play();
         printSongList();
      }
      if(str.equals("D"))
      {
         delete();
         printSongList();
      }
      
   }
   public static void add()
   {
      try
      {
         String add = "\tSong to add? ";
         System.out.print(add);
         bufferRead = new BufferedReader(new InputStreamReader(System.in));
         String str = bufferRead.readLine();
         songQueue.add(str.trim());
      }
      catch(Exception e)
      {
         System.out.print("No input");
      }
   }
   public static void play()
   {
      if(!songQueue.isEmpty())
      System.out.print("\tNow playing: " + songQueue.remove() + "\n");
      else
      System.out.print("There is no more music in your playlist!");
   }
   public static void delete()
   {
      try 
      {
         bufferRead = new BufferedReader(new InputStreamReader(System.in));
         System.out.print("\tEnter song to delete(exact match): ");
         String s = bufferRead.readLine();   
         if(songQueue.contains(s))
            songQueue.remove(s);
         else
            System.out.print("\tSong is not in the list!");
      }
      catch(Exception e)
      {
         System.out.print("No input");
      }
   }
   public static void printSongList()
   {
      System.out.println("\nYour music queue: " + songQueue + "\n");
   }
}
