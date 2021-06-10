package pacman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pacman.model.Utilisateur;

import java.io.IOException;
import java.util.Objects;

public class ControllerAmeliorationCompetence extends Controller {
    public Utilisateur utilisateur;
    public Text description;
    public ImageView pointTirer;
    public ImageView pointFreeze;
    public ImageView pointTeleporteur;

    public ControllerAmeliorationCompetence() {
        utilisateur = new Utilisateur();
    }

    @FXML
    public void initialize() {
        description.setFont(this.rosemary);
        description.setText("Vous avez " + utilisateur.pointJoueur + " points\n\nVeuillez améliorer vos compétences, ou cliquer sur valider");
        description.setFont(rosemary);
        if (utilisateur.niveauCompetenceTirer>-1) pointTirer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacman/ressources/image/Ecran_Competence/groupePoint" + utilisateur.niveauCompetenceTirer + "Vide.png"))));
        else pointTirer.setImage(null);
        if (utilisateur.niveauCompetenceFreeze>-1)pointFreeze.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacman/ressources/image/Ecran_Competence/groupePoint" + utilisateur.niveauCompetenceFreeze + "Vide.png"))));
        else pointFreeze.setImage(null);
        if (utilisateur.niveauCompetenceTeleporteur>-1)pointTeleporteur.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacman/ressources/image/Ecran_Competence/groupePoint" + utilisateur.niveauCompetenceTeleporteur + "Vide.png"))));
        else pointTeleporteur.setImage(null);
    }

    /**
     * Lorsque l'on clique sur le bouton de compétence dans le shop, améliore la compétence au prochain niveau si il a les points nécessaires.
     * @param actionEvent Récupère les informations sur le bouton qui est cliqué.
     */
    public void ameliorationPouvoir(ActionEvent actionEvent) {
        String image;
        switch (((Node) actionEvent.getSource()).getId()) {
            case "tirer":
                if ((utilisateur.pointJoueur - Integer.parseInt(utilisateur.tabCompetence[utilisateur.niveauCompetenceTirer+1][1]))>=0){
                    if (utilisateur.niveauCompetenceTirer<3) utilisateur.niveauCompetenceTirer ++;
                    setDescriptionTirerGlobal();
                    image = "/pacman/ressources/image/Ecran_Competence/groupePoint" + (utilisateur.niveauCompetenceTirer) + "Vide.png";
                    if (utilisateur.niveauCompetenceTirer>-1) pointTirer.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(image))));
                    utilisateur.pointJoueur-= Integer.parseInt(utilisateur.tabCompetence[utilisateur.niveauCompetenceTirer+1][1]);

                }
                break;
            case "freeze":
                if ((utilisateur.pointJoueur - Integer.parseInt(utilisateur.tabCompetence[utilisateur.niveauCompetenceFreeze+1][5]))>=0){
                    if (utilisateur.niveauCompetenceFreeze<3) utilisateur.niveauCompetenceFreeze ++;
                    setDescriptionFreezeGlobal();
                    image = "/pacman/ressources/image/Ecran_Competence/groupePoint" + (utilisateur.niveauCompetenceFreeze) + "Vide.png";
                    if (utilisateur.niveauCompetenceFreeze>-1) pointFreeze.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(image))));
                    utilisateur.pointJoueur-= Integer.parseInt(utilisateur.tabCompetence[utilisateur.niveauCompetenceFreeze+1][5]);

                }
                break;
            case "teleporteur":
                if ((utilisateur.pointJoueur - Integer.parseInt(utilisateur.tabCompetence[utilisateur.niveauCompetenceTeleporteur+1][9]))>=0){
                    if (utilisateur.niveauCompetenceTeleporteur<3) utilisateur.niveauCompetenceTeleporteur ++;
                    setDescriptionTeleportGlobal();
                    image = "/pacman/ressources/image/Ecran_Competence/groupePoint" + (utilisateur.niveauCompetenceTeleporteur) + "Vide.png";
                    if (utilisateur.niveauCompetenceTeleporteur>-1) pointTeleporteur.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(image))));
                    utilisateur.pointJoueur-= Integer.parseInt(utilisateur.tabCompetence[utilisateur.niveauCompetenceTeleporteur+1][9]);
                }
                break;
        }
    }

    /**
     * Remet la description de base dans le shop.
     */
    public void unsetDescription() {
        description.setText("Vous avez " + utilisateur.pointJoueur + " points\n\nVeuillez améliorer vos compétences, ou cliquer sur valider");
    }

    /**
     * Met la description du sort Tirer dans le shop.
     */
    public void setDescriptionTirerGlobal(){
        String text = utilisateur.tabCompetence[utilisateur.niveauCompetenceTirer+1][0];
        if (utilisateur.niveauCompetenceTirer < 3) text+= "\n prix : "+utilisateur.tabCompetence[utilisateur.niveauCompetenceTirer+1][1];
        description.setText(text);
    }

    /**
     * Met la description du sort Geler dans le shop.
     */
    public void setDescriptionFreezeGlobal(){
        String text = utilisateur.tabCompetence[utilisateur.niveauCompetenceFreeze+1][4];
        if (utilisateur.niveauCompetenceFreeze < 3) text+= "\n prix : "+utilisateur.tabCompetence[utilisateur.niveauCompetenceFreeze+1][5];
        description.setText(text);
    }

    /**
     * Met la description du sort Teleporteur dans le shop.
     */
    public void setDescriptionTeleportGlobal(){
        String text = utilisateur.tabCompetence[utilisateur.niveauCompetenceTeleporteur+1][8];
        if (utilisateur.niveauCompetenceTeleporteur < 3) text+= "\n prix : "+utilisateur.tabCompetence[utilisateur.niveauCompetenceTeleporteur+1][9];
        description.setText(text);
    }


    public void switchToScene(ActionEvent actionEvent) throws IOException {
        utilisateur.ecritureUtilisateur();
        super.switchToScene(actionEvent);
    }
}
