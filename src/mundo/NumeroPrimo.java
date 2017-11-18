package mundo;

import java.io.Serializable;

public class NumeroPrimo extends Numero{
	private int numeroDePrimo;
	public NumeroPrimo(String num, int nume){
		numeroDePrimo=nume;
		this.setNumero(num);
	}
	@Override
	public boolean equals(Object obj) {
		NumeroPrimo np=(NumeroPrimo)obj;
		return numeroDePrimo==np.darNumeroDePrimo();
	}
	@Override
	public int hashCode() {
		return numeroDePrimo;
	}
	@Override
	public String getFactorizacionPrima() {
		return getNumero().toString();
	}
	@Override
	public String getDivisores() {
		return getNumero().toString();
	}
	public int darNumeroDePrimo(){
		return numeroDePrimo;
	}
	@Override
	public String toString() {
		return getNumero().toString();
	}
}
