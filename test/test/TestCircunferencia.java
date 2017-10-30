package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Circunferencia;
import mundo.FormulaParaParametrizarIncompleta;

public class TestCircunferencia {

	private Circunferencia circunferencia;
	
	private void setupEscenario1(){
		try {
			circunferencia= new Circunferencia("(x-3)^2+(y-4)^2=4");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail("No deber�a lanzar excepci�n");
		}
	}
	private void setupEscenario2(){
		try {
			circunferencia= new Circunferencia("(x)^2+(y)^2=4");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail("No deber�a lanzar excepci�n");
		}
	}
	private void setupEscenario3(){
		try {
			circunferencia= new Circunferencia("(x+12)^2+(y+12)^2=16");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail("No deber�a lanzar excepci�n");
		}
	}
	
	private void setupEscenario4(){
		try {
			circunferencia= new Circunferencia("(x-2)^2+(y)^2=16");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail("No deber�a lanzar excepci�n");
		}
	}
	private void setupEscenario5(){
		try {
			circunferencia= new Circunferencia("(x-2.5)^2+(y-2.6)^2=16.4");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail("No deber�a lanzar excepci�n");
		}
	}
	@Test
	public void probarMetodoParser(){
		setupEscenario1();
		assertTrue(circunferencia.darCentroX()==3);
		assertTrue(circunferencia.darCentroY()==4);
		assertTrue(circunferencia.darRadio()==2);
		
		setupEscenario2();
		assertTrue(circunferencia.darCentroX()==0);
		assertTrue(circunferencia.darCentroY()==0);
		assertTrue(circunferencia.darRadio()==2);
		
		setupEscenario3();
		assertTrue(circunferencia.darCentroX()==-12);
		assertTrue(circunferencia.darCentroY()==-12);
		assertTrue(circunferencia.darRadio()==4);
		setupEscenario4();
		assertTrue(circunferencia.darCentroX()==2);
		assertTrue(circunferencia.darCentroY()==0);
		assertTrue(circunferencia.darRadio()==4);
		setupEscenario5();
		assertTrue(circunferencia.darCentroX()==2.5);
		assertTrue(circunferencia.darCentroY()==2.6);
		assertTrue(circunferencia.darRadio()==Math.sqrt(16.4));
		
	}
	
	@Test(expected= FormulaParaParametrizarIncompleta.class)
	public void probarMetodoParserConExcepcion() throws FormulaParaParametrizarIncompleta{
		circunferencia = new Circunferencia("(x)^2+(y)^2");
	}

}
