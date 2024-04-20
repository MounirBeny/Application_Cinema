package Controleur;

import java.sql.SQLException;
import java.util.List;
import Modele.Film;
import DAO.FilmDAO;

public class FilmControleur {
    // Méthode pour récupérer tous les films
    public List<Film> getAllFilms() {
        try {
            return FilmDAO.getAllFilms();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode pour ajouter un nouveau film
    public void ajouterFilm(Film film) {
        try {
            FilmDAO.insertFilm(film);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un film
    public void supprimerFilm(int filmId) {
        try {
            FilmDAO.deleteFilm(filmId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les détails d'un film
    public void modifierFilm(Film film) {
        try {
            FilmDAO.updateFilm(film);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer les détails d'un film spécifique en fonction de son ID
    public Film getFilmById(int filmId) {
        try {
            return FilmDAO.getFilmById(filmId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
