import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Mikroswiat extends JPanel{

	private Dimension wymiar;	
	
	public Mikroswiat(WielkoscPlanszyEnum wielkosc) {
		
		wymiar = wielkosc.getWymiar();
		this.setBackground(Color.WHITE);
		this.setPreferredSize(wymiar);
		
		
		
		this.addMouseListener(new MikroswiatMouseListener());
	}


	@Override
	public synchronized void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
	}


	private void drawGrid(){
		
		int width = wymiar.width;
		int height = wymiar.height;
		
		int size = Komorka.CELL_SIZE;
		
		
		
	}
	
	
    private class MikroswiatMouseListener extends MouseAdapter {

        private int startX;
        private int startY;

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Graphics g = getGraphics();
            g.setColor(Color.BLUE);
            g.drawLine(startX, startY, e.getX(), e.getY());
        }
	
    }
}