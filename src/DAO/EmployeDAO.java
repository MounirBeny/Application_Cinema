package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modele.Employe;
import Main.DatabaseConnection;

public class EmployeDAO {
    // Méthode pour récupérer tous les employés depuis la base de données
    public List<Employe> getAllEmployes() {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM Employes";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int employeID = resultSet.getInt("employeID");
                String emailEmploye = resultSet.getString("emailEmploye");
                String motDePasse = resultSet.getString("motDePasse");

                // Créer un objet Employe avec les données récupérées
                Employe employe = new Employe(employeID, emailEmploye, motDePasse);
                employes.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employes;
    }

    // Méthode pour insérer un nouvel employé dans la base de données
    public void insertEmploye(Employe employe) {
        String query = "INSERT INTO Employes (emailEmploye, motDePasse) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, employe.getEmailEmploye());
            preparedStatement.setString(2, employe.getMotDePasse());

            preparedStatement.executeUpdate();
            System.out.println("Employé inséré avec succès dans la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les informations d'un employé dans la base de données
    public void updateEmploye(Employe employe) {
        String query = "UPDATE Employes SET emailEmploye = ?, motDePasse = ? WHERE employeID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, employe.getEmailEmploye());
            preparedStatement.setString(2, employe.getMotDePasse());
            preparedStatement.setInt(3, employe.getEmployeID());

            preparedStatement.executeUpdate();
            System.out.println("Employé mis à jour avec succès dans la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un employé de la base de données
    public void deleteEmploye(int employeID) {
        String query = "DELETE FROM Employes WHERE employeID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, employeID);

            preparedStatement.executeUpdate();
            System.out.println("Employé supprimé avec succès de la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
