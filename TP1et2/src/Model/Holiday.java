package Model;

public class Holiday {
    private int id;
    private String nom;
    private String dateDebut;
    private String dateFin;
    private Type type;

    public Holiday(String dateDebut, String dateFin, Type type,String nom) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.nom=nom;
    }

    public Holiday() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}


	public enum Type{
		Congé_Payé,
		Congé_Non_Payé,
		Congé_Maladie
	}

	
}
