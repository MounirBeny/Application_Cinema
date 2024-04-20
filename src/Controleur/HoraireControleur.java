package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.Horaire;
import DAO.HoraireDAO;

public class HoraireControleur {
    // Méthode pour récupérer tous les horaires
    public List<Horaire> getAllHoraires() throws SQLException {
        HoraireDAO horaireDAO = new HoraireDAO();
        return horaireDAO.getAllHoraires();
    }

    // Méthode pour ajouter un nouvel horaire
    public void ajouterHoraire(Horaire horaire) throws SQLException {
        HoraireDAO horaireDAO = new HoraireDAO();
        horaireDAO.insertHoraire(horaire);
    }

    // Méthode pour mettre à jour les informations d'un horaire
    public void modifierHoraire(Horaire horaire) throws SQLException {
        HoraireDAO horaireDAO = new HoraireDAO();
        horaireDAO.updateHoraire(horaire);
    }

    // Méthode pour supprimer un horaire
    public void supprimerHoraire(int horaireID) throws SQLException {
        HoraireDAO horaireDAO = new HoraireDAO();
        horaireDAO.deleteHoraire(horaireID);
    }
}
