package vista;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import excepciones.NotFoundException;
import manager.ManagerJugador;
import manager.ManagerMovimientos;
import manager.ManagerPokemon;
import modelo.Jugador;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Profesor;
import utils.RutasImg;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JSlider;

public class VistaProfesor extends JFrame implements ActionListener {

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
	private JButton confirmarCambios;
	private JButton banear;
	private JButton atras;
	private JButton editar;
	private JComboBox<String> comboBox;
	private RutasImg rutas = new RutasImg();
	private ManagerPokemon mp = new ManagerPokemon();
	private ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
	private ManagerMovimientos mm = new ManagerMovimientos();
	private ArrayList<Movimiento> movimientos;
	private JSlider slider;
	private JLabel num_pkdxC;
	private JLabel num_pkdxD;
	private JLabel num_pkdxA;
	private JLabel pkdex_arriba;
	private JLabel pkdex_centro;
	private JLabel pkdex_abajo;
	ImageIcon[] galeria = new ImageIcon[3];

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
			pokemon = mp.selectAll();
			movimientos = mm.selectAll();
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 932, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		num_pkdxD = new JLabel("");
		num_pkdxD.setBounds(246, 290, 38, 36);
		contentPane.add(num_pkdxD);

		num_pkdxC = new JLabel("");
		num_pkdxC.setBounds(267, 221, 27, 36);
		contentPane.add(num_pkdxC);

		num_pkdxA = new JLabel("");
		num_pkdxA.setBounds(246, 154, 38, 36);
		contentPane.add(num_pkdxA);

		pkdex_abajo = new JLabel("");
		pkdex_abajo.setHorizontalAlignment(SwingConstants.CENTER);
		pkdex_abajo.setBounds(188, 290, 38, 38);
		contentPane.add(pkdex_abajo);

		pkdex_arriba = new JLabel("");
		pkdex_arriba.setHorizontalAlignment(SwingConstants.CENTER);
		pkdex_arriba.setBounds(188, 153, 38, 38);
		contentPane.add(pkdex_arriba);

