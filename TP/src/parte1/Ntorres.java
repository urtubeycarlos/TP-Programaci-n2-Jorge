package parte1;

public class Ntorres {  
	 private int n;
	 private int [][] soluciones;
	 private int nn; // todas las combinaciones posibles con n  private 
	 int acum = 0;
	 private int cont = 1;
	// private int cantLlamadas = 0;
	 
	 
	 
	 public Ntorres(int n){ //invariante: n > 0
		 this.n = n;									//USAR
		 this.nn = (int)Math.pow(n, n);
		 soluciones =new  int [nn][n];
		 }
	 
	 public void iterativo(){  
	  int i;
	  int nn = (int)Math.pow(n, n);  
	  int indice = 0;
	  
	  while (indice<n){
		  for (i=0;i<nn;i++){
			  soluciones [i][indice] = sigiente(indice,i);
			  }  
	   indice++;
	   acum = 0;
	   cont = 1;       
	   }
	  
	  for (i=0;i<nn;i++){
		  if (esSolucion(soluciones[i],n)){
			  imprimir(soluciones[i]);
			  }
		  }
	  }  
	    
	 /*public void recursivo(boolean bk){
		 int [] v = new int [n];  
		 cantLlamadas= 0;  
	  if (bk){
		  recursivoBK(v,0);
		  }
	  else{
		  recursivoFB(v,0);
		  }
	  System.out.println("cantLlamadas:"+cantLlamadas);
	  }
	 
	 public void recursivoBK(int [] v, int i){
		 int j;
		 if (esSolucion(v,n)){
			 imprimir(v);
			 }
		 else{
			 for (j=0;j<n;j++){
				 v[i]=j+1;
				 if (esSolucion(v,i+1)){
					 recursivoBK(v,i+1);
					 cantLlamadas++;
					 }
				 }
			 }
		 }
	 
	 public void recursivoFB(int [] v, int i){
		 int j;
		 if (i==n){
			 if (esSolucion(v,n)){
				 imprimir(v);
				 }
			 }
		 else{
			 for (j=0;j<n;j++){
				 v[i]=j+1;
				 recursivoFB(v,i+1);
				 cantLlamadas++;
				 }
			 }
		 }         
	 */
	private void imprimir(int [] v1){
		int cont=0;
		for (int j=0;j<n;j++){
			System.out.print( v1[j]) ;
		cont++;	
		}
		System.out.println("") ;

	}
	public boolean esSolucion(int [] v1, int hasta){
		int [] v2  = new int [n];
		int ret=0;
		int j;
		for (j=0;j<n;j++){
			if (v1[j]-1>=0){
				v2[v1[j]-1]++;
				}
			}  
	  for (j=0;j<n;j++){
		  if (v2[j]>0){
			  ret++;
			  }
		  }
	  return ret>=hasta-n;		//CLAVE
	  }
	private int sigiente( int indice, int i){
		int paso;
		paso  = (int)(nn / Math.pow(n, indice+1));
		if (acum < paso){
			acum++;
			}
		else{
			acum = 1;
			cont++;
			if (cont > n){
				cont = 1;
				}
			}
		return cont;     
		}
	
/*	public String toString(){
		String ret = "";
		int nn = (int)Math.pow(n, n);
		for (int j=0;j<nn;j++){
			for (int i=0;i<n;i++){
				ret = ret + soluciones[j][i] ;
				}
			ret = ret + "\n";
			}
		return ret;
		}
	
	
	
*/	public static void main(String[] args) {
		Ntorres N= new Ntorres(3);
		N.iterativo();
		
		}
	}   
