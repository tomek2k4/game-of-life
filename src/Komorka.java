import java.awt.Rectangle;

import javax.swing.JButton;


public class Komorka extends Rectangle{

	public static final int CELL_SIZE = 20;
    private JButton komorkaButton;
    private StanKomorkiEnum stan = StanKomorkiEnum.MARTWA;
	
	public StanKomorkiEnum getStan() {
		return stan;
	}

	public void setStan(StanKomorkiEnum stan) {
		this.stan = stan;
	}

	public Komorka(int i, int j, int cellWidth, int cellHeight) {
		super(i,j,cellWidth,cellHeight);
	}

	public JButton getKomorkaButton() {
		return komorkaButton;
	}

	public void setKomorkaButton(JButton komorkaButton) {
		this.komorkaButton = komorkaButton;
	}

	
}
