import java.awt.Dimension;


public enum WielkoscPlanszyEnum {
	
	
	MIKRO(new Dimension(60,60)),
	MALA(new Dimension(400,400)),
	SREDNIA(new Dimension(600,600)),
	DUZA(new Dimension(800,800)),
	OGROMNA(new Dimension(800,800));
	
	

	private final Dimension dimension;
	
	WielkoscPlanszyEnum(Dimension dim)
	{
		this.dimension = dim;
	}
	
	Dimension getWymiar(){
		return dimension;
	}
}
