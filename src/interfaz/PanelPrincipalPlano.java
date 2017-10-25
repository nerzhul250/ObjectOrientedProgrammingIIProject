package interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelPrincipalPlano extends JPanel{
	private PanelPlanoxy ppxy;
	private PanelObjetoDibujable pod;
	public PanelPrincipalPlano(){
		setLayout(new BorderLayout());
		ppxy=new PanelPlanoxy();
		pod=new PanelObjetoDibujable();
		
		add(pod,BorderLayout.WEST);
		add(ppxy,BorderLayout.EAST);
	}
	
}
