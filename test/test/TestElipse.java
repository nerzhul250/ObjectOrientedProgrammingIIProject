package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Elipse;
import mundo.FormulaParaParametrizarIncompleta;

public class TestElipse {

	private Elipse elipse;
	
	private void setupEscenario1(){
		try {
			elipse = new Elipse("0.1111(x-3.5)^2+0.25(y-4)^2=1");
		} catch (FormulaParaParametrizarIncompleta e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setupEscenario2(){
		try {
			elipse = new Elipse("(x-3)^2+0.25(y-4.4)^2=1");
		} catch (FormulaParaParametrizarIncompleta e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setupEscenario3(){
		try {
			elipse = new Elipse("(x)^2+(y)^2=1");
		} catch (FormulaParaParametrizarIncompleta e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setupEscenario4(){
		try {
			elipse = new Elipse("(y+4)^2+0.0625(x-3)^2=1");
		} catch (FormulaParaParametrizarIncompleta e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void probarMetodoParser(){
		setupEscenario1();
		assertTrue(elipse.darCentroX()==3.5);
		assertTrue(elipse.darCentroY()==4);
		assertTrue(elipse.darCoeficienteX()==0.1111);
		assertTrue(elipse.darCoeficienteY()==0.25);
		setupEscenario2();
		assertTrue(elipse.darCentroX()==3);
		assertTrue(elipse.darCentroY()==4.4);
		assertTrue(elipse.darCoeficienteX()==1);
		assertTrue(elipse.darCoeficienteY()==0.25);
		setupEscenario3();
		assertTrue(elipse.darCentroX()==0);
		assertTrue(elipse.darCentroY()==0);
		assertTrue(elipse.darCoeficienteX()==1);
		assertTrue(elipse.darCoeficienteY()==1);
		setupEscenario4();
		assertTrue(elipse.darCentroX()==3);
		assertTrue(elipse.darCentroY()==-4);
		assertTrue(elipse.darCoeficienteX()==0.0625);
		assertTrue(elipse.darCoeficienteY()==1);
	}

}
