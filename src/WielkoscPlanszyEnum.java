import java.awt.Dimension;


public enum WielkoscPlanszyEnum {
	
	
	MIKRO(new Dimension(50,20)),
	MALA(new Dimension(300,200)),
	SREDNIA(new Dimension(600,400)),
	DUZA(new Dimension(800,600));
	

	private final Dimension dimension;
	
	WielkoscPlanszyEnum(Dimension dim)
	{
		this.dimension = dim;
	}
	
	Dimension getWymiar(){
		return dimension;
	}
	
}
