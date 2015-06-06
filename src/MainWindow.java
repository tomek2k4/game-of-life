import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;



public class MainWindow extends JFrame {
	
	private static final int MAKS_LINI_W_KONSOLI = 1000;
	private Dimension wymiaryGlownegoOkna;
	private Dimension wymiaryPrzycisku;

	private int panelStatusuMinHeigh = 100;
	private int gap = 50;
	
	private Container panelSterowania = new JPanel();
	private Container panelStatusu = new JPanel();
    private JTextArea konsola;
	private Mikroswiat mikroswiat;
	
    private JButton startButton = new JButton("Start");
    private JButton pauzaButton = new JButton("Pauza");
    private JButton krokButton = new JButton("Do przodu");
    
    
    
    public MainWindow(WielkoscPlanszyEnum plansza){
    	super("Gra w Zycie");
    	
    	wymiaryPrzycisku =  new JButton("default button").getPreferredSize();
    	
    	wymiaryGlownegoOkna = new Dimension(plansza.getWymiar().width + wymiaryPrzycisku.width + gap, 
    			plansza.getWymiar().height +  panelStatusuMinHeigh);
    			
        this.setSize(wymiaryGlownegoOkna);
        this.setMinimumSize(wymiaryGlownegoOkna);
        
    	Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    	((JComponent) getContentPane()).setBorder(empty);
        
        
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
    	
	
    	Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    	panelSterowania.setLayout(new GridLayout(3,1, 5, 5));
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
    	
    	
    	empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    	panelStatusu.setLayout(new GridLayout(1,1, 5, 5));
    	konsola = new JTextArea(2, 30);
    	konsola.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(konsola);
//			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
//			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        konsola.getDocument().addDocumentListener(new LimitLinesDocumentListener(MAKS_LINI_W_KONSOLI, false));
        panelStatusu.add(konsola);
        ((JComponent) panelStatusu).setBorder(empty);
        
//    	JButton button = new JButton("Long-Named Button 4");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.ipady = 10;      //make this component tall
    	c.weightx = 0.0;
    	c.gridwidth = 3;
    	c.gridx = 0;
    	c.gridy = 1;
    	pane.add(konsola, c);
    	
    	
//        panelStatusu.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        konsola = new JTextArea(5,10);
//        konsola.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(konsola);
//        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
//        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        konsola.getDocument().addDocumentListener(
//        		new LimitLinesDocumentListener(MAKS_LINI_W_KONSOLI, false));
//
//        panelStatusu.add(konsola);
//        
        dodajKomunikatDoKonsoli("Tomek");
        dodajKomunikatDoKonsoli("Tomek1");
        dodajKomunikatDoKonsoli("Tomek2");
        dodajKomunikatDoKonsoli("Tomek3");
        dodajKomunikatDoKonsoli("Tomek4");
        dodajKomunikatDoKonsoli("Tomek5");
        dodajKomunikatDoKonsoli("Tomek6");
        dodajKomunikatDoKonsoli("Tomek7");
        dodajKomunikatDoKonsoli("Tomek8");
        dodajKomunikatDoKonsoli("Tomek9");
        dodajKomunikatDoKonsoli("Tomek10");
//    	
        


        
        
        JLabel label = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        //c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        pane.add(label, c);
    	
    }
    
    public void dodajKomunikatDoKonsoli(String text){
        konsola.setText(text + "\n" + konsola.getText());
        
        //textArea.append(text + newline);
        konsola.selectAll();

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        konsola.setCaretPosition(0);
    }

    public static void main(String[] args) {
    	MainWindow okno = new MainWindow(WielkoscPlanszyEnum.MALA);

    }
	
	
	

}
