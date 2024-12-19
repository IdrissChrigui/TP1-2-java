package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Model.Employee;
import Model.Holiday;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
public class HolidayView extends JFrame{
    private JComboBox<String> type,dateDebut,dateFin,nom;
    public JButton ajouter, supprimer,modifier,Employes,Conges;
    public JTable JT;
    public DefaultTableModel tableModel;
    private JPanel p0,p1,p2,p3,p4,p5;   
    private String[] s;
    public HolidayView() {
    	s= java.util.Arrays.stream(Holiday.Type.values()).map(Enum::name).toArray(String[]::new);

        setTitle("Gestion des Employés et Congés");
        setSize(600, 400);
        setLayout(new BorderLayout());
        
        p1 = new JPanel(new FlowLayout());
        Employes = new JButton("Employes");
        Conges = new JButton("Conges");
        p1.add(Employes);
        p1.add(Conges);
        
        p2 = new JPanel(new GridLayout(5, 2));
        p2.add(new JLabel("Nom de l'employe:"));
        nom = new JComboBox<>();
        p2.add(nom);
        p2.add(new JLabel("Type:"));
        type = new JComboBox<>(s);
        p2.add(type);
        p2.add(new JLabel("Date de début:"));
        dateDebut= new JComboBox<>();
        p2.add(dateDebut);
        p2.add(new JLabel("Date de fin:"));
        dateFin = new JComboBox<>();
        p2.add(dateFin);
        p2.add(new JLabel("Liste des congés"));
        
        p3 = new JPanel(new BorderLayout());
		p3.add(p1,BorderLayout.NORTH);
        p3.add(p2,BorderLayout.CENTER);
        
        
        p5=new JPanel(new FlowLayout());
        ajouter= new JButton("Ajouter");
        supprimer = new JButton("Suprimer");
        modifier= new JButton("Modifier");
        p5.add(ajouter);
        p5.add(supprimer);
        p5.add(modifier);
        
        
        p4=new JPanel(new BorderLayout());
        // Table Holiday
        tableModel = new DefaultTableModel(new String[]{"iD", "Employé", "Date de début", "Date de fin", "Type"}, 0) {
        	   @Override
               public boolean isCellEditable(int row, int column) {
                   return false; // Empêche l'édition des cellules
               }
        };
        JT = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(JT);
        p4.add(scrollPane, BorderLayout.CENTER);
        p4.add(p5,BorderLayout.SOUTH);
        
        
        p0=new JPanel(new BorderLayout());
        p0.add(p3,BorderLayout.NORTH);
        p0.add(p4,BorderLayout.CENTER);
        this.add(p0);
        
        
        

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    // Getters pour les valeurs des JComboBox
    public String getNom() {
        return (String) nom.getSelectedItem();
    }
    
    public String getSelectedType() {
        return (String) type.getSelectedItem();
    }
    
    public String getDateDebut() {
        return (String) dateDebut.getSelectedItem();
    }
    
    public String getDateFin() {
        return (String) dateFin.getSelectedItem();
    }
    
    
    public int getSolde() {
        if (getDateDebut() != null && getDateFin() != null) {
            // Conversion des chaînes de caractères en LocalDate
            LocalDate debut = LocalDate.parse(getDateDebut());
            LocalDate fin = LocalDate.parse(getDateFin());
            return (int) java.time.temporal.ChronoUnit.DAYS.between(debut, fin);
        } else {
            throw new IllegalArgumentException("Les dates de début et de fin ne doivent pas être nulles.");
        }
    }
    
    public void clearFields() {
        type.setSelectedIndex(-1); // Réinitialise la sélection de la ComboBox
        nom.setSelectedIndex(-1);
        dateDebut.setSelectedIndex(-1);
        dateFin.setSelectedIndex(-1);


    }

    public static void remplirComboBox(JComboBox<String> comboBox, List<String> items) {
        if (comboBox == null) {
            throw new IllegalArgumentException("Le JComboBox fourni est null.");
        }
        if (items == null || items.isEmpty()) {
            System.out.println("Aucune donnée à ajouter dans le JComboBox.");
            return;
        }

        comboBox.removeAllItems();
        for (String item : items) {
            comboBox.addItem(item);
        }
    }
    
    public static void remplirCombo(JComboBox<String> comboBox, List<Employee> items) {
        comboBox.removeAllItems();
        for (Employee employee : items) {
            comboBox.addItem(employee.getNom());
        }
    }

	public void afficherMessageError(String message) {
		JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.ERROR_MESSAGE);
	}
	public void afficherMessageSuccess(String message) {
		JOptionPane.showMessageDialog(this, message,"Succes",JOptionPane.INFORMATION_MESSAGE);
	}
	
  
	  
	    public JComboBox<String> getTypeComboBox() {
	        return type;
	    }

	    public JComboBox<String> getNomComboBox() {
	        return nom;
	    }
	    
	    public JComboBox<String> DateDebut() {
	        return dateDebut;
	    }

	    public JComboBox<String> DateFin() {
	        return dateFin;
	    }
	    
	    public void updateTable(Object[][] data) {
	        tableModel.setRowCount(0); 
	        for (Object[] row : data) {
	            tableModel.addRow(row);
	        }
	    }
	    
	    

}
