import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class LadowaniePlanszyActionListener implements ActionListener {

	public enum AkcjeLadowaniaPlanszy {
		ZALADUJ_MALA,
	    ZALADUJ_SREDNIA,
	    ZALADUJ_DUZA,
	    ZALADUJ_OGROMNA
	  }
	
	JFrame glowneMenu;
	
	public LadowaniePlanszyActionListener(JFrame glowneMenu) {
		this.glowneMenu = glowneMenu;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {

		if(evt.getActionCommand() == AkcjeLadowaniaPlanszy.ZALADUJ_MALA.name()){
			glowneMenu.setVisible(false);
			GlowneOkno glowneOkno = new GlowneOkno(WielkoscPlanszyEnum.MALA);
		}else if(evt.getActionCommand() == AkcjeLadowaniaPlanszy.ZALADUJ_SREDNIA.name()){
			glowneMenu.setVisible(false);
			GlowneOkno glowneOkno = new GlowneOkno(WielkoscPlanszyEnum.SREDNIA);
		}else if(evt.getActionCommand() == AkcjeLadowaniaPlanszy.ZALADUJ_DUZA.name()){
			glowneMenu.setVisible(false);
			GlowneOkno glowneOkno = new GlowneOkno(WielkoscPlanszyEnum.DUZA);
		}else if(evt.getActionCommand() == AkcjeLadowaniaPlanszy.ZALADUJ_OGROMNA.name()){
			glowneMenu.setVisible(false);
			GlowneOkno glowneOkno = new GlowneOkno(WielkoscPlanszyEnum.OGROMNA);
		}
		
	}

}