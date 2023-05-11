package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import controlador.MetodosVista;
import excepciones.NotFoundException;
import manager.ManagerJugador;
import manager.ManagerProfesor;
import modelo.Jugador;
import modelo.Profesor;
import modelo.Usuario;
import utils.RutasImg;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

public class VistaLogin extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton atras;
	private Metodos metodos = new Metodos();
	private MetodosVista metodosV = new MetodosVista();
	private JTextField loginTF;
	private JPasswordField passw1TF;
	private JButton btnLogin;
	private JLabel errLogin;
	private JLabel errPassw;
	private RutasImg rutas = new RutasImg();
	private ArrayList<Usuario> users = new ArrayList<Usuario>();
	private ManagerJugador mj = new ManagerJugador();
	private ManagerProfesor mp = new ManagerProfesor();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		try {
			users.addAll(mj.selectAll());
			users.addAll(mp.selectAll());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 518, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loginTF = new JTextField();
		loginTF.setBounds(72, 103, 139, 20);
		contentPane.add(loginTF);
		loginTF.setColumns(10);

		atras = new JButton("Atras");
		atras.setBounds(90, 314, 86, 23);
		atras.addActionListener(this);
		contentPane.add(atras);

		passw1TF = new JPasswordField();
		passw1TF.setBounds(72, 175, 139, 20);
		contentPane.add(passw1TF);

		btnLogin = new JButton("Iniciar sesion");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(313, 314, 122, 23);
		contentPane.add(btnLogin);

		errLogin = new JLabel("");
		errLogin.setForeground(Color.RED);
		errLogin.setBounds(235, 109, 228, 14);
		contentPane.add(errLogin);
		errLogin.setVisible(false);

		errPassw = new JLabel("");
		errPassw.setForeground(Color.RED);
		errPassw.setBounds(235, 181, 228, 14);
		contentPane.add(errPassw);
		errPassw.setVisible(false);

		rutas.rutaPC();
		ImageIcon pkmnImg1 = new ImageIcon("img/pc.jpg");

		JLabel labelUser = new JLabel("Nickname:");
		labelUser.setBounds(72, 78, 86, 14);
		contentPane.add(labelUser);

		JLabel labelContra = new JLabel("Contraseña:");
		labelContra.setBounds(72, 150, 104, 14);
		contentPane.add(labelContra);
		JLabel imgFondo = new JLabel();
		imgFondo.setBounds(0, 0, 502, 429);
		contentPane.add(imgFondo);
		imgFondo.setIcon(pkmnImg1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int i = 0;
		if (e.getSource() == atras) {
			this.dispose();
		} else {

			if (metodos.esVacio(loginTF.getText())) {
				errLogin.setVisible(true);
				errLogin.setText("Este campo no puede esar vacio.");
			} else {
				errLogin.setVisible(false);
				i++;
			}

			if (metodos.esVacio(String.valueOf(passw1TF.getPassword()))) {
				errPassw.setVisible(true);
				errPassw.setText("La contraseña no puede estar vacia.");
			} else {
				errPassw.setVisible(false);
				i++;
			}

			if (i == 2) {
				Usuario user = metodos.encontrarUsuario(users, loginTF.getText(),
						String.valueOf(passw1TF.getPassword()));

				if (user == null) {
					JOptionPane.showMessageDialog(null,
							"La combinacion login/contraseña introducida es incorrecta, pruebe de nuevo.", "ERROR",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (user instanceof Jugador) {
					if (((Jugador) user).isBan()) {
						JOptionPane.showMessageDialog(null, "ESTE USUARIO A SIDO BANEADO POR INFRINGIR LAS NORMAS.",
								"ALERTA!!!!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						metodosV.guardarLogin(user);
						VistaPerfil vp = new VistaPerfil(((Jugador) user));
						vp.setVisible(true);
						this.dispose();
					}

				} else {
					metodosV.guardarLogin(user);
					VistaProfesor vp = new VistaProfesor(((Profesor) user));
					vp.setVisible(true);
					this.dispose();
				}

			}

		}
	}
}
