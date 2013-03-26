package models;

public class Video implements IDbModel{
	public String ISBN;
	public String Title;
	public String Subject;
	public String Rating;
	public String Price;
	public String Format;
	public String YearOfProduction;
	public String ActorName;
	public String Director;
	
	public String toString(){
		return	"ISBN: "+ISBN+"\n"+
				"Title: "+Title+"\n"+
				"Subject: "+Subject+"\n"+
				"Rating: "+Rating+"\n"+
				"Price: "+Price+"\n"+
				"Format: "+Format +"\n"+
				"Year of Poduction: "+ YearOfProduction+"\n\n";
	}
}
