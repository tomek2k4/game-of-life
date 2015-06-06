import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JPanel;


public class Mikroswiat extends JPanel{

    private int columnCount;
    private int rowCount;
    private List<Komorka> cells;
    private Point selectedCell;
	private Dimension wymiarPlanszy;

    public Mikroswiat(WielkoscPlanszyEnum plansza) {
 
    	wymiarPlanszy = plansza.getWymiar();
    	rowCount = plansza.getWymiar().width / Komorka.CELL_SIZE;
    	columnCount = plansza.getWymiar().height / Komorka.CELL_SIZE;
    	
    	cells = new ArrayList<>(columnCount * rowCount);
  
        MouseAdapter mouseHandler;
        mouseHandler = new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
              Point point = e.getPoint();

              int width = getWidth();
              int height = getHeight();

              int cellWidth = width / columnCount;
              int cellHeight = height / rowCount;

              int column = e.getX() / cellWidth;
              int row = e.getY() / cellHeight;

              selectedCell = new Point(column, row);
              repaint();	
			}
        	  
        	
        	
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                Point point = e.getPoint();
//
//                int width = getWidth();
//                int height = getHeight();
//
//                int cellWidth = width / columnCount;
//                int cellHeight = height / rowCount;
//
//                int column = e.getX() / cellWidth;
//                int row = e.getY() / cellHeight;
//
//                selectedCell = new Point(column, row);
//                repaint();
//
//            }
        };
        addMouseListener(mouseHandler);
    }

    @Override
    public Dimension getPreferredSize() {
        return wymiarPlanszy;
    }

    @Override
    public void invalidate() {
        cells.clear();
        selectedCell = null;
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
        if (cells.isEmpty()) {
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Komorka cell =  new Komorka(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cell.setStan(StanKomorkiEnum.MARTWA);
                    cells.add(cell);
                }
            }
        }

        if (selectedCell != null) {

            int index = selectedCell.x + (selectedCell.y * columnCount);
            Komorka cell = cells.get(index);
            
            // Toggle state of selected cell
            if(cell.getStan().equals(StanKomorkiEnum.MARTWA)){
            	cell.setStan(StanKomorkiEnum.ZYWA);
            }else if(cell.getStan().equals(StanKomorkiEnum.ZYWA)){
            	cell.setStan(StanKomorkiEnum.MARTWA);
            }else{
            	//default, should never get here
            	cell.setStan(StanKomorkiEnum.MARTWA);
            }
        }

        //Redraw all states 
        for (Komorka cell : cells) {
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
	
