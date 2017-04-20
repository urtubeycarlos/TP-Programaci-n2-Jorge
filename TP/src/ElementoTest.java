

import static org.junit.Assert.*;


import org.junit.Test;

public class ElementoTest {

	@Test
	public void dameNombreTest()
	{
		Elemento elem = new Elemento("Tijera");
		assertEquals("Tijera",elem.dameNombre());
	}

}
