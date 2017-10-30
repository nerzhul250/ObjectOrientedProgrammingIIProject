package mundo;

public class FormulaParaParametrizarIncompleta extends Exception {

	private String formula;
	public FormulaParaParametrizarIncompleta(String men, String form){
		super(men);
		formula = form;
	}
	public String darFormula() {
		return formula;
	}
	public void modificarFormula(String formula) {
		this.formula = formula;
	}
	
}
