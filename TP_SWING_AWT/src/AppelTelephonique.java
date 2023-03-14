//KPATOUKPA Kpodjro

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;

import java.awt.EventQueue;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppelTelephonique {

	private JFrame frame;
	private JTable tableDesAppels = new JTable();
	int i = 0,j=1,k=1;
	double sm = 0;
	// contructeur
	public AppelTelephonique() {
		initierCommunication();
	}

	private void initierCommunication() {
		//création du menu général
		frame = new JFrame();
		frame.setTitle("Communication Téléphonique");
		frame.setBounds(100, 100, 827, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//La partie d'appel : Pu, Durée,Région,Type de jour
		JPanel appel = new JPanel();
		appel.setBounds(0, 0, 400, 396);
		appel.setBackground( Color.green );
		appel.setPreferredSize(new Dimension(400, 190));
		frame.getContentPane().add(appel);
		appel.setLayout(null);
		// Le prix Unitaire défini pour l'appel
		JLabel pu = new JLabel("PU :");
		pu.setFont(new Font("Tahoma", Font.BOLD, 16));
		pu.setBounds(24, 10, 65, 30);
		pu.setForeground( Color.red );
		appel.add(pu);
		// Liste des PU possibles
		Object[] dataObject = {"1.25", "1.30", "2.0"};
		JComboBox comboBox_pu = new JComboBox(dataObject);
		comboBox_pu.setBackground(new Color(253, 245, 230));
		comboBox_pu.setFont(new Font("Serif", Font.BOLD, 15));
		comboBox_pu.setBounds(117, 10, 89, 30);
		appel.add(comboBox_pu);
		// Etiquette de la durée
		JLabel d = new JLabel("Durée :");
		d.setFont(new Font("Tahoma", Font.BOLD, 16));
		d.setBounds(24, 69, 65, 21);
		d.setForeground( Color.red );
		appel.add(d);
		// Initialisation de partie de l'heure
		JTextField h = new JTextField("0");
		h.setEditable(false);
		h.setHorizontalAlignment(SwingConstants.CENTER);
		h.setFont(new Font("Tahoma", Font.BOLD, 16));
		h.setBackground( Color.red);
		h.setBounds(115, 68, 30, 30);
		appel.add(h);
		// Initialisation de partie de la minute
		JTextField m = new JTextField("0");
		m.setEditable(false);
		m.setHorizontalAlignment(SwingConstants.CENTER);
		m.setBackground( Color.yellow );
		m.setFont(new Font("Tahoma", Font.BOLD, 16));
		m.setBounds(148, 68, 30, 30);
		appel.add(m);
		// Initialisation de partie de seconde
		JTextField s = new JTextField("0");
		s.setEditable(false);
		s.setHorizontalAlignment(SwingConstants.CENTER);
		s.setBackground( Color.blue);
		s.setFont(new Font("Tahoma", Font.BOLD, 16));
		s.setBounds(181, 68, 30, 30);
		appel.add(s);
		
		// définition du bouton de fin d'appel
		JButton decro = new JButton("");
		decro.setBounds(334, 112, 30, 30);
		appel.add(decro);
		ImageIcon icon1 = new ImageIcon("decro.jpg");
		Image img1 = icon1.getImage();  
		Image newimg1 = img1.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH);  
		icon1 = new ImageIcon( newimg1 );
		decro.setIcon(icon1);
		//définition du bouton de lancement de l'appel
		JButton accro = new JButton("");
		accro.setBounds(334, 69, 30, 30);
		appel.add(accro);
		ImageIcon icon2 = new ImageIcon("accro.png");
		Image img2 = icon2.getImage();  
		Image newimg2 = img2.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH);
		icon2 = new ImageIcon( newimg2 );
		accro.setIcon(icon2);
	

		//---------------------- Les radios boutons de Zone ----------------------
		JRadioButton urbain = new JRadioButton("Urbain");
		urbain.setSelected(true);
		urbain.setBackground(new Color(255, 240, 245));
		urbain.setFont(new Font("Tahoma", Font.BOLD, 13));
		JRadioButton interurbain = new JRadioButton("InterUrbain");
		interurbain.setBackground(new Color(255, 240, 245));
		interurbain.setFont(new Font("Tahoma", Font.BOLD, 13));
		JRadioButton international = new JRadioButton("International");
		international.setBackground(new Color(255, 240, 245));
		international.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		ButtonGroup zone = new ButtonGroup();
		JPanel bp1 = new JPanel(new GridLayout(3, 1));
		bp1.setBackground(new Color(255, 240, 245));
		bp1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Zone", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bp1.setBounds(78, 190, 128, 116);
		zone.add(urbain);
		zone.add(interurbain);
		zone.add(international);
		bp1.add(urbain);
		bp1.add(interurbain);
		bp1.add(international);
		appel.add(bp1);
		
		//---------------------- Les radios boutons de Red ----------------------
		JRadioButton nonferie = new JRadioButton("Non Férié");
		nonferie.setSelected(true);
		nonferie.setBackground( Color.white );
		nonferie.setFont(new Font("Tahoma", Font.BOLD, 13));
		JRadioButton ferie = new JRadioButton("Férié");
		ferie.setBackground( Color.white );
		ferie.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		ButtonGroup red = new ButtonGroup();
		JPanel bp2 = new JPanel(new GridLayout(2, 1));
		bp2.setBackground( Color.white );
		bp2.setBorder(new TitledBorder(null, "Red", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		bp2.setBounds(216, 190, 109, 114);
		red.add(nonferie);
		red.add(ferie);
		bp2.add(nonferie);
		bp2.add(ferie);
		appel.add(bp2);
		
		JLabel cout = new JLabel("Coût :");
		cout.setFont(new Font("Tahoma", Font.BOLD, 16));
		cout.setBounds(24, 328, 65, 30);
		cout.setForeground( Color.red );
		appel.add(cout);
		
		JTextField coutvalue = new JTextField("0.0");
		coutvalue.setEditable(false);
		coutvalue.setHorizontalAlignment(SwingConstants.CENTER);
		coutvalue.setBackground(new Color(245, 255, 250));
		coutvalue.setFont(new Font("Tahoma", Font.BOLD, 16));
		coutvalue.setBounds(120, 328, 234, 30);
		appel.add(coutvalue);
		
		JPanel liste = new JPanel();
		liste.setBounds(405, 0, 405, 396);
		liste.setBackground(Color.BLUE );
		liste.setPreferredSize(new Dimension(200, 100));
		frame.getContentPane().add(liste);
		liste.setLayout(null);
		
		JLabel listeaff = new JLabel("Liste");
		listeaff.setFont(new Font("Tahoma", Font.BOLD, 17));
		listeaff.setBounds(5, 10, 103, 38);
		listeaff.setHorizontalAlignment(SwingConstants.LEFT);
		liste.add(listeaff);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 375, 188);
		liste.add(scrollPane);
		
		JTextField somme = new JTextField("0.0");
		somme.setEditable(false);
		somme.setHorizontalAlignment(SwingConstants.CENTER);
		somme.setBackground(new Color(255, 250, 250));
		somme.setFont(new Font("Tahoma", Font.BOLD, 16));
		somme.setBounds(119, 303, 266, 31);
		liste.add(somme);
	
		String[] NomsColonnes = {"Date", "Coût"};
		tableDesAppels = new JTable(new DefaultTableModel(new Object[]{"Date", "Coût"}, 0));
		tableDesAppels.setBackground( Color.white);
		tableDesAppels.setFont(new Font("Tahoma", Font.BOLD, 14));
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Date");
		model.addColumn("Coût");
		tableDesAppels.setModel(model);
		scrollPane.setViewportView(tableDesAppels);
	
		JLabel sommelabel = new JLabel("Coût Total :");
		sommelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		sommelabel.setBounds(10, 303, 103, 31);
		liste.add(sommelabel);
		
		JButton quitter = new JButton("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitter.setBackground( Color.red);
		quitter.setFont(new Font("Tahoma", Font.BOLD, 16));
		quitter.setBounds(282, 344, 103, 31);
		liste.add(quitter);

		// ---------------- Timer ---------------		
		int delay = 50; //milliseconds
		ActionListener taskPerformer = new ActionListener() {
			
		      public void actionPerformed(ActionEvent evt) {
		    	  s.setText(Integer.toString(i));
		    	  i++;
		    	  if(i == 60)
		    	  {
		    		  m.setText(Integer.toString(j));
		    		  s.setText(Integer.toString(i = 0));
		    		  i++;     j++;
		    		  if(j == 60)
		    		  {
		    			  h.setText(Integer.toString(k));
		    			  m.setText(Integer.toString(j = 0));
		    			  j++; k++;
		    		  }
		    	  }}
		  };
		Timer timer = new Timer(delay, taskPerformer);				

	//--------Pour séléctionner automatiquement un RadionButton si on choisit un PU-----------
		comboBox_pu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				String value = event.getItem().toString();
				if(value.equals("1.25")) {
					urbain.setSelected(true);
				}
				if(value.equals("1.30")) {
					interurbain.setSelected(true);
				}
				if(value.equals("2.0")) {
					international.setSelected(true);
				}	
			}
		});
		
		//--------Pour changer automatiquement la valeur du pu si on Séléctionne un radioButton -----------
		//__________ Urbain _________
        urbain.addItemListener(new ItemListener() {        
		    
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            //----- changer la valeur du comboBox-----
		        	comboBox_pu.setSelectedItem("1.25");
		        }
		    }
		});
      //__________ Interurbain _________
        interurbain.addItemListener(new ItemListener() {        
		    
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            //----- changer la valeur du comboBox-----
		        	comboBox_pu.setSelectedItem("1.30");
		        }
		    }
		});
      //__________ International _________
        international.addItemListener(new ItemListener() {        
		    
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            //----- changer la valeur du comboBox-----
		        	comboBox_pu.setSelectedItem("2.0");
		        }
		    }
		});

		// -------- ActionListener de Accro (Start) ---------
		accro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				h.setText("0");
				m.setText("0");
				s.setText("0");
				timer.start();
				i = 0; j = k = 1;
			}
		});
		
		// ----- ActionListener de decro (Stop) -----------
		decro.addActionListener(new ActionListener() {
			//String pu = comboBox_pu.getSelectedItem().toString();
			double h1 = 0;
			double m1 = 0;
			double s1 = 0;
			
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				//_____ Récuperation de la durée _____
				if(h.getText().equals(""))
					h1 = 0;
				else
					h1 = Double.parseDouble(h.getText());
				if(m.getText().equals(""))
					m1 = 0;
				else
					m1 = Double.parseDouble(m.getText());
				if(s.getText().equals(""))
					s1 = 0;
				else
					s1 = Double.parseDouble(s.getText());
				
				double dur = h1*60 + m1 + s1/60;
				//_______________ Calcul du coût _______________
				double pu = Double.valueOf(comboBox_pu.getSelectedItem().toString());
				double cout = pu*dur;
				
				//-----Si le jour est non férié alors pas de réduction---------
				if(nonferie.isSelected())
				{					
						coutvalue.setText(String.valueOf(cout));					
				}
				//-----Si le jour est férié alors on a une réduction de 50% ---------
				else
				{
					//----- Réduction de 50% du cout----------				 
					coutvalue.setText(String.valueOf(cout*0.5));
					cout*=0.5;
				}
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				  LocalDateTime now = LocalDateTime.now(); 
				  Object[]dataTable = { dtf.format(now) , cout };
				  model.insertRow(0, dataTable);
				       sm+=cout;
						somme.setText(Double.toString(sm));
			}
		});
}  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppelTelephonique window = new AppelTelephonique();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}