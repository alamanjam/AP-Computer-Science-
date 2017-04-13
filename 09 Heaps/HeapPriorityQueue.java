 //Name:   Date
import java.util.*;
public class HeapPriorityQueue<E extends Comparable<E>>
{
   private ArrayList<E> myHeap;
   private int size;
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
   }
   public HeapPriorityQueue(int size)
   {
      myHeap = new ArrayList<E>(size);
   }
   public boolean add(E e)
   {
         myHeap.add(e);
         heapUp(myHeap.indexOf(e));
         size++;
         return true;
   }
   public E remove()
   {
      swap(0, size()-1);
      E obj = myHeap.remove(size()-1);
      heapDown(0, myHeap.size()-1);
      size--;
      return obj;
   }
   public int size()
   {
      return size;
   }
   public boolean isEmpty()
   {
      return size == 0;
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }
   private void heapDown(int k, int size)
   {
      int left = 2*k;
      int right = 2*k+1;
      if(k > size || left > size || right > size)
         return;   
      else
      {
         int maxChild = (myHeap.get(left).compareTo(myHeap.get(right)) > 0) ? left:right;
         if(myHeap.get(k).compareTo(myHeap.get(maxChild)) < 0)
         {
            swap(k, maxChild);
            heapDown(maxChild, size);
         }
      }
   }
   private void heapUp(int k)
   {
      while(k > 1 && (myHeap.get(k).compareTo(myHeap.get(k/2)) > 0))
      {
         swap(k, k/2);
         k = k/2;
      }
   }
   public String toString()
   {
      return myHeap.toString();   
   }
}