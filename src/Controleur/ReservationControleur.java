package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.Reservation;
import DAO.ReservationDAO;

public class ReservationControleur {
    // Méthode pour insérer une nouvelle réservation dans la base de données
    public void insertReservation(Reservation reservation) {
        try {
            ReservationDAO.insertReservation(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour calculer le prix total avec réduction pour les membres
    public double calculerPrixTotalAvecReduction(Reservation reservation) {
        try {
            return ReservationDAO.calculerPrixTotalAvecReduction(reservation);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    // Méthode pour récupérer l'historique des réservations d'un client
    public List<Reservation> getHistoriqueReservations(int clientID) {
        try {
            return ReservationDAO.getHistoriqueReservations(clientID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
