


import java.util.ArrayList;

public class PPTLS {
	private ArrayList<Elemento> elementos;
	
	public PPTLS(){
		this.elementos=new ArrayList<Elemento>();
	}
	//valida el invariante, no admitimos repetidos
	public void AgregarElemento (String elemento) { 
		if( !yaExiste(elemento) ){
		elementos.add( new Elemento( elemento ) );
		}
	}
	
	public boolean yaExiste(String elemento){
		boolean ret = false;
		for(int i = 0 ; i < elementos.size() ; i++){
			ret = ret || ( elementos.get(i).dameNombre() == elemento );
		}
		return ret;
	}
	
	//hace cumplir el invariante, si no existe un elemento, entonces no es valida
	public void AgregarRegla(String Ganador , String Perdedor){
		// deben existir ambos elementos
		if( (yaExiste(Ganador)) && (yaExiste(Perdedor)) ){
			int ind_pierde = 0;
			int ind_gana = 0;
			//busco sus posiciones
			for( int i = 0 ; i < elementos.size() ; i++){
				if (elementos.get(i).dameNombre() == Perdedor){
					ind_pierde=i;
				}
				if (elementos.get(i).dameNombre() == Ganador){
					ind_gana = i;
				}
			}
			//agergo el elemento debil al arraylist de debiles del ganador
			elementos.get(ind_gana).AgregarDebil(elementos.get(ind_pierde));
		}
	}
	public int DameIndice(String elemento){
		int ret = 0;
		for ( int i = 0 ; i<elementos.size() ; i++){
			if (elemento == elementos.get(i).dameNombre()){
				ret = i;
			}
		}
		return ret;
	}
	public Integer Jugar(String elemento1 , String elemento2){
		//valido el invariante
		//validar que no exista un elemento?
		if (!yaExiste(elemento1) || !yaExiste(elemento2) ){
			throw new IllegalArgumentException("Se intentó jugar con un elemento inexistente");
		}
		Elemento a = elementos.get(DameIndice(elemento1));
		Elemento b =  elementos.get(DameIndice(elemento2));
		if( (a.Ganador(b)) && (b.Ganador(a)) ){
			throw new IllegalArgumentException("No pueden empatar dos objetos diferentes");
		}
		if (a.Ganador(b)){
			return 1;
		}
		if ( b.Ganador(a) ){
			return 2;
		}
		
		throw new IllegalArgumentException("No se pudo determinar un ganador");
		
	}
}
