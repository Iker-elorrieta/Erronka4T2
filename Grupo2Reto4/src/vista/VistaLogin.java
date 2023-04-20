package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Metodos;
import javax.swing.JLabel;
import java.awt.Color;

public class VistaLogin extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton atras;
	Metodos metodos = new Metodos();
	private JTextField loginTF;
	private JPasswordField passw1TF;
	private JButton btnLogin;
	private JLabel errLogin;
	private JLabel errPassw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
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
	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loginTF = new JTextField();
		loginTF.setBounds(146, 96, 139, 20);
		contentPane.add(loginTF);
		loginTF.setColumns(10);

		atras = new JButton("Atras");
		atras.setBounds(10, 10, 86, 23);
		atras.addActionListener(this);
		contentPane.add(atras);

		passw1TF = new JPasswordField();
		passw1TF.setBounds(146, 168, 139, 20);
		contentPane.add(passw1TF);

		btnLogin = new JButton("Abrir sesion");
		btnLogin.setBounds(10, 128, 112, 23);
		contentPane.add(btnLogin);

		errLogin = new JLabel("");
		errLogin.setForeground(Color.RED);
		errLogin.setBounds(323, 99, 228, 14);
		contentPane.add(errLogin);
		errLogin.setVisible(false);

		errPassw = new JLabel("");
		errPassw.setForeground(Color.RED);
		errPassw.setBounds(323, 171, 228, 14);
		contentPane.add(errPassw);
		errPassw.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == atras) {
			this.dispose();
		} else {
			if (metodos.esVacio(loginTF.getText())) {
				errLogin.setVisible(true);
				errLogin.setText("Este campo no puede esar vacio.");
			}

			if (metodos.esVacio(String.valueOf(passw1TF.getPassword()))) {
				errPassw.setVisible(true);
				errPassw.setText("La contraseña no puede estar vacia.");
			}
		}
	}

}