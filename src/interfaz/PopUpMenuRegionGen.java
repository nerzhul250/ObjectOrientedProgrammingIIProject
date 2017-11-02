package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenuRegionGen extends JPopupMenu implements ActionListener {
	public final static String ORGANIZAR="ORGANIZAR";
	private JMenuItem itOrganizar;
	
	private InterfazMathy principal;
    public PopUpMenuRegionGen(InterfazMathy in){
    	principal=in;
        itOrganizar = new JMenuItem("Agregar nueva funcion");
        itOrganizar.addActionListener(this);
        itOrganizar.setActionCommand(ORGANIZAR);
        add(itOrganizar);
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ORGANIZAR)){
			principal.organizarRegiones();
		}
	}
}
