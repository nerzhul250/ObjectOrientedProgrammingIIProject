package mundo;

public class FormulaParaParametrizarIncompleta extends Exception {
	/**
	 * Formula que se quiere parametrizar
	 */
	private String formula;
	/**
	 * Crea una nueva excepción que indica que la fóruma que se quiere parametrizar
	 * está incompleta
	 * @param men mensaje
	 * @param form formula a parametriza
	 */
	public FormulaParaParametrizarIncompleta(String men, String form){
		super(men);
		formula = form;
	}
	/**
	 * Devuelve la fórmula que se quiere parametrizar
	 * @return
	 */
	public String darFormula() {
		return formula;
	}
	/**
	 * Modifica la fórmula a parametrizar
	 * @param formula
	 */
	public void modificarFormula(String formula) {
		this.formula = formula;
	}
	
}
