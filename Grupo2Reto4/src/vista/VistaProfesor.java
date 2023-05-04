package vista;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.NotFoundException;
import manager.ManagerJugador;
import modelo.Jugador;
import modelo.Profesor;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaProfesor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField nickname;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private ManagerJugador mj = new ManagerJugador();
	private JPasswordField passwordField;
	private Jugador jugador;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaProfesor frame = new VistaProfesor(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param user
	 */
	public VistaProfesor(Profesor user) {
		try {
			jugadores = mj.selectAll();
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
		setBounds(100, 100, 932, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(64, 128, 128));
		separator.setForeground(new Color(64, 128, 128));
		separator.setBounds(451, 0, 11, 504);
		contentPane.add(separator);

		JComboBox<Jugador> comboBox = new JComboBox<Jugador>();
		comboBox.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				jugador = (Jugador) comboBox.getSelectedItem();
				nombre.setText(jugador.getNombre());
				nickname.setText(jugador.getLogin());
				passwordField.setText(jugador.getPass());
			}
		});
		comboBox.setBounds(544, 55, 200, 27);
		contentPane.add(comboBox);
		
		for (int i=0;i < jugadores.size();i++)
			comboBox.addItem(jugadores.get(i));

		nombre = new JTextField();
		nombre.setEditable(false);
		nombre.setBounds(654, 139, 200, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);

		nickname = new JTextField();
		nickname.setEditable(false);
		nickname.setBounds(654, 196, 200, 20);
		contentPane.add(nickname);
		nickname.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(560, 142, 84, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nickname:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(560, 199, 84, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton editar = new JButton("Editar");
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nombre.isEditable()) {
					nombre.setEditable(true);
					nickname.setEditable(true);
					passwordField.setEditable(true);
				}else {
					nombre.setEditable(false);
					nickname.setEditable(false);
					passwordField.setEditable(false);
				}
					
			}
		});
		editar.setBounds(539, 380, 97, 23);
		contentPane.add(editar);
		
		JButton banear = new JButton("Banear");
		banear.setBounds(792, 380, 97, 23);
		contentPane.add(banear);
		
		JButton seleccionar = new JButton("Seleccionár");
		seleccionar.setBounds(777, 57, 89, 23);
		contentPane.add(seleccionar);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(654, 253, 200, 20);
		contentPane.add(passwordField);
		
		JLabel jpass = new JLabel("Contraseña:");
		jpass.setHorizontalAlignment(SwingConstants.TRAILING);
		jpass.setBounds(560, 256, 84, 14);
		contentPane.add(jpass);
	}
}
