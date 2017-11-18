package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mundo.MathyGen;

public class DialogoAgregarCurvaParametrica extends JDialog implements ActionListener {
	/**
	 * Es la interfaz principal
	 */
	private InterfazMathy principal;
	/**
	 * Es el JcomboBox que permite seleccionar el tipo de curva a dibujar
	 */
	private JComboBox<String>curvas;
	/**
	 * Es el botón que permite agregar una nueva curva
	 */
	private JButton btnAgregar;
	/**
	 * Es el textField que permite ingresar la fóruma de la curva
	 */
	private JTextField txtFormula;
	/**
	 * Es el lbl que contiene el texto "Curvas"
	 */
	private JLabel lblCurvas;
	/**
	 * Es el lbl que contiene el texto "Formula"
	 */
	private JLabel lblFormula;
	/**
	 * Es el panel que contiene el seleccionador de colores
	 */
	private JColorChooser colores;
	/**
	 * Arreglo que tiene los tipos de curvas
	 */
	private String[] opciones={"Circunferencia","Elipse"};
	
	/**
	 * Crea un nuevo dialógo para crear una nueva curva paramétrica
	 * @param p Interfaz principal
	 */
	public DialogoAgregarCurvaParametrica(InterfazMathy p){
		super(p, "Agregar curva paramétrica");
		setLayout(new BorderLayout());
		principal=p;
		setLocation(principal.getX()+100,principal.getY()+100);
		
		btnAgregar=new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setActionCommand(DialogoAgregarFuncion.AGREGAR);
		
		lblCurvas= new JLabel("Curvas paramétricas");
		lblFormula= new JLabel("Ingrese su formula");
		
		txtFormula= new JTextField();
		
		curvas = new JComboBox<String>(opciones);
		colores= new JColorChooser();
		colores.setPreviewPanel(new JPanel());
		JPanel aux= new JPanel();
		aux.setLayout(new GridLayout(6,1));
		add(colores,BorderLayout.CENTER);
		aux.add(lblFormula);
		aux.add(lblFormula);
		aux.add(txtFormula);
		aux.add(lblCurvas);
		aux.add(curvas);
		aux.add(btnAgregar);
		add(aux, BorderLayout.SOUTH);
		pack();
	}
	/**
	 * Método que escucha los botones
	 */
	public void actionPerformed(ActionEvent arg0) {
		String comando=arg0.getActionCommand();
		if(comando.equals(DialogoAgregarFuncion.AGREGAR)){
			int curva=MathyGen.CIRCUNFERENCIA;
			if(curvas.getSelectedItem().toString().equals("Elipse"))
				curva=MathyGen.ELIPSE;
			principal.agregarCurvaParametrica(txtFormula.getText(), colores.getColor(), curva);
		}
		dispose();
	}

}
