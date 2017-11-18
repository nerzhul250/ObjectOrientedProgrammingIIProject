package mundo;

import java.io.Serializable;
import java.math.BigInteger;

public abstract class Numero implements Serializable{
	private String numero;

	public BigInteger getNumero() {
		return new BigInteger(numero);
	}

	public void setNumero(String num) {
		numero=num;
	}

	public abstract String getFactorizacionPrima();

	public abstract String getDivisores();
	
}
