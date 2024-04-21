package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modele.Reservation;
import Main.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import Modele.Reservation;


public class ReservationDAO {
    // Méthode pour insérer une nouvelle réservation dans la base de données
    public static void insertReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO Reservations (FilmID, ClientID, NombreBillets, PrixTotal) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getFilmID());
            statement.setInt(2, reservation.getClientID());
            statement.setInt(3, reservation.getNombreBillets());
            statement.setDouble(4, reservation.getPrixTotal());
            statement.executeUpdate();
            System.out.println("Nouvelle réservation insérée avec succès !");
        }
    }

    // Méthode pour calculer le prix total avec réduction pour les membres
    public static double calculerPrixTotalAvecReduction(Reservation reservation) throws SQLException {
        // Récupérer le type de membre du client
        String typeDeMembre = ClientDAO.getTypeDeClient(reservation.getClientID());

        // Récupérer la valeur de réduction correspondante au type de membre
        double valeurReduction = ReductionDAO.getValeurReduction(typeDeMembre);

        // Calculer le prix total avec réduction
        double prixTotal = reservation.getNombreBillets() * reservation.getPrixTicket() * (1 - valeurReduction);

        return prixTotal;
    }

    // Méthode pour récupérer l'historique des réservations d'un client
    public static List<Reservation> getHistoriqueReservations(int clientID) throws SQLException {
        List<Reservation> historiqueReservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations WHERE ClientID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setReservationID(resultSet.getInt("ReservationID"));
                    reservation.setFilmID(resultSet.getInt("FilmID"));
                    reservation.setClientID(resultSet.getInt("ClientID"));
                    reservation.setNombreBillets(resultSet.getInt("NombreBillets"));
                    reservation.setPrixTotal(resultSet.getDouble("PrixTotal"));
                    historiqueReservations.add(reservation);
                }
            }
        }
        return historiqueReservations;
    }
}
