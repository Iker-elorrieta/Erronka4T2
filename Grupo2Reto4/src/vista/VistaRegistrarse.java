package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Metodos;
import excepciones.NotFoundException;
import manager.ManagerJugador;
import manager.ManagerPokemon;
import modelo.Jugador;
import modelo.Pokemon;
import utils.RutasImg;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.BevelBorder;

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
	private RutasImg rutas = new RutasImg();
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel errpkmn;
	private ArrayList<Pokemon> EquipoPKMN = new ArrayList<Pokemon>();
	private JButton verDatos;
	private JLabel jlabelpkmn;
	private Pokemon pokemon;
	private JButton btnValidar;
	private ManagerPokemon mp = new ManagerPokemon();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws NotFoundException 
	 */
	public VistaRegistrarse(){

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1037, 503);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setInheritsPopupMenu(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nombreTF = new JTextField();
		nombreTF.setBounds(413, 113, 185, 20);
		contentPane.add(nombreTF);
		nombreTF.setColumns(10);

		loginTF = new JTextField();
		loginTF.setBounds(413, 169, 185, 20);
		contentPane.add(loginTF);
		loginTF.setColumns(10);

		atras = new JButton("Atras");
		atras.setBounds(10, 11, 105, 23);
		atras.addActionListener(this);
		contentPane.add(atras);

		JLabel jlabelNombre = new JLabel("Nombre del ususario:");
		jlabelNombre.setBounds(255, 116, 148, 14);
		jlabelNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(jlabelNombre);

		JLabel jlabelNick = new JLabel("Nickname del usuario:");
		jlabelNick.setBounds(250, 172, 153, 14);
		jlabelNick.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(jlabelNick);

		JLabel jlabelpassw1 = new JLabel("Contraseña:");
		jlabelpassw1.setBounds(295, 239, 108, 14);
		jlabelpassw1.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(jlabelpassw1);

		JLabel jlabelpassw2 = new JLabel("Confirmar contraseña:");
		jlabelpassw2.setBounds(250, 285, 153, 14);
		jlabelpassw2.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(jlabelpassw2);

		JLabel jlabelTip = new JLabel("Aviso: Al hacer sesion, se pide el nick y la contraseña.");
		jlabelTip.setBounds(205, 45, 331, 14);
		jlabelTip.setForeground(Color.RED);
		contentPane.add(jlabelTip);

		passw1TF = new JPasswordField();
		passw1TF.setBounds(413, 236, 185, 20);
		contentPane.add(passw1TF);

		passw2TF = new JPasswordField();
		passw2TF.setBounds(413, 282, 185, 20);
		contentPane.add(passw2TF);

		errNombreV = new JLabel("El nombre no puede estar vacio");
		errNombreV.setHorizontalAlignment(SwingConstants.TRAILING);
		errNombreV.setBounds(10, 115, 213, 20);
		errNombreV.setForeground(Color.RED);
		contentPane.add(errNombreV);
		errNombreV.setVisible(false);

		err2Nick = new JLabel("");
		err2Nick.setHorizontalAlignment(SwingConstants.TRAILING);
		err2Nick.setBounds(10, 171, 237, 14);
		err2Nick.setForeground(Color.RED);
		contentPane.add(err2Nick);
		err2Nick.setVisible(false);

		err3Passw = new JLabel("");
		err3Passw.setHorizontalAlignment(SwingConstants.TRAILING);
		err3Passw.setBounds(10, 257, 237, 14);
		err3Passw.setForeground(Color.RED);
		contentPane.add(err3Passw);
		err3Passw.setVisible(false);

		btnValidar = new JButton("Registrarse");
		btnValidar.addActionListener(this);
		btnValidar.setBounds(381, 371, 114, 23);
		contentPane.add(btnValidar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(634, 142, 350, 110);
		contentPane.add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 3, 10, 10));

		jlabelpkmn = new JLabel("Elije un pokemon inicial:");
		jlabelpkmn.setBounds(634, 115, 213, 14);
		contentPane.add(jlabelpkmn);

		errpkmn = new JLabel("Elije un pokemon inicial");
		errpkmn.setForeground(new Color(255, 0, 0));
		errpkmn.setBounds(181, 375, 179, 14);
		contentPane.add(errpkmn);
		errpkmn.setVisible(false);

		verDatos = new JButton("Ver datos");
		verDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					verDatosEnVentana();
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		verDatos.setBounds(895, 11, 105, 23);
		contentPane.add(verDatos);

		elegirPokemon();
		contentPane.updateUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == atras) {
			this.dispose();
		} else if (e.getSource() == btnValidar) {
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

			if (EquipoPKMN.size() == 0) {
				errpkmn.setVisible(true);
			} else {
				errpkmn.setVisible(false);
				ok++;
			}

			if (ok == 4) {

				JOptionPane.showMessageDialog(null, "Prueba a hacer login.", "Registrado correctamente",
						JOptionPane.INFORMATION_MESSAGE);
				Jugador user = new Jugador(nombreTF.getText(), loginTF.getText(),
						String.valueOf(passw1TF.getPassword()), EquipoPKMN, null, false);
				ManagerJugador mj = new ManagerJugador();
				try {
					mj.insert(user);
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

	public void verDatosEnVentana() throws NotFoundException {

		if (pokemon != null) {
			VistaDatos vd = new VistaDatos(pokemon);
			vd.setVisible(true);
		} else
			throw new NotFoundException("No has seleccionadon ningun pokemon.");

	}

	public void elegirPokemon() {

		int i = 0;
		JLabel[] jlabelspkmn = new JLabel[3];

		while (i < 3) {
			int rnum = (int) ((Math.random() * 648) + 1);
			ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGfrontalPKMN(rnum));
			JLabel pkmnIMG1 = new JLabel();
			pkmnIMG1.setBorder(null);
			pkmnIMG1.setToolTipText(String.valueOf(rnum));
			panel.add(pkmnIMG1);
			pkmnIMG1.setIcon(pkmnImg1);
			pkmnIMG1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					try {
						pokemon = mp.selectPokemon(Integer.valueOf(pkmnIMG1.getToolTipText()));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (pkmnIMG1.getBorder() == null) {
						for (JLabel pklabel : jlabelspkmn)
							pklabel.setBorder(null);
						pkmnIMG1.setBorder(
								new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, null, null));
						EquipoPKMN.clear();
						EquipoPKMN.add(pokemon);

					} else {
						pkmnIMG1.setBorder(null);
						EquipoPKMN.remove(pokemon);
						pokemon = null;
					}

				}
			});
			jlabelspkmn[i] = pkmnIMG1;
			i++;
		}
		contentPane.add(scrollPane);

	}
}