		pkdex_centro = new JLabel("");
		pkdex_centro.setHorizontalAlignment(SwingConstants.CENTER);
		pkdex_centro.setBounds(159, 192, 97, 97);
		contentPane.add(pkdex_centro);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(64, 128, 128));
		separator.setForeground(new Color(64, 128, 128));
		separator.setBounds(451, 0, 11, 504);
		contentPane.add(separator);

		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j = comboBox.getSelectedIndex();
				jugador = jugadores.get(j);
				nombre.setText(jugador.getNombre());
				nickname.setText(jugador.getLogin());
				passwordField.setText(jugador.getPass());
			}
		});
		comboBox.setBounds(544, 55, 200, 27);
		contentPane.add(comboBox);

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

		editar = new JButton("Editar");
		editar.addActionListener(this);
		editar.setBounds(539, 380, 97, 23);
		contentPane.add(editar);
		editar.setToolTipText(
				"Pulsa una vez para habilitar la edición de campos, pulsa una segunda vez para deshabilitarla.");

		banear = new JButton("Banear");
		banear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jugador.isBan()) {
					jugador.setBan(false);
					JOptionPane.showMessageDialog(null, "Se ha levantado el ban a el jugador: " + jugador.getNombre()
							+ ",  de nickname: " + jugador.getLogin());
				} else {
					jugador.setBan(true);
					JOptionPane.showMessageDialog(null, "El jugador: " + jugador.getNombre() + ",  de nickname: "
							+ jugador.getLogin() + ", ha sido baneado.");
				}
			}
		});
		banear.setBounds(792, 380, 97, 23);
		contentPane.add(banear);
		banear.setEnabled(false);

		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(654, 253, 200, 20);
		contentPane.add(passwordField);

		JLabel jpass = new JLabel("Contraseña:");
		jpass.setHorizontalAlignment(SwingConstants.TRAILING);
		jpass.setBounds(560, 256, 84, 14);
		contentPane.add(jpass);

		confirmarCambios = new JButton("Confirmar Cambios");
		confirmarCambios.setEnabled(false);
		confirmarCambios.setBounds(654, 305, 157, 23);
		confirmarCambios.addActionListener(this);
		contentPane.add(confirmarCambios);

		atras = new JButton("Atras");
		atras.setBounds(10, 11, 105, 23);
		atras.addActionListener(this);
		contentPane.add(atras);

		try {
			rellenarCampos();
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

		JLabel labelSelect = new JLabel("Seleccionár");
		labelSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!num_pkdxC.getText().equals("")) {
					VistaModificarDatos vd = new VistaModificarDatos(pokemon.get(Integer.valueOf(num_pkdxC.getText()) - 1),
						movimientos);
					vd.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Deslizate por el Slider lateral para seleccionar un pokemon.");
				}

			}
		});
		labelSelect.setHorizontalAlignment(SwingConstants.CENTER);
		labelSelect.setBounds(131, 399, 112, 46);
		contentPane.add(labelSelect);

		ImageIcon pkmnImg1 = new ImageIcon(rutas.jpgPoekdex());
		JLabel pkmnIMG1 = new JLabel();
		pkmnIMG1.setBounds(87, 71, 230, 400);
		contentPane.add(pkmnIMG1);
		pkmnIMG1.setIcon(pkmnImg1);

		slider = new JSlider();
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				slider.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						num_pkdxA.setText(String.valueOf(slider.getValue() - 1));
						num_pkdxC.setText(String.valueOf(slider.getValue()));
						num_pkdxD.setText(String.valueOf(slider.getValue() + 1));

						galeria[1] = new ImageIcon(rutas.PNGfrontalPKMN(slider.getValue()));
						if (Integer.valueOf(num_pkdxC.getText()) == slider.getMinimum()) {
							num_pkdxA.setText("");
							galeria[0] = null;
							galeria[2] = new ImageIcon(rutas.PNGpequenyo(slider.getValue() + 1));
						} else if (Integer.valueOf(num_pkdxC.getText()) == slider.getMaximum()) {
							num_pkdxD.setText("");
							galeria[2] = null;
							galeria[0] = new ImageIcon(rutas.PNGpequenyo(slider.getValue() - 1));
						} else {
							galeria[0] = new ImageIcon(rutas.PNGpequenyo(slider.getValue() - 1));
							galeria[1] = new ImageIcon(rutas.PNGfrontalPKMN(slider.getValue()));
							galeria[2] = new ImageIcon(rutas.PNGpequenyo(slider.getValue() + 1));
						}
						pkdex_arriba.setIcon(galeria[0]);
						pkdex_centro.setIcon(galeria[1]);
						pkdex_abajo.setIcon(galeria[2]);
					}
				});
			}
		});
		slider.setPaintTicks(true);
		slider.setValue(1);
		slider.setSnapToTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setMinimum(1);
		slider.setMaximum(649);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(361, 58, 76, 413);
		contentPane.add(slider);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == atras) {
			this.dispose();
		} else if (e.getSource() == editar) {
			try {
				if (!esNull()) {
					if (!nombre.isEditable()) {
						nombre.setEditable(true);
						nickname.setEditable(true);
						passwordField.setEditable(true);
						confirmarCambios.setEnabled(true);
						banear.setEnabled(true);
					} else {
						nombre.setEditable(false);
						nickname.setEditable(false);
						passwordField.setEditable(false);
						confirmarCambios.setEnabled(false);
						banear.setEnabled(false);
					}
				}
			} catch (NotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		} else if (e.getSource() == confirmarCambios) {
			try {
				Jugador jugadorNew = new Jugador(nombre.getText(), nickname.getText(),
						String.valueOf(passwordField.getPassword()), jugador.getEquipo(), jugador.getPc(),
						jugador.isBan());
				mj.update(jugador, jugadorNew);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public boolean esNull() throws NotFoundException {
		boolean estaNull = false;
		if (jugador == null)
			throw new NotFoundException("No hay jugador seleccionado, selecciona uno.");

		return estaNull;
	}

	public void rellenarCampos() throws NotFoundException {

		for (int i = 0; i < jugadores.size(); i++)
			comboBox.addItem(jugadores.get(i).getNombre() + " \"" + jugadores.get(i).getLogin() + "\"");
	}
}
