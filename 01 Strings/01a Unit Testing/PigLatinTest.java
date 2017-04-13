import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PigLatinTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void myTests() 
   {
      Assert.assertEquals("Galanos", "Alanosgay" , pig("Galanos"));
      Assert.assertEquals("pigeon", "igeonpay" , pig("pigeon"));
      Assert.assertEquals("\"Yes!\"", "\"Esyay\"" , pig("\"Yes\""));
      Assert.assertEquals("Pourquoi?", "Ourquoipay?" , pig("Pourquoi?"));
      Assert.assertEquals("bonjour!", "onjourbay!" , pig("bonjour!"));
      Assert.assertEquals("ilovetj", "ilovetjway" , pig("ilovetj"));
      Assert.assertEquals("gooooocolonials!", "ooooocolonialsgay!" , pig("gooooocolonials!"));
      Assert.assertEquals("hhhghghgh", "INVALID" , pig("hhhghghgh"));
      Assert.assertEquals("Anjam:", "Anjamway:" , pig("Anjam:"));
      Assert.assertEquals("fry", "yfray" , pig("fry"));
      Assert.assertEquals("question", "estionquay" , pig("question"));
      Assert.assertEquals("failed", "ailedfay" , pig("failed"));
      Assert.assertEquals("everybody", "everybodyway" , pig("everybody"));
      Assert.assertEquals("jgrasp", "aspjgray" , pig("jgrasp"));
      Assert.assertEquals("boshal", "oshalbay" , pig("boshal"));
      
   }
    public static int firstVowel(String s)
   {
      for (int i = 0; i < s.length(); i++)
      {
         if (s.charAt(i)==('a')||s.charAt(i)==('e')||s.charAt(i)==('i')||s.charAt(i)==('o')||s.charAt(i)==('u'))
         {
            return i;
         }
      }
      return -1;
   }
   public static int firstu(String s)
   {
      for (int i = 0; i < s.length(); i++)
      {
         if (s.charAt(i)=='u')
         {
            return i;
         }
      }
      return -1;
   }
   public static int firsty(String s)
   {
      for (int i = 0; i < s.length(); i++)
      {
         if (s.charAt(i)=='y')
         {
            return i;
         }
      }
      return -1;
   }
   public static String punctuation(String s)
   {
      for (int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i)=='?')
         {
            s = s.substring(0, i) + s.substring(i+1) + "?";
            return s;
         }
         if(s.charAt(i)=='!')
         {
            s = s.substring(0, i) + s.substring(i+1) + "!";
            return s;
         }
         if(s.charAt(i)=='.')
         {
            s = s.substring(0, i) + s.substring(i+1) + ".";
            return s;
         }
         if(s.charAt(i)=='"')
         {
            s = "\"" + s.substring(0,i) + s.substring(i+1);
         }
         if(s.charAt(i)==':')
         {
            s = s.substring(0,i) + s.substring(i+1) + ":";
         }
         if(s.charAt(i)==',')
         {
            s = s.substring(0,i) + s.substring(i+1) + ",";
         }
         if(s.charAt(i)==';')
         {
            s = s.substring(0,i) + s.substring(i+1) + ";";
         }
         if(s.charAt(i)=='[')
         {
            s = "[" + s.substring(0,i) + s.substring(i+1) ;
         }
         if(s.charAt(i)=='{')
         {
            s = "{" + s.substring(0,i) + s.substring(i+1) ;
         }
      }
      
      return s;
     
   }
   public static String punctuation2(String s)
   {
      for (int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i)=='"')
         {
            s = s.substring(0,i) + s.substring(i+1) + "\"";    
         }
         if(s.charAt(i)==']')
         {
            s = s.substring(0,i) + s.substring(i+1) + "]"; 
         }
         if(s.charAt(i)=='}')
         {
            s = s.substring(0,i) + s.substring(i+1) + "}"; 
         }
      }
      return s;
   }
   public static String punctuation3(String s)
   {
      for (int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i)=='"')
         {
            s =  "\"" + s.substring(0,i) + s.substring(i+1);
            break;
         }
      }
      return s;
   }
   public static String pig(String s)
   {
      String st = s.toLowerCase();
      int vowel = firstVowel(st);
      int u = firstu(st);
      int y = firsty(st);
      if(vowel>=0||st.contains("y"))
      {
        
         if(vowel==0||(vowel==1&&st.charAt(0)=='"'))
         {
          
            s= s+"way";
            s = punctuation(s);
            s = punctuation2(s);
            s = punctuation3(s);
            return s;
         }
         
         else
         {
            if(st.startsWith("y")||(st.charAt(0)=='"')&&(st.charAt(1)=='y'))
            {
               String start = s.substring(0, vowel);
               String end = s.substring(vowel);
               String result = end + start.toLowerCase() + "ay";
               if(Character.isUpperCase(s.charAt(0))==true)
               {
                  result = (result.substring(0,1).toUpperCase()+result.substring(1).toLowerCase());
               }
               result = punctuation(result);
               result = punctuation2(result);
               result = punctuation3(result);
               if(Character.isUpperCase(s.charAt(1))==true)
               {
                  result = (result.charAt(0) + result.substring(1,2).toUpperCase()+result.substring(2));
               }
               return result;
            }
            if(st.contains("u")&&st.charAt(u-1)=='q')
            { 
            
               String start = s.substring(0, vowel+1);
               String end = s.substring(vowel+1);
               String result = end + start.toLowerCase() + "ay";
               if(Character.isUpperCase(s.charAt(0))==true)
               {
                  result = (result.substring(0,1).toUpperCase()+result.substring(1).toLowerCase());
               }
               result = punctuation(result);
               result = punctuation2(result);
               result = punctuation3(result);
               if(Character.isUpperCase(s.charAt(1))==true)
               {
                  result = (result.charAt(0) + result.substring(1,2).toUpperCase()+result.substring(2));
               }
               return result;
            }
            
            else
            {
               if(st.contains("y")&&(y<vowel||y==st.length()))
               {
                  String start = s.substring(0, y);
                  String end = s.substring(y);
                  String result = end + start.toLowerCase() + "ay";
                  if(Character.isUpperCase(s.charAt(0))==true)
                  {
                     result = (result.substring(0,1).toUpperCase()+result.substring(1));
                  }
                  result = punctuation(result);
                  result = punctuation2(result);
                  result = punctuation3(result);
                  if(Character.isUpperCase(s.charAt(1))==true)
                  {
                     result = (result.charAt(0) + result.substring(1,2).toUpperCase()+result.substring(2));
                  }
                  return result;
               }
               else
               {
                  if(y==st.length()-1&&vowel==-1)
                  {
                     String start = s.substring(0, y);
                     String end = s.substring(y);
                     String result = end + start.toLowerCase() + "ay";
                     if(Character.isUpperCase(s.charAt(0))==true)
                     {
                        result = (result.substring(0,1).toUpperCase()+result.substring(1));
                     }
                     result = punctuation(result);
                     result = punctuation2(result);
                     result = punctuation3(result);
                    
                     if(Character.isUpperCase(s.charAt(1))==true)
                     {
                        result = (result.charAt(0) + result.substring(1,2).toUpperCase()+result.substring(2));
                     }
                     return result;
                  }
                  else
                  {
                     String start = s.substring(0, vowel);
                     String end = s.substring(vowel);
                     String ay = "ay";
                     String result = end + start.toLowerCase() + ay;
                     if(Character.isUpperCase(s.charAt(0))==true)
                     {
                        result = (result.substring(0,1).toUpperCase()+result.substring(1));
                     }
                                         
                     result = punctuation(result);
                     result = punctuation2(result);
                     result = punctuation3(result);
                  
                     if(Character.isUpperCase(s.charAt(1))==true)
                     {
                        result = (result.charAt(0) + result.substring(1,2).toUpperCase()+result.substring(2));
                     }
                      
                     return result;
                  }
               }
            }
         } 
      }
      else
      {
         return "INVALID";
      }
   }

}
