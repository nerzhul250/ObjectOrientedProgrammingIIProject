package mundo;

public class MatrizNoInvertibleException extends Exception{
	/**
	 * Crea una neuva excepción que indica que la matriz a la que se queire sacar
	 * Solución no es invertible
	 * @param men mensaje
	 */
	public MatrizNoInvertibleException(String men){
		super(men);
	}
}
