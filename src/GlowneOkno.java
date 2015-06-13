

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;


public class GlowneOkno extends JFrame implements Observer,ActionListener {
	
	private static final Font FONT_SETUP = new Font("Verdana", Font.PLAIN, 11);

	private enum AkcjePlanszy {
	    START,
	    STOP,
	    ZALADUJ, 
	    DEC_DO_PRZODU,
	    DO_PRZODU,
	    ZAPISZ,
	    WYCZYSC
	  }
	
	private static final int ILOSC_LINI_PANELU_STATUSU = 5;
	private static final int MAKS_LINI_W_KONSOLI = 1000;
	private static final int ODSTEP_GLOWNEGO_OKNA = 5;
	private static final int ODSTEP_PANELI = 5;
	
	private static final int MIN_WYSOLOSC_PANELU_STATUSU = 140;
	
	private static final String ROZPOCZNIJ_STR = "Rozpocznij";
	private static final String WSTRZYMAJ_STR = "Wstrzymaj";
	
	private static final String REGULA_CONWAYA = "23/3";
	
	private Dimension wymiaryGlownegoOkna;
	private Dimension wymiaryPrzycisku;
	
	private Container panelSterowania = new JPanel();
	private Container panelStatusu = new JPanel();
    private JTextArea konsola;
	
    private Mikroswiat mikroswiat;
	private StanGry stanGry;
	private Zycie zycie;
	
	private Thread symulacjaThread;
	
	private JLabel regulaLabel = new JLabel("Regula:");
	private JFormattedTextField regulaTextField = new JFormattedTextField();
    private JButton startStopButton = new JButton(ROZPOCZNIJ_STR);
    private JButton krokButton = new JButton("Do przodu");
    private JButton decKrokButton = new JButton("x10");
    private JButton zapiszButton = new JButton("Zapisz");
    private JButton zaladujButton = new JButton("Zaladuj");
    private JButton wyczyscButton = new JButton("Wyczysc");
	private static JFrame mainMenu;

    
    public GlowneOkno(WielkoscPlanszyEnum plansza){
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
    	this.mikroswiat.addObserver(this);
    	
    	this.stanGry = new StanGry();
    	this.stanGry.addObserver(this);

    	this.zycie = new Zycie(mikroswiat.getListeKomorek());
    	this.zycie.addObserver(this);    	
    	symulacjaThread = new Thread(this.zycie);
    	symulacjaThread.start();
    	
    	//Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridBagLayout());
        
        //Set up the content pane.
        this.addMikroswiatPanel();
        this.addPanelSterowania();
        this.addPanelStatusu();
        
        dodajKomunikatDoKonsoli("Rozloz komorki klikajac myszka lub zaladuj z pliku. Gdy juz skonczysz uruchom symulacje za pomoca przyciskow.");
       
        //Display the window.
        this.pack();
        this.setVisible(true);
    }
    
    public void addMikroswiatPanel(){
    	if(mikroswiat!=null){
    		Border border = BorderFactory.createLineBorder(Color.BLACK);
    		((JComponent) mikroswiat.getMikroswiatJPanel()).setBorder(border);
    		GridBagConstraints c = new GridBagConstraints();
    		c.fill = GridBagConstraints.HORIZONTAL;
    		c.gridx = 0;
    		c.gridy = 0;
    		c.weighty = 0;
    		getContentPane().add(mikroswiat.getMikroswiatJPanel(), c);    		
    	}
    }
    
