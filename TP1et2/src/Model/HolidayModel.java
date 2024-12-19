package Model;
import java.util.List;
import DAO.HolidayDAOimpl;
import Model.Holiday.Type;
public class HolidayModel {
	private HolidayDAOimpl dao;
	
	public HolidayModel(HolidayDAOimpl dao) {
		this.dao=dao;
	}
	
	public boolean ajouter(String dateDebut, String dateFin, Type type,String nom) {
		
		try {
	        java.sql.Date startDate = java.sql.Date.valueOf(dateDebut);
	        java.sql.Date endDate = java.sql.Date.valueOf(dateFin);

	        // Vérification si la date de début est avant la date de fin
	        if (startDate.after(endDate)) {
	            System.out.println("La date de début doit être avant la date de fin.");
	            return false;
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println("Les dates doivent être au format 'yyyy-MM-dd'.");
	        return false;
	    }
		Holiday nv= new Holiday(dateDebut,dateFin,type,nom);
		dao.ajouter(nv);
		return true;
	}
	
	public boolean modifier(int id,String dateDebut, String dateFin, Type type,String nom) {
		
		try {
	        java.sql.Date startDate = java.sql.Date.valueOf(dateDebut);
	        java.sql.Date endDate = java.sql.Date.valueOf(dateFin);

	        // Vérification si la date de début est avant la date de fin
	        if (startDate.after(endDate)) {
	            System.out.println("La date de début doit être avant la date de fin.");
	            return false;
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println("Les dates doivent être au format 'yyyy-MM-dd'.");
	        return false;
	    }
		Holiday nv= new Holiday(dateDebut,dateFin,type,nom);
		dao.modifier(id,nv);
		return true;
	}
	

	
	public List<Holiday> afficher() {
		 return dao.afficher();
	}
	
	public boolean supprimer(int id) {
		if(id<0) {
			return false;
		}
		dao.supprimer(id);
		return true;
	}
	
	
	 public List<Employee> getEmployesName()
	 {
		 return dao.getEmployesName();
	 }
	   public int getSolde(String nom) {
		   return dao.getSolde(nom);
	   }
	   
	    public void updateSolde(String nom, int nouveauSolde) {
	    	dao.updateSolde(nom, nouveauSolde);
	    }


}
