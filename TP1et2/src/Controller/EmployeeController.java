package Controller;
import View.EmployeeView;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Employee;
import Model.EmployeeModel;

public class EmployeeController {
	private EmployeeView view;
	private EmployeeModel model;
	
	public EmployeeController(EmployeeView view ,EmployeeModel model) {
		this.view=view;
		this.model=model;
		this.view.ajouter.addActionListener(e->ajouter());
		this.view.afficher.addActionListener(e->afficher());
		this.view.supprimer.addActionListener(e->drop());
		this.view.modifier.addActionListener(e->modifier());
	}
	
	public void ajouter() {
		try {
		String nom= view.getNom();
		String prenom = view.getPrenom();
		String email= view.getEmail();
		String telephone = view.getTelephone();
		Double salaire=view.getSalaire();
		String poste = view.getPoste();
		String role = view.getRole();
		if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty()|| poste == null || role ==null ) {
			view.afficherMessageError("Tous les champs doivent être remplis.");
			return;
		}
		boolean succes= model.ajouter(nom, prenom, email, telephone, salaire,Employee.Poste.valueOf(poste),Employee.Role.valueOf(role));
		if(succes) {
			view.afficherMessageSuccess("Employee ajouter");
			view.clearFields();

		}else {
			view.afficherMessageError("Erreur lors de l'ajout");
		}
		}catch(Exception ex) {
            view.afficherMessageError("Erreur : " + ex.getMessage());
		}
	}
	
	public void afficher() {
		try {
			view.tableModel.setRowCount(0);
			List<Employee>employes= model.afficher();
			for(Employee e:employes) {
				Object[]row={
					e.getId(),
					e.getNom(),
					e.getPrenom(),
					e.getEmail(),
					e.getTelephone(),
					e.getSalaire(),
					e.getPoste(),
					e.getRole(),
					e.getSolde()
					
				};
				view.tableModel.addRow(row);
				view.clearFields();

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void drop() {
		 view.JT.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseClicked(MouseEvent e) {
	             int row = view.JT.getSelectedRow();
	             if (row != -1) {
	                 int Id = (int) view.tableModel.getValueAt(row, 0); 
	                 int reponse = JOptionPane.showConfirmDialog(null, 
	                         "Êtes-vous sûr de vouloir supprimer ce personne ?", 
	                         "Confirmation", 
	                         JOptionPane.YES_NO_OPTION);
	                 if(reponse  == JOptionPane.YES_OPTION) {
	                     boolean success = model.supprimer(Id);
	                     if(success) {
	                    	    view.tableModel.removeRow(row);
	                            view.afficherMessageSuccess("personne supprimer avec succès.");
	                     }else {
	                    	 view.afficherMessageError("Erreur lors de la suppression");
	                     }
	                     
	                	 
	                 }}
	 }
	 });
	 }
	 
	
	 
	private void modifier() {
		 view.JT.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseClicked(MouseEvent e) {
	        		String nom=view.getNom();
	        		String prenom=view.getPrenom();
	        		String email= view.getEmail();
	        		String telephone=view.getRole();
	        		String poste = view.getPoste();
	        		String role= view.getRole();
	        		Double salaire= view.getSalaire();
	        		if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty()|| poste == null || role ==null ) {
	        			view.afficherMessageError("Tous les champs doivent être remplis.");
	        		}
	        	   int row = view.JT.getSelectedRow(); 
	               if (row != -1) { 
	                   int Id = (int) view.tableModel.getValueAt(row, 0); 
	                   int reponse = JOptionPane.showConfirmDialog(null, 
	                           "Êtes-vous sûr de vouloir modifier ce personne ?", 
	                           "Confirmation", 
	                           JOptionPane.YES_NO_OPTION);
	                   if(reponse  == JOptionPane.YES_OPTION) {
	                	   boolean succes= model.modifier(Id, nom,prenom,email,telephone,salaire,Employee.Poste.valueOf(poste),Employee.Role.valueOf(role));
	   	        		view.clearFields();

	                	  if(succes) {
	                          view.afficherMessageSuccess("personne modifier avec succès.");
	                	  }
	                   }
	                   
	        	 
	               } }
		 });
		 
	 }
	 

}
