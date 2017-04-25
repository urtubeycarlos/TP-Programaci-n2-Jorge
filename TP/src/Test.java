
public class Test {

	public static void main(String[] args) {
		
		PPTLS juego = new PPTLS();
		
		juego.AgregarElemento("piedra");
		juego.AgregarElemento("papel");
		juego.AgregarElemento("tijera");
		juego.AgregarRegla("piedra", "tijera");
		juego.AgregarRegla("papel", "piedra");
		juego.AgregarRegla("tijera", "papel");
		
		System.out.println(juego.Jugar("piedra","tijera"));
		
		System.out.println("-----------------------------");
		
		PPTLS juego2 = new PPTLS();
		
		juego2.AgregarElemento("spock");
		juego2.AgregarElemento("tijera");
		juego2.AgregarElemento("piedra");
		juego2.AgregarRegla("spock", "piedra");
		juego2.AgregarRegla("spock", "tijera");
		juego2.AgregarRegla("piedra","tijera");
		
		System.out.println(juego2.Jugar("spock", "tijera"));
		System.out.println(juego2.Jugar("piedra", "tijera"));
		
		System.out.println("-----------------------------");
		
//		PPTLS juego3 = new PPTLS();   // este juego es para romper nuestro invariante, si se desea probar hay que descomentar
//		juego3.AgregarElemento("piedra");
//		juego3.AgregarElemento("papel");
//		juego3.AgregarElemento("tijera");
//		juego3.AgregarRegla("papel", "piedra");
//		
//		System.out.println(juego3.Jugar("papel","tijera"));
		

	}

}
