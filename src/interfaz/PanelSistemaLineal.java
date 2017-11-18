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
	/**
	 * Es la interfaz principal
	 */
	private InterfazMathy principal;
	/**
	 * Es el panel que contiene las matrices
	 */
	private JPanel panelMatrices;
	/**
	 * Matriz 1
	 */
	private JTextField[][] matriz1;
	/**
	 * Matriz 2
	 */
	private JTextField[][]matriz2;
	//Para el panel Opciones
	/**
	 * Botón encargado de inicializar las matrices
	 */
	private JButton btnInicializarMatrices;
	/**
	 * Botpon encargado de lanzar la orden de calcular el producto entre matrices
	 */
	private JButton btnCalcProducto;
	/**
	 * Es el botón que ordena el cáluclo de determinante
	 */
	private JButton btnCalcDeterminante;
	/**
	 * Es el botón que calcula la solución dela matriz 1
	 */
	private JButton btnCalcSolucion;
	/**
	 * botón que carga la matriz gigante 1
	 */
	private JButton btnCargarMatrizGigante1;
	/**
	 * Botón que carga la matriz gingante 2
	 */
	private JButton btnCargarMatrizGigante2;
	/**
	 * Botón que guarda el sistema actual en el historial
	 */
	private JButton btnGuardarHistorial;
	/**
	 * Botón que busca un sistema lineal en el historial
	 */
	private JButton btnBuscarSistema;
	/**
	 * Es un arreglo que contiene todos los posibles tamaños de las matrices
	 */
	String[] tamanosMatrices={"2x2","3x3","4x4","5x5","6x6","7x7"};
	/**
	 * JComboBox que indica el tamaño de las matrices
	 */
	private JComboBox listaTamano;
	
	//CONSTANTES
	/**
	 * Constante de calcular producto
	 */
	public final static String CALCULAR_PRODUCTO="calcularProducto";
	/**
	 * Constante de calcular determinante
	 */
	public final static String CALCULAR_DETERMINANTE="calcDeterminante";
	/**
	 * Constante que calcula una solución
	 */
	public final static String CALCULAR_SOLUCION="calcSolucion";
	/**
	 * constante de inicializar matrices
	 */
	public final static String INICIALIZAR_MATRICES="iniMatrices";
	/**
	 * constante de cargar amtrices
	 */
	public final static String CARGAR_MATRICES_GIGANTES="cgrma1";
	/**
	 * Constante de mutliplicar matrices gigantes
	 */
	public final static String MULTIPLICAR_MATRICES_GIGANTES="cgrma2";
	/**
	 * Constante de guardar el sistema en el historial
	 */
	public final static String GUARDAR_EN_HISTORIAL="grdEnHis";
	/**
	 * constante de buscar sistema en el historial
	 */
	public final static String BUSCAR_SISTEMA="bsrcas";
	
	/**
	 * Crea un nuevo panel de sistema lineal
	 * @param p interfaz principal
	 */
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
		
		btnGuardarHistorial= new JButton("Guardar sistema actual en el historial");
		btnGuardarHistorial.addActionListener(this);
		btnGuardarHistorial.setActionCommand(GUARDAR_EN_HISTORIAL);
		
		btnBuscarSistema= new JButton("Buscar sistema");
		btnBuscarSistema.addActionListener(this);
		btnBuscarSistema.setActionCommand(BUSCAR_SISTEMA);
		
		listaTamano=new JComboBox(tamanosMatrices);
		listaTamano.addActionListener(this);
		JPanel panelAuxiliarOpciones= new JPanel();
		panelAuxiliarOpciones.setLayout(new GridLayout(3,3));
		panelAuxiliarOpciones.add(listaTamano);
		panelAuxiliarOpciones.add(btnInicializarMatrices);
		panelAuxiliarOpciones.add(btnCalcProducto);
		panelAuxiliarOpciones.add(btnCalcDeterminante);
		panelAuxiliarOpciones.add(btnCalcSolucion);
		panelAuxiliarOpciones.add(btnCargarMatrizGigante1);
		panelAuxiliarOpciones.add(btnCargarMatrizGigante2);
		panelAuxiliarOpciones.add(btnGuardarHistorial);
		panelAuxiliarOpciones.add(btnBuscarSistema);
		add(panelAuxiliarOpciones,BorderLayout.SOUTH);
		
	}
	/**
	 * Inicializa las matrices de texto
	 */
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
	/**
	 * Inicializa las matrices según el tamaño pasado por parámetro
	 * @param tamano cant de filas o columnas
	 */
	public void inicializarMatrices(int tamano){
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
	
	/**
	 * Da la matriz 1 en valores
	 * @return double[][] matriz de valores
	 * @throws NoEsNumeroException en caso de que alguna casilla de la matriz 1 no sea un numero
	 */
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
	/**
	 * Devuelve la matriz 2 en valores
	 * @return double[][] matriz de valores
	 * @throws NoEsNumeroException en caso de que alguna casilla no sea un numero
	 */
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
	/**
	 * Despliega la matriz que se pasa por parámetro
	 * @param matriz !=null
	 */
	public void mostrarMatriz1(double[][] matriz){
		for(int i =0;i<matriz.length;i++){
			for(int j=0;j<matriz.length;j++){
				matriz1[i][j].setText(matriz[i][j]+"");
			}
		}
	}
	/**
	 * Despliega la matriz pasada por parámetro
	 * @param matriz !=null
	 */
	public void mostrarMatriz2(double[][] matriz){
		for(int i =0;i<matriz.length;i++){
			for(int j=0;j<matriz.length;j++){
				matriz2[i][j].setText(matriz[i][j]+"");
			}
		}
	}
	/**
	 * Método que escucha los botones
	 */
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
		}else if(comando.equals(GUARDAR_EN_HISTORIAL)){
			String men=JOptionPane.showInputDialog("Digite el nombre del sistema lineal");
			if(men!= null)
			principal.guardarSistema(men);
		}else if(comando.equals(BUSCAR_SISTEMA)){
			String men = JOptionPane.showInputDialog("Digite el nombre del sistema a buscar");
			if(men!= null)
				principal.buscarSistema(men);
		}
		
	}
}
