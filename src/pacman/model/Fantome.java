package pacman.model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fantome extends Group {

    public Map map;
    int numFantome;
    public Image imageFantome;
    public Image imageFantomeVulnerable;
    public ImageView imageView;

    private int initPosX;
    private int initPosY;

    private FantomeGroup fantomeGroup;


    public Fantome(int numFantome, FantomeGroup fantomeGroup) {
        this.numFantome = numFantome;
        this.fantomeGroup = fantomeGroup;
        this.imageFantome = new Image(getClass().getResourceAsStream("/pacman/ressources/image/Ecran_jouer/labyrinthe/ghost" + numFantome + ".gif"));
        this.imageFantomeVulnerable = new Image(getClass().getResourceAsStream("/pacman/ressources/image/Ecran_jouer/labyrinthe/blueghost.gif"));
        initFantome();
    }

    // rajouter par Antoine
//    public Fantome(int numFantome, Image imageFantome) {
//        this.numFantome = numFantome;
//        this.imageFantome = new Image(getClass().getResourceAsStream("/pacman/ressources/image/Ecran_jouer/labyrinthe/ghost" + imageFantome));
//        initFantome();
//    }

    private void initFantome() {
        switch (numFantome) {
            case 1:
                initPosX = 241;
                initPosY = 261;
                break;
            case 2:
                initPosX = 221;
                initPosY = 281;
                break;
            case 3:
                initPosX = 241;
                initPosY = 281;
                break;
            case 4:
                initPosX = 261;
                initPosY = 281;
                break;
        }
        imageView = new ImageView(this.imageFantome);
        imageView.setX(initPosX);
        imageView.setY(initPosY);
        imageView.setFitWidth(Map.TAILLE_CASE-2);
        imageView.setFitHeight(Map.TAILLE_CASE-2);
    }

    public void avanceDroite() {
        this.imageView.setX(this.imageView.getX() + fantomeGroup.velocity);
//        System.out.println("X:  " + this.imageView.getX() + "    Y:  " + this.imageView.getY());
    }
    public void avanceBas() {
        this.imageView.setY(this.imageView.getY() + fantomeGroup.velocity);
//        System.out.println("X:  " + this.imageView.getX() + "    Y:  " + this.imageView.getY());
    }
    public void avanceGauche() {
        this.imageView.setX(this.imageView.getX() - fantomeGroup.velocity);
//        System.out.println("X:  " + this.imageView.getX() + "    Y:  " + this.imageView.getY());
    }
    public void avanceHaut() {
        this.imageView.setY(this.imageView.getY() - fantomeGroup.velocity);
//        System.out.println("X:  " + this.imageView.getX() + "    Y:  " + this.imageView.getY());
    }

    public boolean peutAvancerHorizontalement(Map map, int i) {
        if (this.imageView.getY() % 20 == 1) {
            if ((this.imageView.getX() % 20 != 1) || (map.grid[((int)this.imageView.getX()/20)+i][(int)this.imageView.getY()/20] != Map.ValeurCase.MUR)) {
                return true;
            }
        }
        return false;
    }
    public boolean peutAvancerVerticalement(Map map, int i) {
        if (this.imageView.getX() % 20 == 1) {
            if ((this.imageView.getY() % 20 != 1) || (map.grid[(int)this.imageView.getX()/20][((int)this.imageView.getY()/20)+i] != Map.ValeurCase.MUR)) {
                return true;
            }
        }
        return false;
    }
}
