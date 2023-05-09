package vista;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import excepciones.NotFoundException;
import manager.ManagerJugador;
import modelo.Jugador;
import modelo.Pokemon;
import utils.RutasImg;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;

public class VistaPerfil extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField nombreTF;
	private JTextField loginTF;
	private JButton atras;
	private JPanel panel;
	private JButton botonEditar;
	private JLabel errNombreV;
	private JLabel err2Nick;
	private JLabel err3Passw;
	private JButton botonAceptarCambios;
	private int editable = 0;
	private JButton botonDarseDeBaja;
	private Jugador jugadorActual;
	private RutasImg rutas = new RutasImg();
	private Metodos metodos = new Metodos();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaPerfil(Jugador jugador) {
		jugadorActual = jugador;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 661, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jlabelNombre = new JLabel("Nombre del ususario:");
		jlabelNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		jlabelNombre.setBounds(220, 129, 148, 14);
		contentPane.add(jlabelNombre);

		JLabel jlabelNick = new JLabel("Nickname del usuario:");
		jlabelNick.setHorizontalAlignment(SwingConstants.TRAILING);
		jlabelNick.setBounds(215, 185, 153, 14);
		contentPane.add(jlabelNick);

		JLabel jlabelpassw1 = new JLabel("Contraseña:");
		jlabelpassw1.setHorizontalAlignment(SwingConstants.TRAILING);
		jlabelpassw1.setBounds(260, 252, 108, 14);
		contentPane.add(jlabelpassw1);

		JLabel jlabelpassw2 = new JLabel("Confirmar contraseña:");
		jlabelpassw2.setHorizontalAlignment(SwingConstants.TRAILING);
		jlabelpassw2.setBounds(215, 298, 153, 14);
		contentPane.add(jlabelpassw2);

		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(378, 295, 185, 20);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setEditable(false);
		passwordField_1.setBounds(378, 249, 185, 20);
		contentPane.add(passwordField_1);

		nombreTF = new JTextField();
		nombreTF.setEditable(false);
		nombreTF.setColumns(10);
		nombreTF.setBounds(378, 182, 185, 20);
		contentPane.add(nombreTF);

		loginTF = new JTextField();
		loginTF.setEditable(false);
		loginTF.setColumns(10);
		loginTF.setBounds(378, 126, 185, 20);
		contentPane.add(loginTF);

		atras = new JButton("Atras");
		atras.addActionListener(this);
		atras.setBounds(500, 11, 105, 23);
		contentPane.add(atras);

		panel = new JPanel();
		panel.setBounds(217, 380, 330, 220);
		panel.setLayout(new GridLayout(0, 3, 10, 10));
		contentPane.add(panel);

		verEquipo();

		errNombreV = new JLabel("El nombre no puede estar vacio");
		errNombreV.setBounds(34, 126, 213, 20);
		errNombreV.setForeground(Color.RED);
		contentPane.add(errNombreV);
		errNombreV.setVisible(false);

		err2Nick = new JLabel("");
		err2Nick.setBounds(34, 185, 213, 14);
		err2Nick.setForeground(Color.RED);
		contentPane.add(err2Nick);
		err2Nick.setVisible(false);

		err3Passw = new JLabel("");
		err3Passw.setBounds(10, 273, 237, 14);
		err3Passw.setForeground(Color.RED);
		contentPane.add(err3Passw);
		err3Passw.setVisible(false);

		botonEditar = new JButton("Editar campos");
		botonEditar.setToolTipText("Al pulsar este boton habilitas los campos de texto para editar tus credenciales.");
		botonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCampos();
			}
		});
		botonEditar.setBounds(394, 71, 153, 23);
		contentPane.add(botonEditar);

		botonDarseDeBaja = new JButton("Darse de baja");
		botonDarseDeBaja.addActionListener(this);
		botonDarseDeBaja.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		botonDarseDeBaja.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 0, 0), new Color(255, 0, 0),
				new Color(255, 0, 0), new Color(255, 0, 0)));
		botonDarseDeBaja.setBackground(Color.LIGHT_GRAY);
		botonDarseDeBaja.setForeground(new Color(0, 0, 0));
		botonDarseDeBaja.setBounds(34, 11, 131, 44);
		contentPane.add(botonDarseDeBaja);

		botonAceptarCambios = new JButton("Aceptar Cambios");
		botonAceptarCambios.setEnabled(false);
		botonAceptarCambios.setBounds(260, 335, 148, 23);
		contentPane.add(botonAceptarCambios);
		
		JButton irAVistaCajas = new JButton("Ver Cajas");
		irAVistaCajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//A vista CajasPC(jugadorActual)
			}
		});
		irAVistaCajas.setBounds(34, 470, 131, 35);
		contentPane.add(irAVistaCajas);
	}

	private void verEquipo() {
		// TODO Auto-generated method stub
		int i = 0;

		for (Pokemon pokemon : jugadorActual.getEquipo()) {

			ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGfrontalPKMN(pokemon.getId()));
			JLabel pkmnIMG1 = new JLabel();
			pkmnIMG1.setToolTipText(jugadorActual.getEquipo().get(i).getNombre_pokemon());
			panel.add(pkmnIMG1);
			pkmnIMG1.setIcon(pkmnImg1);

			i++;
		}

		if (i < 7) {
			ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGfrontalPKMN(0));
			JLabel pkmnIMG1 = new JLabel();
			pkmnIMG1.setToolTipText("Vacio.");
			panel.add(pkmnIMG1);
			pkmnIMG1.setIcon(pkmnImg1);

			i++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == atras) {
			this.dispose();
		} else if (e.getSource() == botonDarseDeBaja){
			int eleccion = JOptionPane.showConfirmDialog(botonDarseDeBaja,
					"¿Estás REALMENTE seguro de que quieres darte de baja?");

			if (eleccion == 0) {
				ManagerJugador mj = new ManagerJugador();

				try {
					mj.delete(jugadorActual);
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

				this.dispose();
			}
		}	else if (e.getSource() == botonAceptarCambios) {
				int ok = 0;

				if (metodos.esVacio(nombreTF.getText()))
					errNombreV.setVisible(true);
				else {
					errNombreV.setVisible(false);
					ok++;
				}

				if (metodos.esVacio(loginTF.getText())) {
					err2Nick.setVisible(true);
					err2Nick.setText("El nick no puede ser vacio.");
				} else {
					boolean existe = false;
					try {
						existe = metodos.existeUsuario(loginTF.getText());
					} catch (NotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					if (existe) {
						err2Nick.setVisible(true);
						err2Nick.setText("Este nick ya le pertenece a otro usuario.");
					} else {
						err2Nick.setVisible(false);
						ok++;
					}
				}

				if (metodos.esVacio(String.valueOf(passwordField_1.getPassword()))) {
					err3Passw.setVisible(true);
					err3Passw.setText("La contraseña no puede estar vacia.");
				} else if (!metodos.esVacio(String.valueOf(passwordField_1.getPassword()))
						&& metodos.esVacio(String.valueOf(passwordField.getPassword()))) {
					err3Passw.setVisible(true);
					err3Passw.setText("Confirmacion de contraseña vacia.");
				} else if (!String.valueOf(passwordField_1.getPassword()).equals(String.valueOf(passwordField.getPassword()))) {
					err3Passw.setVisible(true);
					err3Passw.setText("Confirmacion de contraseña incorrecta.");
				} else {
					err3Passw.setVisible(false);
					ok++;
				}
				
				if (ok == 3) {

					JOptionPane.showMessageDialog(null, "Prueba a hacer login.", "Registrado correctamente",
							JOptionPane.INFORMATION_MESSAGE);
					Jugador userUpdate = new Jugador(nombreTF.getText(), loginTF.getText(),
							String.valueOf(passwordField_1.getPassword()), jugadorActual.getEquipo(), jugadorActual.getPc(), false);
					ManagerJugador mj = new ManagerJugador();
					try {
						mj.update(jugadorActual, userUpdate);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.dispose();

				}
				
		}
	}

	public void editarCampos() {
		if (editable == 0) {
			passwordField.setEditable(true);
			passwordField_1.setEditable(true);
			nombreTF.setEditable(true);
			loginTF.setEditable(true);
			botonAceptarCambios.setEnabled(true);
			editable = 1;
		} else {
			passwordField.setEditable(false);
			passwordField_1.setEditable(false);
			nombreTF.setEditable(false);
			loginTF.setEditable(false);
			botonAceptarCambios.setEnabled(false);
			editable = 0;
		}

	}
}
