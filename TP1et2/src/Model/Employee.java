package Model;

public class Employee {
	private String nom,prenom,email,telephone;
	private Poste poste;
	private Role role;
	private double salaire;
	private int id,solde;
	
	public Employee(String nom,String prenom,String email,String telephone,Double salaire,Poste poste ,Role role) {
		this.nom=nom;
		this.prenom=prenom;
		this.salaire=salaire;
		this.email=email;
		this.telephone=telephone;
		this.poste=poste;
		this.role=role;
		
	}
	
	public Employee() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public enum Poste {
		INGENIEUR_ETUDE_ET_DEVELOPPEMENT,
		TEAM_LEADER,
		PILOTE
	};
	
	public enum Role{
		ADMIN,
		EMPLOYEE,
		MANAGER
	}
	
}
