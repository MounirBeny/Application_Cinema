package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modele.Reduction;
import Main.DatabaseConnection;

public class ReductionDAO {
    // Méthode pour insérer une nouvelle réduction dans la base de données
    public static void insertReduction(Reduction reduction) throws SQLException {
        String query = "INSERT INTO Reductions (TypeDeMembre, ValeurReduction) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reduction.getTypeDeMembre());
            statement.setDouble(2, reduction.getValeurReduction());
            statement.executeUpdate();
            System.out.println("Nouvelle réduction insérée avec succès !");
        }
    }

    // Méthode pour récupérer toutes les réductions de la base de données
    public static List<Reduction> getAllReductions() throws SQLException {
        List<Reduction> reductions = new ArrayList<>();
        String query = "SELECT * FROM Reductions";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Reduction reduction = new Reduction();
                reduction.setReductionID(resultSet.getInt("ReductionID"));
                reduction.setTypeDeMembre(resultSet.getString("TypeDeMembre"));
                reduction.setValeurReduction(resultSet.getDouble("ValeurReduction"));
                reductions.add(reduction);
            }
        }
        return reductions;
    }

    // Méthode pour supprimer une réduction de la base de données
    public static void deleteReduction(int reductionID) throws SQLException {
        String query = "DELETE FROM Reductions WHERE ReductionID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reductionID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Réduction supprimée avec succès !");
            } else {
                System.out.println("Aucune réduction trouvée avec cet ID.");
            }
        }
    }

    // Méthode pour mettre à jour les détails d'une réduction dans la base de données
    public static void updateReduction(Reduction reduction) throws SQLException {
        String query = "UPDATE Reductions SET TypeDeMembre = ?, ValeurReduction = ? WHERE ReductionID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reduction.getTypeDeMembre());
            statement.setDouble(2, reduction.getValeurReduction());
            statement.setInt(3, reduction.getReductionID());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Détails de la réduction mis à jour avec succès !");
            } else {
                System.out.println("Aucune réduction trouvée avec cet ID.");
            }
        }
    }

    // Méthode pour récupérer la valeur de réduction en fonction du type de membre
    public static double getValeurReduction(String typeDeMembre) throws SQLException {
        String query = "SELECT ValeurReduction FROM Reductions WHERE TypeDeMembre = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, typeDeMembre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("ValeurReduction");
                }
            }
        }
        return 0.0;
    }
}
