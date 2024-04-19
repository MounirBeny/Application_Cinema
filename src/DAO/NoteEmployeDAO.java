package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modele.NoteEmploye;
import Main.DatabaseConnection;

public class NoteEmployeDAO {
    // Méthode pour récupérer toutes les notes des employés pour un film donné
    public List<NoteEmploye> getNotesEmployeByFilm(int filmID) {
        List<NoteEmploye> notesEmploye = new ArrayList<>();
        String query = "SELECT * FROM NotesEmployes WHERE filmID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, filmID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int noteID = resultSet.getInt("noteID");
                int employeID = resultSet.getInt("employeID");
                double note = resultSet.getDouble("note");

                // Créer un objet NoteEmploye avec les données récupérées
                NoteEmploye noteEmploye = new NoteEmploye(noteID, filmID, employeID, note);
                notesEmploye.add(noteEmploye);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notesEmploye;
    }

    // Méthode pour insérer une nouvelle note d'employé pour un film donné
    public void insertNoteEmploye(NoteEmploye noteEmploye) {
        String query = "INSERT INTO NotesEmployes (filmID, employeID, note) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, noteEmploye.getFilmID());
            preparedStatement.setInt(2, noteEmploye.getEmployeID());
            preparedStatement.setDouble(3, noteEmploye.getNote());

            preparedStatement.executeUpdate();
            System.out.println("Note d'employé insérée avec succès dans la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour calculer la moyenne des notes des employés pour un film donné
    public double calculerMoyenneNotesEmployes(int filmID) {
        List<NoteEmploye> notesEmploye = getNotesEmployeByFilm(filmID);
        double sommeNotes = 0;

        for (NoteEmploye note : notesEmploye) {
            sommeNotes += note.getNote();
        }

        if (notesEmploye.size() > 0) {
            return sommeNotes / notesEmploye.size();
        } else {
            return 0;
        }
    }

    // Méthode pour récupérer toutes les notes des employés pour un film spécifique
    public static List<NoteEmploye> getAllNotesForFilm(int filmID) throws SQLException {
        List<NoteEmploye> notes = new ArrayList<>();
        String query = "SELECT * FROM NotesEmployes WHERE FilmID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, filmID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NoteEmploye noteEmploye = new NoteEmploye();
                    noteEmploye.setNoteID(resultSet.getInt("NoteID"));
                    noteEmploye.setFilmID(resultSet.getInt("FilmID"));
                    noteEmploye.setEmployeID(resultSet.getInt("EmployeID"));
                    noteEmploye.setNote(resultSet.getDouble("Note"));
                    notes.add(noteEmploye);
                }
            }
        }
        return notes;
    }
}
