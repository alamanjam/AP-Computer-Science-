public class Customer
{
   int arrival;
   public Customer(int x)
   {
      arrival = x;
   }
   public int getArrival()
   {
      return arrival;
   }
   public void setArrival(int a)
   {
      arrival = a;
   }
   public String toString()
   {
      return "" + arrival;
   }
}