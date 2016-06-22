package Piece;

import application.Deplacement;
import application.Piece;

public class Tour extends Piece {

  public Tour(String Couleur) {
    super("Tour", Couleur);
  }

  public boolean estValide(Deplacement deplacement) {
    // Teste si la coordonn�e d'arriv� - la coordoon�e de d�part change si non 
    // le r�sultat est 0 donc permet le mouvement
    return deplacement.getDeplacementX() * deplacement.getDeplacementY() == 0
        && !deplacement.isNul();
  }

}
