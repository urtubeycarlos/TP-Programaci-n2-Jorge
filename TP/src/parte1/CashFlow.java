package parte1;

import java.util.ArrayList;
import java.util.Stack;

public class CashFlow {
	private ArrayList<Registro> registros; 
	private Integer saldo;	//saldo gral
	private static Integer cantReg;
	private int acum;
	private int cont;
public CashFlow(){
	saldo = 0;
	cantReg = 0;
	registros = new ArrayList<Registro>();
	
}
private int sigiente( int indice){
	int paso;
	paso  = (int)(Math.pow(cantReg,cantReg) / Math.pow(cantReg, indice+1));
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
	  int nn = (int)Math.pow(cantReg, cantReg);  
	  int indice = 0;
	  
	  while (indice<cantReg){
		  for (i=0;i<nn;i++){
			  matriz [i][indice] = sigiente(indice);
			  }  
	   indice++;
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
	int [][] ordenes = new int [(int) Math.pow(cantReg, cantReg)][cantReg];
	ArrayList <int []> cola= new ArrayList< int[] >();
	iterativo(ordenes); //n^n
	  for (int i=0;i<(int) Math.pow(cantReg, cantReg);i++){
			  imprimir(ordenes[i]);
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
