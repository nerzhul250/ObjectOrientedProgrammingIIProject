package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mundo.MathyGen;

public class DialogoAgregarFuncion extends JDialog implements ActionListener{
	
	/**
	 * Constante de agregar
	 */
	public final static String AGREGAR="AGREGAR";
	
	/**
	 * Ventana principal
	 */
	private InterfazMathy principal;
	
	/**
	 * Lista de seleccion del tipo de funcion
	 */
	private JComboBox<String>funciones;
	/**
	 * lista de seleccion del grosor
	 */
	private JComboBox<Integer>grosor;
	/**
	 * campo para escribir la funcion
	 */
	private JTextField txtAgrFun;
	/**
	 * Paleta de colores
	 */
	private JColorChooser jccPaleta;
	/**
	 * Boton para agregar la funcion
	 */
	private JButton butAgregar;
	/**
	 * Muestra donde se va a hacer el agregar
	 */
	private JLabel lblAgregar;
	/**
	 * Muestra donde se seleccionara el grosor
	 */
	private JLabel lblGrosor;
	
	/**
	 * Costruye el dialogo
	 * @param in ventana principal
	 * @param title titulo del dialogo
	 */
	public DialogoAgregarFuncion(InterfazMathy in, String title){
		super(in,title);
		principal=in;
		setLocation(principal.getX()+100,principal.getY()+100);
		JPanel aux=new JPanel();
		JPanel aux2=new JPanel();
		aux.setLayout(new GridLayout(6,1));
		Integer[] fon={1,2,3,4};
		//TODO AGREGAR CIRCUNFERENCIA y elipse
		String[] fan={"Polinomio","Trigonometrico"};
		
		grosor=new JComboBox<Integer>(fon);
		funciones=new JComboBox<String>(fan);
		txtAgrFun=new JTextField();
		butAgregar=new JButton("Agregar Funcion");
		butAgregar.addActionListener(this);
		butAgregar.setActionCommand(AGREGAR);
		jccPaleta=new JColorChooser();
		jccPaleta.setPreviewPanel(new JPanel());
		jccPaleta.setColor(Color.black);
		lblAgregar=new JLabel("Escriba su funcion");
		lblGrosor=new JLabel("Elija el grosor de la funcion");
		
		
		aux2.add(jccPaleta);
		aux.add(funciones);
		aux.add(lblGrosor);
		aux.add(grosor);
		aux.add(lblAgregar);
		aux.add(txtAgrFun);
		aux.add(butAgregar);
		
		add(aux2,BorderLayout.NORTH);
		add(aux,BorderLayout.SOUTH);
		pack();
	}
	/**
	 * Metodo que decide que hacer segun el boton oprimido
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(AGREGAR)){
			String fun=(String) funciones.getSelectedItem();
			if(fun.equals("Polinomio")){
				principal.agregarFuncion(txtAgrFun.getText(),jccPaleta.getColor(),grosor.getSelectedIndex(),MathyGen.POLINOMIO);
			}else if(fun.equals("Trigonometrico")){
				principal.agregarFuncion(txtAgrFun.getText(),jccPaleta.getColor(),grosor.getSelectedIndex(),MathyGen.TRIGONOMETRICO);
			}
			dispose();
		}
	}
}
