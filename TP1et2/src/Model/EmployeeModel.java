package Model;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import DAO.EmployeeDAOimpl;
public class EmployeeModel {
	private EmployeeDAOimpl dao;
	
	public EmployeeModel(EmployeeDAOimpl dao) {
		this.dao=dao;
	}
	
	public boolean ajouter(String nom,String prenom,String email,String telephone,Double salaire,Employee.Poste poste,Employee.Role role) {
	    // Regex pour vérifier le format de l'email
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    if (!pattern.matcher(email).matches()) {
	        System.out.println("Email invalide : " + email);
	        return false;
	    }

		Employee nv= new Employee(nom,prenom,email,telephone,salaire,poste,role);
		dao.ajouter(nv);
		return true;
	}
	
	
	public boolean modifier(int id,String nom,String prenom,String email,String telephone,Double salaire,Employee.Poste poste,Employee.Role role) {
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"; //elle acceptera des emails comme ayaeljahidi@example.com ou ayaeljahidi.name+alias@domain.co.
	    Pattern pattern = Pattern.compile(emailRegex);
	    if (!pattern.matcher(email).matches()) {
            JOptionPane.showMessageDialog(null, "Email invalide", "Erreur", JOptionPane.ERROR_MESSAGE);

	        System.out.println("Email invalide : " + email);
	        return false;
	    }
	
		Employee nv= new Employee(nom,prenom,email,telephone,salaire,poste,role);
		dao.modifier(id,nv);
		return true;
	}
	
	public List<Employee> afficher() {
		 return dao.afficher();
	}
	
	public boolean supprimer(int id) {
		if(id<0) {
			return false;
		}
		dao.supprimer(id);
		return true;
	}
	

}
