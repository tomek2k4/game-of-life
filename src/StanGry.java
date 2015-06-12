import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;



public class StanGry extends NotyfikatorGlownegoOkna{
	
	private static final String NAZWA_PLIKU = new String("gra_w_zycie_zapis.txt");
	private static final String ZNACZNIK_ZYWA_KOMORKA = new String("z");
	private static final String ZNACZNIK_MARTWA_KOMORKA = new String("m");
	
	public void zapisz(List<Komorka> komorki) throws IOException{
        try (Writer w = new FileWriter(NAZWA_PLIKU)){
            
        	for (int i=0;i<komorki.size();i++) {
        		w.write(komorki.get(i).getStan().equals(StanKomorkiEnum.ZYWA) ? ZNACZNIK_ZYWA_KOMORKA : ZNACZNIK_MARTWA_KOMORKA);
        		if (i!=komorki.size()-1)
					w.write(",");
				else
					w.write(";");
        	}
        	
        } catch (IOException e) {
            throw e;
        }
	}

	public void zaladuj(List<Komorka> komorki) throws IOException,IndexOutOfBoundsException{
		int liczbaNarodzonych = 0;
		int liczbaUsmierconych = 0;
		int liczbaZyjacych = 0;
		int liczbaMartwych = 0;
		
		
        try (Reader inp = new FileReader(NAZWA_PLIKU)) {
        	Komorka komorka;
        	BufferedReader br = new BufferedReader(inp);
        	String line = null;
        	int i = 0;
        	while ((line = br.readLine()) != null) {
                for(String stanStr:line.split(",|;")){         	
                	try{
                		komorka = komorki.get(i);
                		if(stanStr.equals(ZNACZNIK_ZYWA_KOMORKA)){
                			liczbaZyjacych++;
                			if(komorka.getStan()!=StanKomorkiEnum.ZYWA){
                				liczbaNarodzonych++;
                				komorka.setStan(StanKomorkiEnum.ZYWA);
                			}
                		}else{
                			liczbaMartwych++;
                			if(komorka.getStan()!=StanKomorkiEnum.MARTWA){
                				liczbaUsmierconych++;
                				komorka.setStan(StanKomorkiEnum.MARTWA);
                			}
                		}              		
                	}catch(IndexOutOfBoundsException e){
                		throw e;
                	}
					i++;
                }

            }
        } catch (IOException e) {
        	throw e;
        }
        zmianaStanu(ZrodloZmianyEnum.ODCZYT_PLIKU,liczbaNarodzonych, liczbaUsmierconych, liczbaZyjacych, liczbaMartwych,0,0); 
	}
	
	
	
	public static String getNazwaPliku() {
		return NAZWA_PLIKU;
	}

}
