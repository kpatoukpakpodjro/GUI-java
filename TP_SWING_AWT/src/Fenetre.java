
import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame 
{
	public static JTabbedPane index;
	public static CarLot ListVoiture;
	
	public Fenetre(){
		// définition des propriétés de la fenêtre
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(3);
		this.setTitle("Parking");
		this.setResizable(false);

		Color Coul = new Color(0.2f, 0.1f, 0.8f, 0.1f);

		// Fichier qui contiendra la liste des voitures
		ListVoiture = new CarLot(15, "list.txt");

		// building tabbed panel display
		index = new JTabbedPane();
		index.setBackground(Coul);
		final JPanel statusTab = Status.startup();
		final JPanel addOrRemoveCarTab = AddOrRemoveCar.startup();

		index.addTab("tatus", statusTab);
		index.addTab("Ajouter Ou Supprimer Voiture", addOrRemoveCarTab);

		this.getContentPane().add(index);
    }

	public static void main(String[] args) 
	{
		// Instanciation
		/*
		Fenetre window = new Fenetre();
		window.setVisible(true);*/
		int N=4;
		JFrame window = new JFrame("");
		GridLayout layout = new GridLayout(N,N);
		window.setLayout(layout);

		for (int row = 0; row < N; row++){
		    for (int col = 0; col < N; col++){
		      //  JPanel panel = new JPanel();
		        JTextField b = new JTextField ("("+row+","+col+")");
		        window.add(b).setLocation(row, col);
		      //  panel.add(b);
 		       // window.add(panel);
		    }
		}

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
}
