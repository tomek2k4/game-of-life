import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Mikroswiat extends Observable{

	private Dimension wymiar;	
	private JPanel mikroswiatPanel=null;
	
	Komorka[][] tablicaKomorek; 
	
	MouseAdapter mouseHandler;
	
    private int columnCount = 5;
    private int rowCount = 5;
    private List<Rectangle> cells;
    private Point selectedCell;
	

	public Mikroswiat(WielkoscPlanszyEnum wielkosc) {
		
		this.mikroswiatPanel = new JPanel();
		
		wymiar = wielkosc.getWymiar();
		mikroswiatPanel.setBackground(Color.WHITE);
		mikroswiatPanel.setPreferredSize(wymiar);
		
		this.drawGrid();

		mouseHandler = new MikroswiatMouseAdapter();
		mikroswiatPanel.addMouseListener(mouseHandler);
		mikroswiatPanel.addMouseMotionListener(mouseHandler);
		

	}

	private void drawGrid(){
		
		int width = wymiar.width;
		int height = wymiar.height;
		
		int size = Komorka.CELL_SIZE;	
		
		rowCount = width / size;
		columnCount = height / size;
				
	}
	
	
    private class MikroswiatMouseAdapter extends MouseAdapter {

        private int startX;
        private int startY;

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Graphics g = mikroswiatPanel.getGraphics();
            g.setColor(Color.BLUE);
            g.drawLine(startX, startY, e.getX(), e.getY());
        }
	
    }

    public JPanel getMikroswiatPanel() {
    	return mikroswiatPanel;
    }
}