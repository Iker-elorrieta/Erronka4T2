package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import manager.ManagerPokemon;
import modelo.Jugador;
import modelo.Pokemon;
import utils.RutasImg;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class VistaCajasPC extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RutasImg rutas = new RutasImg();
	private JPanel panel;
	private Pokemon pokemonEquipo;
	private Pokemon pokemonFocus;
	private ManagerPokemon mp = new ManagerPokemon();
	private Jugador jugador;
	private JLabel[] jlabelspkmn;
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
		jugador=jugadorActual;
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
		btnVerDatos.setBounds(14, 515, 183, 38);
		contentPane.add(btnVerDatos);

		JLabel imgGrandePkmn = new JLabel("pokeimg");
		imgGrandePkmn.setHorizontalAlignment(SwingConstants.CENTER);
		imgGrandePkmn.setBounds(54, 72, 100, 100);
		contentPane.add(imgGrandePkmn);

		panel = new JPanel();
		panel.setLocation(22, 301);
		panel.setSize(170, 200);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 5, 5));

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

	}
	
	public void refrescarPkmn() {
		jlabelspkmn = new JLabel[6];
		int i = 0;
		for (i = 0; i < jugador.getEquipo().size(); i++) {

			ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGpequenyo(jugador.getEquipo().get(i).getId()));
			JLabel pkmnIMG1 = new JLabel();
			pkmnIMG1.setBorder(null);
			pkmnIMG1.setToolTipText(i + "e");
			panel.add(pkmnIMG1);
			pkmnIMG1.setIcon(pkmnImg1);
			pkmnIMG1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					pokemonEquipo = jugador.getEquipo().get(Integer.valueOf(pkmnIMG1.getToolTipText().split("e")[0]));

					if (pkmnIMG1.getBorder() == null) {
						for (JLabel pklabel : jlabelspkmn)
							pklabel.setBorder(null);
						pkmnIMG1.setBorder(
								new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, null, null));
						
					} else {
						pkmnIMG1.setBorder(null);
						pokemonEquipo = null;
					}
				}
			});
			jlabelspkmn[i] = pkmnIMG1;
		}
		
		for(i=jugador.getEquipo().size();i<7;i++) {
			
		}
	}
	
}
