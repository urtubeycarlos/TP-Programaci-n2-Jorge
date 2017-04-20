package parte1;

import java.util.ArrayList;
import java.util.Stack;

public class CashFlow {
	private ArrayList<Registro> registros;
	private int acum = 0;  
	private int cont = 1;  
	private Integer saldo;	//saldo gral
	private int nn;
	private static Integer cantReg;
	
public CashFlow(){
	saldo = 0;
	cantReg = 0;
	registros = new ArrayList<Registro>();

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
	
	//...
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
private int sigiente( int indice, int i){
	int paso;
	paso  = (int)(nn / Math.pow(cantReg, indice+1));
	if (acum < paso){
		acum++;
		}
	else{   acum = 1;
	cont++;
	if (cont > cantReg){ 
		cont = 1;
		}
	}
	return cont;

}
public void iterativo(){ 
	  int i;
	  int nn = (int)Math.pow(cantReg, cantReg);
	  int [][] soluciones =  new int [nn][cantReg];
	 
	  int indice = 0;
	  while (indice<cantReg){
		  for (i=0;i<nn;i++){
			  soluciones [i][indice] = sigiente(indice,i);
			  } 
	 
	   indice++;
	   acum = 0;
	   cont = 1;
	   } 
	 
	  for (i=0;i<nn;i++){
		  
			  imprimir(soluciones[i]);
			  }
		  }
private void imprimir(int [] v1){
	for (int j=0;j<cantReg;j++){
		System.out.print( v1[j]) ;
		}
	System.out.println("") ;
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
