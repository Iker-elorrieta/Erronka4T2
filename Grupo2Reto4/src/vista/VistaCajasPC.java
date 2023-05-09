package vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SwingConstants;
import java.awt.Font;

public class VistaCajasPC extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private RutasImg rutas = new RutasImg();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCajasPC frame = new VistaCajasPC();
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
	public VistaCajasPC() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1007, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ImageIcon pkmnImg1 = new ImageIcon("img/pc_background.png");
		
		JLabel numCaja = new JLabel("num");
		numCaja.setFont(new Font("Verdana", Font.PLAIN, 24));
		numCaja.setHorizontalAlignment(SwingConstants.CENTER);
		numCaja.setBounds(559, 11, 113, 51);
		contentPane.add(numCaja);
		JLabel pkmnIMG1 = new JLabel();
		pkmnIMG1.setBounds(0, 0, 889, 561);
		pkmnIMG1.setIcon(pkmnImg1);
		contentPane.add(pkmnIMG1);
		
	}

}
