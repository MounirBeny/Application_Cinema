package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modele.Reservation;
import Main.DatabaseConnection;


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
}
