package Controller;
import View.HolidayView;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Holiday;
import Model.HolidayModel;

public class HolidayController {
	private HolidayView view;
	private HolidayModel model;
	
	public HolidayController(HolidayView view ,HolidayModel model) {
		this.view=view;
		this.model=model;
        initComboBoxData();
        initDateComboBoxData();  // Charger les dates dans les ComboBox de dateDebut et dateFin
        
        afficher();
		this.view.ajouter.addActionListener(e->ajouter());
		this.view.supprimer.addActionListener(e->drop());
		this.view.modifier.addActionListener(e->modifier());
	}
	

    private void initDateComboBoxData() {
        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin = LocalDate.of(2025,2,21); 
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        List<String> dates = new ArrayList<>();
        LocalDate currentDate = dateDebut;
        while (!currentDate.isAfter(dateFin)) {
            dates.add(currentDate.format(formatter)); 
            currentDate = currentDate.plusDays(1); 
        }
        
        HolidayView.remplirComboBox(view.DateDebut(), dates);
        HolidayView.remplirComboBox(view.DateFin(), dates);
    }
    
	
	private void initComboBoxData() {
        HolidayView.remplirCombo(view.getNomComboBox(), model.getEmployesName());
    }
	
	public void ajouter() {
		try {
		String nom= view.getNom();
		String dateDebut = view.getDateDebut();
		String dateFin= view.getDateFin();
		String type = view.getSelectedType();
        int solde = view.getSolde();
		if(nom.isEmpty() || dateDebut.isEmpty() || dateFin.isEmpty() || type == null) {
			view.afficherMessageError("Tous les champs doivent être remplis.");
			return;
		}
		
		  if (solde > model.getSolde(nom)) {
              view.afficherMessageError("Solde insuffisant, votre solde est : " + model.getSolde(nom));
              return;
          }
		
		boolean succes= model.ajouter(dateDebut,dateFin,Holiday.Type.valueOf(type),nom);
		if(succes) {
			view.afficherMessageSuccess("Holiday ajouter");
			view.clearFields();
			 List<Holiday> updatedHolidays = model.afficher();
			 model.updateSolde(nom,model.getSolde(nom)-solde);
	            Object[][] data = new Object[updatedHolidays.size()][5]; // 5 colonnes pour id, date_debut, date_fin, type, nom

	            for (int i = 0; i < updatedHolidays.size(); i++) {
	                Holiday holiday = updatedHolidays.get(i);
	                data[i][0] = holiday.getId();
	                data[i][1] = holiday.getDateDebut();
	                data[i][2] = holiday.getDateFin();
	                data[i][3] = holiday.getType();
	                data[i][4] = holiday.getNom();
	            }
	            view.updateTable(data);

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
			List<Holiday>holidays= model.afficher();
			for(Holiday e:holidays) {
				Object[]row={
					e.getId(),
					e.getNom(),
					e.getDateDebut(),
					e.getDateFin(),
					e.getType()
					
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
	                 String nom = (String) view.tableModel.getValueAt(row, 1);
	                 String dateDebut = (String) view.tableModel.getValueAt(row, 2);
	                 String dateFin = (String) view.tableModel.getValueAt(row, 3);
	                 LocalDate debut = LocalDate.parse(dateDebut);
	                 LocalDate fin = LocalDate.parse(dateFin);
	                 int solde= (int) java.time.temporal.ChronoUnit.DAYS.between(debut, fin);
	                 int reponse = JOptionPane.showConfirmDialog(null, 
	                         "Êtes-vous sûr de vouloir supprimer ce congé ?", 
	                         "Confirmation", 
	                         JOptionPane.YES_NO_OPTION);
	                 if(reponse  == JOptionPane.YES_OPTION) {
	                     boolean success = model.supprimer(Id);
	                     model.updateSolde(nom,model.getSolde(nom)+solde);
	                     if(success) {
	                    	    view.tableModel.removeRow(row);
	                            view.afficherMessageSuccess("congé supprimer avec succès.");
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
	        	 String nom= view.getNom();
	     		String dateDebut = view.getDateDebut();
	     		String dateFin= view.getDateFin();
	     		String type = view.getSelectedType();
	     		int solde = view.getSolde();
	     		if(nom.isEmpty() || dateDebut.isEmpty() || dateFin.isEmpty() || type == null) {
	     			view.afficherMessageError("Tous les champs doivent être remplis.");
	     			return;
	     		}
	        	   int row = view.JT.getSelectedRow(); 
	               if (row != -1) { 
	                   int Id = (int) view.tableModel.getValueAt(row, 0); 
	                   String nomT = (String) view.tableModel.getValueAt(row, 1);
		                 String dateDebutT = (String) view.tableModel.getValueAt(row, 2);
		                 String dateFinT = (String) view.tableModel.getValueAt(row, 3);
		                 LocalDate debut = LocalDate.parse(dateDebutT);
		                 LocalDate fin = LocalDate.parse(dateFinT);
		                 int soldeT= (int) java.time.temporal.ChronoUnit.DAYS.between(debut, fin);
	                   int reponse = JOptionPane.showConfirmDialog(null, 
	                           "Êtes-vous sûr de vouloir modifier ce congé ?", 
	                           "Confirmation", 
	                           JOptionPane.YES_NO_OPTION);
	                   if(reponse  == JOptionPane.YES_OPTION) {
	               		boolean succes= model.modifier(Id,dateDebut,dateFin,Holiday.Type.valueOf(type),nom);
	               		model.updateSolde(nomT,model.getSolde(nomT)+soldeT);
	               		model.updateSolde(nom,model.getSolde(nom)-solde);
	   	        		view.clearFields();
	   	        	 List<Holiday> updatedHolidays = model.afficher(); // Récupérer les congés mis à jour
                     
                     // Convertir la liste en tableau d'objets pour le tableau
                     Object[][] data = new Object[updatedHolidays.size()][5]; // 5 colonnes pour id, date_debut, date_fin, type, nom

                     for (int i = 0; i < updatedHolidays.size(); i++) {
                         Holiday holiday = updatedHolidays.get(i);
                         data[i][0] = holiday.getId();
                         data[i][1] = holiday.getDateDebut();
                         data[i][2] = holiday.getDateFin();
                         data[i][3] = holiday.getType();
                         data[i][4] = holiday.getNom();
                     }

                     // Mettre à jour le tableau avec les données actualisées
                     view.updateTable(data);

	                	  if(succes) {
	                          view.afficherMessageSuccess("congé modifier avec succès.");
	                	  }
	                   }
	                   
	        	 
	               } }
		 });
		 
	 }
	 

}
