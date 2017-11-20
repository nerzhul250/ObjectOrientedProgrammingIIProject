package mundo;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
/**
 * Es la clase encargada de representar una elipse
 * @author steve
 *
 */
public class Elipse extends CurvaParametrica implements Animable {
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
	/**
	 * Método encargado de encontrar el centro de x o de y según el índice
	 * <b>pre:</b> la fórmula pasada por parámetro, los x y y deben estar delimitados con ()
	 * @param indice indice desde donde el método empezará a rastrear
	 * @param form fórmula a extraer los centros
	 * @param num Es el centro que se busca
	 * @return String representa el centro de la formula
	 */
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
	/**
	 * Encuentra el coeficiente de la elipse ya sea el de x o de y
	 * @param indice indice desde donde se empezará a analizar
	 * @param form la fórmula. Debe estar en forma canónica y sus números expresados sin operaciones. Deben ser positivos
	 * @param num Es el coeficiente parcial
	 * @return String el coeficiente que se halló
	 */
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
	/**
	 * Da la posición en x de la elipse según el parámetro t
	 * @param t es el parámetro t que arrojará un número para x
	 * @return double coordenada en x de la elipse según t
	 */
	public double darPosicionX(double t) {
		double numero= (Math.cos(t)/Math.sqrt(coeficienteX))+darCentroX();
		return numero;
	}
	/**
	 * Da la posición en x de la elipse según el parámetro t
	 * @param t Es el parámetro t
	 * @return double posición en y de la elipse según el parámetro t
	 */
	public double darPosicionY(double t) {
		double numero= (Math.sin(t)/Math.sqrt(coeficienteY))+darCentroY();
		return numero;
	}

	/**
	 * Método que dibuja en el panel la elipse
	 * @param g2d es el graphics del plano donde se va a dibujar
	 * @param alcance es el máximo alcance que se verá en el plano
	 * @param traslY es dónde está dibujado el centro en el plano en y
	 * @param traslX es donde esta dibujado el centro en el plano en x
	 */
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
	/**
	 * Método que devuelve la fórmula de la elipse
	 */
	@Override
	public String toString(){
		String centroX= (super.darCentroX()*-1<0)?""+super.darCentroX()*-1:"+"+super.darCentroX()*-1;
		String centroY=(super.darCentroY()*-1<0)?""+super.darCentroY()*-1:"+"+super.darCentroY()*-1;
		String men=coeficienteX+"(x"+centroX+")^2+"+coeficienteY+"(y"+centroY+")^2=1";
		return men;
	}

	/**
	 * devuelve el coeficiente de la elipse en x
	 * @return
	 */
	public Double darCoeficienteX() {
		return coeficienteX;
	}
	/**
	 * modifica el coeficiente en x de la elipse
	 * @param coeficienteX
	 */
	public void modificarCoeficienteX(Double coeficienteX) {
		this.coeficienteX = coeficienteX;
	}
	/**
	 * Devuleve el coeficiente de Y de la elipse
	 * @return
	 */
	public Double darCoeficienteY() {
		return coeficienteY;
	}
	/**
	 * Modifica el coeficiente en y de la elipse
	 * @param coeficienteY
	 */
	public void modificarCoeficienteY(Double coeficienteY) {
		this.coeficienteY = coeficienteY;
	}

	/**
	 * Método que dibuja en el panel la elipse pero de manera animada
	 * @param g2d es el graphics del plano donde se va a dibujar
	 * @param alcance es el máximo alcance que se verá en el plano
	 * @param traslY es dónde está dibujado el centro en el plano en y
	 * @param traslX es donde esta dibujado el centro en el plano en x
	 */
	public void animarse(Graphics2D g2d, double alcance, double traslY, double traslX, int ancho) {
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
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
