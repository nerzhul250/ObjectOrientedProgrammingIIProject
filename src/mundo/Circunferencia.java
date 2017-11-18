package mundo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Circunferencia extends CurvaParametrica {

	
	private Double radio;
	public Circunferencia(String form) throws FormulaParaParametrizarIncompleta {
		parsearFormula(0, form);
		
	}
	/**
	 * M�todo encargado de extraer toda la informaci�n de la f�rmula para circunferencias
	 * @param indice indice de la letra en donde el m�todo est� analizando
	 * @param form Formula de la circunferencia. La formula debe est�r en forma can�nica, si no
	 * tiene centros igualmente encerrar el x y y en par�ntesis.
	 * @throws FormulaParaParametrizarIncompleta en caso de que no se cumpla con los requisitos
	 */
	public void parsearFormula(int indice, String form) throws FormulaParaParametrizarIncompleta{
		if(indice<form.length()){
			if(form.charAt(indice)=='x' || form.charAt(indice)=='X'){
				String numero= buscarNumero(indice, form, "");
				Double nume=(double) 0;
				if(!numero.equals("")){
					nume= Double.parseDouble(numero);
					if(form.charAt(indice+1)=='+'){
						nume=nume*(-1);
					}
				}
				super.modificarCentroX(nume);
			}else if(form.charAt(indice)=='Y'||form.charAt(indice)=='y'){
				String numero = buscarNumero(indice, form, "");
				Double nume= (double)0;
				if(!numero.equals("")){
					nume= Double.parseDouble(numero);
					if(form.charAt(indice+1)=='+'){
						nume=nume*(-1);
					}
				}
				super.modificarCentroY(nume);
			}else if(form.charAt(indice)=='='){
				String numero = buscarNumero(indice, form, "");
				if(numero.equals("")){
					throw new FormulaParaParametrizarIncompleta("La siguiente f�rmula para parametrizar est� incompleta o no es v�lida: ", form);
				}else{
					radio= Math.sqrt(Double.parseDouble(numero));
				}
			}
			indice++;
			parsearFormula(indice, form);
		}else if(radio ==null){
			throw new FormulaParaParametrizarIncompleta("La siguiente f�rmula para parametrizar est� incompleta o no es v�lida: ", form);
		}
	}
	/**
	 * M�todo que retorna la f�rmula de la circunferencia
	 */
	@Override
	public String toString(){
		DecimalFormat df= new DecimalFormat();
		df.setRoundingMode(RoundingMode.CEILING);
		String men="(x"+((super.darCentroX()*-1<0)?+super.darCentroX()*-1:"+"+super.darCentroX()*-1)+
				")^2+(y"+((super.darCentroY()*-1<0)?+super.darCentroY()*-1:"+"+super.darCentroY()*-1)+")^2="+
				(df.format(radio*radio));
		return men;
	}
	/**
	 * Rastrea un n�mero empez�ndolo a analizar del indice que se pasa por par�metro
	 * @param indice indice que indica donde est� analizando el m�todo
	 * @param form f�rmula del c�rculo. Los x y y deben estar delimitados con (). los n�meros 
	 * se deben expresar no en operaciones y los decimales con .
	 * @param num es e numero que se est� extrayendo
	 * @return String n�mero rastreado
	 */
	public String buscarNumero(int indice,String form, String num){
		String devolver= num;
		if(indice<form.length()){
			if(form.charAt(indice)!=')'){
				try{
					devolver+= Integer.parseInt(form.charAt(indice)+"");
				}catch(Exception e){
					if(form.charAt(indice)=='.'){
						devolver+='.';
					}
				}
				indice++;
				devolver= buscarNumero(indice, form, devolver);
			}
			
		}
		return devolver;
	}
	
	/**
	 * Devuelve la posici�n de la elipse en x seg�n el par�metro t
	 */
	public double darPosicionX(double t) {
		double numero =radio*Math.cos(t)+ darCentroX();
		return numero;
	}
	/**
	 * Devuelve la posici�n de la elipse en y seg�ne l par�metro t
	 */
	public double darPosicionY(double t) {
		double numero = radio*Math.sin(t)+darCentroY();
		return numero;
	}

	/**
	 * M�todo encargado de dibujar la circunferencia
	 * @param g2d graphics del panel donde se dibbujar�
	 * @param alcance es el alcance que tiene el plano
	 * @param tralY es la traslaci�n en y del centro en el plano
	 * @param tralX es la traslacion en x del centro en el plano
	 */
	public void dibujarse(Graphics2D g2d, double alcance, double traslY, double traslX, int ancho) {
		g2d.setColor(darColor());
		g2d.setStroke(new BasicStroke(1));
		int wx1=(int) (MathyGen.ANCHOPLANO*(darPosicionX(0)+alcance-traslX)/(2*alcance));
		int wy1=(int) (MathyGen.LARGOPLANO*(darPosicionY(0)-alcance-traslY)/(-2*alcance));
		for(int i =1; i<1001;i++){
			int wx=(int) (MathyGen.ANCHOPLANO*(darPosicionX(i*2*Math.PI/1000.0)+alcance-traslX)/(2*alcance));
			int wy=(int) (MathyGen.LARGOPLANO*(darPosicionY(i*2*Math.PI/1000.0)-alcance-traslY)/(-2*alcance));
			g2d.drawLine(wx1,wy1, wx,wy);
			wy1=wy;
			wx1=wx;
		}
	}

	/**
	 * Devuelve el radio
	 * @return
	 */
	public double darRadio(){
		return radio;
	}
	
}
