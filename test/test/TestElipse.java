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
			fail();
		}
	}
	private void setupEscenario2(){
		try {
			elipse = new Elipse("(x-3)^2+0.25(y-4.4)^2=1");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail();
		}
	}
	private void setupEscenario3(){
		try {
			elipse = new Elipse("(x)^2+(y)^2=1");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail();
		}
	}
	private void setupEscenario4(){
		try {
			elipse = new Elipse("(y+4)^2+0.0625(x-3)^2=1");
		} catch (FormulaParaParametrizarIncompleta e) {
			fail();
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
	@Test (expected= FormulaParaParametrizarIncompleta.class)
	public void probarMetodoParserConExcepcion() throws FormulaParaParametrizarIncompleta {
		try {
			elipse = new Elipse("");
		} catch (FormulaParaParametrizarIncompleta e) {
			try {
				elipse = new Elipse("zas");
			} catch (FormulaParaParametrizarIncompleta e1) {
				elipse= new Elipse("0.1111-3.5^2+0.25(y-4^2=1");
			}

		}
	}



	@Test
	public void probarMetodoParaExtraerCentros(){
		setupEscenario1();
		assertTrue(elipse.encontrarNumeroParaCentro(7,"0.1111(x-3.5)^2+0.25(y-4)^2=1", "").equals("3.5"));
		assertTrue(elipse.encontrarNumeroParaCentro(21,"0.1111(x-3.5)^2+0.25(y-4)^2=1", "").equals("4"));
		assertTrue(elipse.encontrarNumeroParaCentro(1, "(x-3)^2+0.25(y-4.4)^2=1", "").equals("3"));
		assertTrue(elipse.encontrarNumeroParaCentro(12, "(x-3)^2+0.25(y-4.4)^2=1", "").equals("4.4"));
		assertTrue(elipse.encontrarNumeroParaCentro(1, "(x)^2+(y)^2=1", "").equals(""));
		assertTrue(elipse.encontrarNumeroParaCentro(7, "(x)^2+(y)^2=1", "").equals(""));
		assertTrue(elipse.encontrarNumeroParaCentro(1,"(y+4)^2+0.0625(x-3)^2=1", "").equals("4"));
		assertTrue(elipse.encontrarNumeroParaCentro(15,"(y+4)^2+0.0625(x-3)^2=1", "").equals("3"));
	}
	
	@Test
	public void probarMetodoParaExtraerCoeficientes(){
		setupEscenario1();
		assertTrue(elipse.encontrarCoeficiente(7, "0.1111(x-3.5)^2+0.25(y-4)^2=1","").equals("0.1111"));
		assertTrue(elipse.encontrarCoeficiente(21, "0.1111(x-3.5)^2+0.25(y-4)^2=1", "").equals("0.25"));
		assertTrue(elipse.encontrarCoeficiente(1, "(x-3)^2+0.25(y-4.4)^2=1", "").equals(""));
		assertTrue(elipse.encontrarCoeficiente(12, "(x-3)^2+0.25(y-4.4)^2=1", "").equals("0.25"));
		assertTrue(elipse.encontrarCoeficiente(1, "(y+4)^2+0.0625(x-3)^2=1", "").equals(""));
		assertTrue(elipse.encontrarCoeficiente(15, "(y+4)^2+0.0625(x-3)^2=1", "").equals("0.0625"));
	}
	
	@Test
	public void probarMetodoToString(){
		setupEscenario1();
		assertTrue(elipse.toString().equals("0.1111(x-3.5)^2+0.25(y-4.0)^2=1"));
		setupEscenario2();
		assertTrue(elipse.toString().equals("1.0(x-3.0)^2+0.25(y-4.4)^2=1"));
		setupEscenario4();
		assertTrue(elipse.toString().equals("0.0625(x-3.0)^2+1.0(y+4.0)^2=1"));
		setupEscenario3();
		assertTrue(elipse.toString().equals("1.0(x+-0.0)^2+1.0(y+-0.0)^2=1"));
	}
	@Test
	public void probarMetodoParametrizacionEnX(){
		//TODO
	}

}
