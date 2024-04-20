package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.Client;
import DAO.ClientDAO;

public class ClientControleur {
    // Méthode pour ajouter un nouveau client
    public void ajouterClient(Client client) {
        ClientDAO.insertClient(client);
    }

    // Méthode pour récupérer tous les clients
    public List<Client> getAllClients() {
        return ClientDAO.getAllClients();
    }

    // Méthode pour mettre à jour les détails d'un client
    public void modifierClient(Client client) {
        ClientDAO.updateClient(client);
    }

    // Méthode pour supprimer un client
    public void supprimerClient(int clientID) {
        ClientDAO.deleteClient(clientID);
    }

    // Méthode pour récupérer le type de client à partir de son ID
    public String getTypeDeClient(int clientID) throws SQLException {
        return ClientDAO.getTypeDeClient(clientID);
    }
}
