package DAO;
import java.util.*;

public interface GenericDAOI<T>{
	 void ajouter(T elm);
	void modifier(int id,T elm);
	void supprimer(int id);
	List<T>  afficher();

}
