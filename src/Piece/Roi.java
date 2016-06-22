package Piece;

import application.Deplacement;
import application.Piece;

public class Roi extends Piece {

  public Roi(String Couleur) {
    super("Roi", Couleur);
  }

  public boolean estValide(Deplacement deplacement) {

    return Math.abs(deplacement.getDeplacementX())
        * Math.abs(deplacement.getDeplacementY()) <= 1
        && Math.abs(deplacement.getDeplacementX())
            - Math.abs(deplacement.getDeplacementY()) <= 1
        && Math.abs(deplacement.getDeplacementX())
            - Math.abs(deplacement.getDeplacementY()) >= -1
        && !deplacement.isNul();
  }
}
