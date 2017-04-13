 //name: Anjam Alam    date: 9/11/15
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
     
      SmartCard jimmy = new SmartCard(20.00); //bought with $20.00 
      jimmy.board(center);            		    //boarded in zone 1
      jimmy.disembark(suburbia);					 //disembark in zone 4
      //jimmy.disembark(uptown);					 //disembark without having boarded
      //lots more test cases!	
      jimmy.board(uptown);
      //jimmy.board(downtown);              //board without having disembarked previously
      jimmy.disembark(downtown); 
      jimmy.board(center);
      jimmy.disembark(downtown);//travel in same zone
      jimmy.board(suburbia);
      jimmy.disembark(center);//travel from zone 4 to zone 1
      //jimmy.addMoney(10.00); //adds 10 dollars to balance
      jimmy.board(suburbia);
      jimmy.disembark(uptown); //travel from zone 4 to zone 2
      jimmy.board(uptown);
      jimmy.disembark(downtown);//travel from zone 4 to zone 1
      jimmy.board(center);
      jimmy.disembark(downtown);//travel in same zone
      jimmy.board(suburbia);
      jimmy.disembark(center); //travel from zone 4 to zone 1
      jimmy.board(suburbia);
      jimmy.disembark(uptown);
      jimmy.board(uptown);
      jimmy.disembark(downtown);
      jimmy.board(center);
      jimmy.disembark(downtown);
      jimmy.board(suburbia);
      jimmy.disembark(suburbia);
      jimmy.board(suburbia);
      jimmy.disembark(uptown);
      //jimmy.board(center); //try to board when balance is < $0.50
   				
   }
}
class SmartCard 
{
   DecimalFormat df = new DecimalFormat("$0.00");
   public double money;
   public double money1;
   public boolean board = true;
   public boolean dis = true;
   public int zone;
   public String name; 
   
   public SmartCard(double d)
   {
      money = d;
   }
   public void addMoney(double m)
   {
      money += m;
      System.out.println("Your balance has been updated to "+ df.format(money)+".");
   }
   public void board(Station s)
   {
      if(money<0.50)
      {
         System.out.println("Warning: Your balance is too low!");
         System.exit(0);
      }
      if(dis==true)
      { 
         System.out.println("Boarded at: " +s.getName());
         name = s.getName();
         zone = s.getZone();
      }
      if(dis==false)
      {
         System.out.println("You have not disembarked!");
         System.exit(0);
      }
      board=true;
      dis =false;
   }
   public double cost(int zone1, Station s2)
   {
      double cost = 0;
      int start = zone1;
      int stop = s2.getZone();
      int value = Math.abs(stop - start);
      if(start==stop)
      {
         cost+=0.5;
      }
      else
      {
         cost+=0.5;
         cost+=0.75*(value);
      }
      return cost;
   }
   public void disembark(Station s)
   {
      
      
      if(board==true)
      {
         money1 = money-cost(zone, s);
         if(money1<0)
         {
         System.out.println("Warning: Your balance is too low to ride to here.");
         System.exit(0);
         }
         System.out.println("From " + name + " to " + s.getName() + " costs " + df.format(cost(zone, s))+"." + "\nBalance: "+ df.format(money1));
         name = s.getName();
         money = money1;
      }
      else
      {
         System.out.println("Please see the station manager!");
         System.exit(0);
      }
      dis = true;
      board=false;
   }
   
}
class Station
{
   private String name;
   private int zone;
   public Station(String s, int x)
   {
      name = s;
      zone = x;
   }
   public Station()
   {
      name = "Vienna";
      zone = 0;
   }
   public String getName()
   {
      return name;
   }
   public int getZone()
   {
      return zone;
   }
   public void setName(String s)
   {
      name = s;
   }
   public void setZone(int x)
   {
      zone = x;
   }
   public String toString()
   {
      return name;
   }
}

 
