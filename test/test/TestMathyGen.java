package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import mundo.Circunferencia;
import mundo.CurvaParametrica;
import mundo.Elipse;
import mundo.FormulaParaParametrizarIncompleta;
import mundo.FuncionYaExisteException;
import mundo.MathyGen;
import mundo.MatrizNoInvertibleException;
import mundo.NombreFaltanteSistemaLinealException;
import mundo.Polinomio;
import mundo.Region;
import mundo.SistemaLineal;

import org.junit.Test;

public class TestMathyGen {
	private MathyGen mathyGen;
	
	private void setUpEscenario1(){
		mathyGen=new MathyGen();
	}
	@Test
	public void testAgregarFuncion() {
		setUpEscenario1();
		assertFalse(mathyGen.estaEnElArbol(new Polinomio("1x^2"),mathyGen.getRaizFuncion())!=null);
		try {
			mathyGen.agregarFuncion("1x^2",Color.white,2,3);
		} catch (FuncionYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(mathyGen.estaEnElArbol(new Polinomio("1x^2"),mathyGen.getRaizFuncion())!=null);
	}
	
	@Test
	public void probarMetodoAgregarSistemaLinealAlHistorial(){
		setUpEscenario1();
		SistemaLineal actual= mathyGen.darHistorialSistemaLineal();
		int cont=0;
		while(actual!= null){
			actual=actual.darSiguiente();
			cont++;
		}
		assertTrue(cont==0);
		double[][] a={{1,2,3},{2,3,4}};
		SistemaLineal s= new SistemaLineal(a,null);
		try {
			mathyGen.agregarSistemaLinealAlHistorial(s,"1");
		} catch (NombreFaltanteSistemaLinealException e) {
			fail();
		}
		cont=0;
		actual=mathyGen.darHistorialSistemaLineal();
		while(actual!= null){
			actual=actual.darSiguiente();
			cont++;
		}
		assertTrue(cont==1);
		
		actual=mathyGen.darHistorialSistemaLineal();
		cont=0;
		s= new SistemaLineal(a, null);
		try {
			mathyGen.agregarSistemaLinealAlHistorial(s, "3");
		} catch (NombreFaltanteSistemaLinealException e) {
			fail();
		}
		while(actual!= null){
			actual=actual.darSiguiente();
			cont++;
		}
		assertTrue(cont==2);
		
		actual=mathyGen.darHistorialSistemaLineal();
		cont=0;
		s= new SistemaLineal(a, null);
		try {
			mathyGen.agregarSistemaLinealAlHistorial(s, "1");
		} catch (NombreFaltanteSistemaLinealException e) {
			fail();
		}
		while(actual!= null){
			actual=actual.darSiguiente();
			cont++;
		}
		assertTrue(cont==2);
		
		s= new SistemaLineal(a, null);
		try {
			mathyGen.agregarSistemaLinealAlHistorial(s, null);
			fail();
		} catch (NombreFaltanteSistemaLinealException e) {
			
		}
		
	}
	@Test
	public void probarMetodoEliminarSistemaDelHistorial(){
		probarMetodoAgregarSistemaLinealAlHistorial();
		try {
			mathyGen.eliminarSistemaLinealDelHistorial("1");
		} catch (Exception e) {
			fail();
		}
		
		SistemaLineal actual=mathyGen.darHistorialSistemaLineal();
		int cont=0;
		while(actual!= null){
			if(actual.darAnterior()!= null){
				assertTrue(!actual.darAnterior().darNombre().equals("1"));
			}
				
			cont++;
			actual=actual.darSiguiente();
		}
		assertTrue(cont==1);
		try {
			mathyGen.eliminarSistemaLinealDelHistorial("3");
		} catch (Exception e) {
			fail();
		}
		actual=mathyGen.darHistorialSistemaLineal();
		assertTrue(actual== null);
		
		try {
			mathyGen.eliminarSistemaLinealDelHistorial("1");
			fail();
		} catch (Exception e) {
		}
	}
	
	@Test
	public void probarMetodosCargarYGuardarHistorialDeSistemasLineales(){
		probarMetodoAgregarSistemaLinealAlHistorial();
		try {
			mathyGen.guardarHistorialSistemaLineal();
			File ar= new File(MathyGen.RUTA_HISTORIAL_SISTEMAS_LINEALES);
			assertTrue(ar.exists());
		} catch (IOException e) {
			fail();
		}
		mathyGen.modificarHistorialSistemaLineal(null);
		assertTrue(mathyGen.darHistorialSistemaLineal()==null);
		try {
			mathyGen.cargarHistorialSistemaLineal();
		} catch (ClassNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
		SistemaLineal cargado= mathyGen.darHistorialSistemaLineal();
		assertTrue(cargado!= null);
	}
	
	@Test
	public void probarMetodoBuscarSistemaLineal(){
		setUpEscenario1();
		assertTrue(mathyGen.buscarSistemaLineal("1")== null);
		probarMetodoAgregarSistemaLinealAlHistorial();
		assertTrue(mathyGen.buscarSistemaLineal("1").darNombre().equals("1"));
		assertTrue(mathyGen.buscarSistemaLineal("3").darNombre().equals("3"));
		assertTrue(mathyGen.buscarSistemaLineal("2")== null);
	}
	
	@Test
	public void probarMetodoCalcularSolucionesMatriz1(){
		setUpEscenario1();
		double[][] matriz= {{1,2},{4,3}};
		mathyGen.iniciarSistemaLineal(matriz, null);
		double[]b= {1,2};
		mathyGen.darSistemaLineal().modificarMatrizB(b);
		try {
			String men= mathyGen.calcularSolucionesMatriz1();
			String com= "X1= "+(1.0/5)+"\n"+"X2= "+(2.0/5)+"\n";
			assertTrue(men.equals(com));
		} catch (MatrizNoInvertibleException e) {
			fail();
		}
	}
	
	@Test
	public void probarEliminarCurvaParametrica(){
		setUpEscenario1();
		CurvaParametrica c2;
		try {
			CurvaParametrica c1= new Elipse("(x)^2+(y)^2=1");
			c2 = new Circunferencia("(x)^2+(y)^2=4");
			mathyGen.añadirCurvaParametrica(c2);
			mathyGen.añadirCurvaParametrica(c1);
			assertTrue(mathyGen.darCurvasParametricas().size()==2);
			mathyGen.eliminarCurvaParametrica(c1);
			assertTrue(mathyGen.darCurvasParametricas().size()==1);
			mathyGen.eliminarCurvaParametrica(c2);
			assertTrue(mathyGen.darCurvasParametricas().size()==0);
		} catch (FormulaParaParametrizarIncompleta e) {
			fail();
		}
	}
	
	@Test
	public void probarAgregarCurvaParametrica(){
		setUpEscenario1();
		try {
			mathyGen.agregarCurvaParametrica("(x)^2+(y)^2=1", Color.BLACK, MathyGen.ELIPSE);
			assertTrue(mathyGen.darCurvasParametricas().get(0) instanceof Elipse);
			mathyGen.agregarCurvaParametrica("(x)^2+(y)^2=4", Color.blue, MathyGen.CIRCUNFERENCIA);
			assertTrue(mathyGen.darCurvasParametricas().get(1) instanceof Circunferencia);
		} catch (FormulaParaParametrizarIncompleta e) {
			fail();
		}
	}
	
	@Test
	public void probarMetodosQueCarganLasMatricesGigantes(){
		setUpEscenario1();
		try {
			assertTrue(mathyGen.cargarMatrizGigante1()!= null);
			assertTrue(mathyGen.cargarMatrizGigante2()!= null);
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void probarMetodoOrganizarRegionesDescendientemente(){
		setUpEscenario1();
		try {
			mathyGen.cargarEstado();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		mathyGen.ordenarRegionesDescendientemente();
		ArrayList<Region> h=mathyGen.darRegiones();
		assertTrue(mathyGen.darRegiones().size()!= 0);
		for(int i=0;i<h.size()-1;i++){
			assertTrue(h.get(i).compareTo(h.get(i+1))>0);
		}
	}
	
	@Test
	public void probarMetodoBuscarRegion(){
		setUpEscenario1();
		try {
			mathyGen.cargarEstado();
		} catch (Exception e) {
			fail();
		}
		mathyGen.organizarRegiones();
		assertTrue(mathyGen.buscarRegion("7,09").area().equals("7,09"));
		assertTrue(mathyGen.buscarRegion("12,59").area().equals("12,59"));
		assertTrue(mathyGen.buscarRegion("10,97").area().equals("10,97"));
		
	}

}
