package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;


import controlador.MetodosVista;
import modelo.Jugador;

class GuardarLoginTest {
	
	MetodosVista m = new MetodosVista();
	
	@Test
	void test() {
		Jugador j = new Jugador("igor333", "Igor", null, null, null);
		m.guardarLogin(j);
		m.guardarLogin(j);
		Date fecha = new Date();
		
		DateFormat dateFormat = new SimpleDateFormat("dd");
		DateFormat dateFormatMes = new SimpleDateFormat("MM");
		DateFormat dateFormatA = new SimpleDateFormat("yyyy");
		
		String dia = dateFormat.format(fecha.getTime());
		String mes = dateFormatMes.format(fecha.getTime());
		String anyo = dateFormatA.format(fecha.getTime());
		
		String fechaS =dia+"/"+mes+"/"+anyo;
		
		DateFormat min = new SimpleDateFormat("mm");
		DateFormat hora = new SimpleDateFormat("hh");
		
		String mins = min.format(fecha.getTime());
		String horas = hora.format(fecha.getTime());
		
		String horaS = horas+":"+mins;
		
		String info = "El usuario "+j.getNombre()+" con usuario "+j.getLogin()+" ha iniciado sesion el "+fechaS+" a la(s) "+horaS+"\r\n"+"El usuario "+j.getNombre()+" con usuario "+j.getLogin()+" ha iniciado sesion el "+fechaS+" a la(s) "+horaS+"\r\n";
		
		String cadena = "";
		try {
			FileReader fic = new FileReader("LoginHistorial/historial.txt");
			BufferedReader buf = new BufferedReader(fic);
			String linea;
			while ((linea = buf.readLine()) != null)
				cadena += linea+"\r\n";
			
			buf.close();
			fic.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		assertEquals(info,cadena);
		
		try {
			FileWriter fic = new FileWriter("LoginHistorial/historial.txt");
			
			fic.write("");
			
			fic.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
