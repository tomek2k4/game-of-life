
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;


public class MainWindow extends JFrame {
	
	private static final int ILOSC_LINI_PANELU_STATUSU = 3;
	private static final int MAKS_LINI_W_KONSOLI = 1000;
	private static final int ODSTEP_GLOWNEGO_OKNA = 5;
	private static final int ODSTEP_PANELI = 5;
	
	private static final int MIN_WYSOLOSC_PANELU_STATUSU = 80;
	
	private Dimension wymiaryGlownegoOkna;
	private Dimension wymiaryPrzycisku;
	
	private Container panelSterowania = new JPanel();
	private Container panelStatusu = new JPanel();
    private JTextArea konsola;
	private Mikroswiat mikroswiat;
	
    private JButton startButton = new JButton("Start");
    private JButton pauzaButton = new JButton("Pauza");
    private JButton krokButton = new JButton("Do przodu");
    private JButton zapiszButton = new JButton("Zapisz");
    private JButton zaladujButton = new JButton("Zaladuj");
    
    public MainWindow(WielkoscPlanszyEnum plansza){
    	super("Gra w Zycie");
    	
    	wymiaryPrzycisku =  new JButton("default button").getPreferredSize();
    	
    	wymiaryGlownegoOkna = new Dimension(plansza.getWymiar().width + wymiaryPrzycisku.width + 2*ODSTEP_GLOWNEGO_OKNA +ODSTEP_PANELI, 
    			plansza.getWymiar().height +  MIN_WYSOLOSC_PANELU_STATUSU + 2*ODSTEP_GLOWNEGO_OKNA + ODSTEP_PANELI);
    			
        this.setSize(wymiaryGlownegoOkna);
        this.setMinimumSize(wymiaryGlownegoOkna);
        
    	Border empty = BorderFactory.createEmptyBorder(ODSTEP_GLOWNEGO_OKNA, ODSTEP_GLOWNEGO_OKNA, ODSTEP_GLOWNEGO_OKNA, ODSTEP_GLOWNEGO_OKNA);
    	((JComponent) getContentPane()).setBorder(empty);
        
    	//Create Mikroswiat Panel
    	this.mikroswiat = new Mikroswiat(plansza);

    	//Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridBagLayout());
        
        //Set up the content pane.
        this.addMikroswiatPanel();
        this.addPanelSterowania();
        this.addPanelStatusu();
        
        dodajKomunikatDoKonsoli("Rozloz komurki klikajac myszka lub zaladuj z pliku. Gdy juz skonczysz uruchom symulacje za pomoca przyciskow.");
        
        //Display the window.
        this.pack();
        this.setVisible(true);
    }
    
    public void addMikroswiatPanel(){
    	if(mikroswiat!=null){
    		Border border = BorderFactory.createLineBorder(Color.BLACK);
    		((JComponent) mikroswiat).setBorder(border);
    		GridBagConstraints c = new GridBagConstraints();
    		c.fill = GridBagConstraints.HORIZONTAL;
    		c.gridx = 0;
    		c.gridy = 0;
    		c.weighty = 0;
    		getContentPane().add(mikroswiat, c);    		
    	}
    }
    
    public void addPanelSterowania(){

    	panelSterowania.setLayout(new GridLayout(5,1,0,0));
    	panelSterowania.add(startButton);
    	panelSterowania.add(pauzaButton);
    	panelSterowania.add(krokButton);
    	panelSterowania.add(zapiszButton);
    	panelSterowania.add(zaladujButton);
    	
       	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 0.5;
    	c.gridx = 1;
    	c.gridy = 0;
    	c.anchor = GridBagConstraints.NORTH;
    	getContentPane().add(panelSterowania, c);
    }
    
    public void addPanelStatusu() {

    	Border empty = BorderFactory.createEmptyBorder(ODSTEP_PANELI,0,0,0);
    	panelStatusu.setLayout(new GridLayout(1,1, 0, 0));
    	konsola = new JTextArea(ILOSC_LINI_PANELU_STATUSU,20);
    	konsola.setEditable(false);
    	konsola.setLineWrap(true);
	    JScrollPane scrollPane = new JScrollPane(konsola,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        konsola.getDocument().addDocumentListener(new LimitLinesDocumentListener(MAKS_LINI_W_KONSOLI, false));
        panelStatusu.add(scrollPane);
        ((JComponent) panelStatusu).setBorder(empty);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_START; //bottom of space
        c.gridx = 0;       //aligned with mikroswiat panel
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 1;       //second row
        c.fill = GridBagConstraints.BOTH;
        getContentPane().add(panelStatusu, c);
    	
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