    public void addPanelSterowania(){

    	panelSterowania.setLayout(new GridLayout(7,1,0,ODSTEP_PANELI));
    	Container regulaCont = new JPanel(new GridLayout(2, 1));
    	regulaLabel.setFont(FONT_SETUP);
    	regulaLabel.setBorder(BorderFactory.createEmptyBorder(0,ODSTEP_PANELI,0,0));
    	regulaCont.add(regulaLabel);
    	regulaCont.add(regulaTextField);
    	regulaTextField.setFont(FONT_SETUP);
    	regulaTextField.setPreferredSize(wymiaryPrzycisku);
    	regulaTextField.setText(REGULA_CONWAYA);
    	
    	panelSterowania.add(regulaCont);
    	panelSterowania.add(startStopButton);
    	startStopButton.setActionCommand(AkcjePlanszy.START.name());
    	startStopButton.addActionListener(this);
    	panelSterowania.add(krokButton);
    	krokButton.setActionCommand(AkcjePlanszy.DO_PRZODU.name());
    	krokButton.addActionListener(this);
    	panelSterowania.add(decKrokButton);
    	decKrokButton.setActionCommand(AkcjePlanszy.DEC_DO_PRZODU.name());
    	decKrokButton.addActionListener(this);
    	panelSterowania.add(zapiszButton);
    	zapiszButton.setActionCommand(AkcjePlanszy.ZAPISZ.name());
    	zapiszButton.addActionListener(this);
    	panelSterowania.add(zaladujButton);
    	zaladujButton.setActionCommand(AkcjePlanszy.ZALADUJ.name());
    	zaladujButton.addActionListener(this);
    	panelSterowania.add(wyczyscButton);
    	wyczyscButton.setActionCommand(AkcjePlanszy.WYCZYSC.name());
    	wyczyscButton.addActionListener(this);
    	
       	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(0, ODSTEP_PANELI, 0, 0);
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
    	konsola.setFont(FONT_SETUP);
    	//konsola.setFont(konsola.getFont().deriveFont(10));
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
    	
    	GlowneMenu glowneMenu = new GlowneMenu();
    	LadowaniePlanszyActionListener ladowaniePlanszyActionListener = 
    			new LadowaniePlanszyActionListener(glowneMenu);
    	glowneMenu.setLadowaniePlanszyActionListener(ladowaniePlanszyActionListener);
    	glowneMenu.wyswietlMenu();
    	
    }

