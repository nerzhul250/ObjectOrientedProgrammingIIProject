package mundo;

public class NoEsNumeroException extends Exception {

	/**
	 * indice de la casilla en donde no hay un número
	 */
	private String indice;
	/**
	 * Crea una nueva excepción que indica que una casilla no tiene un número
	 * @param men mensaje
	 * @param ind indice de la casilla
	 */
	public NoEsNumeroException(String men, String ind){
		super(men);
		indice= ind;
	}
	/**
	 * Devuelve el índice de la casilla 
	 * @return
	 */
	public String darIndice(){
		return indice;
	}
}
