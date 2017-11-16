package mundo;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
/**
 * Es la clase encargada de representar una elipse
 * @author steve
 *
 */
public class Elipse extends CurvaParametrica {
	/**
	 * Es el coeficiente que representa el alargamiento de la elipse en
	 * el eje x
	 */
	private Double coeficienteX;
	/**
	 * Es el coeficiente que representa el alargamiento de la elipse en el
	 * eje y
	 */
	private Double coeficienteY;
	/**
	 * Crea una nueva elipse
	 * @param form formula de la elipse que se va a parametrizar
	 * @throws FormulaParaParametrizarIncompleta si hay alguna incosisencia en la formula
	 */
	public Elipse(String form) throws FormulaParaParametrizarIncompleta{
		parserElipse(0, form);
		if(coeficienteX== null || coeficienteY== null){
			throw new FormulaParaParametrizarIncompleta("Ingrese una formula valida", form);
		}
	}
	
	/**
	 * Es el método encargado de leer la fórmula de la elipse para extraer su información
	 * <b>pre:</b> La fórmula para parametrizar debe estar en forma canónica.<br>
	 * <b>pre:</b> La fórmula debe expresarse sin coeficientes o números en operaciones<br>
	 * <b>pre:</b> Cada variable así no tenga centro, debe estar encerrada en paréntesis<br>
	 * @param indice es el índice encargado de indicarle al método en qué letra está analizando
	 * @param form es la fórmula de la elipse.
	 * @throws FormulaParaParametrizarIncompleta En caso de que la fórmula no esté ingresada como se indica
	 */
	public void parserElipse(int indice, String form) throws FormulaParaParametrizarIncompleta{
		if(form==null|| form.equals(""))
			throw new FormulaParaParametrizarIncompleta("Ingrese una formula válida", form);
		if(indice<form.length()){
			if(form.charAt(indice)=='X'||form.charAt(indice)=='x'){
				String centroX= encontrarNumeroParaCentro(indice, form, "");
				if(centroX.equals("")){
					modificarCentroX(0.0);
				}else{
					modificarCentroX(Double.parseDouble(centroX));
					if(form.charAt(indice+1)=='+'){
						modificarCentroX(darCentroX()*-1);
					}
				}
				String coeX= encontrarCoeficiente(indice, form, "");
				if(coeX.equals("")){
					coeficienteX=1.0;
				}else{
					coeficienteX=Double.parseDouble(coeX);
				}
			}else if (form.charAt(indice)=='Y'|| form.charAt(indice)=='y'){
				String centroY= encontrarNumeroParaCentro(indice, form, "");
				if(centroY.equals("")){
					modificarCentroY(0.0);
				}else{
					modificarCentroY(Double.parseDouble(centroY));
					if(form.charAt(indice+1)=='+'){
						modificarCentroY(darCentroY()*-1);
					}
				}
				String coeY= encontrarCoeficiente(indice, form, "");
				if(coeY.equals("")){
					coeficienteY=1.0;
				}else{
					coeficienteY=Double.parseDouble(coeY);
				}
			}
			indice++;
			parserElipse(indice, form);
		}
	}
	
	public String encontrarNumeroParaCentro(int indice, String form, String num){
		String devolver= num;
		if(indice<form.length()){
			if(form.charAt(indice)!= ')'){
				try{
					devolver+=Integer.parseInt(form.charAt(indice)+"");
				}catch(NumberFormatException e){
					if(form.charAt(indice)=='.'){
						devolver+=".";
					}
				}
				indice++;
				devolver= encontrarNumeroParaCentro(indice, form, devolver);
			}
		}
		return devolver;
	}
	public String encontrarCoeficiente(int indice, String form, String num){
		String devolver = num;
		if(indice>=0&&form.charAt(indice)!='+'){
			try{
				devolver= Integer.parseInt(form.charAt(indice)+"")+devolver;
			}catch(NumberFormatException e){
				if(form.charAt(indice)=='.'){
					devolver= "."+devolver;
				}
			}
			indice--;
			devolver=encontrarCoeficiente(indice, form, devolver);
		}
		return devolver;
	}
	public double darPosicionX(double t) {
		double numero= (Math.cos(t)/Math.sqrt(coeficienteX))+darCentroX();
		return numero;
	}

	public double darPosicionY(double t) {
		double numero= (Math.sin(t)/Math.sqrt(coeficienteY))+darCentroY();
		return numero;
	}

	public void dibujarse(Graphics2D g2d, double alcance, double traslY, double traslX, int ancho) {
		g2d.setColor(darColor());
		double wx1=MathyGen.ANCHOPLANO*(darPosicionX(0)+alcance-traslX)/(2*alcance);
		double wy1=MathyGen.LARGOPLANO*(darPosicionY(0)-alcance-traslY)/(-2*alcance);
		for(int i =1; i<1001;i++){
			
			double wx=MathyGen.ANCHOPLANO*(darPosicionX(i*Math.PI*2/1000)+alcance-traslX)/(2*alcance);
			double wy=MathyGen.LARGOPLANO*(darPosicionY(i*Math.PI*2/1000)-alcance-traslY)/(-2*alcance);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawLine((int)(wx1), (int)(wy1),(int) (wx), (int)(wy));
			wx1=wx;
			wy1=wy;
			
		}
		
	}
	@Override
	public String toString(){
		String centroX= (super.darCentroX()*-1<0)?""+super.darCentroX()*-1:"+"+super.darCentroX()*-1;
		String centroY=(super.darCentroY()*-1<0)?""+super.darCentroY()*-1:"+"+super.darCentroY()*-1;
		String men=coeficienteX+"(x"+centroX+")^2+"+coeficienteY+"(y"+centroY+")^2=1";
		return men;
	}

	public Double darCoeficienteX() {
		return coeficienteX;
	}
	
	public void modificarCoeficienteX(Double coeficienteX) {
		this.coeficienteX = coeficienteX;
	}
	
	public Double darCoeficienteY() {
		return coeficienteY;
	}
	
	public void modificarCoeficienteY(Double coeficienteY) {
		this.coeficienteY = coeficienteY;
	}
}
