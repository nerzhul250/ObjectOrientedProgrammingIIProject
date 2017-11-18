package mundo;

public class FormulaParaParametrizarIncompleta extends Exception {
	/**
	 * Formula que se quiere parametrizar
	 */
	private String formula;
	/**
	 * Crea una nueva excepci�n que indica que la f�ruma que se quiere parametrizar
	 * est� incompleta
	 * @param men mensaje
	 * @param form formula a parametriza
	 */
	public FormulaParaParametrizarIncompleta(String men, String form){
		super(men);
		formula = form;
	}
	/**
	 * Devuelve la f�rmula que se quiere parametrizar
	 * @return
	 */
	public String darFormula() {
		return formula;
	}
	/**
	 * Modifica la f�rmula a parametrizar
	 * @param formula
	 */
	public void modificarFormula(String formula) {
		this.formula = formula;
	}
	
}
