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
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class VistaDatos extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RutasImg rutas = new RutasImg();

	/**
	 * Launch the application.VistaDatos.java
	 */

	/**
	 * Create the frame.
	 */
	public VistaDatos(Pokemon pokemon) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		setBounds(100, 100, 478, 451);
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
		progressBarHP.setToolTipText(String.valueOf(pokemon.getHp()));
		progressBarHP.setValue(pokemon.getHp());
		progressBarHP.setBounds(233, 33, 214, 20);
		contentPane.add(progressBarHP);

		JProgressBar progressBarAtt = new JProgressBar();
		progressBarAtt.setMaximum(255);
		progressBarAtt.setToolTipText(String.valueOf(pokemon.getAtt()));
		progressBarAtt.setValue(pokemon.getAtt());
		progressBarAtt.setBounds(233, 64, 214, 20);
		contentPane.add(progressBarAtt);

		JProgressBar progressBarSAtt = new JProgressBar();
		progressBarSAtt.setMaximum(255);
		progressBarSAtt.setToolTipText(String.valueOf(pokemon.getSatt()));
		progressBarSAtt.setValue(pokemon.getSatt());
		progressBarSAtt.setBounds(233, 95, 214, 20);
		contentPane.add(progressBarSAtt);

		JProgressBar progressBarDeff = new JProgressBar();
		progressBarDeff.setMaximum(255);
		progressBarDeff.setToolTipText(String.valueOf(pokemon.getDef()));
		progressBarDeff.setValue(pokemon.getDef());
		progressBarDeff.setBounds(233, 126, 214, 20);
		contentPane.add(progressBarDeff);

		JProgressBar progressBarSDeff = new JProgressBar();
		progressBarSDeff.setMaximum(255);
		progressBarSDeff.setToolTipText(String.valueOf(pokemon.getSdef()));
		progressBarSDeff.setValue(pokemon.getSdef());
		progressBarSDeff.setBounds(233, 157, 214, 20);
		contentPane.add(progressBarSDeff);

		JProgressBar progressBarVel = new JProgressBar();
		progressBarVel.setMaximum(255);
		progressBarVel.setToolTipText(String.valueOf(pokemon.getVel()));
		progressBarVel.setValue(pokemon.getVel());
		progressBarVel.setBounds(233, 188, 214, 20);
		contentPane.add(progressBarVel);

		JButton atras = new JButton("Atras");
		atras.addActionListener(this);
		atras.setBounds(0, 389, 89, 23);
		contentPane.add(atras);

		JLabel jName = new JLabel(pokemon.getNombre_pokemon());
		jName.setHorizontalAlignment(SwingConstants.CENTER);
		jName.setBounds(25, 211, 119, 14);
		contentPane.add(jName);

		JLabel jgen = new JLabel("Generación: " + pokemon.getReg().getNombre());
		jgen.setToolTipText(tabla());
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
		
		String tipo2S="";
		if(pokemon.getTipo()[1] != null)
			tipo2S=pokemon.getTipo()[1].getNombre_tipo();
		JLabel tipo2 = new JLabel(tipo2S);
		tipo2.setBounds(97, 185, 62, 14);
		contentPane.add(tipo2);

		JLabel lblMov1 = new JLabel(pokemon.getMovimientos().get(0).getNombre());
		lblMov1.setToolTipText("<html>" + "Tipo: " + pokemon.getMovimientos().get(0).getTipo().getNombre_tipo() + "<br>"
				+ "Potencia: " + pokemon.getMovimientos().get(0).getPotencia() + "<br>" + "Precision: "
				+ pokemon.getMovimientos().get(0).getPrecision() + "<br>" + "PP: "
				+ pokemon.getMovimientos().get(0).getPuntosPoder() + "</html>");
		lblMov1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblMov1.setForeground(new Color(0, 0, 0));
		lblMov1.setBackground(Color.WHITE);
		lblMov1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMov1.setBounds(34, 256, 154, 43);
		contentPane.add(lblMov1);

		JLabel lblMov2 = new JLabel(pokemon.getMovimientos().get(1).getNombre());
		lblMov2.setToolTipText("<html>" + "Tipo: " + pokemon.getMovimientos().get(1).getTipo().getNombre_tipo() + "<br>"
				+ "Potencia: " + pokemon.getMovimientos().get(1).getPotencia() + "<br>" + "Precision: "
				+ pokemon.getMovimientos().get(1).getPrecision() + "<br>" + "PP: "
				+ pokemon.getMovimientos().get(1).getPuntosPoder() + "</html>");
		lblMov2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMov2.setForeground(Color.BLACK);
		lblMov2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblMov2.setBackground(Color.WHITE);
		lblMov2.setBounds(265, 256, 154, 43);
		contentPane.add(lblMov2);

		JLabel lblMov3 = new JLabel(pokemon.getMovimientos().get(2).getNombre());
		lblMov3.setToolTipText("<html>" + "Tipo: " + pokemon.getMovimientos().get(2).getTipo().getNombre_tipo() + "<br>"
				+ "Potencia: " + pokemon.getMovimientos().get(2).getPotencia() + "<br>" + "Precision: "
				+ pokemon.getMovimientos().get(2).getPrecision() + "<br>" + "PP: "
				+ pokemon.getMovimientos().get(2).getPuntosPoder() + "</html>");
		lblMov3.setHorizontalAlignment(SwingConstants.CENTER);
		lblMov3.setForeground(Color.BLACK);
		lblMov3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblMov3.setBackground(Color.WHITE);
		lblMov3.setBounds(34, 326, 154, 43);
		contentPane.add(lblMov3);

		JLabel lblMov4 = new JLabel(pokemon.getMovimientos().get(3).getNombre());
		lblMov4.setToolTipText("<html>" + "Tipo: " + pokemon.getMovimientos().get(3).getTipo().getNombre_tipo() + "<br>"
				+ "Potencia: " + pokemon.getMovimientos().get(3).getPotencia() + "<br>" + "Precision: "
				+ pokemon.getMovimientos().get(3).getPrecision() + "<br>" + "PP: "
				+ pokemon.getMovimientos().get(3).getPuntosPoder() + "</html>");
		lblMov4.setHorizontalAlignment(SwingConstants.CENTER);
		lblMov4.setForeground(Color.BLACK);
		lblMov4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblMov4.setBackground(Color.WHITE);
		lblMov4.setBounds(265, 326, 154, 43);
		contentPane.add(lblMov4);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	public String tabla() {
		String tabla = "<html>" + "<style>\r\n" + "table, th, td {\r\n" + "\r\n" + "border: 1px solid black;\r\n"
				+ "text-align:center;\r\n" + "border-collapse: collapse;\r\n" + "}\r\n" + "td{\r\n"
				+ "height: 20px;\r\n" + "width: 120px;\r\n" + "}\r\n" + "</style>" + "<body>" + "<table>" + "<tr>"
				+ "<th>Region</th>" + "<th>Cantidad de pokemon</th>" + "</tr>" + "<tr>" + "<td>Kanto</td>"
				+ "<td>1-151</td>" + "</tr>" + "<tr>" + "<td>Johto</td>" + "<td>152-242</td>" + "</tr>" + "<tr>"
				+ "<td>Hoenn</td>" + "<td>243-341</td>" + "</tr>" + "<tr>" + "<td>Shinnoh</td>" + "<td>342-493</td>"
				+ "</tr>" + "<tr>" + "<td>Tesselia</td>" + "<td>494-649</td>" + "</tr>" + "</table>" + "</body>"
				+ "</html>";
		return tabla;
	}
}
