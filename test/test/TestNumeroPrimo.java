package test;

import static org.junit.Assert.*;
import mundo.NumeroPrimo;

import org.junit.Test;

public class TestNumeroPrimo {
	private NumeroPrimo numeroPrimo;
	private void setUpEscenario1(){
		numeroPrimo=null;
	}
	@Test
	public void testInicializacionNumeroPrimo() {
		setUpEscenario1();
		numeroPrimo=new NumeroPrimo("7",4);
		assertEquals("7",numeroPrimo.getNumero().toString());
	}
}
