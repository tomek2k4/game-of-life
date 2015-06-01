import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;





public class MainWindow extends JFrame {
	
	private static final Dimension MALE_OKNO = new Dimension(300, 200);
	private static final Dimension SREDNIE_OKNO = new Dimension(600, 400);
	private static final Dimension DUZE_OKNO = new Dimension(800, 600);

    private Container panel_mikroswiat = new JPanel();
    private Container panel_status = new JPanel();
    private Container panel_sterowania = new JPanel();
    private JButton start = new JButton("Start");
    private JButton pauza = new JButton("Pauza");
    private JButton krok = new JButton("Do przodu");
    private JLabel status = new JLabel("Tu bedzie cos napisane.");
    private Border empty;
	
	public MainWindow(){
        super("Okno Glowne");
        setSize(DUZE_OKNO);
        
        setLocationRelativeTo(null);
        setResizable(false);

        Container ekran = getContentPane();
        ekran.setLayout( new BorderLayout() );
        
        
        panel_mikroswiat.setSize(new Dimension(300,200));
        ekran.add(panel_mikroswiat, BorderLayout.CENTER);
        ekran.add(panel_sterowania, BorderLayout.EAST);
        ekran.add(panel_status, BorderLayout.SOUTH);
        
        panel_status.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel_status.add(status);
        
        
        BoxLayout panel_sterowania_layout = new BoxLayout(panel_sterowania, BoxLayout.Y_AXIS);
        
        
        empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        panel_sterowania.setLayout(panel_sterowania_layout);
        panel_sterowania.add(Box.createRigidArea(new Dimension(0,5)));
        panel_sterowania.add(start);
        panel_sterowania.add(Box.createRigidArea(new Dimension(0,5)));
        panel_sterowania.add(pauza);
        panel_sterowania.add(Box.createRigidArea(new Dimension(0,5)));
        panel_sterowania.add(krok);
        ((JComponent) panel_sterowania).setBorder(empty);
        
        
        panel_mikroswiat.addMouseListener(new MyMouseListener());
		
	}
	
    private class MyMouseListener extends MouseAdapter {

        private int startX;
        private int startY;

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Graphics g = panel_mikroswiat.getGraphics();
            g.setColor(Color.BLUE);
            g.drawLine(startX, startY, e.getX(), e.getY());
        }
        
    }
	
	
	public static void main(String[] args) {
		MainWindow okno = new MainWindow();
        
        okno.setVisible(true);
	}
	
	
}
