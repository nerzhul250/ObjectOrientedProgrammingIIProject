package mundo;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Elipse extends CurvaParametrica {

	private Double coeficienteX;
	private Double coeficienteY;
	
	public Elipse(String form){
		parserElipse(0, form);
	}
	
	public void parserElipse(int indice, String form){
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
	public double darPosicionX(int t) {
		double numero= (Math.cos(t)/Math.sqrt(coeficienteX))+darCentroX();
		return numero;
	}

	public double darPosicionY(int t) {
		double numero= (Math.sin(t)/Math.sqrt(coeficienteY))+darCentroY();
		return numero;
	}

	public void dibujarse(Graphics2D g2d, double alcance, double traslY, double traslX, int ancho) {
		g2d.setColor(darColor());
		for(int i =1; i<MathyGen.ANCHOPLANO*6*(1/coeficienteX);i++){
			double wx=MathyGen.ANCHOPLANO*(darPosicionX(i)+alcance-traslX)/(2*alcance);
			double wy=MathyGen.LARGOPLANO*(darPosicionY(i)-alcance-traslY)/(-2*alcance);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawLine((int)wx, (int)wy,(int) wx, (int)wy);
			
		}
		
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
