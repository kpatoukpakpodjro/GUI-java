import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calculatrice extends JFrame{
	Container menu;
	JButton buttons[] = new JButton[12];
	//Pour les boutons 0 à 9 et . =
	JButton operations[] = new JButton[9];
	//pour les opérations + - * / ln exp ^ racine_carrée Clean
	JLabel ecran;
	double v1 = 0;
	double v2 = 0;
	String opt = "";
	ImageIcon icon = new ImageIcon("calcul.png");

	public Calculatrice()
	{
		// ---- Quelques propriétés sur JFrame ----
		this.setTitle("CASIO");
		this.setIconImage(icon.getImage());

		// ---- Resisement de JFrame ----
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);

		// ---- Initialisation du Container ----
		menu = this.getContentPane();
		menu.setLayout(new BorderLayout(6, 6));

		// ---- Initialisation des tables des boutons ----
		for (int i = 0; i < 12; i++)
			buttons[i] = new JButton();
		for (int i = 0; i < 9; i++)
			operations[i] = new JButton();

		// ---- Ajout de la zone de Text ----
		JPanel txt = new JPanel();
		ecran = new JLabel();
		ecran.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
		txt.setPreferredSize(new Dimension(300, 50));
		txt.add(ecran);

		// ---- Création de la grille des numéros ----
		JPanel numeros = new JPanel();
		numeros.setLayout(new GridLayout(4, 3, 5, 5));
		numeros.setPreferredSize(new Dimension(200, 200));

		// ---- Ajout des boutons Ã  la grille ----
		for (int i = 0; i <= 9 ; i++) 
		{
			buttons[i].setText(Integer.toString(i));
			buttons[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
			// ____ ActionListener pour chaque bouton ____
			buttons[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					String txt = evt.getActionCommand();
					ecran.setText(ecran.getText() + txt);
				}
			});
			numeros.add(buttons[i]);
		}
		// ____ Boutons de la virgule et égal ____
		buttons[10].setText(".");
		buttons[11].setText("=");
		buttons[11].setBackground(new Color(187, 172, 172));

		numeros.add(buttons[10]);
		numeros.add(buttons[11]);

		// ---- Création de la grille des opérations ----
		JPanel op = new JPanel();
		op.setLayout(new GridLayout(4, 2, 5, 5));
		op.setPreferredSize(new Dimension(180, 800));

		// ---- Ajout des boutons à  la grille ----
		// _____ Soustraction - _____
		operations[0].setText("-");
		op.add(operations[0]);
		// _____ Division / _____
		operations[1].setText("/");
		op.add(operations[1]);
		// _____ Multiplication x _____
		operations[2].setText("x");
		op.add(operations[2]);
		// _____ Addition + _____
		operations[3].setText("+");
		op.add(operations[3]);
		// _____ Racine carrée - _____
		operations[4].setText("√");
		op.add(operations[4]);
		// _____ Puissance ^ _____
		operations[5].setText("^");
		op.add(operations[5]);
		// _____ ln  _____
		operations[6].setText("ln");
		op.add(operations[6]);
		// _____ exponentiel exp _____
		operations[7].setText("exp");
		op.add(operations[7]);
		// ---- Police des boutons ----
		for (int i = 0; i < 11; i++)
		{
			buttons[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
			buttons[i].setBackground(new Color(250, 240, 230));
		}
		for (int i = 0; i < 9; i++)
		{
			operations[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
			operations[i].setBackground(new Color(240, 255, 255));
		}

		// ---- Ajout du bouton C (Pour effacer l'ecran) ----
		operations[8].setText("C");
		operations[8].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
		operations[8].setBackground(new Color(233, 201, 177));

		// ---- ActionListener pour le bouton Clean ----
		operations[8].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					ecran.setText("");
				}
		});

		// ---- ActionListener pour le bouton "." (Virgule)----
		buttons[10].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					ecran.setText(ecran.getText() + ".");
				}
		});

		// ---- Ajout des éléments au conteneur ----
		menu.add(txt, BorderLayout.NORTH); // Zone de texte
		menu.add(operations[8], BorderLayout.SOUTH); // Grille du bouton Clean
		menu.add(op, BorderLayout.EAST); // Grille des opérations
		menu.add(numeros, BorderLayout.CENTER); // Grille des numeros
		
		// ---- ActionListener pour les boutons des operations ---- 	
		// ____ Soustraction ____
		operations[0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
					v1 = Double.parseDouble(ecran.getText());
					opt = "-";
					ecran.setText("");
				}
		});
		// ____ Division ____
		operations[1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
				v1 = Double.parseDouble(ecran.getText());
					opt = "/";
					ecran.setText("");
				}
		});
		// ____ Multiplication ____
		operations[2].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
				v1 = Double.parseDouble(ecran.getText());
					opt = "x";
					ecran.setText("");
				}
		});
		// ____ Addition ____
		operations[3].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
				v1 = Double.parseDouble(ecran.getText());
					opt = "+";
					ecran.setText("");
				}
		});
		// ____ racine carrée ____
		operations[4].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
						{
							opt = "v";
							ecran.setText("");
						}
				});
		// ____ Puissance ____
		operations[5].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
						{
						v1 = Double.parseDouble(ecran.getText());
							opt = "p";
							ecran.setText("");
						}
				});
				// ____ ln ____
		operations[6].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
						{
							opt = "l";
							ecran.setText("");
						}
				});
				// ____ exponentiel ____
		operations[7].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
						{
							opt = "e";
							ecran.setText("");
						}
				});
		// ____ Le résultat "=" ____
		buttons[11].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
				{
				v2 = Double.parseDouble(ecran.getText());
					switch (opt) 
					{
						case "-" :
							ecran.setText( v1+" - "+v2+" = "+Double.toString((v1 - v2)));
							break;
						case "+" :
							ecran.setText( v1+" + "+v2+" = "+Double.toString((v1 + v2)));
							break;
						case "/" :
							if (v2 == 0)
							{
								 ecran.setForeground(Color.red);
								ecran.setText("ERROR !");
								// ecran.setForeground(Color.black);
							}
							else
								ecran.setText(v1+" / "+v1+" = "+Double.toString((v1 / v2)));
							break;
						case "x" :
							ecran.setText(v1+" * "+v2+" = "+Double.toString((v1 * v2)));
							break;
						case "v" :
							if (v2 < 0)
							{
								 ecran.setForeground(Color.red);
								ecran.setText("ERROR !");
							}
							else
							ecran.setText(" √( "+v2+" ) = "+Double.toString((Math.sqrt(v2))));
							break;
						case "p" :
							ecran.setText(v1+" ^ "+v2+" = "+Double.toString((Math.pow(v1,v2))));
							break;
						case "l" :
							if (v2 <= 0)
							{
								 ecran.setForeground(Color.red);
								ecran.setText("ERROR !");
							}
							else
							ecran.setText(" ln( "+v2+" ) = "+Double.toString((Math.log(v2))));
							break;
						case "e" :
							ecran.setText(" exp( "+v2+" ) = "+Double.toString((Math.exp(v2))));
							break;
					}
				}
		});

		// ---- Fonction de fermeture ----
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


// ************************************ Main ************************************
	public static void main(String[] args) {
		new Calculatrice().setVisible(true);
	}
}