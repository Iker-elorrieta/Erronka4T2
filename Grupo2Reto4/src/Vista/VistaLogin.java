package Vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utils.RutasImg;

import java.awt.Color;
import javax.swing.JLabel;

public class VistaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel oakIMG;
	private RutasImg rutas = new RutasImg();
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
		setBounds(100, 100, 710, 506);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String url = rutas.randomGifFrontal();
		ImageIcon oak = new ImageIcon(url);
		
		
		oakIMG = new JLabel();
		oakIMG.setBounds(50, 28, 75, 70);
		contentPane.add(oakIMG);
		oakIMG.setIcon(oak);
		
		JLabel oakIMG_1 = new JLabel();
		oakIMG_1.setBounds(526, 49, 75, 70);
		contentPane.add(oakIMG_1);
		oakIMG.setIcon(oak);
		
		JLabel oakIMG_2 = new JLabel();
		oakIMG_2.setBounds(161, 326, 75, 70);
		contentPane.add(oakIMG_2);
		oakIMG.setIcon(oak);
		
		JLabel oakIMG_3 = new JLabel();
		oakIMG_3.setBounds(497, 171, 75, 70);
		contentPane.add(oakIMG_3);
		oakIMG.setIcon(oak);
		
		JLabel oakIMG_4 = new JLabel();
		oakIMG_4.setBounds(179, 142, 75, 70);
		contentPane.add(oakIMG_4);
		oakIMG.setIcon(oak);
		
	}
}
