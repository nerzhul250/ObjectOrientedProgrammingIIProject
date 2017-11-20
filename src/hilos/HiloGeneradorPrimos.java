package hilos;

import java.math.BigInteger;
import java.util.ArrayList;

import mundo.MathyGen;
import mundo.NumeroPrimo;

public class HiloGeneradorPrimos implements Runnable{
	private MathyGen mundo;
	
	public HiloGeneradorPrimos(MathyGen mundo) {
		this.mundo=mundo;
	}
	public void run() {
		ArrayList<NumeroPrimo>primos=mundo.getPrimos();
		BigInteger num=primos.get(primos.size()-1).getNumero().add(BigInteger.ONE);
		while(mundo.estaBuscandoPrimos()){
			boolean isPrime=true;
			for (int j = 0; j < primos.size()&&isPrime; j++) {
				if(num.mod(primos.get(j).getNumero()).equals(BigInteger.ZERO)){
					isPrime=false;
				}
			}
			if(isPrime){
				primos.add(new NumeroPrimo(num.toString(),primos.size()));
			}
			num=num.add(BigInteger.ONE);
		}
	}

}