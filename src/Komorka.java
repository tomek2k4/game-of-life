import java.awt.Rectangle;

import javax.swing.JButton;


public class Komorka extends Rectangle{


	public static int CELL_SIZE = 20;
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

	public static void setCellSize(int i) {
		CELL_SIZE = 10;
	}	
}
