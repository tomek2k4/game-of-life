import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;





public class MainWindow extends JFrame {
	
	private Dimension wymiaryGlownegoOkna;
	private Dimension wymiaryPrzycisku;

	private int panelStatusuMinHeigh = 100;
	private int gap = 50;
	
	private Container panelSterowania = new JPanel();
	private Container panelStatusu = new JPanel();
	private Mikroswiat mikroswiat;
	
    private JButton startButton = new JButton("Start");
    private JButton pauzaButton = new JButton("Pauza");
    private JButton krokButton = new JButton("Do przodu");
    private JLabel statusLabel = new JLabel("Tu bedzie cos napisane.");
    
    
    
    public MainWindow(WielkoscPlanszyEnum plansza){
    	super("Gra w Zycie");
    	
    	wymiaryPrzycisku =  new JButton("default button").getPreferredSize();
    	
    	wymiaryGlownegoOkna = new Dimension(plansza.getWymiar().width + wymiaryPrzycisku.width + gap, 
    			plansza.getWymiar().height +  panelStatusuMinHeigh);
    			
        this.setSize(wymiaryGlownegoOkna);
        this.setMinimumSize(wymiaryGlownegoOkna);
   	
    	//Create Mikroswiat Panel
    	this.mikroswiat = new Mikroswiat(plansza);

    	//Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        this.getContentPane().setLayout(new GridBagLayout());
        //Set up the content pane.
        this.addMikroswiatPanel(this.getContentPane(),mikroswiat);
        
        this.addOtherComponentsToPane(this.getContentPane());
 
        //Display the window.
        this.pack();
        this.setVisible(true);
    	
    }
    
 
    public void addMikroswiatPanel(Container pane,JPanel mikroswiat){
    	Border border = BorderFactory.createLineBorder(Color.BLACK);
    	
    	((JComponent) mikroswiat).setBorder(border);
    	
    	GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    pane.add(mikroswiat, c);
    
    
    }
    
    public void addOtherComponentsToPane(Container pane) {
    	

    	GridLayout panelSterowaniaLayout = new GridLayout(3,1, 5, 5);  	
    	Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    	panelSterowania.setLayout(panelSterowaniaLayout);
    	panelSterowania.add(startButton);
    	panelSterowania.add(pauzaButton);
    	panelSterowania.add(krokButton);
        ((JComponent) panelSterowania).setBorder(empty);
    	

       	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 0.5;
    	c.gridx = 1;
    	c.gridy = 0;
    	c.anchor = c.NORTH;
    	pane.add(panelSterowania, c);
    	
    	
    	
        panelStatusu.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelStatusu.add(statusLabel);
    	
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        pane.add(panelStatusu, c);
    	
    	
    	
    }

    
    public static void main(String[] args) {
    	MainWindow okno = new MainWindow(WielkoscPlanszyEnum.DUZA);

    }
	
	
	

}
