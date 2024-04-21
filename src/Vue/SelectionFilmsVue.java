package Vue;

import java.util.List;
import Modele.Film;

public class SelectionFilmsVue {
    public void afficherListeFilms(List<Film> films) {
        System.out.println("Liste des films disponibles :\n");
        for (Film film : films) {
            System.out.println(film.getTitre());
        }
    }

    public void afficherErreur(String message) {
        System.out.println("Erreur : " + message);
    }
}
