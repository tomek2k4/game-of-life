import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;


public class StanGry {
	
	private static final String NAZWA_PLIKU = new String("gra_w_zycie_zapis.txt");
	
	public static void zapisz(List<Komorka> komorki) throws IOException{
        try (Writer w = new FileWriter(NAZWA_PLIKU)){
            
        	for (int i=0;i<komorki.size();i++) {
        		w.write(komorki.get(i).getStan().equals(StanKomorkiEnum.ZYWA) ? "z" : "m");
        		if (i!=komorki.size()-1)
					w.write(",");
				else
					w.write(";");
        	}
        	
        } catch (IOException e) {
            throw e;
        }
	}

	public static void zaladuj(){
		
	}
	
	public static String getNazwaPliku() {
		return NAZWA_PLIKU;
	}

}
