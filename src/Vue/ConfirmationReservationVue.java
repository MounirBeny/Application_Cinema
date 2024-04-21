package Vue;

public class ConfirmationReservationVue {
    public void afficherConfirmation() {
        System.out.println("La réservation a été effectuée avec succès !");
    }

    public void afficherAnnulation() {
        System.out.println("La réservation a été annulée.");
    }

    public void afficherErreur(String message) {
        System.out.println("Erreur : " + message);
    }
}
