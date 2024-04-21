package Vue;

import Modele.Client;
import Modele.Membre;

public class GestionProfilVue {
    public void afficherProfil(Client client, Membre membre) {
        System.out.println("Profil du client :\n");
        System.out.println("ID du client : " + client.getClientID());
        System.out.println("Type de client : " + client.getTypeDeClient());
        if (membre != null) {
            System.out.println("ID du membre : " + membre.getMembreID());
            System.out.println("Type de membre : " + membre.getTypeDeMembre());
            System.out.println("Email : " + membre.getEmail());
            // Ajoutez d'autres informations du membre ici si nécessaire
        } else {
            System.out.println("Client non enregistré en tant que membre.");
        }
    }

    public void afficherModificationReussie() {
        System.out.println("Les modifications ont été enregistrées avec succès !");
    }

    public void afficherErreur(String message) {
        System.out.println("Erreur : " + message);
    }
}
