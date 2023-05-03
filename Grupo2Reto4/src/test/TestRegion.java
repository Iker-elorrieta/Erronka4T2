package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Region;
import modelo.Tipo;

class TestRegion {
	
	
	
	@Test
	void testConstructor_Getter() {
		
		int id = 1;
		String nombre = "Kanto";
		Region reg = new Region(id, nombre);
		
		assertEquals(reg.getId(), id);
		assertEquals(reg.getNombre(), nombre);
	}
	
	@Test
	void testString() {
		Region reg = new Region(1, "Johto");
		
		String info = reg.toString();
		
		assertEquals(info, reg.toString());
		
	}
	
	
	@Test
	void testRegionEquals() {
		
		Region reg = new Region(2, "Johto");
		Region reg2 = new Region(2, "Johto");
		Region regn = null;
		Tipo t = new Tipo(1,"Normal");
		Region reg3 = new Region(1, "Kanto");
		
		reg.equals(reg);
		reg.equals(reg2);
		reg.equals(regn);
		reg.equals(t);
		reg.equals(reg3);
		
	}
	
	@Test
	void testTipoHashCode() {
		Region reg = new Region(2, "Johto");
		Region reg2 = new Region(2, "Johto");
		assertEquals(reg.hashCode(), reg2.hashCode());
		
	}
}
