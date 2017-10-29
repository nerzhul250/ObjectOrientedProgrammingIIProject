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
			fail("No debería lanzar excepción");
		}
	}
	private void setupEscenario2(){
		try {
			circunferencia= new Circunferencia("(x)^2+(y)^2=4");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail("No debería lanzar excepción");
		}
	}
	private void setupEscenario3(){
		try {
			circunferencia= new Circunferencia("(x+12)^2+(y+12)^2=16");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail("No debería lanzar excepción");
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
		
	}
	
	@Test(expected= FormulaParaParametrizarIncompleta.class)
	public void probarMetodoParserConExcepcion() throws FormulaParaParametrizarIncompleta{
		circunferencia = new Circunferencia("(x)^2+(y)^2");
	}

}
