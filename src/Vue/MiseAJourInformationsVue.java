package Vue;

import java.util.Scanner;
import Modele.Membre;

public class MiseAJourInformationsVue {
    private Scanner scanner;

    public MiseAJourInformationsVue() {
        scanner = new Scanner(System.in);
    }

    // Méthode pour afficher le formulaire de mise à jour des informations personnelles
    public Membre afficherFormulaireMiseAJourInformations(Membre membre) {
        System.out.println("=== Mise à jour des informations personnelles ===");
        System.out.println("Veuillez entrer les nouvelles informations (Laissez vide pour ne pas modifier) :");

        System.out.print("Nouveau email : ");
        String nouvelEmail = scanner.nextLine().trim();
        if (!nouvelEmail.isEmpty()) {
            membre.setEmail(nouvelEmail);
        }

        System.out.print("Nouveau mot de passe : ");
        String nouveauMotDePasse = scanner.nextLine().trim();
        if (!nouveauMotDePasse.isEmpty()) {
            membre.setMotDePasse(nouveauMotDePasse);
        }

        System.out.println("Informations mises à jour avec succès !");
        return membre;
    }
}
