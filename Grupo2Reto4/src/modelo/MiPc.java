package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class MiPc {

	private ArrayList<Caja> cajas;
	private int id_pc;

	public MiPc(ArrayList<Caja> cajas, int id_pc) {
		this.cajas = cajas;
		this.id_pc=id_pc;
	}

	public ArrayList<Caja> getCajas() {
		return cajas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cajas, id_pc);
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
		return id_pc == other.id_pc;
	}

	@Override
	public String toString() {
		return "MiPc [cajas=" + cajas + ", id_pc=" + id_pc + "]";
	}

	public int getId_pc() {
		return id_pc;
	}

}
