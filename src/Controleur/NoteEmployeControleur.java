package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.NoteEmploye;
import DAO.NoteEmployeDAO;

public class NoteEmployeControleur {
    // Méthode pour récupérer toutes les notes des employés pour un film donné
    public List<NoteEmploye> getNotesEmployeByFilm(int filmID) {
        NoteEmployeDAO noteEmployeDAO = new NoteEmployeDAO();
        return noteEmployeDAO.getNotesEmployeByFilm(filmID);
    }

    // Méthode pour insérer une nouvelle note d'employé pour un film donné
    public void insertNoteEmploye(NoteEmploye noteEmploye) {
        NoteEmployeDAO noteEmployeDAO = new NoteEmployeDAO();
        noteEmployeDAO.insertNoteEmploye(noteEmploye);
    }

    // Méthode pour calculer la moyenne des notes des employés pour un film donné
    public double calculerMoyenneNotesEmployes(int filmID) {
        NoteEmployeDAO noteEmployeDAO = new NoteEmployeDAO();
        return noteEmployeDAO.calculerMoyenneNotesEmployes(filmID);
    }

    // Méthode pour récupérer toutes les notes des employés pour un film spécifique
    public List<NoteEmploye> getAllNotesForFilm(int filmID) throws SQLException {
        return NoteEmployeDAO.getAllNotesForFilm(filmID);
    }
}
