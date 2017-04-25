


import java.util.ArrayList;

public class Elemento {

	private String nombre;
	private ArrayList<Elemento> debiles;
	
	Elemento(String nombre){
		this.nombre = nombre;
		this.debiles = new ArrayList<Elemento>();
	}
	
	public void AgregarDebil(Elemento nuevodebil){
		debiles.add(nuevodebil);
	}
	
	public boolean Ganador(Elemento oponente){
		
		boolean ret = false;
		for (int i = 0 ; i < debiles.size() ; i++){
			ret = ret || ( oponente.dameNombre() == debiles.get(i).dameNombre() );
		}
		return ret;
	}
	
	public String dameNombre(){
		return this.nombre;
	}
}
