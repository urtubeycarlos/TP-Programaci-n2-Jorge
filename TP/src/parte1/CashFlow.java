package parte1;

import java.util.ArrayList;
import java.util.Stack;

public class CashFlow {
	private ArrayList<Registro> registros; 
	private Integer saldo;	//saldo gral
	private static Integer cantReg;
	private int acum; //variables necesarias para el funcionamietno de Iterativo / siguiente
	private int cont; //variables necesarias para el funcionamietno de Iterativo / siguiente
public CashFlow(){
	saldo = 0;
	cantReg = 0;
	registros = new ArrayList<Registro>();
	
}
private int sigiente( int indice){
	int paso;
	//paso tiene el calculo que permite saber que valor va en cada fila/columna (lo vimos en clase)
	paso  = (int)(Math.pow(cantReg,cantReg) / Math.pow(cantReg, indice+1));
	// este codigo te va a resultar muy claro, solo fijate  que cont es el valor de retorno, siempre varia de 1 hasta n
	if (acum < paso){
		acum++; 
		}
	else{
		acum = 1;
		cont++;
		if (cont > cantReg-1){ //para que no llegue a n(porq si hay n y tenemos fecha 0, la fecha n no exite)
			cont = 0; //ultima modificacion, esto estaba en 1
			}
		}
	return cont;     
	}
public void iterativo(int [][] matriz){  
	  int i; 
	  int nn = (int)Math.pow(cantReg, cantReg);  //tamaño de la matriz
	  int indice = 0; //indice sirve para recorrer las columnas
	  
	  while (indice<cantReg){
		  for (i=0;i<nn;i++){ //recorremos las filas
			  matriz [i][indice] = sigiente(indice); //siguiente te da el valor que corresponde a cada columna
			  }  
	   indice++; //incrementamos el indice para llenar otra columna
	   acum = 0;
	   cont = 0;       
	   }

	 
	  }  

public void agregarRegistro(Registro r){
	
	r.fecha = cantReg;
	cantReg++;
	
	registros.add(r);	
	
	saldo = saldo + r.importe;
	
	// por construccion fechaReal viene vacio, por las dudas pregunto
	if (saldo < 0 && r.fechaReal != null)
		throw new RuntimeException("saldo negativo!: " + saldo);
	
}
public void minimizarDistanciaAcumulada(){
	int acum_chequearSaldo = 0;
	boolean elPrimerEsValido = true;
	//en caso de que ya sea valido, lo dejamos como esta.
	for( int i = 0 ; i<registros.size() ; i++){
		acum_chequearSaldo = acum_chequearSaldo + registros.get(i).importe;
		if(acum_chequearSaldo < 0){
			System.out.println("entro en el false");
			elPrimerEsValido = false; //ya no necesito seguir
		}
	}
	if (elPrimerEsValido == true){
		//asigno las fechas reales
		for ( int i = 0 ; i < cantReg; i++){
			registros.get(i).fechaReal = i;
		}
	}else{
	//creamos una matriz vacia para despues llenarla
	int [][] Combinaciones = new int [(int) Math.pow(cantReg, cantReg)][cantReg];
	iterativo(Combinaciones); //n^n recorre por filas y las va llenando
	//reducimos la matriz a un ArrayList con las combinaciones validas
	ArrayList <int []>CombinacionesValidas = CombinacionesValidas(Combinaciones);
	//ahora solo queda elegir una :D
	int [] MejorCombinacion = MejorOpcion(CombinacionesValidas);
	//ahora asignamos las fechas reales
	for( int i = 0; i<cantReg ; i++){
		registros.get(i).fechaReal = MejorCombinacion[i];
	}

	}
		
}

//metodo que elige la mejor combinacion
//hay que recorrerlos y quedarse con la mejor distAcumulada.
//como comparo las distancias?
public int [] MejorOpcion(ArrayList<int[]>combinacionesvalidas){
	//tomamos el primero. similara el metodo BucarMenor(int [])
	int IndiceDelMejor = 0; //como partimos del primero
	
	int distanciaAcum = DistanciaAcumulada(combinacionesvalidas.get(0)); // la mejor, partiendo de la primera
	
	//vamos a generar un arrayList con todas las distancias(relacion por indice)
	for ( int i = 0 ; i < combinacionesvalidas.size() ; i++){ //no se cuantas iteraciones hace. pero en el peor caso es n^n -n
		if ( distanciaAcum > DistanciaAcumulada(combinacionesvalidas.get(i))){
			distanciaAcum =  DistanciaAcumulada(combinacionesvalidas.get(i));
			IndiceDelMejor = i;
		}
	}
	return combinacionesvalidas.get(IndiceDelMejor);
}
//metodo que retorna la distancia acumulada de una posible combinacion
public int DistanciaAcumulada(int [] fechasReales){
	int ret = 0;
	for ( int i = 0; i < fechasReales.length ; i++){
		System.out.println("mira que cagada"+registros.get(i).fecha+"y las reales: "+fechasReales[i]);
		int diferencia = registros.get(i).fecha - fechasReales[i];
		if(diferencia < 0){
			diferencia = diferencia *(-1); //yo quiero la distancia
		}
		
		ret =  ret + (diferencia);
		System.out.println("Distancia acumulada  :"+ret);
	}
	System.out.println("--------------------------------------");
	return ret;
}

// tengo que crear un arrayList con las combinaciones posibles

public ArrayList<int[]> CombinacionesValidas(int[][] matriz){
	ArrayList<int []> ret= new ArrayList<int []>(); //aca van las validas
	//recorro cada fila de la matriz para poder validarlas
	for ( int i = 0 ; i <matriz.length ; i++){// n^n iteraciones(tamaño de la matriz)
		if(!TodosIguales(matriz[i])){ // se puede unir con el el otro if
		if (SaldoPositivo(matriz[i])){
			ret.add(matriz[i]);
		}
	}
	}
	return ret;
}
public boolean TodosIguales(int [] combinacion){
	boolean ret = true;
	for (int i = 0 ; i < combinacion.length ; i++){
		ret = ret && (combinacion[0] == combinacion[i]);
	}
	return ret;
}
//mi objetivo con este metodo es contrar las combinaciones valida, y pasarlas a un array
public boolean SaldoPositivo(int [] fila){
//es un metodo que no me gusta, pero es lo que se me ocurrio
	int Saldo = 0;
	int SaldoFechas = 0; //soluciona el hecho de que haya muchas fechas reales iguales
	int FechaReal = 1;  // asi podemos tomar todas las que sean 1, 2, 3 , 4
	while( FechaReal < cantReg){
	for (int i = 0 ; i < fila.length ; i++){ //n iteraciones
		if( fila[i] == FechaReal){ //n iteraciones
			SaldoFechas = SaldoFechas + registros.get(i).importe;
		}
	}
	//control de que todo va bien
	if (SaldoFechas >= 0){
		Saldo = Saldo +  SaldoFechas;
	}else{//sino, ya es falso
		return false;
	}
	FechaReal++;
	SaldoFechas = 0;
	}
	return true;
}





public void forzarInvariante(){
	
	Stack<Registro> regsAux = new Stack<Registro>();
		
	int i;
	
	Registro regAux;
	
	for (i=registros.size()-1; i > -1 ; i--){	// voy al revez para no romper el indice al borrar
		if (registros.get(i).importe < 0) {

			// guardo los negativos primero, para que luego esten al final
			regsAux.push(registros.get(i));
			registros.remove(i);
		}
	}

	for (i=registros.size()-1; i > -1 ; i--){	
			// guardo el resto
			regsAux.push(registros.get(i));
			registros.remove(i);
	}

	//rearmo el saldo
	saldo = 0;
	i=0;
	while (!regsAux.empty()){
		//quedan los importes positivos primero
		
		regAux = regsAux.pop();
		regAux.fechaReal = i; //asignamos la fecha real correcta
				
		registros.add(regAux);
		
		saldo = saldo + registros.get(i).importe;
		
		//se tiene que cumplir para todo i
		if (saldo < 0) 
			throw new RuntimeException("saldo negativo!: " + saldo);		
		
		i++;
	}
	

	
}

public Integer saldo(){
	return saldo;
}
@Override
public String toString(){
	String ret = "";
	
	for (Registro e: registros){
		ret = ret + e + "; ";
	}
	
	ret = ret + " Saldo: " + saldo ;
	
	return ret;
}


}
