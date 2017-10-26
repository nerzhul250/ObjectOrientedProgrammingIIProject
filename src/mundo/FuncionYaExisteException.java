package mundo;

public class FuncionYaExisteException extends Exception{
	public FuncionYaExisteException(String form) {
		super("Esta funcion ya existe: "+form);
	}
}
