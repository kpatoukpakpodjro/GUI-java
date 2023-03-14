import java.util.HashSet;

public class Parking {
    int PlacesOccupees; 
    int Capacite ; 
    public HashSet<Voiture> infoVoitures = new HashSet<Voiture>();
    Parking(int size){ this.Capacite = size;} 
    int places(){ return (this.Capacite - this.PlacesOccupees); }  
	
    synchronized boolean  accept(Voiture Voit) {
	if  (this.places() >0 )
	    { 
		this.PlacesOccupees ++ ;
		infoVoitures.add(Voit); 
		System.out.format("[Parking] :%s acceptée, il reste %d places \n", Voit.getNom(), this.places());
		System.out.format("Voiture Garees\n");
		System.out.println(infoVoitures);
		return (true) ; 
	    }
	else {
	    System.out.format("Parking : %s refusée, il reste  %d places \n", Voit.nom,this.places());
	    return(false) ;
	}
    }
    synchronized void leave(Voiture Voit) {
	PlacesOccupees --; 
	infoVoitures.remove(Voit);
	System.out.format("Parking :[%s] est sortie, reste  %d places\n", Voit.nom, places());
    }}
