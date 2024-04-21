package Main;

import java.sql.SQLException;
import java.util.List;
import DAO.FilmDAO;
import Modele.Film;
import Vue.SelectionFilmsVue;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        FilmDAO filmDAO = new FilmDAO();
        SelectionFilmsVue selectionFilmsVue = new SelectionFilmsVue();

        try {
            // Récupérer la liste des films depuis la base de données
            List<Film> films = filmDAO.getAllFilms();

            // Afficher la liste des films à l'utilisateur
            selectionFilmsVue.afficherListeFilms(films);
        } catch (SQLException e) {
            selectionFilmsVue.afficherErreur("Erreur lors de la récupération des films : " + e.getMessage());
        }

        launch(args);
    }
}