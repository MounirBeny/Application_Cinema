package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modele.Membre;
import Main.DatabaseConnection;

public class MembreDAO {
    // Méthode pour insérer un nouveau membre dans la base de données
    public static void insertMembre(Membre membre) throws SQLException {
        String query = "INSERT INTO Membres (ClientID, TypeDeMembre, Email, MotDePasse) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, membre.getClientID());
            statement.setString(2, membre.getTypeDeMembre());
            statement.setString(3, membre.getEmail());
            statement.setString(4, membre.getMotDePasse());
            statement.executeUpdate();
            System.out.println("Nouveau membre inséré avec succès !");
        }
    }

    // Méthode pour récupérer tous les membres de la base de données
    public static List<Membre> getAllMembres() throws SQLException {
        List<Membre> membres = new ArrayList<>();
        String query = "SELECT * FROM Membres";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Membre membre = new Membre();
                membre.setMembreID(resultSet.getInt("MembreID"));
                membre.setClientID(resultSet.getInt("ClientID"));
                membre.setTypeDeMembre(resultSet.getString("TypeDeMembre"));
                membre.setEmail(resultSet.getString("Email"));
                membre.setMotDePasse(resultSet.getString("MotDePasse"));
                membres.add(membre);
            }
        }
        return membres;
    }

    // Méthode pour supprimer un membre de la base de données
    public static void deleteMembre(int membreID) throws SQLException {
        String query = "DELETE FROM Membres WHERE MembreID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, membreID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Membre supprimé avec succès !");
            } else {
                System.out.println("Aucun membre trouvé avec cet ID.");
            }
        }
    }

    // Méthode pour mettre à jour les détails d'un membre dans la base de données
    public static void updateMembre(Membre membre) throws SQLException {
        String query = "UPDATE Membres SET ClientID = ?, TypeDeMembre = ?, Email = ?, MotDePasse = ? WHERE MembreID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, membre.getClientID());
            statement.setString(2, membre.getTypeDeMembre());
            statement.setString(3, membre.getEmail());
            statement.setString(4, membre.getMotDePasse());
            statement.setInt(5, membre.getMembreID());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Détails du membre mis à jour avec succès !");
            } else {
                System.out.println("Aucun membre trouvé avec cet ID.");
            }
        }
    }
}
