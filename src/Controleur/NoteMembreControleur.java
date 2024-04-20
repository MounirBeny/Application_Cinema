package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.NoteMembre;
import DAO.NoteMembreDAO;

public class NoteMembreControleur {
    // Méthode pour insérer une nouvelle note de membre dans la base de données
    public void insertNoteMembre(NoteMembre noteMembre) {
        try {
            NoteMembreDAO.insertNoteMembre(noteMembre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer toutes les notes de membres de la base de données
    public List<NoteMembre> getAllNotesMembres() {
        try {
            return NoteMembreDAO.getAllNotesMembres();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour supprimer une note de membre de la base de données
    public void deleteNoteMembre(int noteID) {
        try {
            NoteMembreDAO.deleteNoteMembre(noteID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour calculer la moyenne des notes des membres pour un film donné
    public double calculerMoyenneNotesMembres(int filmID) {
        try {
            return NoteMembreDAO.calculerMoyenneNotesMembres(filmID);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    // Méthode pour récupérer toutes les notes de membres pour un film spécifique
    public List<NoteMembre> getAllNotesForFilm(int filmID) {
        try {
            return NoteMembreDAO.getAllNotesForFilm(filmID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}