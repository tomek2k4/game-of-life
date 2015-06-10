import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Observable;


public class Zycie extends Observable implements Runnable{

	private TreeSet<Integer> regulyPrzezycia;
	private TreeSet<Integer> regulyNarodzin;	
	private Integer wymiarMikroswiata;
	
	public Zycie() {
		super();
		
		this.regulyPrzezycia = new TreeSet<Integer>();
		this.regulyPrzezycia.add(3);
		this.regulyPrzezycia.add(2);
		
		this.regulyNarodzin = new TreeSet<Integer>();
		this.regulyNarodzin.add(3);
	}
	
	
	public void jedenCykl(List<Komorka> komorki) {
		
		wymiarMikroswiata = (int) Math.sqrt(komorki.size());
		
		List<Komorka> obecneKomorki = new ArrayList<Komorka>(); //current state
//		List<Komorka> nowyStanMikroswiata =  new ArrayList<Komorka>(); //next state
		// make a deep copy of list
		for(Komorka komorka:komorki){
			obecneKomorki.add((Komorka)komorka.clone());
		}

		for(int index=0;index<komorki.size();index++){
			Komorka nowaKomorka = komorki.get(index);//(Komorka)(komorki.get(index).clone());
			Komorka obecnaKomorka = obecneKomorki.get(index);
			int zyweKomorki = znajdzLiczbeZywychSasiadow(obecneKomorki,index);
			if(obecnaKomorka.getStan().equals(StanKomorkiEnum.MARTWA)){
				boolean czyNarodziSie = false;
				for(Integer narodziny:regulyNarodzin){
					if(narodziny.equals(zyweKomorki)){
						czyNarodziSie = true;
					}
				}
				if(czyNarodziSie==true) nowaKomorka.setStan(StanKomorkiEnum.ZYWA);
			}else if(obecnaKomorka.getStan().equals(StanKomorkiEnum.ZYWA)){
				boolean czyPrzezyje = false;
				for(Integer przezycie:regulyPrzezycia){
					if(przezycie.equals(zyweKomorki)){
						czyPrzezyje = true;
					}
				}
				if(czyPrzezyje == false) nowaKomorka.setStan(StanKomorkiEnum.MARTWA);
			}
			//nowyStanMikroswiata.add(nowaKomorka);
		}
		//komorki = nowyStanMikroswiata;
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
