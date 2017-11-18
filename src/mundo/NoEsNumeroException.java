package mundo;

public class NoEsNumeroException extends Exception {

	/**
	 * indice de la casilla en donde no hay un n�mero
	 */
	private String indice;
	/**
	 * Crea una nueva excepci�n que indica que una casilla no tiene un n�mero
	 * @param men mensaje
	 * @param ind indice de la casilla
	 */
	public NoEsNumeroException(String men, String ind){
		super(men);
		indice= ind;
	}
	/**
	 * Devuelve el �ndice de la casilla 
	 * @return
	 */
	public String darIndice(){
		return indice;
	}
}
