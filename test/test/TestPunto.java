package test;

import static org.junit.Assert.*;
import mundo.Punto;

import org.junit.Test;

public class TestPunto {
	private Punto punto;
	
	private void setUpEscenario1(){
		punto=null;
	}
	@Test
	public void testInicializacion() {
		punto=new Punto(1,1);
		assertTrue(punto.getX()==1 && punto.getY()==1);
	}

}
