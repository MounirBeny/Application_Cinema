package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.Membre;
import DAO.MembreDAO;

public class MembreControleur {
    // Méthode pour récupérer tous les membres
    public List<Membre> getAllMembres() throws SQLException {
        MembreDAO membreDAO = new MembreDAO();
        return membreDAO.getAllMembres();
    }

    // Méthode pour ajouter un nouveau membre
    public void ajouterMembre(Membre membre) throws SQLException {
        MembreDAO membreDAO = new MembreDAO();
        membreDAO.insertMembre(membre);
    }

    // Méthode pour mettre à jour les informations d'un membre
    public void modifierMembre(Membre membre) throws SQLException {
        MembreDAO membreDAO = new MembreDAO();
        membreDAO.updateMembre(membre);
    }

    // Méthode pour supprimer un membre
    public void supprimerMembre(int membreID) throws SQLException {
        MembreDAO membreDAO = new MembreDAO();
        membreDAO.deleteMembre(membreID);
    }
}
