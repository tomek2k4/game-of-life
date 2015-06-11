import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class Zycie extends NotyfikatorGlownegoOkna implements Runnable{

	private static final int DZIESIEC_CYKLI = 10;
	private TreeSet<Integer> regulyPrzezycia;
	private TreeSet<Integer> regulyNarodzin;	
	private Integer wymiarMikroswiata;
	private Integer numerKroku = 0;
	
	public Zycie() {
		super();
		
		this.regulyPrzezycia = new TreeSet<Integer>();
		this.regulyPrzezycia.add(3);
		this.regulyPrzezycia.add(2);
		
		this.regulyNarodzin = new TreeSet<Integer>();
		this.regulyNarodzin.add(3);
	}
	
	
	public void decCykl(List<Komorka> komorki) {
		int ln=0,lu=0;

		StanMikroswiata sm = null;
		for(int i=0;i<DZIESIEC_CYKLI;i++){
			sm = cykl(komorki);
			ln = ln + sm.getLiczbaNarodzonychKomorek();
			lu = lu + sm.getLiczbaUsmierconychKomorek();
		}
		zmianaStanu(sm.getZrodlo(), 
				ln, 
				lu, 
				sm.getLiczbaZyjacych(), sm.getLiczbaMartwych(), 
				10, sm.getNumerCyklu());
	}
	
	public void jedenCykl(List<Komorka> komorki){
		StanMikroswiata sm = cykl(komorki);
		zmianaStanu(sm);
	}
	
	private StanMikroswiata cykl(List<Komorka> komorki) {
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


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}




}
