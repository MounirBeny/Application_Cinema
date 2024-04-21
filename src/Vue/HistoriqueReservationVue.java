package Vue;

import Modele.Reservation;

import java.util.List;

public class HistoriqueReservationVue {
    public void afficherHistorique(List<Reservation> reservations) {
        System.out.println("Historique des réservations :\n");
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation trouvée.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println("ID de la réservation : " + reservation.getReservationID());
                System.out.println("ID du client : " + reservation.getClientID());
                System.out.println("ID du film : " + reservation.getFilmID());
                System.out.println(); // Ligne vide pour séparer les réservations
            }
        }
    }

    public void afficherErreur(String message) {
        System.out.println("Erreur : " + message);
    }
}
