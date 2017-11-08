package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import mundo.Punto;
import mundo.Region;

import org.junit.Test;

public class TestRegion {
	private Region region;
	
	private void setUpEscenario1(){
		ArrayList<Punto>puntos=new ArrayList<Punto>();
		puntos.add(new Punto(1,1));
		puntos.add(new Punto(1,2));
		puntos.add(new Punto(2,2));
		puntos.add(new Punto(2,1));
		region=new Region(puntos,Color.black);
	}
	@Test
	public void testCalcularArea() {
		setUpEscenario1();
		assertTrue(region.getArea()==1);
	}

}
