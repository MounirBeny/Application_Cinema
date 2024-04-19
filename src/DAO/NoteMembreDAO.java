package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modele.NoteMembre;
import Main.DatabaseConnection;

public class NoteMembreDAO {
    // Méthode pour insérer une nouvelle note de membre dans la base de données
    public static void insertNoteMembre(NoteMembre noteMembre) throws SQLException {
        String query = "INSERT INTO NotesMembres (FilmID, MembreID, Note) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, noteMembre.getFilmID());
            statement.setInt(2, noteMembre.getMembreID());
            statement.setDouble(3, noteMembre.getNote());
            statement.executeUpdate();
            System.out.println("Nouvelle note de membre insérée avec succès !");
        }
    }

    // Méthode pour récupérer toutes les notes de membres de la base de données
    public static List<NoteMembre> getAllNotesMembres() throws SQLException {
        List<NoteMembre> notesMembres = new ArrayList<>();
        String query = "SELECT * FROM NotesMembres";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                NoteMembre noteMembre = new NoteMembre();
                noteMembre.setNoteID(resultSet.getInt("NoteID"));
                noteMembre.setFilmID(resultSet.getInt("FilmID"));
                noteMembre.setMembreID(resultSet.getInt("MembreID"));
                noteMembre.setNote(resultSet.getDouble("Note"));
                notesMembres.add(noteMembre);
            }
        }
        return notesMembres;
    }

    // Méthode pour supprimer une note de membre de la base de données
    public static void deleteNoteMembre(int noteID) throws SQLException {
        String query = "DELETE FROM NotesMembres WHERE NoteID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, noteID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Note de membre supprimée avec succès !");
            } else {
                System.out.println("Aucune note de membre trouvée avec cet ID.");
            }
        }
    }

    // Méthode pour calculer la moyenne des notes des membres pour un film donné
    public static double calculerMoyenneNotesMembres(int filmID) throws SQLException {
        String query = "SELECT AVG(Note) AS Moyenne FROM NotesMembres WHERE FilmID = ?";
        double moyenne = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, filmID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    moyenne = resultSet.getDouble("Moyenne");
                }
            }
        }
        return moyenne;


    }

    // Méthode pour récupérer toutes les notes de membres pour un film spécifique
    public static List<NoteMembre> getAllNotesForFilm(int filmID) throws SQLException {
        List<NoteMembre> notes = new ArrayList<>();
        String query = "SELECT * FROM NotesMembres WHERE FilmID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, filmID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NoteMembre noteMembre = new NoteMembre();
                    noteMembre.setNoteID(resultSet.getInt("NoteID"));
                    noteMembre.setFilmID(resultSet.getInt("FilmID"));
                    noteMembre.setMembreID(resultSet.getInt("MembreID"));
                    noteMembre.setNote(resultSet.getDouble("Note"));
                    notes.add(noteMembre);
                }
            }
        }
        return notes;
    }

}
