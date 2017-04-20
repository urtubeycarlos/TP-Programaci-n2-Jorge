

import static org.junit.Assert.*;


import org.junit.Test;

public class ElementoTest {

	@Test
	public void dameNombreTest()
	{
		Elemento elem = new Elemento("Tijera");
		assertEquals("Tijera",elem.dameNombre());
	}
	
	@Test
	public void agregarDebilTest()
	{
		Elemento elem = new Elemento("Piedra");
		Elemento elem2 = new Elemento("Papel");
		elem.AgregarDebil(elem);
		assertEquals(1,elem.tamañoDebiles());
	}
	
	@Test
	public void ganadorTest()
	{
		Elemento elem = new Elemento("Piedra");
		Elemento debil = new Elemento("Papel");
		elem.AgregarDebil(debil);
		assertEquals(true,elem.Ganador(debil));
	}

}
