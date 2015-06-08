
public class StanMikroswiata {

	private ZrodloZmianyEnum zrodlo;
	private Integer liczbaNarodzonychKomorek;
	private Integer liczbaUsmierconychKomorek;
	private Integer liczbaZyjacych;
	private Integer liczbaMartwych;
	
	
	public StanMikroswiata(ZrodloZmianyEnum zrodlo,
			Integer liczbaNarodzonychKomorek, Integer liczbaUsmierconychKomorek) {
		super();
		this.zrodlo = zrodlo;
		this.liczbaNarodzonychKomorek = liczbaNarodzonychKomorek;
		this.liczbaUsmierconychKomorek = liczbaUsmierconychKomorek;
	}
	
	public ZrodloZmianyEnum getZrodlo() {
		return zrodlo;
	}


	public void setZrodlo(ZrodloZmianyEnum zrodlo) {
		this.zrodlo = zrodlo;
	}


	public Integer getLiczbaNarodzonychKomorek() {
		return liczbaNarodzonychKomorek;
	}


	public void setLiczbaNarodzonychKomorek(Integer liczbaNarodzonychKomorek) {
		this.liczbaNarodzonychKomorek = liczbaNarodzonychKomorek;
	}


	public Integer getLiczbaUsmierconychKomorek() {
		return liczbaUsmierconychKomorek;
	}


	public void setLiczbaUsmierconychKomorek(Integer liczbaUsmierconychKomorek) {
		this.liczbaUsmierconychKomorek = liczbaUsmierconychKomorek;
	}




}
