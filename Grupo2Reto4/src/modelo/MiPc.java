package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class MiPc {

	private ArrayList<Caja> cajas;
	
	public MiPc(ArrayList<Caja> cajas) {
		this.cajas=cajas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cajas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MiPc other = (MiPc) obj;
		return Objects.equals(cajas, other.cajas);
	}

	@Override
	public String toString() {
		return "MiPc [cajas=" + cajas + "]";
	}

	public ArrayList<Caja> getCajas() {
		return cajas;
	}
	
//	public void InsertarCaja() {
//		Caja caja = new Caja();
//		if(cajas.size()<8)
//			cajas.add(caja);
//	}
	
	
	
	
}
