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
		if (cont > cantReg){
			cont = 1;
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
	   cont = 1;       
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
	//creamos una matriz vacia para despues llenarla
	int [][] ordenes = new int [(int) Math.pow(cantReg, cantReg)][cantReg];
	iterativo(ordenes); //n^n recorre por filas y las va llenando
	  for (int i=0;i<(int) Math.pow(cantReg, cantReg);i++){
		  //el for recorre siempe las filas
			  imprimir(ordenes[i]);
			  //hasta aca tenemos todas las posibles combinaciones
			  //estas serian las fechas reales a asignar...
			  //el problema esta en como validar las que sirve y despues como calcular la distancia acumulada
			  //tengo unas ideas, pero no se si estan bien.. y no las pude desarrollar mucho(son muy vuelteras)
	}
		
}
private void imprimir(int [] v1){
	int cont=0;
	for (int j=0;j<cantReg;j++){
		System.out.print( v1[j]) ;
	cont++;	
	}
	System.out.println("") ;

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
