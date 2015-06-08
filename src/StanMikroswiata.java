
public class StanMikroswiata {

	private ZrodloZmianyEnum zrodlo;
	private Integer liczbaNarodzonychKomorek;
	private Integer liczbaUsmierconychKomorek;
	private Integer liczbaZyjacych;
	private Integer liczbaMartwych;
	
	
	public StanMikroswiata(ZrodloZmianyEnum zrodlo,
			Integer liczbaNarodzonychKomorek, Integer liczbaUsmierconychKomorek, int liczbaZyjacychKomorek, int liczbaMartwychKomorek) {
		super();
		this.zrodlo = zrodlo;
		this.liczbaNarodzonychKomorek = liczbaNarodzonychKomorek;
		this.liczbaUsmierconychKomorek = liczbaUsmierconychKomorek;
		this.liczbaZyjacych = liczbaZyjacychKomorek;
		this.liczbaMartwych = liczbaMartwychKomorek;
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

	public Integer getLiczbaZyjacych() {
		return liczbaZyjacych;
	}

	public void setLiczbaZyjacych(Integer liczbaZyjacych) {
		this.liczbaZyjacych = liczbaZyjacych;
	}

	public Integer getLiczbaMartwych() {
		return liczbaMartwych;
	}

	public void setLiczbaMartwych(Integer liczbaMartwych) {
		this.liczbaMartwych = liczbaMartwych;
	}


}
