package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import mundo.NoEsNumeroException;

public class PanelSistemaLineal extends JPanel implements ActionListener{
	
	private InterfazMathy principal;
	private JPanel panelMatrices;
	private JTextField[][] matriz1;
	private JTextField[][]matriz2;
	//Para el panel Opciones
	private JButton btnInicializarMatrices;
	private JButton btnCalcProducto;
	private JButton btnCalcDeterminante;
	private JButton btnCalcSolucion;
	private JButton btnCargarMatrizGigante1;
	private JButton btnCargarMatrizGigante2;
	String[] tamanosMatrices={"2x2","3x3","4x4","5x5","6x6","7x7"};
	private JComboBox listaTamano;
	
	//CONSTANTES
	public final static String CALCULAR_PRODUCTO="calcularProducto";
	public final static String CALCULAR_DETERMINANTE="calcDeterminante";
	public final static String CALCULAR_SOLUCION="calcSolucion";
	public final static String INICIALIZAR_MATRICES="iniMatrices";
	public final static String CARGAR_MATRICES_GIGANTES="cgrma1";
	public final static String MULTIPLICAR_MATRICES_GIGANTES="cgrma2";
	
	
	
	public PanelSistemaLineal(InterfazMathy p){
		principal=p;
		setLayout(new BorderLayout());
		panelMatrices= new JPanel();
		add(panelMatrices,BorderLayout.CENTER);
		//BOTONES PARA PANEL OPCIONES
		btnInicializarMatrices= new JButton("Iniciar matrices");
		btnInicializarMatrices.setActionCommand(INICIALIZAR_MATRICES);
		btnInicializarMatrices.addActionListener(this);
		
		btnCalcProducto= new JButton("Calcular Producto entre matrices");
		btnCalcProducto.addActionListener(this);
		btnCalcProducto.setActionCommand(CALCULAR_PRODUCTO);
		
		btnCalcDeterminante= new JButton("Calcular determinante matriz 1");
		btnCalcDeterminante.addActionListener(this);
		btnCalcDeterminante.setActionCommand(CALCULAR_DETERMINANTE);
		
		btnCalcSolucion=new JButton("Calcular solucion matriz 1");
		btnCalcSolucion.addActionListener(this);
		btnCalcSolucion.setActionCommand(CALCULAR_SOLUCION);
		
		btnCargarMatrizGigante1= new JButton("Cargar matrices gigantes");
		btnCargarMatrizGigante1.addActionListener(this);
		btnCargarMatrizGigante1.setActionCommand(CARGAR_MATRICES_GIGANTES);
		
		btnCargarMatrizGigante2= new JButton("Multiplicar Matrices gigantes");
		btnCargarMatrizGigante2.addActionListener(this);
		btnCargarMatrizGigante2.setActionCommand(MULTIPLICAR_MATRICES_GIGANTES);
		
		listaTamano=new JComboBox(tamanosMatrices);
		listaTamano.addActionListener(this);
		JPanel panelAuxiliarOpciones= new JPanel();
		panelAuxiliarOpciones.setLayout(new GridLayout(1,7));
		panelAuxiliarOpciones.add(listaTamano);
		panelAuxiliarOpciones.add(btnInicializarMatrices);
		panelAuxiliarOpciones.add(btnCalcProducto);
		panelAuxiliarOpciones.add(btnCalcDeterminante);
		panelAuxiliarOpciones.add(btnCalcSolucion);
		panelAuxiliarOpciones.add(btnCargarMatrizGigante1);
		panelAuxiliarOpciones.add(btnCargarMatrizGigante2);
		add(panelAuxiliarOpciones,BorderLayout.SOUTH);
		
	}
	public void inicializarMatrices(){
		int tamano=Integer.parseInt(listaTamano.getSelectedItem().toString().charAt(0)+"");
		remove(panelMatrices);
		matriz1= new JTextField[tamano][tamano];
		matriz2= new JTextField[tamano][tamano];
		for(int i =0;i<tamano;i++){
			for(int j=0;j<tamano;j++){
				matriz1[i][j]= new JTextField();
				matriz2[i][j]= new JTextField();
				matriz1[i][j].setPreferredSize(new Dimension(450/(tamano), 450/(tamano)));
				matriz1[i][j].setHorizontalAlignment(JLabel.CENTER);
				matriz1[i][j].setFont(new Font("Courier", Font.BOLD,18));
				matriz2[i][j].setPreferredSize(new Dimension(450/tamano, 450/tamano));
				matriz2[i][j].setHorizontalAlignment(JLabel.CENTER);
				matriz2[i][j].setFont(new Font("Courier", Font.BOLD,18));
			}
		}
//		JPanel ma1=new JPanel();
//		ma1.setLayout(new GridLayout(tamano,tamano));
//		ma1.setBorder(new TitledBorder("Matriz 1"));
		JPanel ma1=new JPanel();
		ma1.setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		ma1.setBorder(new TitledBorder("Matriz 1"));
		JPanel ma2=new JPanel();
		ma2.setLayout(new GridBagLayout());
		ma2.setBorder(new TitledBorder("Matriz 2"));
		for(int i=0;i<tamano;i++){
			for(int j=0;j<tamano;j++){
				gbc.gridx=j;
				gbc.gridy=i;
				ma1.add(matriz1[i][j],gbc);
				ma2.add(matriz2[i][j],gbc);
			}
		}
		panelMatrices= new JPanel();
		panelMatrices.setLayout(new GridLayout(1, 2));
		panelMatrices.add(ma1);
		panelMatrices.add(ma2);
		add(panelMatrices,BorderLayout.CENTER);
		
	}
	
	public double[][] darMatriz1() throws NoEsNumeroException{
		double[][] matriz=new double[matriz1.length][matriz1.length];
		for(int i =0;i<matriz.length;i++){
			for(int j=0;j<matriz.length;j++){
				try{
					matriz[i][j]=Integer.parseInt(matriz1[i][j].getText());
					
				}catch(NumberFormatException e){
					throw new NoEsNumeroException("Debes ingresar un valor en la casilla de la matriz 1: ", (i+1)+" "+(j+1));
				}
			}
		}
		return matriz;
		
	}
	public double[][] darMatriz2() throws NoEsNumeroException{
		double[][] matriz=new double[matriz1.length][matriz1.length];
		for(int i =0;i<matriz.length;i++){
			for(int j=0;j<matriz.length;j++){
				try{
					matriz[i][j]=Integer.parseInt(matriz2[i][j].getText());
					
				}catch(NumberFormatException e){
					throw new NoEsNumeroException("Debes ingresar un valor en la casilla de la matriz 2: ", (i+1)+" "+(j+1));
				}
			}
		}
		return matriz;
		
	}

	public void actionPerformed(ActionEvent e) {
		String comando= e.getActionCommand();
		if(comando.equals(INICIALIZAR_MATRICES)){
			inicializarMatrices();
			principal.pack();
		}else if(comando.equals(CALCULAR_PRODUCTO)){
			principal.iniciarProductoEntreMatrices();
		}else if(comando.equals(CALCULAR_DETERMINANTE)){
			principal.calcularDeterminanteMatriz1();
		}else if(comando.equals(CALCULAR_SOLUCION)){
			principal.ventanaMatrizBVisible();
		}else if(comando.equals(CARGAR_MATRICES_GIGANTES)){
			principal.cargarMatricesGigantes();
		}else if(comando.equals(MULTIPLICAR_MATRICES_GIGANTES)){
			principal.iniciarMultiplicacionMatricesGigantes();
		}
		
	}
}
