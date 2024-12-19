package DAO;
import Model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDAOimpl implements GenericDAOI<Employee>{
	private Connection conn;
	public EmployeeDAOimpl() {
		this.conn=DBConnection.getConnection();
	}
	
	@Override
	public void ajouter(Employee employe) {
		String query="INSERT INTO Employe(nom,prenom,email,telephone,salaire,poste,role) values(?,?,?,?,?,?,?)";
		try(PreparedStatement stmt= conn.prepareStatement(query)){
			stmt.setString(1,employe.getNom());
			stmt.setString(2, employe.getPrenom());
			stmt.setString(3, employe.getEmail());
			stmt.setString(4, employe.getTelephone());
			stmt.setDouble(5, employe.getSalaire());
			stmt.setString(6, employe.getPoste().name());
			stmt.setString(7, employe.getRole().name());
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void modifier(int id,Employee employe) {
		String query="Update Employe Set nom=?,prenom=?,email=?,telephone=?,salaire=?,poste=?,role=? where id=?";
		try(PreparedStatement stmt= conn.prepareStatement(query)){
			stmt.setString(1,employe.getNom());
			stmt.setString(2, employe.getPrenom());
			stmt.setString(3, employe.getEmail());
			stmt.setString(4, employe.getTelephone());
			stmt.setDouble(5, employe.getSalaire());
			stmt.setString(6, employe.getPoste().name());
			stmt.setString(7, employe.getRole().name());
	        stmt.setInt(8, id); 
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void supprimer(int id) {
		String query="delete from Employe where id= ?";
		try(PreparedStatement stmt= conn.prepareStatement(query)){
			stmt.setInt(1, id);
			stmt.executeUpdate();

	}catch(SQLException e) {
		e.printStackTrace();
	}
}
	
	@Override
	public List<Employee> afficher(){
		List<Employee> employees= new ArrayList<>();
		String query = "SELECT id, nom, prenom, email, telephone, salaire, poste, role, solde FROM Employe";

		try(PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs= stmt.executeQuery()){
			while(rs.next()) {
				Employee emp= new Employee();
				emp.setId(rs.getInt("id"));
				emp.setNom(rs.getString("nom"));
				emp.setPrenom(rs.getString("prenom"));
				emp.setEmail(rs.getString("email"));
				emp.setTelephone(rs.getString("telephone"));
				emp.setSalaire(rs.getDouble("salaire"));
				emp.setPoste(Employee.Poste.valueOf(rs.getString("poste")));
				emp.setRole(Employee.Role.valueOf(rs.getString("role")));
				emp.setSolde(rs.getInt("solde"));
				employees.add(emp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employees;

	}
}
