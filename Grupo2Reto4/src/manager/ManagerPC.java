package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import excepciones.NotFoundException;
import modelo.Caja;
import modelo.MiPc;
import utils.DBConexion;

public class ManagerPC implements ManagerInterface<MiPc> {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	ResultSet registro2;
	Metodos m = new Metodos();
	ManagerJugador mj = new ManagerJugador();

	@Override
	public ArrayList<MiPc> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<MiPc> pcs = new ArrayList<MiPc>();
		ManagerCajas mc = new ManagerCajas();
		ArrayList<Caja> cajasPC = mc.selectAll();
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Caja> cajas = new ArrayList<Caja>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT pc_box_id FROM " + DBConexion.T_CAJAS + " where pc_id = " + id + ";");

				while (registro2.next() == true) {
					int idbox = registro2.getInt(1);
					Caja caja = null;
					int i = 0;
					do {
						if (cajasPC.get(i).getId_caja() == idbox) {
							caja = cajasPC.get(i);
							cajasPC.remove(caja);
							cajas.add(caja);
						}
						i++;
					} while (caja == null && i < cajasPC.size());

				}

				MiPc pc = new MiPc(cajas, id);
				pcs.add(pc);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}

		return pcs;
	}

	@Override
	public void insert(MiPc pc) throws SQLException, Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(MiPc t_old, MiPc t_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(MiPc pc) throws SQLException, Exception {
		// TODO Auto-generated method stub
	}

}
