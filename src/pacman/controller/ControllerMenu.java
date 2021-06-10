package pacman.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pacman.model.Map;
import pacman.model.Utilisateur;
import pacman.model.deplacement.Pacman;

import java.util.Objects;

public class ControllerMenu extends Controller{
    @FXML public Pacman pacman;
    @FXML public Map map;
    @FXML ImageView fleche1;
    @FXML ImageView fleche2;
    @FXML ImageView fleche3;
    @FXML ImageView fleche4;
    @FXML ImageView fleche5;


    public ControllerMenu() {
        super();
        if (!modelMusic.isPlaying("menu")) {
            this.stopAllMusic();
            this.playMusic("menu", true);
        }
        initUtilisateur();
    }

    /**
     * initialise l'utilisateur
     */
    private void initUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.reinitialiseCompetencesUtilisateur();
        utilisateur.ecritureUtilisateur();
    }

    /**
     * Affiche la flèche du premier élèment du menu
     */
    public void affiche_fleche1() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacman/ressources/image/Menu/pacman_fleche.png")));
        fleche1.setImage(image);
    }

    /**
     * Enlève toutes les flèches
     */
    public void enlever_fleches() {
        fleche1.setImage(null);
        fleche2.setImage(null);
        fleche3.setImage(null);
        fleche4.setImage(null);
        fleche5.setImage(null);
    }

    /**
     * Affiche la flèche du deuxième élèment du menu
     */
    public void affiche_fleche2() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacman/ressources/image/Menu/pacman_fleche.png")));
        fleche2.setImage(image);
    }

    /**
     * Affiche la flèche du troisième élèment du menu
     */
    public void affiche_fleche3() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacman/ressources/image/Menu/pacman_fleche.png")));
        fleche3.setImage(image);
    }

    /**
     * Affiche la flèche du quatrième élèment du menu
     */
    public void affiche_fleche4() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacman/ressources/image/Menu/pacman_fleche.png")));
        fleche4.setImage(image);
    }

    /**
     * Affiche la flèche du cinquième élèment du menu
     */
    public void affiche_fleche5() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacman/ressources/image/Menu/pacman_fleche.png")));
        fleche5.setImage(image);
    }

    /**
     * Quitte l'application.
     */
    public void quitter() {
        Platform.exit();
        System.exit(0);
    }
}
