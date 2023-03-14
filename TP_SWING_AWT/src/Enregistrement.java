import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class Enregistrement extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbtitre1,lbtitre2,lbtitre3,lbnum_place,lbtype_place,lbnombre,lb_idproprio;
	JLabel lb_nomproprio,lb_mtype,lb_place,lb_matricule;
	JLabel lbplaces_voiture_occup,lbplaces_VP_occup,lbplaces_VA_occup;
	JTextField tfnum_place,tftype_place,tf_idproprio,tf_nomproprio,tf_matricule,tf_place;
	JComboBox combo_typlace,combo_typemt;
	JButton btenrg,btsupp,btplace_dispo,btactu,btenrg_occup,btliberer_place,btrech;
	ImageIcon icon = new ImageIcon("imageV.png");
	    public Enregistrement(){
		this.setTitle("PARKING");
		this.setIconImage(icon.getImage());
		this.setSize(900,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		pn.setBackground(new Color(180,200,240));
		
		lbtitre1=new JLabel("Formulaire d'enregistrement des places");
		lbtitre1.setBounds(50,10,400,30);
		lbtitre1.setFont(new Font("Arial",Font.BOLD,22));
		pn.add(lbtitre1);
		
		lbtitre2=new JLabel("Places disponibles");
		lbtitre2.setBounds(80,180,300,30);
		lbtitre2.setFont(new Font("Arial",Font.BOLD,22));
		pn.add(lbtitre2);
		
		lbtitre3=new JLabel("Enregistrement des occupations de places");
		lbtitre3.setBounds(50,320,500,30);
		lbtitre3.setFont(new Font("Arial",Font.BOLD,20));
		pn.add(lbtitre3);
		
		lbplaces_voiture_occup=new JLabel("NT de voitures des visiteurs garées");
		lbplaces_voiture_occup.setBounds(400,590,500,30);
		lbplaces_voiture_occup.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lbplaces_voiture_occup);
		
		lbplaces_VP_occup=new JLabel("NT de voitures du personnel   garées");
		lbplaces_VP_occup.setBounds(400,610,500,30);
		lbplaces_VP_occup.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lbplaces_VP_occup);
		
		lbplaces_VA_occup=new JLabel("NT de voitures de l'administratif garées");
		lbplaces_VA_occup.setBounds(400,630,500,30);
		lbplaces_VA_occup.setFont(new Font("Arial",Font.BOLD,15));
		pn.add(lbplaces_VA_occup);
		
		lbnombre=new JLabel("");
		lbnombre.setBounds(100,270,300,30);
		lbnombre.setFont(new Font("Arial",Font.BOLD,19));
		lbnombre.setForeground(new Color(50,150,50));
		pn.add(lbnombre);
		
		//Numéro place
		lbnum_place=new JLabel("Numéro place");
		lbnum_place.setBounds(63,50,170,25);
		lbnum_place.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbnum_place);
						
		tfnum_place=new JTextField();
		tfnum_place.setBounds(193,50,80,25);
		pn.add(tfnum_place);
		//type place
		lbtype_place=new JLabel("Type place");
		lbtype_place.setBounds(63,80,170,25);
		lbtype_place.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbtype_place);
		
		combo_typlace=new JComboBox();
		combo_typlace.addItem("");
		combo_typlace.addItem(" VISITEUR");
		combo_typlace.addItem(" PERSONNEL");
		combo_typlace.addItem(" ADMINISTRATIF");
		combo_typlace.setBounds(193,80,130,25);
		pn.add(combo_typlace);
		//identifiant propriétaire
		lb_idproprio=new JLabel("Numéro du propriétaire (TEL ou CIN) ");
		lb_idproprio.setBounds(15,360,300,25);
		lb_idproprio.setFont(new Font("Arial",Font.BOLD,14));
		pn.add(lb_idproprio);
		
		tf_idproprio=new JTextField();
		tf_idproprio.setBounds(290,360,80,25);
		pn.add(tf_idproprio);
		//nom propriétaire
		lb_nomproprio=new JLabel("Nom du propriétaire");
		lb_nomproprio.setBounds(15,390,250,25);
		lb_nomproprio.setFont(new Font("Arial",Font.BOLD,14));
		pn.add(lb_nomproprio);
		
		tf_nomproprio=new JTextField();
		tf_nomproprio.setBounds(240,390,130,25);
		pn.add(tf_nomproprio);
		//type de moyen de transport
		lb_mtype=new JLabel("Type de moyen de transport");
		lb_mtype.setBounds(15,420,250,25);
		lb_mtype.setFont(new Font("Arial",Font.BOLD,14));
		pn.add(lb_mtype);
		
		combo_typemt=new JComboBox();
		combo_typemt.addItem("");
		combo_typemt.addItem("VISITEUR");
		combo_typemt.addItem("PERSONNEL");
		combo_typemt.addItem("ADMINISTRATIF");
		combo_typemt.setBounds(240,420,130,25);
		pn.add(combo_typemt);
		//Numéro de place
		lb_place=new JLabel("Numéro de place");
		lb_place.setBounds(15,450,250,25);
		lb_place.setFont(new Font("Arial",Font.BOLD,14));
		pn.add(lb_place);
		
		tf_place=new JTextField();
		tf_place.setBounds(240,450,130,25);
		pn.add(tf_place);
		//Numéro d'immatriculation
		lb_matricule=new JLabel("Numéro d'immatriculation");
		lb_matricule.setBounds(15,480,250,25);
		lb_matricule.setFont(new Font("Arial",Font.BOLD,14));
		pn.add(lb_matricule);
		
		tf_matricule=new JTextField();
		tf_matricule.setBounds(240,480,130,25);
		pn.add(tf_matricule);
		//bouton enregistrement place
		btenrg=new JButton("ENREGISTRER");
		btenrg.setBounds(55,140,120,25);
       btenrg.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent ev){
	String numero,type;
	numero=tfnum_place.getText();
	type=combo_typlace.getSelectedItem().toString();
	
	String rq1="insert into tb_place(num_place,type_place,disponible) values('"+numero+"','"+type+"','oui')";
	try{
		st=con.laConnection().createStatement();
		if(!numero.equals("")&&!type.equals("")){
		st.executeUpdate(rq1);
		JOptionPane.showMessageDialog(null,"Insertion reussie!",null,JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null,"Completez le formulaire!",null,JOptionPane.ERROR_MESSAGE);
		}
	}
	catch(SQLException ex){
    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
    }
	dispose();
	Enregistrement eg=new Enregistrement();
	eg.setVisible(true);
}

});
		pn.add(btenrg);
		//bouton suppréssion place
		btsupp=new JButton("SUPPRIMER");
		btsupp.setBounds(200,140,120,25);
		btsupp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String numero=tfnum_place.getText();
				String sql="delete from tb_place where num_place='"+numero+"'";
				try{
					st=con.laConnection().createStatement();
					if(JOptionPane.showConfirmDialog(null,"voulez vous Supprimer? ",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){  
			          st.executeUpdate(sql);
			          JOptionPane.showMessageDialog(null,"Suppréssion éffectuée avec succès !",null,JOptionPane.INFORMATION_MESSAGE);
		            }
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
				dispose();
				Enregistrement eg=new Enregistrement();
				eg.setVisible(true);
			}
		});
		pn.add(btsupp);
		//bouton pour actualiser la fenetre
		btactu=new JButton("ACTUALISER");
		btactu.setBounds(600,5,120,25);
		btactu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				dispose();
				Enregistrement eg=new Enregistrement();
				eg.setVisible(true);
				
			}
		});
		pn.add(btactu);
		//bouton pour vérifier les places disponibles
		btplace_dispo=new JButton("VERIFIER");
		btplace_dispo.setBounds(100,230,120,25);
		btplace_dispo.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent ev){
				String type=combo_typlace.getSelectedItem().toString();
				DefaultTableModel df=new  DefaultTableModel();
				  init();
				  pn.add(scroll);
				 df.addColumn("Numéro place");
				 df.addColumn("Type place");
				 table.setModel(df);
				 String rq="select * from tb_place where type_place='"+type+"' and disponible='oui' ";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq);
					 while(rst.next()){
						 df.addRow(new Object[]{
		rst.getString("num_place"),rst.getString("type_place")
								 });
						 
					 }					 					 
				 }
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
				 //nbre de places disponibles par type de moyens de transport
		 String rq2="select count(num_place) as nombre from tb_place where type_place='"+type+"' and disponible='oui'";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq2);
					 if(rst.next()){
						 lbnombre.setText("Nombre total = "+rst.getString("nombre"));
					 }
					 
					 
				 }
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
			}
		});
		pn.add(btplace_dispo);
		//bouton pour enregistrer les occupations de places
		btenrg_occup=new JButton("ENREGISTRER UNE OCCUPATION");
		btenrg_occup.setBounds(100,530,220,25);
		btenrg_occup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String   idproprio,nomproprio,typemt,num_place,matricule;
				idproprio=tf_idproprio.getText();
				nomproprio=tf_nomproprio.getText();
				typemt=combo_typemt.getSelectedItem().toString();
				matricule=tf_matricule.getText();
				num_place=tf_place.getText();
				
				String rq="insert into tb_occupation(proprio_id,proprio_nom,mt_type,mt_matricule,id_place)"
						+ "values('"+idproprio+"','"+nomproprio+"','"+typemt+"','"+matricule+"','"+num_place+"')";
				String rq2="update tb_place set disponible='non' where num_place='"+num_place+"'";
				try{
					st=con.laConnection().createStatement();
					if(!idproprio.equals("")&&!nomproprio.equals("")&&!typemt.equals("")&&!num_place.equals("")){
					st.executeUpdate(rq);
					st.executeUpdate(rq2);
					JOptionPane.showMessageDialog(null,"Occupation enregistrée avec succès !",null,JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"Completez le formulaire!",null,JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
				dispose();
				Enregistrement eg=new Enregistrement();
				eg.setVisible(true);
				
			}
		});
		
		pn.add(btenrg_occup);
		//bouton pour chercher une occupations de place
		btrech=new JButton("CHERCHER UNE OCCUPATION");
		btrech.setBounds(100,570,220,25);
		btrech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String num_place=tf_place.getText();
				String rq="select * from tb_occupation where id_place='"+num_place+"'";
				try{
					st=con.laConnection().createStatement();
					rst=st.executeQuery(rq);
					if(rst.next()){
						tf_idproprio.setText(rst.getString("proprio_id"));
						tf_nomproprio.setText(rst.getString("proprio_nom"));
						combo_typemt.setSelectedItem(rst.getString("mt_type"));
						tf_place.setText(rst.getString("id_place"));
						tf_matricule.setText(rst.getString("mt_matricule"));	
					}
					else{
						JOptionPane.showMessageDialog(null,"Enregistrement inexistant!",null,JOptionPane.ERROR_MESSAGE);
					}
							
					
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
				
			}
		});
		pn.add(btrech);
		//bouton pour libérer les occupations de places
		btliberer_place=new JButton("LIBERER UNE PLACE");
		btliberer_place.setBounds(100,610,220,25);
		btliberer_place.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String num_place=tf_place.getText();
				String rq1="update tb_place set disponible='oui' where num_place='"+num_place+"'",
						rq2="delete from tb_occupation where id_place='"+num_place+"'";
				try{
					st=con.laConnection().createStatement();
					if(JOptionPane.showConfirmDialog(null,"voulez-vous libérer cette place? ",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){ 
						st.executeUpdate(rq1);
						st.executeUpdate(rq2);
						JOptionPane.showMessageDialog(null,"Libération de place confirmée !",null,JOptionPane.INFORMATION_MESSAGE);				
					}
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
			}
		});
		pn.add(btliberer_place);
		//affichage de la liste des places
		DefaultTableModel df=new  DefaultTableModel();
		  init();
		  pn.add(scroll);
		 df.addColumn("Numéro place");
		 df.addColumn("Type place");
		 table.setModel(df);
		 String rq="select * from tb_place ";
		 try{
			 st=con.laConnection().createStatement();
			 rst=st.executeQuery(rq);
			 while(rst.next()){
				 df.addRow(new Object[]{
rst.getString("num_place"),rst.getString("type_place")
						 });
				 
			 }
			 
				 
			 }
			 
		 catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
		    }
		//affichage de la liste des places occupées
		 DefaultTableModel df2=new  DefaultTableModel();
		  init2();
		  pn.add(scroll2);
		 df2.addColumn("Num_place");
		 df2.addColumn("Num_proprio");
		 df2.addColumn("Nom_proprio");
		 df2.addColumn("Type moyen de transport");
		 df2.addColumn("Numéro d'immatriculation");
		 table2.setModel(df2);
		 String rq2="select * from tb_occupation ";
		 try{
			 st=con.laConnection().createStatement();
			 rst=st.executeQuery(rq2);
			 while(rst.next()){
				 df2.addRow(new Object[]{
rst.getString("id_place"),rst.getString("proprio_id"),rst.getString("proprio_nom"),rst.getString("mt_type")
,rst.getString("mt_matricule")
						 });
 			 } }
			 
		 catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
		    }
		 //
		 //Nombre total de voitures de visiteur garpées
		 String req1="select count(*) as nbvisiteur from tb_occupation where mt_type='VISITEUR'";
		 try{
			 st=con.laConnection().createStatement();
			 rst=st.executeQuery(req1);
			 if(rst.next()){
				 
			lbplaces_voiture_occup.setText("Nombre total de voitures de visiteurs : "+rst.getString("nbvisiteur"));	 
			 }
			 
		 }
		 catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
		    }
		//Nombre total de voitures du personnel garpées
		 String req2="select count(*) as nbpersonnel from tb_occupation where mt_type='PERSONNEL'";
		 try{
			 st=con.laConnection().createStatement();
			 rst=st.executeQuery(req2);
			 if(rst.next()){
				 
			lbplaces_VP_occup.setText("Nombre total de voitures du personnel  : "+rst.getString("nbpersonnel"));	 
			 }
			 
		 }
		 catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
		    }
		//Nombre total de voitures de l'administratif garées
		 String req3="select count(*) as nbadmin from tb_occupation where mt_type='ADMINISTRATIF'";
		 try{
			 st=con.laConnection().createStatement();
			 rst=st.executeQuery(req3);
			 if(rst.next()){
				 
			lbplaces_VA_occup.setText("Nombre total de voitures de l'administratif : "+rst.getString("nbadmin"));	 
			 }
			 
		 }
		 catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
		    }
	}
		
		
	    private void init(){
			table=new JTable();
			scroll=new JScrollPane();
			scroll.setBounds(400,40,460,270);
			scroll.setViewportView(table);
			
		}
	    
	    private void init2(){
			table2=new JTable();
			scroll2=new JScrollPane();
			scroll2.setBounds(400,370,460,220);
			scroll2.setViewportView(table2);
			
		}
	    
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Enregistrement enrg=new Enregistrement();
		enrg.setVisible(true);
		
	}

		}