package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Model.Employee;
public class EmployeeView extends JFrame {
    private JComboBox<String> poste,role;
    private JTextField nom, prenom, email, telephone, salaire;
    public JButton ajouter, supprimer, afficher, modifier;
    public JTable JT;
    private String[] s1,s2;
    
    public DefaultTableModel tableModel;

    public EmployeeView() {
    	s1= java.util.Arrays.stream(Employee.Poste.values()).map(Enum::name).toArray(String[]::new);
    	s2= java.util.Arrays.stream(Employee.Role.values()).map(Enum::name).toArray(String[]::new);

        setTitle("Gestion des Employés");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));

        panelForm.add(new JLabel("Nom:"));
        nom = new JTextField(20);
        panelForm.add(nom);

        panelForm.add(new JLabel("Prénom:"));
        prenom = new JTextField(20);
        panelForm.add(prenom);

        panelForm.add(new JLabel("Email:"));
        email = new JTextField(20);
        panelForm.add(email);

        panelForm.add(new JLabel("Téléphone:"));
        telephone = new JTextField(20);
        panelForm.add(telephone);

        panelForm.add(new JLabel("Salaire:"));
        salaire = new JTextField(20);
        panelForm.add(salaire);

        panelForm.add(new JLabel("Poste:"));
        poste = new JComboBox<>(s1);
        panelForm.add(poste);

        panelForm.add(new JLabel("Rôle:"));
        role = new JComboBox<>(s2);
        panelForm.add(role);

        add(panelForm, BorderLayout.NORTH);

        // Boutons d'action
        JPanel panelBoutons = new JPanel(new FlowLayout());

        ajouter = new JButton("Ajouter");
        panelBoutons.add(ajouter);

        supprimer = new JButton("Supprimer");
        panelBoutons.add(supprimer);

        afficher = new JButton("Afficher");
        panelBoutons.add(afficher);

        modifier = new JButton("Modifier");
        panelBoutons.add(modifier);

        add(panelBoutons, BorderLayout.SOUTH);

        // Table des employés
        tableModel = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Email", "Téléphone", "Salaire", "Poste", "Rôle", "Solde"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Empêche l'édition des cellules
            }
        };

        JT = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(JT);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public void clearFields() {
        nom.setText("");
        prenom.setText("");
        email.setText("");
        telephone.setText("");
        salaire.setText("");
        poste.setSelectedIndex(-1); // Réinitialise la sélection de la ComboBox
        role.setSelectedIndex(-1);
    }

	public String getPoste() {
		return (String) poste.getSelectedItem();
	}

	public String getRole() {
		return (String) role.getSelectedItem();
	}
	
	public String getNom() {
		return nom.getText();
	}

	public String getPrenom() {
		return prenom.getText();
	}

	public String getEmail() {
		return email.getText();
	}

	public String getTelephone() {
		return telephone.getText();
	}

	public double getSalaire() {
		return Double.parseDouble(salaire.getText().trim());
	}
	
	public void afficherMessageError(String message) {
		JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.ERROR_MESSAGE);
	}
	public void afficherMessageSuccess(String message) {
		JOptionPane.showMessageDialog(this, message,"Succes",JOptionPane.INFORMATION_MESSAGE);
	}
	
    }
