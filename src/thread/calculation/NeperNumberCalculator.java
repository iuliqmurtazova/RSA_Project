package thread.calculation;

import java.math.BigDecimal;
import java.math.MathContext;


public class NeperNumberCalculator {
    
    MathContext mc = new MathContext(1000);
    BigDecimal e = new BigDecimal("0", mc);
    
    public static BigDecimal factorial(int x){
		BigDecimal prod = new BigDecimal("1");
		for(int i = x; i > 1; i--)
			prod = prod.multiply(new BigDecimal(i));
		return prod;
	}
    
    public BigDecimal calculateNeperNumberLinear(int members) {
        BigDecimal numE = new BigDecimal("0", mc);
		for(int i = 0; i < members; i++){
		numE = numE.add(BigDecimal.valueOf(2*i+1).divide(factorial(2*i), mc)).
				setScale(60,BigDecimal.ROUND_HALF_UP);
		}
		return numE;
	}

    
    public BigDecimal calculateNeperNumberAsynch(int members, int threadNumber) {
		Thread[] thread = new Thread[threadNumber];
		
		for (int t = 0; t < thread.length; t++) {
			final int t2 = t;
			thread[t] = new Thread(){
				public void run(){
					for(int i = t2; i < members; i+=threadNumber){
						synchronized (e) {
							e = e.add(BigDecimal.valueOf(2*i+1).divide(factorial(2*i),mc)).
                                  setScale(60,BigDecimal.ROUND_HALF_UP);
						}
					}
				}
			};
		}
		for (Thread thread2 : thread) {
			thread2.start();
			try {
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
          return e;
	}
}

