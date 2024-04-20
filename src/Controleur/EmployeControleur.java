package Controleur;

import java.util.List;
import Modele.Employe;
import DAO.EmployeDAO;

public class EmployeControleur {
    // Méthode pour récupérer tous les employés
    public List<Employe> getAllEmployes() {
        EmployeDAO employeDAO = new EmployeDAO();
        return employeDAO.getAllEmployes();
    }

    // Méthode pour ajouter un nouvel employé
    public void ajouterEmploye(Employe employe) {
        EmployeDAO employeDAO = new EmployeDAO();
        employeDAO.insertEmploye(employe);
    }

    // Méthode pour mettre à jour les informations d'un employé
    public void modifierEmploye(Employe employe) {
        EmployeDAO employeDAO = new EmployeDAO();
        employeDAO.updateEmploye(employe);
    }

    // Méthode pour supprimer un employé
    public void supprimerEmploye(int employeID) {
        EmployeDAO employeDAO = new EmployeDAO();
        employeDAO.deleteEmploye(employeID);
    }
}