	@Override
	public void update(Observable o, Object arg) {
		StanMikroswiata sm = (StanMikroswiata)arg;
		StringBuilder str = new StringBuilder();
		String numerCykluInfo = "";
		
		if(sm.getZrodlo() == ZrodloZmianyEnum.SYMULACJA){
			mikroswiat.getMikroswiatJPanel().repaint();
		}
		
		
		if(sm.getZrodlo() == ZrodloZmianyEnum.UZYTKOWNIK){
			str.append("Gracz ");
			if(sm.getLiczbaNarodzonychKomorek()>0){
				str.append("stworzyl jedna nowa komorke");
				if(sm.getLiczbaUsmierconychKomorek()>0){
					str.append(" oraz ");
				}
			}
			if(sm.getLiczbaUsmierconychKomorek()>0){
				str.append("usmiercil jedna zyjaca komorke");
			}
		}else if(sm.getZrodlo() == ZrodloZmianyEnum.ZYCIE){
			str.append("Przez "+sm.getZmianaCykli()+" cykli");
			str.append(" zycie ");
			if(sm.getLiczbaNarodzonychKomorek()>0){
				str.append("stworzylo "+sm.getLiczbaNarodzonychKomorek()+" nowych komorek");
				if(sm.getLiczbaUsmierconychKomorek()>0){
					str.append(" oraz ");
				}
			}
			if(sm.getLiczbaUsmierconychKomorek()>0){
				str.append("usmiercilo "+sm.getLiczbaUsmierconychKomorek()+" zyjacych komorek");
			}
		}else if(sm.getZrodlo() == ZrodloZmianyEnum.ODCZYT_PLIKU){
			str.append("Po odczycie z pliku ");
			if(sm.getLiczbaNarodzonychKomorek()>0){
				str.append("narodzonych zostalo "+sm.getLiczbaNarodzonychKomorek()+" nowych komorek");
				if(sm.getLiczbaUsmierconychKomorek()>0){
					str.append(" oraz ");
				}
			}
			
			if(sm.getLiczbaUsmierconychKomorek()>0){
				str.append("usmierconych zostalo "+sm.getLiczbaUsmierconychKomorek()+" zyjacych komorek");
			}
		}
		
		if(sm.getLiczbaNarodzonychKomorek() != 0 ||
				sm.getLiczbaUsmierconychKomorek() != 0){
			//Zmiana jaka nastapila
			dodajKomunikatDoKonsoli(str.toString());
			
			if(sm.getZrodlo().equals(ZrodloZmianyEnum.ZYCIE)) numerCykluInfo= new String(" K = "+sm.getNumerCyklu());
			
			//podsumowanie stanu obecnego
			dodajKomunikatDoKonsoli("Obecnie jest "+sm.getLiczbaZyjacych()+" zyjacych komorek oraz "+
								sm.getLiczbaMartwych()+ " martwych."+numerCykluInfo);
		}

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand() == AkcjePlanszy.START.name()){
			startStopButton.setText(WSTRZYMAJ_STR);
			startStopButton.setActionCommand(AkcjePlanszy.STOP.name());
			mikroswiat.setSymulacja(true);
			dodajKomunikatDoKonsoli("Symulacja rozpoczeta by znow wprowadzac zmiany kliknij Wstrzymaj");
			krokButton.setEnabled(false);
			decKrokButton.setEnabled(false);
			zapiszButton.setEnabled(false);
			zaladujButton.setEnabled(false);
			wyczyscButton.setEnabled(false);
			
			if(zycie.ustawRegule(regulaTextField.getText())){
				dodajKomunikatDoKonsoli("Ustawiono regule: "+regulaTextField.getText());
			}else{
				dodajKomunikatDoKonsoli("Regula nie spelnia zalozen, wpisz np.:"+REGULA_CONWAYA);
			}
			zycie.setSymuluj(true);
		}else if(evt.getActionCommand() == AkcjePlanszy.STOP.name()){
			startStopButton.setText(ROZPOCZNIJ_STR);
			startStopButton.setActionCommand(AkcjePlanszy.START.name());
			mikroswiat.setSymulacja(false);
			dodajKomunikatDoKonsoli("Symulacja zatrzymana");
			krokButton.setEnabled(true);
			decKrokButton.setEnabled(true);
			zapiszButton.setEnabled(true);
			zaladujButton.setEnabled(true);
			wyczyscButton.setEnabled(true);
			
			zycie.setSymuluj(false);
		}else if (evt.getActionCommand() == AkcjePlanszy.ZAPISZ.name()) {
			try {
				stanGry.zapisz(mikroswiat.getListeKomorek());
				dodajKomunikatDoKonsoli("Zapisano stan gry pod nazwa pliku: "+StanGry.getNazwaPliku());
			} catch (IOException e) {
				dodajKomunikatDoKonsoli("Zapisanie stanu gry nie powiodlo sie!");
				e.printStackTrace();
			}
		}else if(evt.getActionCommand() == AkcjePlanszy.ZALADUJ.name()){
			try {
				stanGry.zaladuj(mikroswiat.getListeKomorek());
				//dodajKomunikatDoKonsoli("Poprawnie zaladowano stan gry z pliku");
				mikroswiat.getMikroswiatJPanel().repaint();
			} catch (IndexOutOfBoundsException e) {
				dodajKomunikatDoKonsoli("Plansza jest za mala w porownaniu do zapisanego pliku");
			} catch (IOException e) {
				dodajKomunikatDoKonsoli("Nie odnalazl pliku ze stanem gry");
				e.printStackTrace();
			}
		}else if(evt.getActionCommand() == AkcjePlanszy.DO_PRZODU.name()){
			if(!zycie.ustawRegule(regulaTextField.getText())){
				dodajKomunikatDoKonsoli("Regula nie spelnia zalozen, wpisz np.:"+REGULA_CONWAYA);
			}
			zycie.jedenCykl();
			mikroswiat.getMikroswiatJPanel().repaint();
		}else if(evt.getActionCommand() == AkcjePlanszy.DEC_DO_PRZODU.name()){
			if(!zycie.ustawRegule(regulaTextField.getText())){
				dodajKomunikatDoKonsoli("Regula nie spelnia zalozen, wpisz np.:"+REGULA_CONWAYA);
			}
			zycie.decCykl();
			mikroswiat.getMikroswiatJPanel().repaint();
		}else if(evt.getActionCommand() == AkcjePlanszy.WYCZYSC.name()){
			mikroswiat.getListeKomorek().clear();
			zycie.setNumerKroku(0);
			mikroswiat.getMikroswiatJPanel().repaint();
		}
	}
}
