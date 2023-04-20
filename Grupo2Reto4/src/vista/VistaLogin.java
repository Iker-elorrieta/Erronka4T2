package vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.RutasImg;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaLogin extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RutasImg rutas = new RutasImg();
	private JLabel pkmnIMG_1 = new JLabel();
	private JLabel pkmnIMG_2 = new JLabel();
	private JLabel pkmnIMG_3 = new JLabel();
	private JLabel pkmnIMG_4 = new JLabel();
	private JLabel pkmnIMG_5 = new JLabel();

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
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ImageIcon pkmnImg1 = new ImageIcon(rutas.randomPNGFrontal());
		pkmnIMG_1.setBounds(501, 322, 90, 90);
		contentPane.add(pkmnIMG_1);
		pkmnIMG_1.setIcon(pkmnImg1);
		
		ImageIcon pkmnImg2 = new ImageIcon(rutas.randomPNGFrontal());
		pkmnIMG_2.setBounds(539, 160, 90, 90);
		contentPane.add(pkmnIMG_2);
		pkmnIMG_2.setIcon(pkmnImg2);
		
		ImageIcon pkmnImg3 = new ImageIcon(rutas.randomPNGFrontal());
		pkmnIMG_3.setBounds(392, 26, 90, 90);
		contentPane.add(pkmnIMG_3);
		pkmnIMG_3.setIcon(pkmnImg3);
		
		ImageIcon pkmnImg4 = new ImageIcon(rutas.randomPNGFrontal());
		pkmnIMG_4.setBounds(92, 26, 90, 90);
		contentPane.add(pkmnIMG_4);
		pkmnIMG_4.setIcon(pkmnImg4);

		ImageIcon pkmnImg5 = new ImageIcon(rutas.rutaImgProfOak());
		pkmnIMG_5.setBounds(29, 142, 190, 308);
		contentPane.add(pkmnIMG_5);
		pkmnIMG_5.setIcon(pkmnImg5);
		
		JButton aInicio = new JButton("Iniciar sesion");
		aInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});
		aInicio.setBounds(257, 159, 156, 23);
		contentPane.add(aInicio);
		
		JButton aRegistro = new JButton("Registrarse");
		aRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});
		aRegistro.setBounds(257, 277, 156, 23);
		contentPane.add(aRegistro);
	}
}
