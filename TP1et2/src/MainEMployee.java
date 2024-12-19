import View.EmployeeView;
import DAO.EmployeeDAOimpl;
import Model.EmployeeModel;
import Controller.EmployeeController;
public class MainEMployee {
	public static void main(String[] args) {
	EmployeeView view = new EmployeeView();
	EmployeeDAOimpl dao = new EmployeeDAOimpl();
	EmployeeModel model= new EmployeeModel(dao);
	new EmployeeController(view,model);

}
}