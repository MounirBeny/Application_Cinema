package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.Reduction;
import DAO.ReductionDAO;

public class ReductionControleur {
    // Méthode pour insérer une nouvelle réduction dans la base de données
    public void insertReduction(Reduction reduction) {
        try {
            ReductionDAO.insertReduction(reduction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer toutes les réductions de la base de données
    public List<Reduction> getAllReductions() {
        try {
            return ReductionDAO.getAllReductions();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour supprimer une réduction de la base de données
    public void deleteReduction(int reductionID) {
        try {
            ReductionDAO.deleteReduction(reductionID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les détails d'une réduction dans la base de données
    public void updateReduction(Reduction reduction) {
        try {
            ReductionDAO.updateReduction(reduction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer la valeur de réduction en fonction du type de membre
    public double getValeurReduction(String typeDeMembre) {
        try {
            return ReductionDAO.getValeurReduction(typeDeMembre);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
