package Piece;

import application.Deplacement;
import application.Piece;

public class Cavalier extends Piece {
        
        public Cavalier(String Couleur) {
                super("Cavalier", Couleur);
        }

  public boolean estValide(Deplacement deplacement) {
    //Teste si la coordonn�e d'arriv� - la coordonn� d'arriv� en divisant les deux soit �gal � 2 ou 1/2.
    // Si c'est le cas on peut ce d�placer sinon on peut pas.
    return (Math.abs(deplacement.getDeplacementX() / deplacement.getDeplacementY())) == 2
        | (Math.abs(deplacement.getDeplacementX()  / deplacement.getDeplacementY())) == .5;
  }
}
