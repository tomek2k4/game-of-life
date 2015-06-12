import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class Zycie extends NotyfikatorGlownegoOkna implements Runnable{

	private static final int DZIESIEC_CYKLI = 10;
	private static final String REGULA_PATTERN = "(\\d*)\\/(\\d*)";
	
	private TreeSet<Integer> regulyPrzezycia;
	private TreeSet<Integer> regulyNarodzin;	
	private Integer wymiarMikroswiata;
	private Integer numerKroku = 0;
	private boolean run = false;
	private boolean symuluj = false;
	private long sleepIntervalMs;
	
	private List<Komorka> komorki;
	


	public Zycie(List<Komorka> komorki) {
		super();
		
		this.komorki = komorki;
		
		this.regulyPrzezycia = new TreeSet<Integer>();
		this.regulyPrzezycia.add(3);
		this.regulyPrzezycia.add(2);
		
		this.regulyNarodzin = new TreeSet<Integer>();
		this.regulyNarodzin.add(3);
		
		sleepIntervalMs = 1000;
	}
	
	
	@Override
	public void run() {
		int ln=0,lu=0;
		StanMikroswiata sm = null;
		run = true;
		while(run){
			if(isSymuluj()){
				sm = cykl();
				ln = ln + sm.getLiczbaNarodzonychKomorek();
				lu = lu + sm.getLiczbaUsmierconychKomorek();	
				zmianaStanu(ZrodloZmianyEnum.SYMULACJA,0,0,0,0,0,0);
				zmianaStanu(sm.getZrodlo(), 
						ln, 
						lu, 
						sm.getLiczbaZyjacych(), sm.getLiczbaMartwych(), 
						1, sm.getNumerCyklu());		
			}
			if(sm!=null){
				ln = 0;
				lu = 0;
				sm = null;
			}
			
			try {
				Thread.sleep(sleepIntervalMs);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void decCykl() {
		int ln=0,lu=0;

		StanMikroswiata sm = null;
		for(int i=0;i<DZIESIEC_CYKLI;i++){
			sm = cykl();
			ln = ln + sm.getLiczbaNarodzonychKomorek();
			lu = lu + sm.getLiczbaUsmierconychKomorek();
		}
		zmianaStanu(sm.getZrodlo(), 
				ln, 
				lu, 
				sm.getLiczbaZyjacych(), sm.getLiczbaMartwych(), 
				10, sm.getNumerCyklu());
	}
	
	public void jedenCykl(){
		StanMikroswiata sm = cykl();
		zmianaStanu(sm);
	}
	
	public boolean ustawRegule(String regulaStr){
        boolean result = true;
        String regulyPrzezyciaStr;
        String regulyNarodzinStr;
		
		if(regulaStr.matches(REGULA_PATTERN)){
			regulyPrzezycia.clear();
			regulyNarodzin.clear();
			
			int przezycie = 0;
			int narodziny = 0;
			regulyPrzezyciaStr = regulaStr.replaceAll(REGULA_PATTERN, "$1");
			regulyNarodzinStr = regulaStr.replaceAll(REGULA_PATTERN, "$2");
			
			BufferedReader przezycieBuffReader = new BufferedReader(new StringReader(regulyPrzezyciaStr));
			BufferedReader narodzinyBuffReader = new BufferedReader(new StringReader(regulyNarodzinStr));
			
	        try {
				while ((przezycie = przezycieBuffReader.read()) != -1) {
					regulyPrzezycia.add(Integer.parseInt(Character.toString ((char) przezycie)));
				}
				while ((narodziny = narodzinyBuffReader.read()) != -1) {
					regulyNarodzin.add(Integer.parseInt(Character.toString ((char) narodziny)));
				}
			} catch (IOException e) {
				result = false;
			}
		}else{
			result = false;
		}
		

		return result;
	}
	
	
	
	
	
	private StanMikroswiata cykl() {
		int liczbaNarodzonych = 0;
		int liczbaUsmierconych = 0;
		int liczbaZyjacychKomorek = 0;
		int liczbaMartwychKomorek = 0;
		wymiarMikroswiata = (int) Math.sqrt(komorki.size());
		numerKroku++;
		
		List<Komorka> obecneKomorki = new ArrayList<Komorka>(); //current state

		// make a deep copy of list
		for(Komorka komorka:komorki){
			obecneKomorki.add((Komorka)komorka.clone());
		}

		for(int index=0;index<komorki.size();index++){
			Komorka nowaKomorka = komorki.get(index);
			Komorka obecnaKomorka = obecneKomorki.get(index);
			int zyweKomorki = znajdzLiczbeZywychSasiadow(obecneKomorki,index);
			if(obecnaKomorka.getStan().equals(StanKomorkiEnum.MARTWA)){
				boolean czyNarodziSie = false;
				for(Integer narodziny:regulyNarodzin){
					if(narodziny.equals(zyweKomorki)){
						czyNarodziSie = true;
					}
				}
				if(czyNarodziSie==true) {
					nowaKomorka.setStan(StanKomorkiEnum.ZYWA);
					liczbaNarodzonych++;
					liczbaZyjacychKomorek++;
				}else{
					liczbaMartwychKomorek++;
				}
			}else if(obecnaKomorka.getStan().equals(StanKomorkiEnum.ZYWA)){
				boolean czyPrzezyje = false;
				for(Integer przezycie:regulyPrzezycia){
					if(przezycie.equals(zyweKomorki)){
						czyPrzezyje = true;
					}
				}
				if(czyPrzezyje == false){
					nowaKomorka.setStan(StanKomorkiEnum.MARTWA);
					liczbaUsmierconych++;
					liczbaMartwychKomorek++;
				}else{
					liczbaZyjacychKomorek++;
				}
			}

		}
	
		return new StanMikroswiata(ZrodloZmianyEnum.ZYCIE, 
				liczbaNarodzonych, liczbaUsmierconych, 
				liczbaZyjacychKomorek, liczbaMartwychKomorek,1,numerKroku);
	}

	private int znajdzLiczbeZywychSasiadow(List<Komorka> komorki,int idx) {
		
		IndeksyKomorek indeksySasiadow = new IndeksyKomorek();
		int liczbaZywychSasiadow = 0;
		
		indeksySasiadow.add(idx-wymiarMikroswiata-1);//upper-left
		indeksySasiadow.add(idx-wymiarMikroswiata);//upper
		indeksySasiadow.add(idx-wymiarMikroswiata+1);//upper-right
		indeksySasiadow.add(idx-1);//left
		indeksySasiadow.add(idx+1);//right
		indeksySasiadow.add(idx+wymiarMikroswiata-1);//lower-left
		indeksySasiadow.add(idx+wymiarMikroswiata);//lower
		indeksySasiadow.add(idx+wymiarMikroswiata+1);//lower-right
		
		for(Integer index:indeksySasiadow){
			if(komorki.get(index).getStan().equals(StanKomorkiEnum.ZYWA)){
				liczbaZywychSasiadow++;
			}
		}
		
		return liczbaZywychSasiadow;
	}

	
	private class IndeksyKomorek extends TreeSet<Integer>{

		@Override
		public boolean add(Integer e) {
			boolean result = false;	
			if( e>=0 && e<wymiarMikroswiata*wymiarMikroswiata-1){
				result = super.add(e);
			}		
			return result;
		}
	}

	public boolean isSymuluj() {
		return symuluj;
	}

	public void setSymuluj(boolean symuluj) {
		this.symuluj = symuluj;
	}

	public long getSleepIntervalMs() {
		return sleepIntervalMs;
	}


	public void setSleepIntervalMs(long sleepIntervalMs) {
		this.sleepIntervalMs = sleepIntervalMs;
	}
	
	public Integer getNumerKroku() {
		return numerKroku;
	}

	public void setNumerKroku(Integer numerKroku) {
		this.numerKroku = numerKroku;
	}
}
