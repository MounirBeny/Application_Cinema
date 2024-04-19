package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modele.Client;
import Main.DatabaseConnection;

public class ClientDAO {
    // Méthode pour insérer un nouveau client dans la base de données
    public static void insertClient(Client client) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO Clients (typeDeClient) VALUES (?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, client.getTypeDeClient());
            statement.executeUpdate();
            System.out.println("Client inséré avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion du client : " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // Méthode pour récupérer tous les clients de la base de données
    public static List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM Clients";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setClientID(resultSet.getInt("clientID"));
                client.setTypeDeClient(resultSet.getString("typeDeClient"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des clients : " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return clients;
    }

    // Méthode pour mettre à jour les détails d'un client dans la base de données
    public static void updateClient(Client client) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String query = "UPDATE Clients SET typeDeClient = ? WHERE clientID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, client.getTypeDeClient());
            statement.setInt(2, client.getClientID());
            statement.executeUpdate();
            System.out.println("Client mis à jour avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du client : " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // Méthode pour supprimer un client de la base de données
    public static void deleteClient(int clientID) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            String query = "DELETE FROM Clients WHERE clientID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, clientID);
            statement.executeUpdate();
            System.out.println("Client supprimé avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du client : " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // Méthode pour récupérer le type de client à partir de son ID
    public static String getTypeDeClient(int clientID) throws SQLException {
        String query = "SELECT TypeDeClient FROM Clients WHERE ClientID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("TypeDeClient");
                }
            }
        }
        return null;
    }
}
