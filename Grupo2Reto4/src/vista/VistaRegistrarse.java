package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class VistaRegistrarse extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreTF;
	private JTextField loginTF;
	private JPasswordField passw1TF;
	private JPasswordField passw2TF;
	private JLabel errNombreV;
	private JLabel err2Nick;
	private JLabel err3Passw;
	private JButton atras;
	private Metodos metodos = new Metodos();
	//private Usuario[] usuarios;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaRegistrarse() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 704, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//usuarios = metodos.arrayUsuarios();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nombreTF = new JTextField();
		nombreTF.setBounds(381, 112, 185, 20);
		contentPane.add(nombreTF);
		nombreTF.setColumns(10);

		loginTF = new JTextField();
		loginTF.setBounds(381, 168, 185, 20);
		contentPane.add(loginTF);
		loginTF.setColumns(10);

		atras = new JButton("Atras");
		atras.setBounds(10, 11, 105, 23);
		atras.addActionListener(this);
		contentPane.add(atras);

		JLabel jlabelNombre = new JLabel("Nombre del ususario:");
		jlabelNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		jlabelNombre.setBounds(223, 115, 148, 14);
		contentPane.add(jlabelNombre);

		JLabel jlabelNick = new JLabel("Nickname del usuario:");
		jlabelNick.setHorizontalAlignment(SwingConstants.TRAILING);
		jlabelNick.setBounds(218, 171, 153, 14);
		contentPane.add(jlabelNick);

		JLabel jlabelpassw1 = new JLabel("Contraseña:");
		jlabelpassw1.setHorizontalAlignment(SwingConstants.TRAILING);
		jlabelpassw1.setBounds(263, 238, 108, 14);
		contentPane.add(jlabelpassw1);

		JLabel jlabelpassw2 = new JLabel("Confirmar contraseña:");
		jlabelpassw2.setHorizontalAlignment(SwingConstants.TRAILING);
		jlabelpassw2.setBounds(218, 284, 153, 14);
		contentPane.add(jlabelpassw2);

		JLabel jlabelTip = new JLabel("Aviso: Al hacer sesion, se pide el nick y la contraseña.");
		jlabelTip.setForeground(Color.RED);
		jlabelTip.setBounds(205, 45, 331, 14);
		contentPane.add(jlabelTip);

		passw1TF = new JPasswordField();
		passw1TF.setBounds(381, 235, 185, 20);
		contentPane.add(passw1TF);

		passw2TF = new JPasswordField();
		passw2TF.setBounds(381, 281, 185, 20);
		contentPane.add(passw2TF);

		errNombreV = new JLabel("El nombre no puede estar vacio");
		errNombreV.setForeground(Color.RED);
		errNombreV.setBounds(10, 115, 213, 20);
		contentPane.add(errNombreV);
		errNombreV.setVisible(false);

		err2Nick = new JLabel("");
		err2Nick.setForeground(Color.RED);
		err2Nick.setBounds(10, 171, 213, 14);
		contentPane.add(err2Nick);
		err2Nick.setVisible(false);

		err3Passw = new JLabel("");
		err3Passw.setForeground(Color.RED);
		err3Passw.setBounds(10, 257, 237, 14);
		contentPane.add(err3Passw);
		err3Passw.setVisible(false);

		JButton btnValidar = new JButton("Registrarse");
		btnValidar.addActionListener(this);
		btnValidar.setBounds(406, 371, 89, 23);
		contentPane.add(btnValidar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == atras) {
			this.dispose();
		} else {
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
			}
			/*
			 * else if(loginTF.getText().equals(usuarios)) { err2Nick.setVisible(true);
			 * err2Nick.setText("Este nick ya le pertenece a otro usuario."); }
			 */
			else {
				err2Nick.setVisible(false);
				ok++;
			}
			if (metodos.esVacio(String.valueOf(passw1TF.getPassword()))) {
				err3Passw.setVisible(true);
				err3Passw.setText("La contraseña no puede estar vacia.");
			} else if (!metodos.esVacio(String.valueOf(passw1TF.getPassword()))
					&& metodos.esVacio(String.valueOf(passw2TF.getPassword()))) {
				err3Passw.setVisible(true);
				err3Passw.setText("Confirmacion de contraseña vacia.");
			} else if (!String.valueOf(passw1TF.getPassword()).equals(String.valueOf(passw2TF.getPassword()))) {
				err3Passw.setVisible(true);
				err3Passw.setText("Confirmacion de contraseña incorrecta.");
			} else {
				err3Passw.setVisible(false);
				ok++;
			}

			if (ok == 3) {
				JOptionPane.showMessageDialog(null, "Prueba a hacer login.", "Registrado correctamente",
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}

		}

	}
}
