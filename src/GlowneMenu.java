import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class GlowneMenu extends JFrame{
	private LadowaniePlanszyActionListener ladowaniePlanszyActionListener;
	
	public GlowneMenu(){
		super();
	}
	
	
	public void wyswietlMenu(){
    	
    	setSize(new Dimension(300,400));
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	
        Container ekran = getContentPane();
        ekran.setLayout(null);
    	final JLabel title = new JLabel("Gra w Zycie");
    	title.setBounds(100, 20, 100, 20);
    	ekran.add(title);
    	title.setHorizontalAlignment(SwingConstants.CENTER);
    	final JLabel info = new JLabel("Tomasz Maslon, v1.0");
    	info.setBounds(80, 40, 150, 20);
    	info.setHorizontalAlignment(SwingConstants.CENTER);
    	ekran.add(info);
    	
        final JButton malaPlanszaButton = new JButton("MALA PLANSZA 20x20");
        malaPlanszaButton.setBounds(50, 80, 200, 40);
        malaPlanszaButton.setActionCommand(LadowaniePlanszyActionListener.AkcjeLadowaniaPlanszy.ZALADUJ_MALA.name());
		malaPlanszaButton.addActionListener(ladowaniePlanszyActionListener);
        ekran.add(malaPlanszaButton);

        final JButton sredniaPlanszaButton = new JButton("SREDNIA PLANSZA 30x30");
        sredniaPlanszaButton.setBounds(50, 140, 200, 40);
        sredniaPlanszaButton.setActionCommand(LadowaniePlanszyActionListener.AkcjeLadowaniaPlanszy.ZALADUJ_SREDNIA.name());
        sredniaPlanszaButton.addActionListener(ladowaniePlanszyActionListener);
        ekran.add(sredniaPlanszaButton);
        
        final JButton duzaPlanszaButton = new JButton("DUZA PLANSZA 40x40");
        duzaPlanszaButton.setBounds(50, 200, 200, 40);
        duzaPlanszaButton.setActionCommand(LadowaniePlanszyActionListener.AkcjeLadowaniaPlanszy.ZALADUJ_DUZA.name());
        duzaPlanszaButton.addActionListener(ladowaniePlanszyActionListener);
        ekran.add(duzaPlanszaButton);
        
        final JButton ogromanPlanszaButton = new JButton("OGROMNA PLANSZA 80x80");
        ogromanPlanszaButton.setBounds(50, 260, 200, 40);
        ogromanPlanszaButton.setActionCommand(LadowaniePlanszyActionListener.AkcjeLadowaniaPlanszy.ZALADUJ_OGROMNA.name());
        ogromanPlanszaButton.addActionListener(ladowaniePlanszyActionListener);
        ekran.add(ogromanPlanszaButton);

        setVisible(true);
		
	}
	
	
	public void setLadowaniePlanszyActionListener(
			LadowaniePlanszyActionListener ladowaniePlanszyActionListener) {
		this.ladowaniePlanszyActionListener = ladowaniePlanszyActionListener;
	}
	

}
