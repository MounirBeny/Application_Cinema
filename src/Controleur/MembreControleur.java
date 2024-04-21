package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.Membre;
import DAO.MembreDAO;
import DAO.ClientDAO;

public class MembreControleur {
    // Méthode pour récupérer tous les membres
    public List<Membre> getAllMembres() throws SQLException {
        MembreDAO membreDAO = new MembreDAO();
        return membreDAO.getAllMembres();
    }

    // Méthode pour ajouter un nouveau membre
    public void ajouterMembre(Membre membre) throws SQLException {
        MembreDAO membreDAO = new MembreDAO();
        membreDAO.insertMembre(membre);
    }

    // Méthode pour mettre à jour les informations d'un membre
    public void modifierMembre(Membre membre) throws SQLException {
        MembreDAO membreDAO = new MembreDAO();
        membreDAO.updateMembre(membre);
    }

    // Méthode pour supprimer un membre
    public void supprimerMembre(int membreID) throws SQLException {
        MembreDAO membreDAO = new MembreDAO();
        membreDAO.deleteMembre(membreID);
    }

    // Méthode pour mettre à jour les informations du membre
    public void mettreAJourInformationsMembre(Membre membre) {
        try {
            // Vérifier si le client est un membre
            if (isClientMembre(membre.getClientID())) {
                MembreDAO.updateMembre(membre);
            } else {
                System.out.println("Impossible de mettre à jour les informations. Seuls les membres peuvent être mis à jour.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode privée pour vérifier si un client est un membre
    private boolean isClientMembre(int clientID) throws SQLException {
        String typeDeClient = ClientDAO.getTypeDeClient(clientID);
        return typeDeClient != null && typeDeClient.equals("Membre");
    }
}
