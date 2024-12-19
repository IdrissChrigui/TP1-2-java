package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Employee;
import Model.Holiday;

public class HolidayDAOimpl implements GenericDAOI<Holiday> {
    private Connection conn;

    public HolidayDAOimpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void ajouter(Holiday holiday) {
        String query = "INSERT INTO Holiday(date_debut, date_fin, type, nom) values(?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, java.sql.Date.valueOf(holiday.getDateDebut()));
            stmt.setDate(2, java.sql.Date.valueOf(holiday.getDateFin()));
            stmt.setString(3, holiday.getType().name());
            stmt.setString(4, holiday.getNom());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(int id, Holiday holiday) {
        String query = "UPDATE Holiday SET date_debut=?, date_fin=?, type=?, nom=? WHERE idHoliday=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, java.sql.Date.valueOf(holiday.getDateDebut()));
            stmt.setDate(2, java.sql.Date.valueOf(holiday.getDateFin()));
            stmt.setString(3, holiday.getType().name());
            stmt.setString(4, holiday.getNom());
            stmt.setInt(5, id); 
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String query = "DELETE FROM Holiday WHERE idHoliday=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Holiday> afficher() {
        List<Holiday> holidays = new ArrayList<>();
        String query = "SELECT idHoliday, date_debut, date_fin, type, nom FROM Holiday";

        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Holiday holi = new Holiday();
                holi.setId(rs.getInt("idHoliday"));  // Correction ici, utilisez "idHoliday"
                holi.setNom(rs.getString("nom"));
                holi.setDateDebut(rs.getString("date_debut"));
                holi.setDateFin(rs.getString("date_fin"));
                try {
                    holi.setType(Holiday.Type.valueOf(rs.getString("type")));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid holiday type found: " + rs.getString("type"));
                }
                holidays.add(holi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holidays;
    }

    public List<Employee> getEmployesName() {
        List<Employee> employes = new ArrayList<>();
        String query = "SELECT nom FROM Employe";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employe = new Employee();
                employe.setNom(rs.getString("nom"));
                employes.add(employe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employes;
    }
    
    public int getSolde(String nom) {
        String query = "SELECT solde FROM Employe WHERE nom = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("solde");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retourne -1 si une erreur survient ou si l'employé n'existe pas
    }

    public void updateSolde(String nom, int nouveauSolde) {
        String query = "UPDATE Employe SET solde = ? WHERE nom = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nouveauSolde);
            stmt.setString(2, nom);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
