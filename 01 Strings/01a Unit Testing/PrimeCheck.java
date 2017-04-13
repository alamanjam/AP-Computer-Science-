//author: Ross Dempsey    date:  February 2013

public class PrimeCheck {
	public static boolean isPrime(int n) {
		for(int i = 2; i < Math.sqrt(n) + 1; i++)
			if(n % i == 0)
				return false;
         if(n==0||n==1)
            return false;
		return true;
	}
}