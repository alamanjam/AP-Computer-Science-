//  name:        date: 
//  implements the List interface with a backing array of Objects. 
//	 also overrides toString().
    
public class TJArrayList_Driver
{  
   public static void main(String [] args)
   {
      TJArrayList myList = new TJArrayList();	
      
      myList.add("Apple");
      myList.add("Banana");
      myList.add("Dates");
      myList.add(2, "Cucumber");
      System.out.println(myList);
      System.out.println("Size is " + myList.size());
      System.out.println("Get 2: " + myList.get(2));
      System.out.print("Set at 2: ");
      myList.set(2, "Cheese");
      System.out.println(myList);
      Object obj = myList.remove(2);
      System.out.println("Removed " + obj+ " from " + myList);
      System.out.println("Size is " + myList.size());
      System.out.print("Add too many items: ");
      for(int i = 3; i <= 10; i++)
         myList.add(new Integer(i));
      System.out.println(myList);
      System.out.println("Size is " + myList.size());   	
      System.out.println("Contains \"Bread\"?  " + myList.contains("Bread"));
      System.out.println("Contains \"Banana\"?  " + myList.contains("Banana"));
   }
}
   
class TJArrayList
{
   private int size;							//stores the number of objects
   private Object[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = new Object[10];
   }
   public int size()
   {
      int count = 0;
      for(int x = 0; x < myArray.length; x++)
      {
      if(myArray[x]!=null)
      count++;
      }
      size = count;
      return size;
   }
 	/* appends obj to end of list; increases size;
         must be an O(1) operation when size < array.length, 
            and O(n) when it doubles the length of the array.
	      @return true  */
   public boolean add(Object obj)
   {
      if(size<myArray.length)
      {
      myArray[size]=obj;
      size++;
      }
      else
      {
      Object temp[] = new Object[myArray.length*2];
      for(int x = 0; x < myArray.length; x++)
      {
      temp[x] = myArray[x];
      }
      temp[size] = obj;
      myArray= temp;
      size++;
      }
      return true; 
   }
      /* inserts obj at position index.  increments size. 
   		*/
   public void add(int index, Object obj)
   {
  
      for(int x = index; x < size; x++)
      {
         myArray[x+1]=myArray[x];
      }
         myArray[index] = obj;
      size++;
   }
      /* return obj at position index.  
   		*/
   public Object get(int index)
   {
      return myArray[index];
   }
    /* replaces obj at position index.  
   		*/
   public void set(int index, Object obj)
   {
      myArray[index] = obj;
   }
    /*  removes the node from position index. shifts elements 
        to the left.   Decrements size.
   	  @return the object at position index.
   	  */
   public Object remove(int index)
   {
      Object temp[] = new Object[myArray.length-1];
      for(int x = 0; x < index;x++)
      {
      temp[x] = myArray[x];
      }
      Object obj = myArray[index];
      for(int x = index+1; x < myArray.length-1; x++)
      {
      temp[x-1] = myArray[x];
      }
      myArray=temp;
      size--;
      return obj;
      }
   
	 /*
      this method compares objects and should use .equals(), not ==
     	*/
   public boolean contains(Object obj)
   {
      for(int x = 0; x < myArray.length; x++)
      {
      if(myArray[x]!=null)
      if(myArray[x].equals(obj))
      return true;
      }
      return false;
   }
      /*returns the Objects in the array, nicely formatted, 
   	   e.g. [Apple, Banana, Cucumber, Date]
        	*/
   public String toString()
   {
      String boshal = "[";
      for(int x = 0; x < myArray.length; x++)
      {
      if(myArray[x]==null)
      continue;
      else
      boshal+=myArray[x].toString()+", ";
      }
      boshal=boshal.substring(0, boshal.lastIndexOf(","))+"]";
      return boshal;
      
      }
   }
