package Vue;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PageAccueil extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer les boutons pour les clients invités et membres
        Button boutonClientInvite = new Button("Client Invité");
        Button boutonClientMembre = new Button("Client Membre");

        // Définir les actions des boutons
        boutonClientInvite.setOnAction(event -> {
            // Action à effectuer lorsque le bouton Client Invité est cliqué
            // Par exemple, rediriger vers la page de sélection de film pour les clients invités
            // Vous pouvez remplacer ce bloc de code par votre propre logique de redirection
            System.out.println("Redirection vers la page de sélection de film pour les clients invités.");
        });

        boutonClientMembre.setOnAction(event -> {
            // Action à effectuer lorsque le bouton Client Membre est cliqué
            // Par exemple, rediriger vers la page de connexion pour les clients membres
            // Vous pouvez remplacer ce bloc de code par votre propre logique de redirection
            System.out.println("Redirection vers la page de connexion pour les clients membres.");
        });

        // Créer une disposition verticale pour les boutons
        VBox root = new VBox(20); // Espacement vertical entre les éléments
        root.setAlignment(Pos.CENTER); // Centrer les éléments dans la fenêtre
        root.setPadding(new Insets(50)); // Ajouter un padding autour de la disposition

        // Ajouter les boutons à la disposition
        root.getChildren().addAll(boutonClientInvite, boutonClientMembre);

        // Créer la scène principale
        Scene scene = new Scene(root, 400, 300);

        // Configurer la scène et afficher la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setTitle("Page d'Accueil");
        primaryStage.show();
    }

    /*public static void main(String[] args) {
        launch(args);
    }*/
}
