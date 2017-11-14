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

public class DialogoAgregarCurvaParametrica extends JDialog implements ActionListener {

	private InterfazMathy principal;
	
	private JComboBox<String>curvas;
	private JButton btnAgregar;
	private JTextField txtFormula;
	private JLabel lblCurvas;
	private JLabel lblFormula;
	private JColorChooser colores;
	private String[] opciones={"Circunferencia","Elipse"};
	
	
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
	public void actionPerformed(ActionEvent arg0) {
		String comando=arg0.getActionCommand();
		if(comando.equals(DialogoAgregarFuncion.AGREGAR)){
			//TODO ACCIONAR EL BOTON
		}
		dispose();
	}

}
