package vista;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.NotFoundException;
import manager.ManagerJugador;
import modelo.Jugador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPerfil frame = new VistaPerfil(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaPerfil(Jugador jugador) {
		jugador= new Jugador("clemen", "TheToastGod", "aaa", null, null);
		jugadorActual=jugador;
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == atras) {
			this.dispose();
		}
		else {
			int eleccion = JOptionPane.showConfirmDialog(botonDarseDeBaja, "¿Estás REALMENTE seguro de que quieres darte de baja?");
			
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
