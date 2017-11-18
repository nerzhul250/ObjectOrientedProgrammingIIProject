package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoAgregarNumero extends JDialog implements ActionListener{
	
	public final static String AGREGAR="AGREGAR";
	
	private JTextField txtNumero;
	private JLabel lblNumero;
	private JButton btAgregar;
	
	private InterfazMathy principal;
	public DialogoAgregarNumero(InterfazMathy in,String title){
		super(in,title);
		principal=in;
		
		txtNumero=new JTextField();
		lblNumero=new JLabel("Agregue el nuevo numero");
		btAgregar=new JButton("Agregar Numero");
		btAgregar.addActionListener(this);
		btAgregar.setActionCommand(AGREGAR);
		
		
		JPanel aux=new JPanel(new GridLayout(3,1));
		aux.add(lblNumero);
		aux.add(txtNumero);
		aux.add(btAgregar);
		
		add(aux);
		
		pack();
	}
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals(AGREGAR)){
			principal.agregarNumero(txtNumero.getText());
			dispose();
		}
	}

}
