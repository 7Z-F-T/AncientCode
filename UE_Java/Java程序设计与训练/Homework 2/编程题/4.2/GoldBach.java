public class GoldBach{
	public static void main(String args[]){
		
		//Create an array to save primes
		int primes[]=new int[90];
		int n=0;//n:iterator
		for(int k=2;k<=100;k++){
			if(isPrime(k)==true){
				primes[n]=k;
				n++;
			}
		}
		//Decomposition

outer:
    for(int k=6;k<=100;k+=2){
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(k==(primes[i]+primes[j])){
					System.out.println(k+"="+primes[i]+"+"+primes[j]);
					continue outer;
				  }
			  }
		  }
		}
			
	}
	
	public static boolean isPrime(int i){
		double sqt=Math.sqrt((double)i);
		for(int j=2;j<=sqt;j++){
			if(i%j==0) return false;
		}
		return true;
	}
}
		