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
	
	private void setupEscenario4(){
		try {
			circunferencia= new Circunferencia("(x-2)^2+(y)^2=16");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail("No debería lanzar excepción");
		}
	}
	private void setupEscenario5(){
		try {
			circunferencia= new Circunferencia("(x-2.5)^2+(y-2.6)^2=16.4");
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
	
	@Test
	public void probarToString(){
		setupEscenario1();
		assertTrue(circunferencia.toString().equals("(x-3.0)^2+(y-4.0)^2=4"));
		setupEscenario2();
		assertTrue(circunferencia.toString().equals("(x+-0.0)^2+(y+-0.0)^2=4"));
		setupEscenario3();
		assertTrue(circunferencia.toString().equals("(x+12.0)^2+(y+12.0)^2=16"));
		setupEscenario5();
		assertTrue(circunferencia.toString().equals("(x-2.5)^2+(y-2.6)^2=16,4"));
	}
	
	@Test
	public void probarMetodoBuscarNumero(){
		setupEscenario1();
		assertTrue(circunferencia.buscarNumero(1,"(x-3.0)^2+(y-4.0)^2=4" , "").equals("3.0"));
		assertTrue(circunferencia.buscarNumero(11,"(x-3.0)^2+(y-4.0)^2=4" , "").equals("4.0"));
		assertTrue(circunferencia.buscarNumero(1,"(x)^2+(y)^2=4" , "").equals(""));
		assertTrue(circunferencia.buscarNumero(7,"(x)^2+(y)^2=4" , "").equals(""));
		assertTrue(circunferencia.buscarNumero(1,"(x-2.5)^2+(y-2.6)^2=16,4" , "").equals("2.5"));
		assertTrue(circunferencia.buscarNumero(11,"(x-2.5)^2+(y-2.6)^2=16,4" , "").equals("2.6"));
	}
	
	@Test
	public void probarParametrizaciones(){
		setupEscenario1();
		for(int i=0;i<1000;i++){
			assertTrue(circunferencia.darPosicionX(i*2*Math.PI/1000)== Math.cos(i*2*Math.PI/1000)*2+circunferencia.darCentroX());
			assertTrue(circunferencia.darPosicionY(i*2*Math.PI/1000)== Math.sin(i*2*Math.PI/1000)*circunferencia.darRadio()+circunferencia.darCentroY());
		}
		setupEscenario2();
		for(int i=0;i<1000;i++){
			assertTrue(circunferencia.darPosicionX(i*2*Math.PI/1000)== Math.cos(i*2*Math.PI/1000)*circunferencia.darRadio()+circunferencia.darCentroX());
			assertTrue(circunferencia.darPosicionY(i*2*Math.PI/1000)== Math.sin(i*2*Math.PI/1000)*circunferencia.darRadio()+circunferencia.darCentroY());
		}
	}
	

}
