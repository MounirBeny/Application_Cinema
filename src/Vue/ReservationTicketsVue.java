package Vue;

import java.util.Scanner;

public class ReservationTicketsVue {
    private Scanner scanner;

    public ReservationTicketsVue() {
        scanner = new Scanner(System.in);
    }

    public int demanderNombreTickets() {
        System.out.println("Veuillez saisir le nombre de tickets à réserver : ");
        int nombreTickets = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la fin de la ligne
        return nombreTickets;
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
