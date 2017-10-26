package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoAgregarFuncion extends JDialog implements ActionListener{
	
	public final static String AGREGAR="AGREGAR";
	
	private InterfazMathy principal;
	private JComboBox<String>funciones;
	private JTextField txtAgrFun;
	private JButton butAgregar;
	public DialogoAgregarFuncion(InterfazMathy in, String title){
		super(in, title);
		principal=in;
		setLocation(principal.getX(),principal.getY());
		JPanel aux=new JPanel();
		aux.setLayout(new GridLayout(3,1));
		
		String[] fan={"Polinomio","Trigonometrico","Exponencial","General"};
		funciones=new JComboBox<String>(fan);
		txtAgrFun=new JTextField();
		butAgregar=new JButton("Agregar Funcion");
		butAgregar.addActionListener(this);
		butAgregar.setActionCommand(AGREGAR);
		
		aux.add(funciones);
		aux.add(txtAgrFun);
		aux.add(butAgregar);
		
		add(aux);
		pack();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(AGREGAR)){
			String fun=(String) funciones.getSelectedItem();
			if(fun.equals("Polinomio")){
				principal.agregarFuncion(txtAgrFun.getText(),3);
			}
			dispose();
		}
	}
}
