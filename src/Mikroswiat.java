import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JPanel;


public class Mikroswiat extends Observable{

    private int columnCount;
    private int rowCount;
    private List<Komorka> stanMikroswiata;

	private Point wybranaKomorka;
	private Dimension wymiarPlanszy;
	
	private MikroswiatJPanel mikroswiatJPanel;

    public Mikroswiat(WielkoscPlanszyEnum plansza) {
 
    	mikroswiatJPanel = new MikroswiatJPanel();
    	
    	wymiarPlanszy = plansza.getWymiar();
    	rowCount = plansza.getWymiar().width / Komorka.CELL_SIZE;
    	columnCount = plansza.getWymiar().height / Komorka.CELL_SIZE;
    	
    	stanMikroswiata = new ArrayList<>(columnCount * rowCount);
  
        MouseAdapter mouseHandler;
        mouseHandler = new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
              Point point = e.getPoint();

              int width = mikroswiatJPanel.getWidth();
              int height = mikroswiatJPanel.getHeight();

              int cellWidth = width / columnCount;
              int cellHeight = height / rowCount;

              int column = e.getX() / cellWidth;
              int row = e.getY() / cellHeight;

              wybranaKomorka = new Point(column, row);
              mikroswiatJPanel.repaint();
                            
			}
        	  
        };
        mikroswiatJPanel.addMouseListener(mouseHandler);
    }



	private class MikroswiatJPanel extends JPanel{
    	
    	@Override
    	public Dimension getPreferredSize() {
    		return wymiarPlanszy;
    	}
    	
    	@Override
    	public void invalidate() {
    		stanMikroswiata.clear();
    		wybranaKomorka = null;
    		super.invalidate();
    	}
    	
    	@Override
    	protected void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		Graphics2D g2d = (Graphics2D) g.create();
    		
    		int width = getWidth();
    		int height = getHeight();
    		
    		int cellWidth = width / columnCount;
    		int cellHeight = height / rowCount;
    		
    		int xOffset = (width - (columnCount * cellWidth)) / 2;
    		int yOffset = (height - (rowCount * cellHeight)) / 2;
    		
    		//Initialization
    		if (stanMikroswiata.isEmpty()) {
    			for (int row = 0; row < rowCount; row++) {
    				for (int col = 0; col < columnCount; col++) {
    					Komorka cell =  new Komorka(
    							xOffset + (col * cellWidth),
    							yOffset + (row * cellHeight),
    							cellWidth,
    							cellHeight);
    					cell.setStan(StanKomorkiEnum.MARTWA);
    					stanMikroswiata.add(cell);
    				}
    			}
    		}
    		
    		if (wybranaKomorka != null) {
    			
    			int index = wybranaKomorka.x + (wybranaKomorka.y * columnCount);
    			Komorka cell = stanMikroswiata.get(index);
    			
    			// Toggle state of selected cell
    			if(cell.getStan().equals(StanKomorkiEnum.MARTWA)){
    				cell.setStan(StanKomorkiEnum.ZYWA);
    			}else if(cell.getStan().equals(StanKomorkiEnum.ZYWA)){
    				cell.setStan(StanKomorkiEnum.MARTWA);
    			}else{
    				//default, should never get here
    				cell.setStan(StanKomorkiEnum.MARTWA);
    			}
    			wybranaKomorka = null;
    		}
    		
    		//Redraw all states 
    		for (Komorka cell : stanMikroswiata) {
    			g2d.setColor(Color.GRAY);
    			g2d.draw(cell);
    			if(cell.getStan().equals(StanKomorkiEnum.ZYWA)){
    				cell.setStan(StanKomorkiEnum.ZYWA);
    				g2d.setColor(Color.BLACK);            	
    				g2d.fill(cell);
    			}
    		}
    		
    		g2d.dispose();
    	}
    }

    
    private void zmianaStanu(ZrodloZmianyEnum zrodlo,int liczbaNarodzonych,int liczbaUsmierconych) {
        setChanged();
        
    
        notifyObservers(new StanMikroswiata(zrodlo,liczbaNarodzonych,liczbaUsmierconych));
        
        
        
        clearChanged();
    }
	
	
	
	
    
    
    public MikroswiatJPanel getMikroswiatJPanel() {
		return mikroswiatJPanel;
	}
    
    public List<Komorka> getStanMikroswiata() {
		return stanMikroswiata;
	}

	public void setStanMikroswiata(List<Komorka> stanMikroswiata) {
		this.stanMikroswiata = stanMikroswiata;
	}
}
	
