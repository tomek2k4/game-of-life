
public class StanMikroswiata {

	private ZrodloZmianyEnum zrodlo;
	private Integer liczbaNarodzonychKomorek;
	private Integer liczbaUsmierconychKomorek;
	private Integer liczbaZyjacych;
	private Integer liczbaMartwych;
	private Integer zmianaCykli;
	private Integer numerCyklu;
	


	public StanMikroswiata(ZrodloZmianyEnum zrodlo,
			Integer liczbaNarodzonychKomorek, Integer liczbaUsmierconychKomorek, 
			int liczbaZyjacychKomorek, int liczbaMartwychKomorek,
			int zmianaCykli,int numerCyklu) {
		super();
		this.zrodlo = zrodlo;
		this.liczbaNarodzonychKomorek = liczbaNarodzonychKomorek;
		this.liczbaUsmierconychKomorek = liczbaUsmierconychKomorek;
		this.liczbaZyjacych = liczbaZyjacychKomorek;
		this.liczbaMartwych = liczbaMartwychKomorek;
		this.zmianaCykli = zmianaCykli;
		this.numerCyklu = numerCyklu;
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
	public Integer getNumerCyklu() {
		return numerCyklu;
	}

	public void setNumerCyklu(Integer numerCyklu) {
		this.numerCyklu = numerCyklu;
	}
	
	public Integer getZmianaCykli() {
		return zmianaCykli;
	}

	public void setZmianaCykli(Integer zmianaCykli) {
		this.zmianaCykli = zmianaCykli;
	}


}
