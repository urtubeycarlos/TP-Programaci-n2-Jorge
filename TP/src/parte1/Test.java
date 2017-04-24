package parte1;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CashFlow c1 = new CashFlow();
		
	
		c1.agregarRegistro(new Registro(100));
		c1.agregarRegistro(new Registro(-100));
		c1.agregarRegistro(new Registro(100));
		c1.agregarRegistro(new Registro(-100));
		
		
		
		
		c1.minimizarDistanciaAcumulada();
		
		System.out.println(c1);
		CashFlow c2 = new CashFlow();
		
		
		c2.agregarRegistro(new Registro(100));
		c2.agregarRegistro(new Registro(-100));
		c2.agregarRegistro(new Registro(100));
		c2.agregarRegistro(new Registro(-100));
		
		
		
		
		c2.forzarInvariante();
		
		System.out.println(c2);
//
//		c1.forzarInvariante();
//		System.out.println(c1);
		
//		
//		CashFlow c2 = new CashFlow(); // no cumplira el invariante
//		// para ningun orden posible
//		
//		c2.agregarRegistro(new Registro(100));
//		c2.agregarRegistro(new Registro(-100));
//		c2.agregarRegistro(new Registro(100));
//		c2.agregarRegistro(new Registro(-101));
//		
//		c2.forzarInvariante();
//		System.out.println(c2);
//			
				
	}

}
