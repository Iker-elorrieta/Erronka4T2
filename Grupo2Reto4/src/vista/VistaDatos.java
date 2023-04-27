package vista;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Pokemon;
import utils.RutasImg;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class VistaDatos extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RutasImg rutas = new RutasImg();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public VistaDatos(Pokemon pokemon) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 485, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGfrontalPKMN(pokemon.getId()));
		JLabel pkmnIMG1 = new JLabel();
		pkmnIMG1.setSize(100, 100);
		pkmnIMG1.setLocation(34, 74);
		contentPane.add(pkmnIMG1);
		pkmnIMG1.setIcon(pkmnImg1);
		
		ImageIcon pkmnImg2 = new ImageIcon(rutas.PNGpequenyo(pokemon.getId()));
		JLabel pkmnIMG2 = new JLabel();
		pkmnIMG2.setLocation(69, 33);
		pkmnIMG2.setSize(30, 30);
		contentPane.add(pkmnIMG2);
		pkmnIMG2.setIcon(pkmnImg2);
		
		JProgressBar progressBarHP = new JProgressBar();
		progressBarHP.setMaximum(255);
		progressBarHP.setValue(pokemon.getHp());
		progressBarHP.setBounds(233, 33, 214, 20);
		contentPane.add(progressBarHP);
		
		JProgressBar progressBarAtt = new JProgressBar();
		progressBarAtt.setMaximum(255);
		progressBarAtt.setValue(pokemon.getAtt());
		progressBarAtt.setBounds(233, 64, 214, 20);
		contentPane.add(progressBarAtt);
		
		JProgressBar progressBarSAtt = new JProgressBar();
		progressBarSAtt.setMaximum(255);
		progressBarSAtt.setValue(pokemon.getSatt());
		progressBarSAtt.setBounds(233, 95, 214, 20);
		contentPane.add(progressBarSAtt);
		
		JProgressBar progressBarDeff = new JProgressBar();
		progressBarDeff.setMaximum(255);
		progressBarDeff.setValue(pokemon.getDef());
		progressBarDeff.setBounds(233, 126, 214, 20);
		contentPane.add(progressBarDeff);
		
		JProgressBar progressBarSDeff = new JProgressBar();
		progressBarSDeff.setMaximum(255);
		progressBarSDeff.setValue(pokemon.getSdef());
		progressBarSDeff.setBounds(233, 157, 214, 20);
		contentPane.add(progressBarSDeff);
		
		JProgressBar progressBarVel = new JProgressBar();
		progressBarVel.setMaximum(255);
		progressBarVel.setValue(pokemon.getVel());
		progressBarVel.setBounds(233, 188, 214, 20);
		contentPane.add(progressBarVel);
		
		JButton atras = new JButton("Atras");
		atras.addActionListener(this);
		atras.setBounds(377, 365, 89, 23);
		contentPane.add(atras);
		
		JLabel jName = new JLabel(pokemon.getNombre_pokemon());
		jName.setHorizontalAlignment(SwingConstants.CENTER);
		jName.setBounds(25, 211, 119, 14);
		contentPane.add(jName);
		
		JLabel jgen = new JLabel("Generación: "+pokemon.getGeneracion());
		jgen.setHorizontalAlignment(SwingConstants.CENTER);
		jgen.setBounds(30, 11, 109, 14);
		contentPane.add(jgen);
		
		JLabel lblHP = new JLabel("HP:");
		lblHP.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHP.setBounds(177, 36, 46, 14);
		contentPane.add(lblHP);
		
		JLabel lblAtt = new JLabel("Att:");
		lblAtt.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAtt.setBounds(177, 64, 46, 14);
		contentPane.add(lblAtt);
		
		JLabel lblSAtt = new JLabel("SAtt");
		lblSAtt.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSAtt.setBounds(177, 95, 46, 14);
		contentPane.add(lblSAtt);
		
		JLabel lblDef = new JLabel("Def:");
		lblDef.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDef.setBounds(177, 126, 46, 14);
		contentPane.add(lblDef);
		
		JLabel lblSdeff = new JLabel("SDeff:");
		lblSdeff.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSdeff.setBounds(177, 157, 46, 14);
		contentPane.add(lblSdeff);
		
		JLabel lblVel = new JLabel("Vel:");
		lblVel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVel.setBounds(177, 188, 46, 14);
		contentPane.add(lblVel);
		
		JLabel tipo1 = new JLabel(pokemon.getTipo()[0].getNombre_tipo());
		tipo1.setBounds(10, 185, 62, 14);
		contentPane.add(tipo1);
		
		JLabel tipo2 = new JLabel(pokemon.getTipo()[1].getNombre_tipo());
		tipo2.setBounds(97, 185, 62, 14);
		contentPane.add(tipo2);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
