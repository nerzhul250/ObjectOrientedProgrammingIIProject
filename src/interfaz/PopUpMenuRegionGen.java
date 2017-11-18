package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenuRegionGen extends JPopupMenu implements ActionListener {
	public final static String ORGANIZAR_ASCENDENTEMENTE="ORGANIZAR";
	public final static String ORGANIZAR_DESCENDIENTEMENTE="ORGA";
	private JMenuItem itOrganizarAscendentemente;
	private JMenuItem itOrganizarDescendentemente;
	private InterfazMathy principal;
    public PopUpMenuRegionGen(InterfazMathy in){
    	principal=in;
        itOrganizarAscendentemente = new JMenuItem("Organizar regiones ascendentemente");
        itOrganizarAscendentemente.addActionListener(this);
        itOrganizarAscendentemente.setActionCommand(ORGANIZAR_ASCENDENTEMENTE);
        
        itOrganizarDescendentemente= new JMenuItem("Organizar regiones descendientemente");
        itOrganizarDescendentemente.setActionCommand(ORGANIZAR_DESCENDIENTEMENTE);
        itOrganizarDescendentemente.addActionListener(this);
        
        add(itOrganizarAscendentemente);
        add(itOrganizarDescendentemente);
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ORGANIZAR_ASCENDENTEMENTE)){
			principal.organizarRegionesAscendentemente();
		}else if(e.getActionCommand().equals(ORGANIZAR_DESCENDIENTEMENTE)){
			principal.organizarRegionesDescendientemente();
		}
	}
}
