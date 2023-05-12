package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import excepciones.NotFoundException;
import modelo.Jugador;
import modelo.Pokemon;
import utils.RutasImg;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaCajasPC extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RutasImg rutas = new RutasImg();
	private JPanel panel;
	private Pokemon pokemonEquipo;
	//private ManagerPokemon mp = new ManagerPokemon();
	private Jugador jugador;
	private JLabel[] jlabelspkmn;
	private JLabel imgGrandePkmn;
	private Pokemon pokemonSeleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCajasPC frame = new VistaCajasPC(null);
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
	 * @param jugadorActual
	 */
	public VistaCajasPC(Jugador jugadorActual) {
		jugador = jugadorActual;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1007, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblcaja1espacio = new JLabel("New label");
		lblcaja1espacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblcaja1espacio.setBounds(220, 72, 100, 82);
		contentPane.add(lblcaja1espacio);

		JButton btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					verDatosEnVentana();
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnVerDatos.setBounds(14, 515, 183, 38);
		contentPane.add(btnVerDatos);

		imgGrandePkmn = new JLabel("");
		imgGrandePkmn.setHorizontalAlignment(SwingConstants.CENTER);
		imgGrandePkmn.setBounds(54, 72, 100, 100);
		contentPane.add(imgGrandePkmn);

		panel = new JPanel();
		panel.setLocation(22, 301);
		panel.setSize(170, 200);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel nombreUser = new JLabel(jugador.getNombre());
		nombreUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		nombreUser.setHorizontalAlignment(SwingConstants.CENTER);
		nombreUser.setBounds(10, 245, 194, 20);
		contentPane.add(nombreUser);

		JLabel numCaja = new JLabel("1");
		numCaja.setFont(new Font("Verdana", Font.PLAIN, 24));
		numCaja.setHorizontalAlignment(SwingConstants.CENTER);
		numCaja.setBounds(559, 11, 113, 51);
		contentPane.add(numCaja);

		JLabel backgroundPC = new JLabel();
		contentPane.add(backgroundPC);
		ImageIcon pkmnImg1 = new ImageIcon("img/pc_background.png");
		backgroundPC.setBounds(0, 0, 889, 561);
		backgroundPC.setIcon(pkmnImg1);
		 refrescarPkmn();
	}

	public void refrescarPkmn() {
		jlabelspkmn = new JLabel[6];
		int i = 0;
		for (i = 0; i < jugador.getEquipo().size(); i++) {

			ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGpequenyo(jugador.getEquipo().get(i).getId()));
			JLabel pkmnIMG1 = new JLabel();
			pkmnIMG1.setBorder(null);
			pkmnIMG1.setToolTipText(i + "e");
			pkmnIMG1.setSize(50, 50);
			pkmnIMG1.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(pkmnIMG1);
			pkmnIMG1.setIcon(pkmnImg1);
			pkmnIMG1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int pos=Integer.valueOf(pkmnIMG1.getToolTipText().split("e")[0]);
					pokemonEquipo = jugador.getEquipo().get(pos);
					pokemonFocus(jugador.getEquipo().get(pos).getId());
					if (pkmnIMG1.getBorder() == null) {
						for (JLabel pklabel : jlabelspkmn)
							pklabel.setBorder(null);
						pkmnIMG1.setBorder(
								new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, null, null));
						pokemonSeleccionado=pokemonEquipo;
					} else {
						pkmnIMG1.setBorder(null);
						imgGrandePkmn.setIcon(null);
						pokemonEquipo = null;
						pokemonSeleccionado=null;
					}
				}
			});
			jlabelspkmn[i] = pkmnIMG1;
		}

		for (i = jugador.getEquipo().size(); i < 6; i++) {
			ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGpequenyo(0));
			JLabel pkmnIMG1 = new JLabel();
			pkmnIMG1.setBorder(null);
			pkmnIMG1.setSize(50, 50);
			pkmnIMG1.setHorizontalAlignment(SwingConstants.CENTER);
			pkmnIMG1.setToolTipText(i + "e");
			panel.add(pkmnIMG1);
			pkmnIMG1.setIcon(pkmnImg1);
			pkmnIMG1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					pokemonEquipo = null;
					pokemonSeleccionado=null;
					pokemonFocus(0);
					if (pkmnIMG1.getBorder() == null) {
						for (JLabel pklabel : jlabelspkmn)
							pklabel.setBorder(null);
						pkmnIMG1.setBorder(
								new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, null, null));
					} else {
						pkmnIMG1.setBorder(null);
						imgGrandePkmn.setIcon(null);
					}
				}
			});
			jlabelspkmn[i] = pkmnIMG1;
		}
	}

	public void pokemonFocus(int id) {

		ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGfrontalPKMN(id));
		imgGrandePkmn.setIcon(pkmnImg1);

	}
	
	public void verDatosEnVentana() throws NotFoundException {

		if (pokemonSeleccionado != null) {
			VistaDatos vd = new VistaDatos(pokemonSeleccionado);
			vd.setVisible(true);
		} else
			throw new NotFoundException("No has seleccionadon ningun pokemon.");

	}

}
