import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PrimeCheckTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void prime13() 
   {
      Assert.assertEquals("13 is prime", true, PrimeCheck.isPrime(13));
      Assert.assertEquals("0 is prime", false, PrimeCheck.isPrime(0));
      Assert.assertEquals("1 is prime", false, PrimeCheck.isPrime(1));
      Assert.assertEquals("21 is prime", false, PrimeCheck.isPrime(21));
      Assert.assertEquals("17 is prime", true, PrimeCheck.isPrime(17));
      Assert.assertEquals("4 is prime", false, PrimeCheck.isPrime(4));
   }
}
