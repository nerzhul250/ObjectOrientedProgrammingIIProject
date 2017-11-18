package mundo;

public class MatrizNoInvertibleException extends Exception{
	/**
	 * Crea una neuva excepci�n que indica que la matriz a la que se queire sacar
	 * Soluci�n no es invertible
	 * @param men mensaje
	 */
	public MatrizNoInvertibleException(String men){
		super(men);
	}
}
