package mundo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class NumeroCompuesto extends Numero{
	private ArrayList<NumeroPrimo>factorizacionPrimos;
	public NumeroCompuesto(String numero){
		factorizacionPrimos=new ArrayList<NumeroPrimo>();
		this.setNumero(numero);
	}
	@Override
	public String getFactorizacionPrima() {
		StringBuilder sb=new StringBuilder();
		int counter=1;
		BigInteger nam=factorizacionPrimos.get(0).getNumero();
		for (int i = 1; i < factorizacionPrimos.size(); i++) {
			if(nam.equals(factorizacionPrimos.get(i).getNumero())){
				counter++;
			}else{
				sb.append(nam.toString()+"^"+counter);
				sb.append(" ");
				nam=factorizacionPrimos.get(i).getNumero();
				counter=1;
			}
		}
		sb.append(nam.toString()+"^"+counter);
		return sb.toString();
	}
	public int calcularFactoresPrimos(ArrayList<NumeroPrimo> primosEncontrados){
		int index=0;
		BigInteger temp=getNumero();
		while(!temp.equals(BigInteger.ONE)){
			if(index==primosEncontrados.size()){
				factorizacionPrimos.add(new NumeroPrimo(getNumero().toString(),0));
				return 0;
			}
			if(primosEncontrados.get(index).getNumero().equals(getNumero())){
				factorizacionPrimos.add(primosEncontrados.get(index));
				return index;
			}
			while(temp.mod(primosEncontrados.get(index).getNumero()).equals(BigInteger.ZERO)){
				factorizacionPrimos.add(primosEncontrados.get(index));
				temp=temp.divide(primosEncontrados.get(index).getNumero());
			}
			index++;
		} 
		return 0;
	}
	@Override
	public String getDivisores() {
		BigInteger num=BigInteger.ONE;
		StringBuilder sb=new StringBuilder();
		while(num.compareTo(getNumero())<=0){
			if(getNumero().mod(num).equals(BigInteger.ZERO)){
				sb.append(num.toString());
				sb.append(" ");
			}
			num=num.add(BigInteger.ONE);
		}
		return sb.toString();
	}
	@Override
	public String toString() {
		return getNumero().toString();
	}
}
