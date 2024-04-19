package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Modele.Horaire;
import Main.DatabaseConnection;

public class HoraireDAO {
    // Méthode pour insérer un nouvel horaire dans la base de données
    public static void insertHoraire(Horaire horaire) throws SQLException {
        String query = "INSERT INTO Horaires (FilmID, DateHoraire, HeureHoraire) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, horaire.getFilmID());
            statement.setDate(2, new java.sql.Date(horaire.getDateHoraire().getTime()));
            statement.setTime(3, new java.sql.Time(horaire.getHeureHoraire().getTime()));
            statement.executeUpdate();
            System.out.println("Nouvel horaire inséré avec succès !");
        }
    }

    // Méthode pour récupérer tous les horaires de la base de données
    public static List<Horaire> getAllHoraires() throws SQLException {
        List<Horaire> horaires = new ArrayList<>();
        String query = "SELECT * FROM Horaires";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Horaire horaire = new Horaire();
                horaire.setHoraireID(resultSet.getInt("HoraireID"));
                horaire.setFilmID(resultSet.getInt("FilmID"));
                horaire.setDateHoraire(resultSet.getDate("DateHoraire"));
                horaire.setHeureHoraire(resultSet.getTime("HeureHoraire"));
                horaires.add(horaire);
            }
        }
        return horaires;
    }

    // Méthode pour supprimer un horaire de la base de données
    public static void deleteHoraire(int horaireID) throws SQLException {
        String query = "DELETE FROM Horaires WHERE HoraireID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, horaireID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Horaire supprimé avec succès !");
            } else {
                System.out.println("Aucun horaire trouvé avec cet ID.");
            }
        }
    }

    // Méthode pour mettre à jour les détails d'un horaire dans la base de données
    public static void updateHoraire(Horaire horaire) throws SQLException {
        String query = "UPDATE Horaires SET FilmID = ?, DateHoraire = ?, HeureHoraire = ? WHERE HoraireID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, horaire.getFilmID());
            statement.setDate(2, new java.sql.Date(horaire.getDateHoraire().getTime()));
            statement.setTime(3, new java.sql.Time(horaire.getHeureHoraire().getTime()));
            statement.setInt(4, horaire.getHoraireID());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Détails de l'horaire mis à jour avec succès !");
            } else {
                System.out.println("Aucun horaire trouvé avec cet ID.");
            }
        }
    }
}
