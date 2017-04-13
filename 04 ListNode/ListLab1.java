  //your name and date
import java.util.Scanner;
public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Extension  ****/
      
      System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode p = pointerToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
       		
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
      print(head);
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   public static Object first(ListNode head)
   {
      if(head==null)
         return null;
      return head.getValue();    
   }
   public static Object second(ListNode head)
   {
      if(head==null)
         return null;
      return head.getNext().getValue();    
   }
   public static ListNode pointerToLast(ListNode head)
   {
      if(head==null)
         return null;
      while(head!=null)
      { 
         if(head.getNext()==null)
            return head;
         head = head.getNext();
      }
      return null;
   }
   
   public static ListNode copyOfLast(ListNode head) 
   {
      if(head==null)
         return null;
      ListNode c=new ListNode(head.getValue(), copyOfLast(head.getNext())); 
      while(c.getNext()!=null)
      {
         c=c.getNext();
      }
      return c;
   }
   public static ListNode insertFirst(ListNode head, Object arg) 
   {
      head = new ListNode(arg, head);
      return head;
   }
   public static ListNode insertLast(ListNode head, Object arg) 
   {
      ListNode temp = head;
      while(head!=null)
      {
         if(temp.getNext()==null)
            break;
         temp = temp.getNext();
      
      }
      ListNode temp2 = new ListNode(arg, null);
      temp.setNext(temp2);
      return head;
   }
   }
   class ListNode
   {
      private Object value;
      private ListNode next;
      public ListNode(Object v, ListNode n)
      {
         value=v;
         next=n;
      }
      public Object getValue()
      {
         return value;
      }
      public ListNode getNext()
      {
         return next;
      }
      public void setValue(Object newv)
      {
         value=newv;
      }
      public void setNext(ListNode newn)
      {
         next=newn;
      }
   }

