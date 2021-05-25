package pacman.model.deplacement;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import pacman.model.Map;



public class Fantome extends Deplacement{
    public deplacements deplacementActuel = deplacements.AUCUN;
    public int positionXFinDeplacement;
    public int positionYFinDeplacement;
    public ArrayList<String> listeCoordoneDeplacementFant = new ArrayList<>();
    public Pacman pacman;
    public deplacements deplacementPasse = deplacements.AUCUN;


    public Fantome(int init_pos_x, int init_pos_y) {
        super(init_pos_x, init_pos_y);
    }

    public void getNextFinalPos(){
        String[] coorXY = this.listeCoordoneDeplacementFant.get(0).split("/");
        this.listeCoordoneDeplacementFant.remove(0);

        int x = Integer.parseInt(coorXY[0]);
        int y = Integer.parseInt(coorXY[1]);

        this.positionXFinDeplacement = tradCoorToPx(x);
        this.positionYFinDeplacement = tradCoorToPx(y);
        setOrientation();
    }

    public void setOrientation(){
        if (positionXFinDeplacement-this.getPosX() < 0) this.deplacementActuel = deplacements.DROITE;
        else if (positionXFinDeplacement-this.getPosX() > 0) this.deplacementActuel = deplacements.GAUCHE;
        else if (positionYFinDeplacement-this.getPosY() < 0) this.deplacementActuel = deplacements.BAS;
        else this.deplacementActuel = deplacements.HAUT;
    }

    public int tradCoorToPx(int coordone){
        return (int) (coordone* Map.TAILLE_CASE + 1);
    }

    public void updateDeplacement() {

        if (this.listeCoordoneDeplacementFant.size() > 0) this.ia();

        if (doitRechargerNextPos()) getNextFinalPos();

        else if (positionXFinDeplacement != this.getPosX() && positionYFinDeplacement != this.getPosY()) {
            switch (this.deplacementActuel) {
                case HAUT:
                    avanceHaut();
                    break;
                case DROITE:
                    avanceDroite();
                    break;
                case BAS:
                    avanceBas();
                    break;
                case GAUCHE:
                    avanceGauche();
                    break;
            }
        }
    }

    public boolean doitRechargerNextPos(){
        return  (this.positionXFinDeplacement == this.getPosX() && this.positionYFinDeplacement == this.getPosY());
    }

    public void ia(){}


    public boolean peutAvancerHorizontalement(Map map, int i) {
        if (getPosY() % 20 == 1) {
            if ((getPosX() % 20 != 1) || (map.grid[(((int)getPosX()/20)+i+25)%25][(int)getPosY()/20] != Map.ValeurCase.MUR)) {
                return true;
            }
        }
        return false;
    }
    public boolean peutAvancerVerticalement(Map map, int i) {
        if (getPosX() % 20 == 1 && getPosX() > 1 && getPosX() < 500) {
            if ((getPosY() % 20 != 1) || (map.grid[(int)getPosX()/20][((int)getPosY()/20)+i] != Map.ValeurCase.MUR)) {
                return true;
            }
        }
        return false;
    }

    public void ancienDeplacementFantome() {
        switch (deplacementPasse) {
            case BAS:
                if (peutAvancerVerticalement(map,1))
                    avanceBas();
                break;
            case DROITE:
                if (peutAvancerHorizontalement(map,1))
                    avanceDroite();
                break;
            case HAUT:
                if (peutAvancerVerticalement(map,-1))
                    avanceHaut();
                break;
            case GAUCHE:
                if (peutAvancerHorizontalement(map,-1))
                    avanceGauche();
                break;
        }
        return;
    }

    public boolean estSurPacman() {
        double ratioX = (this.getPosX()*1.0) / (pacman.getPosX()*1.0);
        double ratioY = (this.getPosY()*1.0) / (pacman.getPosY()*1.0);
        if (((ratioX>0.95 && ratioX<1.05) || (ratioX<-0.95 && ratioX>-1.05)) && ((ratioY>0.95 && ratioY<1.05) || (ratioY<-0.95 && ratioY>-1.05))) {
            return true;
        }
        return false;
    }

    @Override
    public void initPosition() {
        super.initPosition();
        deplacementPasse = deplacements.AUCUN;
        deplacementActuel = deplacements.AUCUN;
    }


    public void iaFantomeAppeure() {
        Random rand = new Random();
        int random = 0;
        if (Math.round(this.getPosX() * 0.0499) > 10 && Math.round(this.getPosX() * 0.0499) < 14 && Math.round(this.getPosY() * 0.0499) > 12 && Math.round(this.getPosY() * 0.0499) < 15) {
            if (this.peutAvancerVerticalement(map, -1)) {
                this.avanceHaut();
                deplacementActuel = deplacements.HAUT;
            }
            else if (this.peutAvancerHorizontalement(map, 1)) {
                this.avanceDroite();
                deplacementActuel = deplacements.DROITE;
            }
            else if (this.peutAvancerHorizontalement(map, -1)) {
                this.avanceGauche();
                deplacementActuel = deplacements.GAUCHE;
            }
        }

        else {
            random = rand.nextInt(4);

            switch (random) {
                case 0:
                    if (this.peutAvancerVerticalement(map, -1) && deplacementActuel != deplacements.BAS) {
                        this.avanceHaut();
                        deplacementActuel = deplacements.HAUT;
                    } else {
                        ancienDeplacementFantome();
                    }
                    break;

                case 1:
                    if (this.peutAvancerHorizontalement(map, -1) && deplacementActuel != deplacements.DROITE) {
                        this.avanceGauche();
                        deplacementActuel = deplacements.GAUCHE;
                    } else {
                        ancienDeplacementFantome();
                        break;
                    }
                    break;

                case 2:
                    if (this.peutAvancerVerticalement(map, 1) && deplacementActuel != deplacements.HAUT) {
                        this.avanceBas();
                        deplacementActuel = deplacements.BAS;

                    } else {
                        ancienDeplacementFantome();
                    }
                    break;

                case 3:
                    if (this.peutAvancerHorizontalement(map, 1) && deplacementActuel != deplacements.GAUCHE) {
                        this.avanceDroite();
                        deplacementActuel = deplacements.DROITE;
                    } else {
                        ancienDeplacementFantome();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
