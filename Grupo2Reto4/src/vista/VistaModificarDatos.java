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
import javax.swing.JSlider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VistaModificarDatos extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RutasImg rutas = new RutasImg();
	private JSlider hpSlider;
	private JSlider atkSlider;
	private JSlider	sAtkSlider;
	private JSlider defSlider;
	private JSlider sDefSlider;
	private JSlider velSlider;
	private JSpinner hpSpinner;
	private JSpinner atkSpinner;
	private JSpinner defSpinner;
	private JSpinner sAtkSpinner;
	private JSpinner sDefSpinner;
	private JSpinner velSpinner;

	/**
	 * Launch the application.VistaDatos.java
	 */

	/**
	 * Create the frame.
	 */
	public VistaModificarDatos(Pokemon pokemon) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 561);
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
		atras.setBounds(0, 499, 89, 23);
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
		lblMov1.setBounds(10, 273, 154, 43);
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
		lblMov2.setBounds(202, 273, 154, 43);
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
		lblMov3.setBounds(399, 273, 154, 43);
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
		lblMov4.setBounds(588, 273, 154, 43);
		contentPane.add(lblMov4);
		
		hpSlider = new JSlider();
		hpSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				hpSlider.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						progressBarHP.setValue(hpSlider.getValue());
						hpSpinner.setValue(hpSlider.getValue());
					}
				});
			}
		});
		hpSlider.setMinorTickSpacing(1);
		hpSlider.setValue(pokemon.getHp());
		hpSlider.setMinimum(1);
		hpSlider.setMaximum(255);
		hpSlider.setBounds(473, 27, 200, 26);
		contentPane.add(hpSlider);
		
		hpSpinner = new JSpinner();
		hpSpinner.setModel(new SpinnerNumberModel(1, 1, 255, 1));
		hpSpinner.setBounds(680, 30, 62, 20);
		contentPane.add(hpSpinner);
		
		atkSlider = new JSlider();
		atkSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				atkSlider.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						progressBarAtt.setValue(atkSlider.getValue());
						atkSpinner.setValue(atkSlider.getValue());
					}
				});
			}
		});
		atkSlider.setMinorTickSpacing(1);
		atkSlider.setValue(pokemon.getAtt());
		atkSlider.setMinimum(1);
		atkSlider.setMaximum(255);
		atkSlider.setBounds(473, 58, 200, 26);
		contentPane.add(atkSlider);
		
		atkSpinner = new JSpinner();
		atkSpinner.setBounds(680, 61, 62, 20);
		atkSpinner.setModel(new SpinnerNumberModel(1, 1, 255, 1));
		contentPane.add(atkSpinner);
		
		sAtkSlider = new JSlider();
		sAtkSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				sAtkSlider.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						progressBarSAtt.setValue(sAtkSlider.getValue());
						sAtkSpinner.setValue(sAtkSlider.getValue());
					}
				});
			}
		});
		sAtkSlider.setMinorTickSpacing(1);
		sAtkSlider.setMinimum(1);
		sAtkSlider.setValue(pokemon.getSatt());
		sAtkSlider.setMaximum(255);
		sAtkSlider.setBounds(473, 89, 200, 26);
		contentPane.add(sAtkSlider);
		
		sAtkSpinner = new JSpinner();
		sAtkSpinner.setBounds(680, 92, 62, 20);
		sAtkSpinner.setModel(new SpinnerNumberModel(1, 1, 255, 1));
		contentPane.add(sAtkSpinner);
		
		defSlider = new JSlider();
		defSlider.setMinorTickSpacing(1);
		defSlider.setValue(pokemon.getDef());
		defSlider.setMinimum(1);
		defSlider.setMaximum(255);
		defSlider.setBounds(473, 120, 200, 26);
		contentPane.add(defSlider);
		
		defSpinner = new JSpinner();
		defSpinner.setBounds(680, 123, 62, 20);
		defSpinner.setModel(new SpinnerNumberModel(1, 1, 255, 1));
		contentPane.add(defSpinner);
		
		sDefSlider = new JSlider();
		sDefSlider.setMinorTickSpacing(1);
		sDefSlider.setValue(pokemon.getSdef());
		sDefSlider.setMinimum(1);
		sDefSlider.setMaximum(255);
		sDefSlider.setBounds(473, 151, 200, 26);
		contentPane.add(sDefSlider);
		
		sDefSpinner = new JSpinner();
		sDefSpinner.setBounds(680, 154, 62, 20);
		sDefSpinner.setModel(new SpinnerNumberModel(1, 1, 255, 1));
		contentPane.add(sDefSpinner);
		
		velSlider = new JSlider();
		velSlider.setMinorTickSpacing(1);
		velSlider.setValue(pokemon.getVel());
		velSlider.setMinimum(1);
		velSlider.setMaximum(255);
		velSlider.setBounds(473, 182, 200, 26);
		contentPane.add(velSlider);
		
		velSpinner = new JSpinner();
		velSpinner.setBounds(680, 185, 62, 20);
		velSpinner.setModel(new SpinnerNumberModel(1, 1, 255, 1));
		contentPane.add(velSpinner);

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
