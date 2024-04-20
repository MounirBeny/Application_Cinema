package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modele.Film;
import Main.DatabaseConnection;

public class FilmDAO {
    // Méthode pour insérer un nouveau film dans la base de données
    public static void insertFilm(Film film) throws SQLException {
        String query = "INSERT INTO Films (Titre, Realisateur, Synopsis, PrixTicket, Salle, NotePresse, "
                + "MoyenneNotesMembres, MoyenneNotesEmployes, Poster) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, film.getTitre());
            statement.setString(2, film.getRealisateur());
            statement.setString(3, film.getSynopsis());
            statement.setDouble(4, film.getPrixTicket());
            statement.setString(5, film.getSalle());
            statement.setDouble(6, film.getNotePresse());
            statement.setDouble(7, film.calculerMoyenneNotesMembres());
            statement.setDouble(8, film.calculerMoyenneNotesEmployes());
            statement.setString(9, film.getPoster());
            statement.executeUpdate();
            System.out.println("Nouveau film inséré avec succès !");
        }
    }

    // Méthode pour récupérer tous les films de la base de données
    public static List<Film> getAllFilms() throws SQLException {
        List<Film> films = new ArrayList<>();
        String query = "SELECT * FROM Films";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Film film = new Film();
                film.setFilmID(resultSet.getInt("FilmID"));
                film.setTitre(resultSet.getString("Titre"));
                film.setRealisateur(resultSet.getString("Realisateur"));
                film.setSynopsis(resultSet.getString("Synopsis"));
                film.setPrixTicket(resultSet.getDouble("PrixTicket"));
                film.setSalle(resultSet.getString("Salle"));
                film.setNotePresse(resultSet.getDouble("NotePresse"));
                film.setMoyenneNotesMembres(resultSet.getDouble("MoyenneNotesMembres"));
                film.setMoyenneNotesEmployes(resultSet.getDouble("MoyenneNotesEmployes"));
                film.setPoster(resultSet.getString("Poster"));
                films.add(film);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des films : " + e.getMessage());
        }
        return films;
    }

    // Méthode pour supprimer un film de la base de données
    public static void deleteFilm(int filmId) throws SQLException {
        String query = "DELETE FROM Films WHERE FilmID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, filmId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Film supprimé avec succès !");
            } else {
                System.out.println("Aucun film trouvé avec cet ID.");
            }
        }
    }

    // Méthode pour mettre à jour les détails d'un film dans la base de données
    public static void updateFilm(Film film) throws SQLException {
        String query = "UPDATE Films SET Titre = ?, Realisateur = ?, Synopsis = ?, PrixTicket = ?, "
                + "Salle = ?, NotePresse = ?, MoyenneNotesMembres = ?, MoyenneNotesEmployes = ?, Poster = ? "
                + "WHERE FilmID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, film.getTitre());
            statement.setString(2, film.getRealisateur());
            statement.setString(3, film.getSynopsis());
            statement.setDouble(4, film.getPrixTicket());
            statement.setString(5, film.getSalle());
            statement.setDouble(6, film.getNotePresse());
            statement.setDouble(7, film.calculerMoyenneNotesMembres()); // Appel de la méthode dans la classe Film
            statement.setDouble(8, film.calculerMoyenneNotesEmployes());
            statement.setString(9, film.getPoster());
            statement.setInt(10, film.getFilmID());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Détails du film mis à jour avec succès !");
            } else {
                System.out.println("Aucun film trouvé avec cet ID.");
            }
        }
    }

    // Méthode pour récupérer les détails d'un film spécifique en fonction de son ID
    public static Film getFilmById(int filmId) throws SQLException {
        String query = "SELECT * FROM Films WHERE FilmID = ?";
        Film film = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, filmId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    film = new Film();
                    film.setFilmID(resultSet.getInt("FilmID"));
                    film.setTitre(resultSet.getString("Titre"));
                    film.setRealisateur(resultSet.getString("Realisateur"));
                    film.setSynopsis(resultSet.getString("Synopsis"));
                    film.setPrixTicket(resultSet.getDouble("PrixTicket"));
                    film.setSalle(resultSet.getString("Salle"));
                    film.setNotePresse(resultSet.getDouble("NotePresse"));
                    film.setMoyenneNotesMembres(resultSet.getDouble("MoyenneNotesMembres"));
                    film.setMoyenneNotesEmployes(resultSet.getDouble("MoyenneNotesEmployes"));
                    film.setPoster(resultSet.getString("Poster"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du film : " + e.getMessage());
        }
        return film;
    }


}
