import java.util.Observable;


public class NotyfikatorGlownegoOkna extends Observable{
	

    protected void zmianaStanu(ZrodloZmianyEnum zrodlo,
    		int liczbaNarodzonych,int liczbaUsmierconych, 
    		int liczbaZyjacychKomorek,int liczbaMartwychKomorek,
    		int zmianaCykli,int numerCyklu) {
        setChanged();
        notifyObservers(new StanMikroswiata(zrodlo,liczbaNarodzonych,liczbaUsmierconych,liczbaZyjacychKomorek,liczbaMartwychKomorek,zmianaCykli,numerCyklu));
        clearChanged();
    }
    
    protected void zmianaStanu(StanMikroswiata sm){
    	zmianaStanu(sm.getZrodlo(),sm.getLiczbaNarodzonychKomorek(),sm.getLiczbaUsmierconychKomorek(),
    			sm.getLiczbaZyjacych(),sm.getLiczbaMartwych(),sm.getZmianaCykli(),sm.getNumerCyklu());
    }
	

}
