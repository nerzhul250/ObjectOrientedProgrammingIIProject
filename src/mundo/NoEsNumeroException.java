package mundo;

public class NoEsNumeroException extends Exception {

	private String indice;
	public NoEsNumeroException(String men, String ind){
		super(men);
		indice= ind;
	}
	public String darIndice(){
		return indice;
	}
}
