package vista;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import controlador.MetodosVista;
import excepciones.ArrayListLlenoException;
import excepciones.NotFoundException;
import manager.ManagerJugador;
import manager.ManagerPokemon;
import modelo.Caja;
import modelo.Jugador;
import modelo.Pokemon;
import utils.RutasImg;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VistaCajasPC extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RutasImg rutas = new RutasImg();
	private JPanel panel;
	private Pokemon pokemonEquipo;
	private Pokemon pokemonCaja;
	private ManagerPokemon mp = new ManagerPokemon();
	private ManagerJugador mj = new ManagerJugador();
	private Jugador jugador;
	private JLabel[] jlabelspkmn;
	private JLabel[] jlabelspkmnCaja;
	private JLabel imgGrandePkmn;
	private Pokemon pokemonSeleccionado;
	private ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
	private JButton atras;
	private JLabel nextlbl;
	private JLabel backlbl;
	private int nCaja = 0;
	private JLabel numCaja;
	private JLabel backgroundPC = new JLabel();
	private JComboBox<String> comboBox;
	private MetodosVista mv = new MetodosVista();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param jugadorActual
	 */
	public VistaCajasPC(Jugador jugadorActual) {
		jugador = jugadorActual;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			pokemon = mp.selectAll();
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

		backlbl = new JLabel();
		backlbl.setHorizontalAlignment(SwingConstants.CENTER);
		backlbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nCaja--;
				imgGrandePkmn.setIcon(null);
				pokemonSeleccionado = null;
				pokemonCaja=null;
				if (nCaja <= -1)
					nCaja = 7;

				numCaja.setText(String.valueOf(nCaja + 1));
				printCaja(jugador.getPc().getCajas().get(nCaja));
				contentPane.revalidate();
				contentPane.repaint();
				contentPane.updateUI();

			}
		});
		backlbl.setBounds(367, 22, 62, 31);
		contentPane.add(backlbl);

		nextlbl = new JLabel();
		nextlbl.setHorizontalAlignment(SwingConstants.CENTER);
		nextlbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nCaja++;
				imgGrandePkmn.setIcon(null);
				pokemonSeleccionado = null;
				pokemonCaja=null;
				if (nCaja >= 8)
					nCaja = 0;

				numCaja.setText(String.valueOf(nCaja + 1));
				printCaja(jugador.getPc().getCajas().get(nCaja));
				contentPane.revalidate();
				contentPane.repaint();
				contentPane.updateUI();
				contentPane.revalidate();
			}
		});
		nextlbl.setBounds(682, 22, 62, 31);
		contentPane.add(nextlbl);

		panel = new JPanel();
		panel.setLocation(22, 301);
		panel.setSize(170, 200);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		printCaja(jugador.getPc().getCajas().get(nCaja));
		refrescarPkmn();

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

		JLabel nombreUser = new JLabel(jugador.getNombre());
		nombreUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		nombreUser.setHorizontalAlignment(SwingConstants.CENTER);
		nombreUser.setBounds(10, 245, 194, 20);
		contentPane.add(nombreUser);

		numCaja = new JLabel("1");
		numCaja.setFont(new Font("Verdana", Font.PLAIN, 24));
		numCaja.setHorizontalAlignment(SwingConstants.CENTER);
		numCaja.setBounds(559, 11, 113, 51);
		contentPane.add(numCaja);

		atras = new JButton("Atras");
		atras.setBounds(909, 0, 103, 38);
		atras.addActionListener(this);
		contentPane.add(atras);

		JButton registrarPKMN = new JButton("<html>Registrar<br> pokemon</html>");
		registrarPKMN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean huecoSeleccionado = false;

				for (JLabel pklabel : jlabelspkmnCaja) {
					if (pklabel.getBorder() != null)
						huecoSeleccionado = true;
				}

				if (huecoSeleccionado) {
					comboBox.setSelectedIndex(0);
					setBounds(100, 100, 1400, 600);
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un hueco del PC.");
				}

			}
		});
		registrarPKMN.setBounds(888, 100, 124, 51);
		contentPane.add(registrarPKMN);

		JButton botonLiberar = new JButton("Liberar");
		botonLiberar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pokemonCaja == null)
					JOptionPane.showMessageDialog(null, "Solo puedes liberar un pokemon desde el PC.");
				else {
					try {
						mv.liberarPokemon(pokemonCaja.getId(), jugador.getPc(), jugador.getPc().getCajas().get(nCaja));
						recargar();
						imgGrandePkmn.setIcon(null);
						pokemonSeleccionado = null;
					} catch (NotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});
		botonLiberar.setBounds(888, 149, 124, 51);
		contentPane.add(botonLiberar);

		JButton btnIntercambiar = new JButton("Intercambiar");
		btnIntercambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (pokemonCaja != null && pokemonEquipo != null) {
					int poscaja=0;
					int posequipo=0;
					int contador=0;
					
					for (JLabel pklabel : jlabelspkmnCaja) {
						if (pklabel.getBorder() != null)
							poscaja=contador;
						contador++;
					}
					contador=0;
					for (JLabel pklabel : jlabelspkmn) {
						if (pklabel.getBorder() != null)
							posequipo=contador;
						contador++;
					}
					Jugador j =jugador;
					jugador=mv.intercambiarFromEquipoToCaja(posequipo, poscaja, jugador, jugador.getPc().getCajas().get(nCaja));
					
					try {
						mj.update(j, jugador);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					recargar();
				}else {
					JOptionPane.showMessageDialog(null,
							"Para poder intercambiar, selecciona un pokemon del equipo y de la caja.");
				}
				
				
			}
		});
		btnIntercambiar.setBounds(888, 196, 124, 51);
		contentPane.add(btnIntercambiar);

		JButton btnSacar = new JButton("Sacar");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean huecoequipo = false;

				for (JLabel pklabel : jlabelspkmnCaja) {
					if (pklabel.getBorder() != null)
						huecoequipo = true;
				}
				if (pokemonEquipo == null && huecoequipo) {

					boolean huecoSeleccionado = false;

					for (JLabel pklabel : jlabelspkmnCaja) {
						if (pklabel.getBorder() != null)
							huecoSeleccionado = true;
					}
					if (huecoSeleccionado) {
						if (pokemonCaja != null) {
							try {
								mv.moverPokemonCajaEquipo(jugador, pokemonCaja,
										jugador.getPc().getCajas().get(nCaja));
							} catch (ArrayListLlenoException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e1.getMessage());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							recargar();
						} else
							JOptionPane.showMessageDialog(null,
									"El hueco seleccionado de la caja tiene que contener un pokemon.");
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona un hueco del pc.");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un hueco vacio del equipo.");
				}

			}
		});
		btnSacar.setBounds(888, 245, 124, 45);
		contentPane.add(btnSacar);

		JButton btnDejar = new JButton("Dejar");
		btnDejar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pokemonEquipo != null) {

					boolean huecoSeleccionado = false;

					for (JLabel pklabel : jlabelspkmnCaja) {
						if (pklabel.getBorder() != null)
							huecoSeleccionado = true;
					}
					if (huecoSeleccionado) {
						if (pokemonCaja == null) {
							try {
								mv.moverPokemonEquipoCaja(jugador, pokemonEquipo, jugador.getPc().getCajas().get(nCaja));
							} catch (ArrayListLlenoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							recargar();
						} else
							JOptionPane.showMessageDialog(null,
									"El hueco seleccionado de la caja tiene que estar vacio.");
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona un hueco del pc.");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un pokemon del equipo.");
				}

			}
		});
		btnDejar.setBounds(888, 286, 124, 45);
		contentPane.add(btnDejar);

		contentPane.add(backgroundPC);
		ImageIcon backPC = new ImageIcon("img/pc_background.png");
		backgroundPC.setBounds(0, 0, 889, 561);
		backgroundPC.setIcon(backPC);

		JLabel labelRegistrar = new JLabel("");
		labelRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		labelRegistrar.setBounds(1117, 171, 100, 100);
		contentPane.add(labelRegistrar);

		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(13);
		comboBox.setBounds(1083, 301, 164, 22);
		contentPane.add(comboBox);
		for (Pokemon pk : pokemon)
			comboBox.addItem(pk.getNombre_pokemon());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon pkmnRegistrar = new ImageIcon(rutas.PNGfrontalPKMN(comboBox.getSelectedIndex() + 1));
				labelRegistrar.setIcon(pkmnRegistrar);
			}
		});

		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mv.anyadirPokemon(comboBox.getSelectedIndex() + 1, jugador.getPc(),
							jugador.getPc().getCajas().get(nCaja));

				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (ArrayListLlenoException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setBounds(100, 100, 1028, 600);

				recargar();
			}
		});
		aceptar.setBounds(1270, 301, 89, 23);
		contentPane.add(aceptar);

	}

	public void refrescarPkmn() {
		contentPane.remove(panel);
		panel = new JPanel();
		panel.setLocation(22, 301);
		panel.setSize(170, 200);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		contentPane.setComponentZOrder(panel, 0);
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
					int pos = Integer.valueOf(pkmnIMG1.getToolTipText().split("e")[0]);
					pokemonEquipo = jugador.getEquipo().get(pos);
					pokemonFocus(jugador.getEquipo().get(pos).getId());
					if (pkmnIMG1.getBorder() == null) {
						for (JLabel pklabel : jlabelspkmn)
							pklabel.setBorder(null);
						pkmnIMG1.setBorder(
								new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, null, null));
						pokemonSeleccionado = pokemonEquipo;
					} else {
						pkmnIMG1.setBorder(null);
						imgGrandePkmn.setIcon(null);
						pokemonEquipo = null;
						pokemonSeleccionado = null;
					}
				}
			});
			jlabelspkmn[i] = pkmnIMG1;
		}

		for (i = jugador.getEquipo().size(); i < 6; i++) {
			ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGpequenyo(0));
			JLabel labelpkmnEquipo = new JLabel();
			labelpkmnEquipo.setBorder(null);
			labelpkmnEquipo.setSize(50, 50);
			labelpkmnEquipo.setHorizontalAlignment(SwingConstants.CENTER);
			labelpkmnEquipo.setToolTipText(i + "e");
			panel.add(labelpkmnEquipo);
			labelpkmnEquipo.setIcon(pkmnImg1);
			labelpkmnEquipo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					pokemonEquipo = null;
					pokemonSeleccionado = null;
					pokemonFocus(0);
					if (labelpkmnEquipo.getBorder() == null) {
						for (JLabel pklabel : jlabelspkmn)
							pklabel.setBorder(null);
						labelpkmnEquipo.setBorder(
								new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, null, null));
					} else {
						labelpkmnEquipo.setBorder(null);
						imgGrandePkmn.setIcon(null);
					}
				}
			});
			jlabelspkmn[i] = labelpkmnEquipo;
		}
		ImageIcon backPC = new ImageIcon("img/pc_background.png");
		backgroundPC.setIcon(null);
		backgroundPC.setIcon(backPC);
	}

	public void pokemonFocus(int id) {

		ImageIcon pkmnImg1 = new ImageIcon(rutas.PNGfrontalPKMN(id));
		imgGrandePkmn.setIcon(pkmnImg1);

	}

	public void printCaja(Caja caja) {
		int count = 0;

		if (jlabelspkmnCaja == null)
			jlabelspkmnCaja = new JLabel[30];
		else {
			for (int i = 0; i < jlabelspkmnCaja.length; i++) {
				jlabelspkmnCaja[i].setEnabled(false);
				jlabelspkmnCaja[i].setVisible(false);
			}

		}

		for (int y = 72; y < 500; y = y + 95) {
			for (int x = 220; x < 800; x = x + 110) {
				JLabel lblcajaespacio = new JLabel();

				if (count < caja.getPokemon().size()) {

					ImageIcon pkmnImg2 = new ImageIcon(rutas.PNGpequenyo(caja.getPokemon().get(count).getId()));
					lblcajaespacio.setIcon(pkmnImg2);
					lblcajaespacio.setToolTipText(count + "c");
					lblcajaespacio.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int pos = Integer.valueOf(lblcajaespacio.getToolTipText().split("c")[0]);
							pokemonCaja = caja.getPokemon().get(pos);
							pokemonFocus(caja.getPokemon().get(pos).getId());
							if (lblcajaespacio.getBorder() == null) {
								for (JLabel pklabel : jlabelspkmnCaja)
									pklabel.setBorder(null);
								lblcajaespacio.setBorder(
										new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, null, null));
								pokemonSeleccionado = pokemonCaja;
							} else {
								lblcajaespacio.setBorder(null);
								imgGrandePkmn.setIcon(null);
								pokemonSeleccionado = null;
								pokemonCaja = null;
							}
						}
					});
					lblcajaespacio.setHorizontalAlignment(SwingConstants.CENTER);
					lblcajaespacio.setBounds(x, y, 100, 82);
					lblcajaespacio.setVisible(true);
					contentPane.add(lblcajaespacio);

				} else {
					ImageIcon pkmnImg2 = new ImageIcon(rutas.PNGpequenyo(0));
					lblcajaespacio.setIcon(pkmnImg2);
					lblcajaespacio.setToolTipText(count + "c");
					lblcajaespacio.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							pokemonCaja = null;
							pokemonSeleccionado = null;
							pokemonFocus(0);
							if (lblcajaespacio.getBorder() == null) {
								for (JLabel pklabel : jlabelspkmnCaja)
									pklabel.setBorder(null);
								lblcajaespacio.setBorder(
										new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 0), null, null, null));
							} else {
								lblcajaespacio.setBorder(null);
								imgGrandePkmn.setIcon(null);
							}
						}
					});
					lblcajaespacio.setHorizontalAlignment(SwingConstants.CENTER);
					lblcajaespacio.setBounds(x, y, 100, 82);
					contentPane.add(lblcajaespacio);
					lblcajaespacio.setVisible(true);
				}
				lblcajaespacio.revalidate();
				contentPane.setComponentZOrder(lblcajaespacio, 0);
				jlabelspkmnCaja[count] = lblcajaespacio;
				count++;
			}
		}
		ImageIcon backPC = new ImageIcon("img/pc_background.png");
		backgroundPC.setIcon(null);
		backgroundPC.setIcon(backPC);
	}

	public void verDatosEnVentana() throws NotFoundException {

		if (pokemonSeleccionado != null) {
			VistaDatos vd = new VistaDatos(pokemonSeleccionado);
			vd.setVisible(true);
		} else
			throw new NotFoundException("No has seleccionadon ningun pokemon.");

	}

	public void recargar() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		try {
			jugadores = mj.selectAll();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Jugador j : jugadores) {
			if (j.getLogin().equals(jugador.getLogin()))
				jugador = j;
		}
		refrescarPkmn();
		printCaja(jugador.getPc().getCajas().get(nCaja));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == atras) {
			VistaPerfil vp = new VistaPerfil(jugador);
			vp.setVisible(true);
			this.dispose();
		}
	}
}
